# Day 117: Interleaving String

- **Platform:** LeetCode #97
- **Difficulty:** Medium
- **Topic:** String, Dynamic Programming
- **Problem Link:** [Interleaving String](https://leetcode.com/problems/interleaving-string/)
- **Language:** Java
- **Time Complexity:** $O(M \cdot N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Determine whether `s3` is formed by an interleaving of `s1` and `s2`.

---

### 2. Intuition & Approach
1D rolling boolean DP `dp[j]` representing whether `s1[0..i-1]` and `s2[0..j-1]` interleave to form `s3[0..i+j-1]`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(M \cdot N)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
Immediate `false` if `s1.length() + s2.length() != s3.length()`.
