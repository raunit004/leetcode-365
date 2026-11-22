# Day 089: N-Queens II

- **Platform:** LeetCode #52
- **Difficulty:** Hard
- **Topic:** Backtracking
- **Problem Link:** [N-Queens II](https://leetcode.com/problems/n-queens-ii/)
- **Language:** Java
- **Time Complexity:** $O(N!)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Return the total number of distinct solutions to the `n`-queens puzzle.

---

### 2. Intuition & Approach
Track occupied columns, main diagonals, and anti-diagonals using bitmasks in a row-by-row DFS, counting every valid placement reaching `row == n`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N!)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
Bitmask tracking avoids board allocation completely; `n = 1` returns `1`, `n = 4` returns `2`.
