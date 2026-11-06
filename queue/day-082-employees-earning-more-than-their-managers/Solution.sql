-- Day 082: Employees Earning More Than Their Managers
-- LeetCode #181: https://leetcode.com/problems/employees-earning-more-than-their-managers/
-- Language: SQL (Compatible with Oracle, MySQL, PostgreSQL, MS SQL Server)

/*
 * Self-join the Employee table on employee's managerId = manager's id.
 * Filters rows where the employee's salary strictly exceeds the manager's salary.
 */
SELECT 
    e.name AS Employee
FROM 
    Employee e
JOIN 
    Employee m ON e.managerId = m.id
WHERE 
    e.salary > m.salary;