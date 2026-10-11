-- Day 035: Rising Temperature
-- LeetCode #197: https://leetcode.com/problems/rising-temperature/

/*
  Query to find all dates' id with higher temperatures compared to yesterday.
  Performs a self-join between w1 (today) and w2 (yesterday) where
  w1.recordDate = w2.recordDate + 1, filtering for w1.temperature > w2.temperature.
*/
SELECT 
    w1.id
FROM Weather w1
JOIN Weather w2
    ON w1.recordDate = w2.recordDate + 1
WHERE w1.temperature > w2.temperature;