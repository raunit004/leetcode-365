# Day 106: Maximal Rectangle

- **Platform:** LeetCode #85
- **Difficulty:** Hard
- **Topic:** Array, Dynamic Programming, Stack, Matrix, Monotonic Stack
- **Problem Link:** [Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/)
- **Language:** Java
- **Time Complexity:** $O(R \cdot C)$
- **Space Complexity:** $O(C)$

---

### 1. Problem Overview
Find the largest rectangle containing only `'1'`s in a binary matrix and return its area.

---

### 2. Intuition & Approach
Build a running histogram `heights[j]` row by row (resetting to `0` on `'0'`) and compute the largest rectangle per row using a monotonic increasing stack.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(R \cdot C)$
- **Space Complexity:** $O(C)$

---

### 4. Edge Cases & Key Takeaways
Single-cell `'0'` returns `0`; single-cell `'1'` returns `1`.
