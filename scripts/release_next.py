import os
import re
import shutil
from pathlib import Path

# Paths
ROOT_DIR = Path(__file__).resolve().parent.parent
QUEUE_DIR = ROOT_DIR / "queue"
SOLUTIONS_DIR = ROOT_DIR / "solutions"
README_FILE = ROOT_DIR / "README.md"
COMMIT_MSG_FILE = ROOT_DIR / ".commit_msg"

def get_next_queued_folder():
    """Finds the lowest numbered day waiting in queue/."""
    if not QUEUE_DIR.exists():
        return None
    folders = [f for f in QUEUE_DIR.iterdir() if f.is_dir() and re.match(r"^day-\d+", f.name)]
    if not folders:
        return None
    # Sort folders by integer day number (e.g., day-028 comes before day-029)
    folders.sort(key=lambda x: int(re.search(r"^day-(\d+)", x.name).group(1)))
    return folders[0]

def parse_daily_readme(readme_path):
    """Extracts problem metadata from the daily solution README.md."""
    content = readme_path.read_text(encoding="utf-8")

    title_match = re.search(r"^# Day (\d+):\s*(.+)$", content, re.MULTILINE)
    day_num = title_match.group(1) if title_match else "000"
    title = title_match.group(2).strip() if title_match else "Unknown"

    diff_match = re.search(r"-\s*\*\*Difficulty:\*\*\s*(Easy|Medium|Hard)", content)
    difficulty = diff_match.group(1) if diff_match else "Medium"

    topic_match = re.search(r"-\s*\*\*Topic:\*\*\s*(.+)$", content, re.MULTILINE)
    topic = topic_match.group(1).strip() if topic_match else "Algorithm"

    url_match = re.search(r"-\s*\*\*Problem Link:\*\*\s*\[.*?\]\((https://leetcode\.com/problems/[^\s\)]+)\)", content)
    url = url_match.group(1) if url_match else "https://leetcode.com/"

    time_match = re.search(r"-\s*\*\*Time Complexity:\*\*\s*(\$[^\$]+\$)", content)
    time_comp = time_match.group(1) if time_match else "$O(N)$"

    space_match = re.search(r"-\s*\*\*Space Complexity:\*\*\s*(\$[^\$]+\$)", content)
    space_comp = space_match.group(1) if space_match else "$O(1)$"

    return {
        "day": day_num,
        "title": title,
        "difficulty": difficulty,
        "topic": topic,
        "url": url,
        "time": time_comp,
        "space": space_comp
    }

def update_root_readme(meta, folder_name):
    """Updates badges, counts, and appends a row to the main tracker table."""
    content = README_FILE.read_text(encoding="utf-8")
    day_int = int(meta["day"])

    # 1. Update streak and completed badges
    content = re.sub(r"Days%20Completed-\d+%2F365", f"Days%20Completed-{day_int}%2F365", content)
    content = re.sub(r"Current%20Streak-\d+%20Days", f"Current%20Streak-{day_int}%20Days", content)

    # 2. Update category counts
    diff = meta["difficulty"]
    if diff == "Easy":
        content = re.sub(r"(\|\s*🟢\s*\*\*Easy\*\*\s*\|\s*)(\d+)(\s*\|)", lambda m: f"{m.group(1)}{int(m.group(2)) + 1}{m.group(3)}", content)
    elif diff == "Medium":
        content = re.sub(r"(\|\s*🟡\s*\*\*Medium\*\*\s*\|\s*)(\d+)(\s*\|)", lambda m: f"{m.group(1)}{int(m.group(2)) + 1}{m.group(3)}", content)
    elif diff == "Hard":
        content = re.sub(r"(\|\s*🔴\s*\*\*Hard\*\*\s*\|\s*)(\d+)(\s*\|)", lambda m: f"{m.group(1)}{int(m.group(2)) + 1}{m.group(3)}", content)

    content = re.sub(r"(\|\s*🎯\s*\*\*Total Solved\*\*\s*\|\s*)\d+\s*/\s*365", f"\\g<1>{day_int} / 365", content)

    # 3. Append the new row to the tracker table
    new_row = (
        f"| {meta['day']} | [{meta['title']}]({meta['url']}) | {meta['topic']} | "
        f"`{meta['difficulty']}` | {meta['time']} | {meta['space']} | "
        f"[Java](solutions/{folder_name}/Solution.java) |\n"
    )

    if "<!-- TRACKER_TABLE_END -->" in content:
        content = content.replace("<!-- TRACKER_TABLE_END -->", f"{new_row}<!-- TRACKER_TABLE_END -->")
    else:
        content += new_row

    README_FILE.write_text(content, encoding="utf-8")

def main():
    target_folder = get_next_queued_folder()
    if not target_folder:
        print("NO_QUEUE: Nothing to release.")
        return

    dest_folder = SOLUTIONS_DIR / target_folder.name
    SOLUTIONS_DIR.mkdir(exist_ok=True)
    shutil.move(str(target_folder), str(dest_folder))

    meta = parse_daily_readme(dest_folder / "README.md")
    update_root_readme(meta, target_folder.name)

    # Generate standard commit message
    commit_msg = f"feat: day {meta['day']} - {meta['title'].lower()} [{meta['difficulty']}]"
    COMMIT_MSG_FILE.write_text(commit_msg, encoding="utf-8")
    print(f"RELEASED: {commit_msg}")

if __name__ == "__main__":
    main()