/**
 * Day 080: Unique Paths II
 * LeetCode #63: https://leetcode.com/problems/unique-paths-ii/
 */
public class Solution {

    /**
     * Calculates the number of possible unique paths from top-left to bottom-right
     * avoiding obstacles marked as 1 in the grid.
     * Uses 1D dynamic programming where an obstacle resets the path count at that
     * column to 0.
     *
     * Time Complexity: O(m * n) where m is rows and n is columns
     * Space Complexity: O(n) auxiliary space for the 1D DP array
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (obstacleGrid[r][c] == 1) {
                    dp[c] = 0;
                } else if (c > 0) {
                    dp[c] += dp[c - 1];
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: obstacle in center [[0,0,0],[0,1,0],[0,0,0]] -> Expected: 2
        int[][] grid1 = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println("Test 1 (Expected 2): " + solver.uniquePathsWithObstacles(grid1));

        // Smoke Test 2: obstacle in top-right [[0,1],[0,0]] -> Expected: 1
        int[][] grid2 = {
            {0, 1},
            {0, 0}
        };
        System.out.println("Test 2 (Expected 1): " + solver.uniquePathsWithObstacles(grid2));

        // Smoke Test 3: obstacle at starting cell [[1]] -> Expected: 0
        int[][] grid3 = {{1}};
        System.out.println("Test 3 (Expected 0): " + solver.uniquePathsWithObstacles(grid3));

        // Smoke Test 4: obstacle blocking end cell [[0,0],[0,1]] -> Expected: 0
        int[][] grid4 = {
            {0, 0},
            {0, 1}
        };
        System.out.println("Test 4 (Expected 0): " + solver.uniquePathsWithObstacles(grid4));
    }
}