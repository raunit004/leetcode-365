# Day 108: Scramble String

- **Platform:** LeetCode #87
- **Difficulty:** Hard
- **Topic:** String, Dynamic Programming
- **Problem Link:** [Scramble String](https://leetcode.com/problems/scramble-string/)
- **Language:** Java
- **Time Complexity:** $O(N^4)$
- **Space Complexity:** $O(N^3)$

---

### 1. Problem Overview
Determine if `s2` is a scrambled string of `s1` of the same length.

---

### 2. Intuition & Approach
Top-down 3D memoized recursion over `(i1, i2, len)` with `regionMatches` fast-path, 26-letter anagram pruning, and both non-swapped and swapped split checks.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N^4)$
- **Space Complexity:** $O(N^3)$

---

### 4. Edge Cases & Key Takeaways
Length-1 strings are resolved immediately via `regionMatches`.
