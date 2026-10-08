-- Day 033: Combine Two Tables
-- LeetCode #175: https://leetcode.com/problems/combine-two-tables/

/*
  Query to report firstName, lastName, city, and state for each person.
  A LEFT JOIN ensures all persons are retained even if no corresponding
  address record exists.
*/
SELECT 
    p.firstName, 
    p.lastName, 
    a.city, 
    a.state 
FROM Person p 
LEFT JOIN Address a 
    ON p.personId = a.personId;