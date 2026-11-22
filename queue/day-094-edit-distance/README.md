# Day 094: Edit Distance

- **Platform:** LeetCode #72
- **Difficulty:** Medium
- **Topic:** String, Dynamic Programming
- **Problem Link:** [Edit Distance](https://leetcode.com/problems/edit-distance/)
- **Language:** Java
- **Time Complexity:** $O(M \cdot N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Return the minimum number of operations (insert, delete, or replace a character) required to convert `word1` to `word2`.

---

### 2. Intuition & Approach
Use 1D space-optimized Levenshtein DP where `dp[j]` holds the edit distance between prefixes `word1[0..i-1]` and `word2[0..j-1]`, tracking `prevDiag` for `dp[i-1][j-1]`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(M \cdot N)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
Empty `word1` or `word2` returns the length of the other string.
