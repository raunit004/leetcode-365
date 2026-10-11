-- Day 036: Game Play Analysis I
-- LeetCode #511: https://leetcode.com/problems/game-play-analysis-i/

/*
  Query to find the first login date for each player.
  Grouping by player_id partitions activity logs per player, and the MIN()
  aggregate function extracts the earliest event_date.
*/
SELECT 
    player_id,
    MIN(event_date) AS first_login
FROM Activity
GROUP BY player_id;