# Day 086: Find Customer Referee

- **Platform:** LeetCode #584
- **Difficulty:** Easy
- **Topic:** Database
- **Problem Link:** [Find Customer Referee](https://leetcode.com/problems/find-customer-referee/)
- **Language:** Oracle SQL / MySQL
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Find the names of customers that are not referred by the customer with `id = 2`.

---

### 2. Intuition & Approach
Filter `Customer` where `referee_id != 2 OR referee_id IS NULL`. Explicit `IS NULL` handling is required because `NULL != 2` evaluates to UNKNOWN in SQL.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Customers with `referee_id = NULL` are valid and must appear in the result.
