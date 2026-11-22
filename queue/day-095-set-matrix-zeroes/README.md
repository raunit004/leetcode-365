# Day 095: Set Matrix Zeroes

- **Platform:** LeetCode #73
- **Difficulty:** Medium
- **Topic:** Array, Hash Table, Matrix
- **Problem Link:** [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes/)
- **Language:** Java
- **Time Complexity:** $O(M \cdot N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Given an `m x n` integer matrix, if an element is `0`, set its entire row and column to `0` in-place.

---

### 2. Intuition & Approach
Use the first row and first column as $O(1)$ marker flags, keeping a separate boolean `firstColZero` for column `0`. Zero out cells bottom-up based on markers.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(M \cdot N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Separating `firstColZero` prevents `matrix[0][0]` from conflating row `0` and column `0`.
