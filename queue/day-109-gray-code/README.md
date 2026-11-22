# Day 109: Gray Code

- **Platform:** LeetCode #89
- **Difficulty:** Medium
- **Topic:** Math, Backtracking, Bit Manipulation
- **Problem Link:** [Gray Code](https://leetcode.com/problems/gray-code/)
- **Language:** Java
- **Time Complexity:** $O(2^n)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Return any valid `n`-bit Gray code sequence of `2^n` integers starting at `0`.

---

### 2. Intuition & Approach
Apply the closed-form binary-to-Gray formula `i ^ (i >> 1)` for `i` from `0` to `(1 << n) - 1`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(2^n)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
`n = 1` returns `[0, 1]`; pre-sizing `ArrayList` avoids resizing overhead.
