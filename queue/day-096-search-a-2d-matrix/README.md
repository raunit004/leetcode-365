# Day 096: Search a 2D Matrix

- **Platform:** LeetCode #74
- **Difficulty:** Medium
- **Topic:** Array, Binary Search, Matrix
- **Problem Link:** [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/)
- **Language:** Java
- **Time Complexity:** $O(\log(M \cdot N))$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Search for `target` in an `m x n` matrix where each row is sorted and the first integer of each row is greater than the last integer of the previous row.

---

### 2. Intuition & Approach
Treat the 2D matrix as a flattened sorted 1D array of length `m * n` and run standard binary search, mapping `mid` to `(mid / n, mid % n)`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(\log(M \cdot N))$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Single-cell matrix (`1 x 1`) and targets outside `[matrix[0][0], matrix[m-1][n-1]]`.
