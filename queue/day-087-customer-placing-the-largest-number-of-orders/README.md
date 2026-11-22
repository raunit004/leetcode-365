# Day 087: Customer Placing the Largest Number of Orders

- **Platform:** LeetCode #586
- **Difficulty:** Easy
- **Topic:** Database
- **Problem Link:** [Customer Placing the Largest Number of Orders](https://leetcode.com/problems/customer-placing-the-largest-number-of-orders/)
- **Language:** Oracle SQL / MySQL
- **Time Complexity:** $O(N \log N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Find the `customer_number` for the customer who has placed the largest number of orders.

---

### 2. Intuition & Approach
Group `Orders` by `customer_number`, order by `COUNT(*) DESC` in an inline view, and select the top row using `WHERE ROWNUM = 1` in Oracle SQL.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N \log N)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
Single-customer table returns that customer; ordering before applying `ROWNUM = 1` ensures the true maximum is selected.
