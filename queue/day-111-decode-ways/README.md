# Day 111: Decode Ways

- **Platform:** LeetCode #91
- **Difficulty:** Medium
- **Topic:** String, Dynamic Programming
- **Problem Link:** [Decode Ways](https://leetcode.com/problems/decode-ways/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Count the number of ways to decode a digit string into letters `'A'`–`'Z'` (`1`–`26`).

---

### 2. Intuition & Approach
Rolling $O(1)$ space DP (`prev2`, `prev1`) adding `prev1` when `s[i-1] != '0'` and `prev2` when the two-digit number is in `[10, 26]`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Strings starting with `'0'` immediately return `0`; invalid pairs like `"30"` propagate `0`.
