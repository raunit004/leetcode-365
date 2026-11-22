# Day 098: Minimum Window Substring

- **Platform:** LeetCode #76
- **Difficulty:** Hard
- **Topic:** Hash Table, String, Sliding Window
- **Problem Link:** [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)
- **Language:** Java
- **Time Complexity:** $O(M + N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Return the minimum window substring of `s` such that every character in `t` (including duplicates) is included in the window, or `""` if none exists.

---

### 2. Intuition & Approach
Maintain an ASCII frequency table `need[128]` and a counter `missing = t.length()`. Expand `right` to satisfy all required characters, then shrink `left` while `missing == 0` to record the shortest valid window.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(M + N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
If `s.length() < t.length()` or no valid window is found, return `""`.
