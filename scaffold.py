import argparse
import os

def main():
    parser = argparse.ArgumentParser(description="Scaffold a daily LeetCode solution folder.")
    parser.add_argument("--day", type=int, required=True, help="Day number (e.g. 34)")
    parser.add_argument("--slug", type=str, required=True, help="Problem slug (e.g. duplicate-emails)")
    parser.add_argument("--title", type=str, required=True, help="Problem title")
    parser.add_argument("--difficulty", type=str, required=True, choices=["Easy", "Medium", "Hard"])
    parser.add_argument("--topic", type=str, required=True, help="Problem topic tags")
    parser.add_argument("--url", type=str, required=True, help="LeetCode URL")
    parser.add_argument("--lang", type=str, default=None, choices=["java", "sql", "py", "cpp"], help="Language override")
    parser.add_argument("--queue", action="store_true", help="Scaffold directly inside queue/ folder")

    args = parser.parse_args()

    # Auto-detect language if not explicitly passed
    lang = args.lang
    if not lang:
        if "database" in args.topic.lower() or "sql" in args.topic.lower():
            lang = "sql"
        else:
            lang = "java"

    # Select target directory
    base_dir = "queue" if args.queue else "solutions"
    day_folder_name = f"day-{args.day:03d}-{args.slug}"
    target_path = os.path.join(base_dir, day_folder_name)
    os.makedirs(target_path, exist_ok=True)

    # 1. Generate Starter Code
    if lang == "sql":
        sol_filename = "Solution.sql"
        sol_code = f"""-- Day {args.day:03d}: {args.title}
-- LeetCode: {args.url}

SELECT 
    -- query
FROM 
;
"""
    elif lang == "java":
        sol_filename = "Solution.java"
        sol_code = f"""/**
 * Day {args.day:03d}: {args.title}
 * LeetCode: {args.url}
 */
public class Solution {{
    public static void main(String[] args) {{
        // Smoke Tests
    }}
}}
"""
    elif lang == "py":
        sol_filename = "Solution.py"
        sol_code = f"""# Day {args.day:03d}: {args.title}
# LeetCode: {args.url}

class Solution:
    pass
"""
    elif lang == "cpp":
        sol_filename = "Solution.cpp"
        sol_code = f"""// Day {args.day:03d}: {args.title}
// LeetCode: {args.url}

class Solution {{
public:
}};
"""

    code_file_path = os.path.join(target_path, sol_filename)
    if not os.path.exists(code_file_path):
        with open(code_file_path, "w", encoding="utf-8") as f:
            f.write(sol_code)

    # 2. Generate Day README.md
    readme_path = os.path.join(target_path, "README.md")
    lang_labels = {"sql": "Oracle SQL / MySQL", "java": "Java", "py": "Python", "cpp": "C++"}
    lang_display = lang_labels.get(lang, "Java")

    readme_content = f"""# Day {args.day:03d}: {args.title}

- **Platform:** LeetCode
- **Difficulty:** {args.difficulty}
- **Topic:** {args.topic}
- **Problem Link:** [{args.title}]({args.url})
- **Language:** {lang_display}
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Statement

---

### 2. Intuition & Approach

---

### 3. Complexity Analysis

- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$
"""

    if not os.path.exists(readme_path):
        with open(readme_path, "w", encoding="utf-8") as f:
            f.write(readme_content)

    print(f"Scaffolded: {target_path} [{sol_filename}]")

if __name__ == "__main__":
    main()