# Day 091: Valid Number

- **Platform:** LeetCode #65
- **Difficulty:** Hard
- **Topic:** String
- **Problem Link:** [Valid Number](https://leetcode.com/problems/valid-number/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Determine if a given string `s` represents a valid decimal or integer number (optionally with an exponent `e`/`E`).

---

### 2. Intuition & Approach
Single-pass state validation tracking `seenDigit`, `seenDot`, and `seenExp`. Signs are only valid at index `0` or immediately after `e`/`E`; dots cannot appear twice or after `e`/`E`; exponents require a preceding digit and reset `seenDigit`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Rejects lone `.`, `+`, `e9`, `1e`, and `1.2.3`; accepts `.1`, `2.`, `-90E3`.
