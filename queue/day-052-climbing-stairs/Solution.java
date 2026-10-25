/**
 * Day 052: Climbing Stairs
 * LeetCode #70: https://leetcode.com/problems/climbing-stairs/
 */
public class Solution {

    /**
     * Calculates the number of distinct ways to climb to the top of a staircase with n steps.
     * Uses dynamic programming with state compression (Fibonacci sequence recurrence).
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1) auxiliary space
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int first = 1;
        int second = 2;

        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }

        return second;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        System.out.println("Test 1 (n = 2, Expected 2): " + solver.climbStairs(2));
        System.out.println("Test 2 (n = 3, Expected 3): " + solver.climbStairs(3));
        System.out.println("Test 3 (n = 1, Expected 1): " + solver.climbStairs(1));
        System.out.println("Test 4 (n = 5, Expected 8): " + solver.climbStairs(5));
    }
}