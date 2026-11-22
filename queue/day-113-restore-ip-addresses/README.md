# Day 113: Restore IP Addresses

- **Platform:** LeetCode #93
- **Difficulty:** Medium
- **Topic:** String, Backtracking
- **Problem Link:** [Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/)
- **Language:** Java
- **Time Complexity:** $O(1)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Return all valid IPv4 addresses formed by inserting three dots into a digit string `s`.

---

### 2. Intuition & Approach
Backtrack over 4 octets of lengths `1..3` with length pruning (`n < 4 || n > 12`), leading-zero rejection, `val <= 255` bounds, and `StringBuilder.setLength` rollback.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(1)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Lone `'0'` is valid (`"0.0.0.0"`), while multi-digit segments starting with `'0'` are rejected.
