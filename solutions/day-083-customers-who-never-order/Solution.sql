-- Day 083: Customers Who Never Order
-- LeetCode #183: https://leetcode.com/problems/customers-who-never-order/
-- Language: SQL (Compatible with Oracle, MySQL, PostgreSQL, MS SQL Server)

/*
 * Left join Customers with Orders on c.id = o.customerId.
 * Rows where Orders.id IS NULL represent customers who have never placed an order.
 */
SELECT 
    c.name AS Customers
FROM 
    Customers c
LEFT JOIN 
    Orders o ON c.id = o.customerId
WHERE 
    o.id IS NULL;