import os
import re
import sys
import json
import time
import html
import urllib.parse
import requests
from pygments import highlight
from pygments.lexers import JavaLexer, SqlLexer
from pygments.formatters import HtmlFormatter
from playwright.sync_api import sync_playwright

REPO_OWNER = "raunit004"
REPO_NAME = "leetcode-365"
BRANCH = "main"
PAYLOAD_FILE = ".social_payload.json"
MIN_AUTO_DAY = 84  # Day 83 is already scheduled manually


def find_latest_day_folder():
    solutions_dir = "solutions"
    folders = [
        f for f in os.listdir(solutions_dir)
        if os.path.isdir(os.path.join(solutions_dir, f))
    ]

    def extract_day(name):
        m = re.search(r"(\d+)", name)
        return int(m.group(1)) if m else -1

    folders.sort(key=extract_day)
    if not folders:
        return None, -1
    latest = folders[-1]
    return latest, extract_day(latest)


def call_gemini(readme_text, code_text, lang, folder_name, github_url):
    api_key = os.environ.get("GEMINI_API_KEY", "").strip()
    if not api_key:
        raise RuntimeError("GEMINI_API_KEY is missing.")

    url = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key={api_key}"
    prompt = f"""You are creating social media content for a 365-Day LeetCode Challenge (@java.codehub).
Read the README and {lang} solution below, and return ONLY valid JSON with these exact keys:
{{
  "problem_badge": "e.g. LEETCODE #65",
  "difficulty": "Easy | Medium | Hard",
  "pattern_badge": "e.g. STATE MACHINE",
  "title": "Short clean problem title",
  "subtitle": "One punchy 8-12 word subtitle",
  "problem_statement": "Concise 2-3 sentence problem statement for a terminal card",
  "pitfall_title": "Short title of the naive/brute-force pitfall",
  "pitfall_body": "2-3 concise sentences explaining why brute force fails or is messy",
  "optimal_title": "Short title of the optimal intuition",
  "optimal_body": "2-3 concise sentences explaining the clean optimal insight",
  "trace_input": "A clean concrete sample input string/array",
  "trace_steps": ["Step 1: ...", "Step 2: ...", "Step 3: ...", "Step 4: ..."],
  "time_complexity": "e.g. O(N)",
  "time_reason": "Short 6-10 word reason",
  "space_complexity": "e.g. O(1)",
  "space_reason": "Short 6-10 word reason",
  "interview_rule": "1-2 punchy sentences summarizing the key interview takeaway",
  "ig_caption": "Engaging Instagram caption for @java.codehub with brief breakdown, GitHub link ({github_url}), and relevant hashtags",
  "li_caption": "Clean technical LinkedIn post with Day number, problem breakdown, complexity, GitHub link ({github_url}), and 5-6 hashtags"
}}

Folder: {folder_name}
README:
{readme_text}

CODE ({lang}):
{code_text}
"""
    payload = {
        "contents": [{"parts": [{"text": prompt}]}],
        "generationConfig": {"responseMimeType": "application/json"},
    }
    resp = requests.post(url, json=payload, timeout=60)
    resp.raise_for_status()
    raw_text = resp.json()["candidates"][0]["content"]["parts"][0]["text"]
    return json.loads(raw_text)


def highlight_code(code_text, lang):
    lexer = JavaLexer() if lang == "Java" else SqlLexer()
    formatter = HtmlFormatter(style="monokai", noclasses=True)
    return highlight(code_text.strip(), lexer, formatter)


def base_ig_html(body_html, slide_num):
    return f"""<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
  * {{ box-sizing: border-box; margin: 0; padding: 0; }}
  body {{
    width: 1080px; height: 1350px;
    background: radial-gradient(circle at top right, #111b33 0%, #090d16 65%);
    color: #f1f5f9;
    font-family: 'Segoe UI', -apple-system, BlinkMacSystemFont, sans-serif;
    padding: 72px;
    display: flex; flex-direction: column; justify-content: space-between;
  }}
  .top-bar {{ display: flex; justify-content: space-between; align-items: center; font-family: 'DejaVu Sans Mono', monospace; font-size: 22px; color: #38bdf8; letter-spacing: 1px; }}
  .handle {{ color: #94a3b8; }}
  .content {{ flex: 1; display: flex; flex-direction: column; justify-content: center; gap: 36px; }}
  .badges {{ display: flex; gap: 14px; flex-wrap: wrap; }}
  .badge {{ padding: 10px 20px; border-radius: 999px; font-size: 20px; font-weight: 700; font-family: 'DejaVu Sans Mono', monospace; background: rgba(56,189,248,0.14); color: #38bdf8; border: 1px solid rgba(56,189,248,0.35); }}
  .badge.diff {{ background: rgba(245,158,11,0.14); color: #fbbf24; border-color: rgba(245,158,11,0.35); }}
  h1 {{ font-size: 68px; line-height: 1.12; font-weight: 800; color: #ffffff; }}
  .subtitle {{ font-size: 30px; color: #94a3b8; line-height: 1.4; }}
  .card {{
    background: #111827; border: 2px solid #1e293b; border-radius: 24px;
    padding: 36px; box-shadow: 0 20px 50px rgba(0,0,0,0.45);
  }}
  .card.red {{ border-color: #ef4444; background: rgba(239,68,68,0.06); }}
  .card.cyan {{ border-color: #06b6d4; background: rgba(6,182,212,0.06); }}
  .card-header {{ font-family: 'DejaVu Sans Mono', monospace; font-size: 22px; color: #38bdf8; margin-bottom: 18px; display: flex; align-items: center; gap: 12px; }}
  .card.red .card-header {{ color: #f87171; }}
  .card-title {{ font-size: 32px; font-weight: 700; margin-bottom: 14px; color: #f8fafc; }}
  .card-body {{ font-size: 26px; line-height: 1.55; color: #cbd5e1; }}
  .dots {{ display: flex; gap: 10px; margin-right: 12px; }}
  .dot {{ width: 14px; height: 14px; border-radius: 50%; }}
  .r {{ background: #ef4444; }} .y {{ background: #f59e0b; }} .g {{ background: #22c55e; }}
  .step {{ font-family: 'DejaVu Sans Mono', monospace; font-size: 23px; line-height: 1.55; color: #e2e8f0; padding: 14px 18px; background: #0b1120; border-left: 4px solid #38bdf8; border-radius: 8px; margin-bottom: 14px; }}
  .code-wrap {{ font-family: 'DejaVu Sans Mono', monospace; font-size: 20px; line-height: 1.48; overflow: hidden; }}
  .code-wrap pre {{ margin: 0; white-space: pre-wrap; word-break: break-word; background: transparent !important; }}
  .grid-2 {{ display: grid; grid-template-columns: 1fr 1fr; gap: 28px; }}
  .big-o {{ font-family: 'DejaVu Sans Mono', monospace; font-size: 54px; font-weight: 800; color: #38bdf8; margin: 10px 0; }}
  .footer {{ display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #1e293b; padding-top: 24px; font-size: 22px; color: #94a3b8; font-family: 'DejaVu Sans Mono', monospace; }}
</style>
</head>
<body>
  <div class="top-bar">
    <span>365-DAY LEETCODE CHALLENGE</span>
    <span class="handle">@java.codehub</span>
  </div>
  <div class="content">{body_html}</div>
  <div class="footer">
    <span>github.com/raunit004/leetcode-365</span>
    <span>SLIDE 0{slide_num} / 05</span>
  </div>
</body>
</html>"""


def build_linkedin_html(code_html, title, lang):
    return f"""<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style>
  * {{ box-sizing: border-box; margin: 0; padding: 0; }}
  body {{
    width: 1200px; min-height: 900px;
    background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 50%, #4f46e5 100%);
    padding: 72px;
    display: flex; align-items: center; justify-content: center;
    font-family: 'Segoe UI', sans-serif;
  }}
  .window {{
    width: 100%; background: #0f172a; border-radius: 22px;
    box-shadow: 0 30px 70px rgba(0,0,0,0.55); overflow: hidden;
    border: 1px solid rgba(255,255,255,0.12);
  }}
  .titlebar {{
    background: #1e293b; padding: 20px 28px;
    display: flex; align-items: center; justify-content: space-between;
  }}
  .dots {{ display: flex; gap: 10px; }}
  .dot {{ width: 15px; height: 15px; border-radius: 50%; }}
  .r {{ background: #ef4444; }} .y {{ background: #f59e0b; }} .g {{ background: #22c55e; }}
  .fname {{ font-family: 'DejaVu Sans Mono', monospace; font-size: 20px; color: #e2e8f0; font-weight: 600; }}
  .lang {{ font-family: 'DejaVu Sans Mono', monospace; font-size: 18px; color: #a5b4fc; }}
  .code {{ padding: 36px; font-family: 'DejaVu Sans Mono', monospace; font-size: 21px; line-height: 1.5; }}
  .code pre {{ margin: 0; white-space: pre-wrap; word-break: break-word; background: transparent !important; }}
</style>
</head>
<body>
  <div class="window">
    <div class="titlebar">
      <div class="dots"><div class="dot r"></div><div class="dot y"></div><div class="dot g"></div></div>
      <div class="fname">{html.escape(title)}</div>
      <div class="lang">{lang}</div>
    </div>
    <div class="code">{code_html}</div>
  </div>
</body>
</html>"""


def render_images(data, code_text, lang, folder_path):
    code_html = highlight_code(code_text, lang)

    slide1 = base_ig_html(f"""
      <div class="badges">
        <span class="badge">{html.escape(data['problem_badge'])}</span>
        <span class="badge diff">{html.escape(data['difficulty'].upper())}</span>
        <span class="badge">{html.escape(data['pattern_badge'])}</span>
      </div>
      <h1>{html.escape(data['title'])}</h1>
      <div class="subtitle">{html.escape(data['subtitle'])}</div>
      <div class="card">
        <div class="card-header"><div class="dots"><div class="dot r"></div><div class="dot y"></div><div class="dot g"></div></div>problem_statement.sh</div>
        <div class="card-body">{html.escape(data['problem_statement'])}</div>
      </div>
    """, 1)

    slide2 = base_ig_html(f"""
      <div class="top-bar" style="font-size:26px;">01 // THE BOTTLENECK &amp; INTUITION</div>
      <div class="card red">
        <div class="card-header">brute_force_pitfall.log</div>
        <div class="card-title">{html.escape(data['pitfall_title'])}</div>
        <div class="card-body">{html.escape(data['pitfall_body'])}</div>
      </div>
      <div class="card cyan">
        <div class="card-header">optimal_intuition.md</div>
        <div class="card-title">{html.escape(data['optimal_title'])}</div>
        <div class="card-body">{html.escape(data['optimal_body'])}</div>
      </div>
    """, 2)

    steps_html = "".join(f'<div class="step">{html.escape(s)}</div>' for s in data["trace_steps"][:5])
    slide3 = base_ig_html(f"""
      <div class="top-bar" style="font-size:26px;">02 // STEP-BY-STEP TRACE</div>
      <div class="card">
        <div class="card-header">input_setup.txt</div>
        <div class="card-body" style="font-family:'DejaVu Sans Mono',monospace;color:#38bdf8;">{html.escape(data['trace_input'])}</div>
      </div>
      <div class="card">
        <div class="card-header">phases_trace.state</div>
        {steps_html}
      </div>
    """, 3)

    slide4 = base_ig_html(f"""
      <div class="top-bar" style="font-size:26px;">03 // CLEAN SOLUTION</div>
      <div class="card">
        <div class="card-header"><div class="dots"><div class="dot r"></div><div class="dot y"></div><div class="dot g"></div></div>Solution.{"java" if lang == "Java" else "sql"}</div>
        <div class="code-wrap">{code_html}</div>
      </div>
    """, 4)

    slide5 = base_ig_html(f"""
      <div class="top-bar" style="font-size:26px;">04 // COMPLEXITY &amp; TAKEAWAY</div>
      <div class="grid-2">
        <div class="card cyan">
          <div class="card-header">time_complexity</div>
          <div class="big-o">{html.escape(data['time_complexity'])}</div>
          <div class="card-body" style="font-size:22px;">{html.escape(data['time_reason'])}</div>
        </div>
        <div class="card cyan">
          <div class="card-header">space_complexity</div>
          <div class="big-o">{html.escape(data['space_complexity'])}</div>
          <div class="card-body" style="font-size:22px;">{html.escape(data['space_reason'])}</div>
        </div>
      </div>
      <div class="card">
        <div class="card-header">interview_rule.txt</div>
        <div class="card-body">{html.escape(data['interview_rule'])}</div>
      </div>
      <div style="text-align:center;font-size:24px;color:#38bdf8;font-weight:600;">Save this for technical interview revision</div>
    """, 5)

    li_html = build_linkedin_html(code_html, data["title"], lang)

    with sync_playwright() as p:
        browser = p.chromium.launch()
        page = browser.new_page(viewport={"width": 1080, "height": 1350})
        for idx, html_str in enumerate([slide1, slide2, slide3, slide4, slide5], start=1):
            page.set_content(html_str)
            page.screenshot(path=os.path.join(folder_path, f"slide{idx}.jpg"), type="jpeg", quality=92)

        li_page = browser.new_page(viewport={"width": 1200, "height": 900})
        li_page.set_content(li_html)
        li_page.screenshot(path=os.path.join(folder_path, "li_card.jpg"), type="jpeg", quality=94, full_page=True)
        browser.close()


def run_generate():
    folder, day_num = find_latest_day_folder()
    if not folder or day_num < MIN_AUTO_DAY:
        print(f"Skipping social generation (latest day={day_num}, starts at Day {MIN_AUTO_DAY}).")
        return

    folder_path = os.path.join("solutions", folder)
    if os.path.exists(os.path.join(folder_path, "Solution.java")):
        code_file, lang = "Solution.java", "Java"
    elif os.path.exists(os.path.join(folder_path, "Solution.sql")):
        code_file, lang = "Solution.sql", "SQL"
    else:
        print(f"No solution file found in {folder_path}")
        return

    with open(os.path.join(folder_path, code_file), "r", encoding="utf-8") as f:
        code_text = f.read()

    readme_path = os.path.join(folder_path, "README.md")
    readme_text = open(readme_path, "r", encoding="utf-8").read() if os.path.exists(readme_path) else ""

    encoded_folder = urllib.parse.quote(folder)
    raw_base = f"https://raw.githubusercontent.com/{REPO_OWNER}/{REPO_NAME}/{BRANCH}/solutions/{encoded_folder}"
    github_url = f"https://github.com/{REPO_OWNER}/{REPO_NAME}/tree/{BRANCH}/solutions/{encoded_folder}"

    data = call_gemini(readme_text, code_text, lang, folder, github_url)
    render_images(data, code_text, lang, folder_path)

    payload = {
        "slide1_url": f"{raw_base}/slide1.jpg",
        "slide2_url": f"{raw_base}/slide2.jpg",
        "slide3_url": f"{raw_base}/slide3.jpg",
        "slide4_url": f"{raw_base}/slide4.jpg",
        "slide5_url": f"{raw_base}/slide5.jpg",
        "li_image_url": f"{raw_base}/li_card.jpg",
        "ig_caption": data["ig_caption"],
        "li_caption": data["li_caption"],
    }
    with open(PAYLOAD_FILE, "w", encoding="utf-8") as f:
        json.dump(payload, f)

    print(f"Generated 5 IG slides + LinkedIn card for {folder}.")


def run_send():
    webhook_url = os.environ.get("MAKE_WEBHOOK_URL", "").strip()
    if not webhook_url or not os.path.exists(PAYLOAD_FILE):
        print("Skipping webhook (no payload or URL).")
        return

    with open(PAYLOAD_FILE, "r", encoding="utf-8") as f:
        payload = json.load(f)

    # Give GitHub Raw CDN time to serve the newly pushed images
    time.sleep(15)
    resp = requests.post(webhook_url, json=payload, timeout=45)
    resp.raise_for_status()
    os.remove(PAYLOAD_FILE)
    print("Webhook sent successfully:", resp.status_code)


if __name__ == "__main__":
    if "--generate" in sys.argv:
        run_generate()
    elif "--send" in sys.argv:
        run_send()