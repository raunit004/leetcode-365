-- Day 034: Duplicate Emails
-- LeetCode #182: https://leetcode.com/problems/duplicate-emails/

/*
  Query to find all emails that appear more than once in the Person table.
  Grouping by email partitions rows into buckets, and the HAVING clause
  filters buckets whose aggregate count strictly exceeds 1.
*/
SELECT email
FROM Person
GROUP BY email
HAVING COUNT(email) > 1;