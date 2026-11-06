import java.util.Arrays;

/**
 * Day 079: Unique Paths
 * LeetCode #62: https://leetcode.com/problems/unique-paths/
 */
public class Solution {

    /**
     * Calculates the number of possible unique paths from the top-left corner
     * to the bottom-right corner of an m x n grid.
     * Uses space-optimized 1D dynamic programming where dp[c] represents the paths
     * to reach column c in the current row.
     *
     * Time Complexity: O(m * n)
     * Space Complexity: O(n) auxiliary space for the 1D DP table
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[c] += dp[c - 1];
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: m = 3, n = 7 -> Expected: 28
        System.out.println("Test 1 (m = 3, n = 7, Expected 28): " + solver.uniquePaths(3, 7));

        // Smoke Test 2: m = 3, n = 2 -> Expected: 3
        System.out.println("Test 2 (m = 3, n = 2, Expected 3): " + solver.uniquePaths(3, 2));

        // Smoke Test 3: m = 1, n = 1 -> Expected: 1
        System.out.println("Test 3 (m = 1, n = 1, Expected 1): " + solver.uniquePaths(1, 1));

        // Smoke Test 4: m = 3, n = 3 -> Expected: 6
        System.out.println("Test 4 (m = 3, n = 3, Expected 6): " + solver.uniquePaths(3, 3));
    }
}