-- Day 037: Second Highest Salary
-- LeetCode #176: https://leetcode.com/problems/second-highest-salary/

/*
  Query to find the second highest distinct salary from the Employee table.
  A subquery retrieves the overall maximum salary, and the outer query takes
  the MAX of all remaining salaries strictly less than that maximum.
  If no second highest salary exists, the outer MAX aggregate naturally evaluates to NULL.
*/
SELECT 
    MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE salary < (
    SELECT MAX(salary)
    FROM Employee
);