# Day 099: Combinations

- **Platform:** LeetCode #77
- **Difficulty:** Medium
- **Topic:** Backtracking
- **Problem Link:** [Combinations](https://leetcode.com/problems/combinations/)
- **Language:** Java
- **Time Complexity:** $O(k \cdot \binom{n}{k})$
- **Space Complexity:** $O(k)$

---

### 1. Problem Overview
Given two integers `n` and `k`, return all possible combinations of `k` numbers chosen from the range `[1, n]`.

---

### 2. Intuition & Approach
Use forward backtracking with upper-bound pruning: at depth `curr.size()`, only iterate `i` up to `n - (k - curr.size()) + 1` so branches without enough remaining numbers are skipped.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(k \cdot \binom{n}{k})$
- **Space Complexity:** $O(k)$

---

### 4. Edge Cases & Key Takeaways
`k == 1` returns singletons; `k == n` returns `[[1, 2, ..., n]]`.
