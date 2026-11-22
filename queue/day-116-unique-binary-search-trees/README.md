# Day 116: Unique Binary Search Trees

- **Platform:** LeetCode #96
- **Difficulty:** Medium
- **Topic:** Math, Dynamic Programming, Tree, Binary Search Tree
- **Problem Link:** [Unique Binary Search Trees](https://leetcode.com/problems/unique-binary-search-trees/)
- **Language:** Java
- **Time Complexity:** $O(N^2)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Return the number of structurally unique BSTs with `n` nodes of unique values `1..n`.

---

### 2. Intuition & Approach
Bottom-up Catalan DP where `dp[nodes] += dp[root - 1] * dp[nodes - root]` with base cases `dp[0] = dp[1] = 1`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N^2)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
`n = 1` returns `1`; `dp[0] = 1` handles empty left/right subtrees.
