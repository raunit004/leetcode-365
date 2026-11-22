# Day 085: Employee Bonus

- **Platform:** LeetCode #577
- **Difficulty:** Easy
- **Topic:** Database
- **Problem Link:** [Employee Bonus](https://leetcode.com/problems/employee-bonus/)
- **Language:** Oracle SQL / MySQL
- **Time Complexity:** $O(N + M)$
- **Space Complexity:** $O(M)$

---

### 1. Problem Overview
Report the `name` and `bonus` amount of each employee whose bonus is less than `1000` (including employees who received no bonus).

---

### 2. Intuition & Approach
Use a `LEFT JOIN` from `Employee` to `Bonus` on `empId` so employees without a bonus row are preserved with `NULL`, then filter with `b.bonus < 1000 OR b.bonus IS NULL`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N + M)$
- **Space Complexity:** $O(M)$

---

### 4. Edge Cases & Key Takeaways
Employees with no entry in `Bonus` must be included via `IS NULL`, since `NULL < 1000` evaluates to UNKNOWN in SQL.
