-- Day 087: Customer Placing the Largest Number of Orders
-- LeetCode #586: https://leetcode.com/problems/customer-placing-the-largest-number-of-orders/

SELECT customer_number
FROM (
    SELECT customer_number
    FROM Orders
    GROUP BY customer_number
    ORDER BY COUNT(*) DESC
)
WHERE ROWNUM = 1;
