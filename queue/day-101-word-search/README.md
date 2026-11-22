# Day 101: Word Search

- **Platform:** LeetCode #79
- **Difficulty:** Medium
- **Topic:** Array, String, Backtracking, Matrix
- **Problem Link:** [Word Search](https://leetcode.com/problems/word-search/)
- **Language:** Java
- **Time Complexity:** $O(M \cdot N \cdot 3^L)$
- **Space Complexity:** $O(L)$

---

### 1. Problem Overview
Determine if `word` exists in an `m x n` grid of characters via sequentially adjacent (horizontal/vertical) cells without reusing any cell.

---

### 2. Intuition & Approach
Run 4-directional DFS from every cell, masking visited cells in-place with `'#'` and restoring them on backtrack.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(M \cdot N \cdot 3^L)$
- **Space Complexity:** $O(L)$

---

### 4. Edge Cases & Key Takeaways
Single-cell board matches immediately; in-place restoration keeps `board` intact for later starting cells.
