import os
import re
import shutil
import sys

QUEUE_DIR = "queue"
SOLUTIONS_DIR = "solutions"
ROOT_README = "README.md"
COMMIT_FILE = ".commit_msg"

LANG_MAP = {
    ".java": "Java",
    ".sql": "SQL",
    ".py": "Python",
    ".cpp": "C++",
    ".js": "JavaScript",
    ".ts": "TypeScript",
}

def detect_solution_file(folder_path):
    """
    Finds the primary solution file in the target day folder.
    Prioritizes files matching Solution.* (PascalCase) or solution.*.
    """
    files = os.listdir(folder_path)
    
    for fname in files:
        base, ext = os.path.splitext(fname)
        if base.lower() == "solution" and ext.lower() in LANG_MAP:
            return fname, LANG_MAP[ext.lower()]
            
    for fname in files:
        _, ext = os.path.splitext(fname)
        if ext.lower() in LANG_MAP:
            return fname, LANG_MAP[ext.lower()]

    return "Solution.java", "Java"

def parse_day_readme(readme_path):
    """
    Extracts metadata from the problem folder's README.md.
    """
    meta = {
        "title": "Problem Solution",
        "difficulty": "Easy",
        "topic": "Algorithms",
        "url": "#",
        "time_comp": "O(N)",
        "space_comp": "O(1)",
    }
    
    if not os.path.exists(readme_path):
        return meta

    with open(readme_path, "r", encoding="utf-8") as f:
        content = f.read()

    m_title = re.search(r"^#\s+Day\s+\d+:\s*(.+)$", content, re.MULTILINE)
    if m_title:
        meta["title"] = m_title.group(1).strip()

    m_diff = re.search(r"-\s+\*\*Difficulty:\*\*\s*(.+)$", content, re.MULTILINE)
    if m_diff:
        meta["difficulty"] = m_diff.group(1).strip()

    m_topic = re.search(r"-\s+\*\*Topic:\*\*\s*(.+)$", content, re.MULTILINE)
    if m_topic:
        meta["topic"] = m_topic.group(1).strip()

    m_link = re.search(r"-\s+\*\*Problem Link:\*\*\s*\[.*?\]\((.+?)\)", content, re.MULTILINE)
    if m_link:
        meta["url"] = m_link.group(1).strip()

    m_time = re.search(r"-\s+\*\*Time Complexity:\*\*\s*(.+)$", content, re.MULTILINE)
    if m_time:
        meta["time_comp"] = m_time.group(1).strip()

    m_space = re.search(r"-\s+\*\*Space Complexity:\*\*\s*(.+)$", content, re.MULTILINE)
    if m_space:
        meta["space_comp"] = m_space.group(1).strip()

    return meta

def update_root_readme(new_day_folder, new_day_num, meta, sol_file, lang_label):
    if not os.path.exists(ROOT_README):
        print(f"Warning: {ROOT_README} not found.")
        return

    with open(ROOT_README, "r", encoding="utf-8") as f:
        readme_text = f.read()

    # Tally problem counts across all solutions directories
    all_solutions = [
        d for d in os.listdir(SOLUTIONS_DIR)
        if os.path.isdir(os.path.join(SOLUTIONS_DIR, d)) and re.match(r"^day-\d+", d)
    ]
    total_solved = len(all_solutions)

    easy_count = 0
    medium_count = 0
    hard_count = 0

    for d in all_solutions:
        sub_readme = os.path.join(SOLUTIONS_DIR, d, "README.md")
        if os.path.exists(sub_readme):
            with open(sub_readme, "r", encoding="utf-8") as rf:
                txt = rf.read()
                if re.search(r"Difficulty:\*\*\s*Easy", txt, re.IGNORECASE):
                    easy_count += 1
                elif re.search(r"Difficulty:\*\*\s*Medium", txt, re.IGNORECASE):
                    medium_count += 1
                elif re.search(r"Difficulty:\*\*\s*Hard", txt, re.IGNORECASE):
                    hard_count += 1

    # 1. Update Badges
    readme_text = re.sub(
        r"Days%20Completed-\d+%2F365-blue",
        f"Days%20Completed-{total_solved}%2F365-blue",
        readme_text
    )
    readme_text = re.sub(
        r"Current%20Streak-\d+%20Days-brightgreen",
        f"Current%20Streak-{total_solved}%20Days-brightgreen",
        readme_text
    )

    # 2. Update Difficulty Breakdown
    readme_text = re.sub(
        r"(\|\s*🟢\s*\*\*Easy\*\*\s*\|\s*)\d+(\s*\|)",
        rf"\g<1>{easy_count}\g<2>",
        readme_text
    )
    readme_text = re.sub(
        r"(\|\s*🟡\s*\*\*Medium\*\*\s*\|\s*)\d+(\s*\|)",
        rf"\g<1>{medium_count}\g<2>",
        readme_text
    )
    readme_text = re.sub(
        r"(\|\s*🔴\s*\*\*Hard\*\*\s*\|\s*)\d+(\s*\|)",
        rf"\g<1>{hard_count}\g<2>",
        readme_text
    )
    readme_text = re.sub(
        r"(\|\s*🎯\s*\*\*Total Solved\*\*\s*\|\s*)\d+\s*/\s*365(\s*\|)",
        rf"\g<1>{total_solved} / 365\g<2>",
        readme_text
    )

    # 3. Format Master Tracker Table Row
    day_str = f"{new_day_num:03d}"
    sol_rel_path = f"solutions/{new_day_folder}/{sol_file}"
    new_row = (
        f"| {day_str} | [{meta['title']}]({meta['url']}) | {meta['topic']} | "
        f"`{meta['difficulty']}` | {meta['time_comp']} | {meta['space_comp']} | "
        f"[{lang_label}]({sol_rel_path}) |"
    )

    # Check specifically for table cell "| 035 |" rather than broad substring
    table_cell_pattern = rf"\|\s*{day_str}\s*\|"
    if not re.search(table_cell_pattern, readme_text):
        if "<!-- TRACKER_TABLE_END -->" in readme_text:
            readme_text = readme_text.replace(
                "<!-- TRACKER_TABLE_END -->",
                f"{new_row}\n<!-- TRACKER_TABLE_END -->"
            )
        else:
            readme_text = readme_text.rstrip() + f"\n{new_row}\n"

    with open(ROOT_README, "w", encoding="utf-8") as f:
        f.write(readme_text)

def main():
    if not os.path.exists(QUEUE_DIR):
        print("Queue directory does not exist. Nothing to release.")
        sys.exit(0)

    queue_entries = [
        d for d in os.listdir(QUEUE_DIR)
        if os.path.isdir(os.path.join(QUEUE_DIR, d)) and re.match(r"^day-(\d+)", d)
    ]

    if not queue_entries:
        print("Queue is empty. No release needed.")
        sys.exit(0)

    # Release lowest-numbered queued day
    queue_entries.sort(key=lambda d: int(re.match(r"^day-(\d+)", d).group(1)))
    next_day_folder = queue_entries[0]
    day_num = int(re.match(r"^day-(\d+)", next_day_folder).group(1))

    src_path = os.path.join(QUEUE_DIR, next_day_folder)
    os.makedirs(SOLUTIONS_DIR, exist_ok=True)
    dst_path = os.path.join(SOLUTIONS_DIR, next_day_folder)

    if os.path.exists(dst_path):
        print(f"Destination {dst_path} already exists. Replacing with queued version.")
        shutil.rmtree(dst_path)

    print(f"Releasing {next_day_folder} from queue to solutions...")
    shutil.move(src_path, dst_path)

    sol_file, lang_label = detect_solution_file(dst_path)
    day_readme = os.path.join(dst_path, "README.md")
    meta = parse_day_readme(day_readme)

    # Update root README.md
    update_root_readme(next_day_folder, day_num, meta, sol_file, lang_label)

    # Create .commit_msg so GitHub Actions executes git commit & push
    commit_msg = f"feat: day {day_num:03d} - {meta['title'].lower()} [{meta['difficulty']}]"
    with open(COMMIT_FILE, "w", encoding="utf-8") as cf:
        cf.write(commit_msg)

    print(f"Successfully released Day {day_num:03d} ({lang_label}) and created {COMMIT_FILE}!")

if __name__ == "__main__":
    main()