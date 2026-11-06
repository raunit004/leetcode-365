/**
 * Day 081: Minimum Path Sum
 * LeetCode #64: https://leetcode.com/problems/minimum-path-sum/
 */
public class Solution {

    /**
     * Finds a path from the top-left corner to the bottom-right corner of an m x n grid
     * that minimizes the sum of all numbers along its path.
     * Uses space-optimized 1D dynamic programming where dp[c] maintains the minimum
     * path sum required to reach column c in the active row.
     *
     * Time Complexity: O(m * n) where m is row count and n is column count
     * Space Complexity: O(n) auxiliary space for the 1D DP table
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];

        // Initialize first row: only reachable by moving right
        for (int c = 1; c < n; c++) {
            dp[c] = dp[c - 1] + grid[0][c];
        }

        // Process remaining rows
        for (int r = 1; r < m; r++) {
            // First column: only reachable by moving down
            dp[0] += grid[r][0];
            for (int c = 1; c < n; c++) {
                dp[c] = grid[r][c] + Math.min(dp[c], dp[c - 1]);
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: grid = [[1,3,1],[1,5,1],[4,2,1]] -> Expected: 7 (Path: 1 -> 3 -> 1 -> 1 -> 1)
        int[][] grid1 = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        System.out.println("Test 1 (Expected 7): " + solver.minPathSum(grid1));

        // Smoke Test 2: grid = [[1,2,3],[4,5,6]] -> Expected: 12 (Path: 1 -> 2 -> 3 -> 6)
        int[][] grid2 = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println("Test 2 (Expected 12): " + solver.minPathSum(grid2));

        // Smoke Test 3: Single element grid [[5]] -> Expected: 5
        int[][] grid3 = {{5}};
        System.out.println("Test 3 (Expected 5): " + solver.minPathSum(grid3));
    }
}