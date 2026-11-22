# Day 093: Simplify Path

- **Platform:** LeetCode #71
- **Difficulty:** Medium
- **Topic:** String, Stack
- **Problem Link:** [Simplify Path](https://leetcode.com/problems/simplify-path/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Convert an absolute Unix-style file path into its simplified canonical path.

---

### 2. Intuition & Approach
Split the path by `/` and maintain a deque of directory names: ignore empty tokens and `.`, pop on `..` if non-empty, and push valid directory names.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
Root `..` stays at `/`; multiple consecutive slashes collapse cleanly.
