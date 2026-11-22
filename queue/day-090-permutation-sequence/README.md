# Day 090: Permutation Sequence

- **Platform:** LeetCode #60
- **Difficulty:** Hard
- **Topic:** Math, Recursion
- **Problem Link:** [Permutation Sequence](https://leetcode.com/problems/permutation-sequence/)
- **Language:** Java
- **Time Complexity:** $O(N^2)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Given `n` and `k`, return the `k`-th permutation sequence of `[1, 2, ..., n]` in lexicographical order.

---

### 2. Intuition & Approach
Use the factorial number system: convert `k` to 0-indexed (`k--`), repeatedly pick index `k / (i-1)!` from the remaining sorted digits, append it, and update `k %= (i-1)!`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N^2)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
`n = 1, k = 1` returns `"1"`; avoids generating all `n!` permutations.
