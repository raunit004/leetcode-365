# Day 088: N-Queens

- **Platform:** LeetCode #51
- **Difficulty:** Hard
- **Topic:** Array, Backtracking
- **Problem Link:** [N-Queens](https://leetcode.com/problems/n-queens/)
- **Language:** Java
- **Time Complexity:** $O(N!)$
- **Space Complexity:** $O(N^2)$

---

### 1. Problem Overview
Place `n` queens on an `n x n` chessboard so no two queens attack each other, returning all distinct board configurations.

---

### 2. Intuition & Approach
Backtrack row by row using boolean lookup arrays for occupied columns, main diagonals (`r - c + n`), and anti-diagonals (`r + c`) for $O(1)$ conflict checks.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N!)$
- **Space Complexity:** $O(N^2)$

---

### 4. Edge Cases & Key Takeaways
`n = 1` returns `[["Q"]]`; `n = 2` and `n = 3` have no valid placements and return `[]`.
