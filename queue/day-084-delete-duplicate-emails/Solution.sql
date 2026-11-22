-- Day 084: Delete Duplicate Emails
-- LeetCode #196: https://leetcode.com/problems/delete-duplicate-emails/

DELETE FROM Person
WHERE id NOT IN (
    SELECT MIN(id)
    FROM Person
    GROUP BY email
);
