/**
 * Day 072: Maximum Subarray
 * LeetCode #53: https://leetcode.com/problems/maximum-subarray/
 */
public class Solution {

    /**
     * Finds the contiguous subarray with the largest sum and returns its sum.
     * Implements Kadane's Algorithm to greedily decide at each element whether
     * to extend the existing contiguous sum or start a fresh subarray.
     *
     * Time Complexity: O(N) where N is nums.length
     * Space Complexity: O(1) auxiliary space
     */
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Test 1 (Expected 6): " + solver.maxSubArray(test1));

        int[] test2 = {1};
        System.out.println("Test 2 (Expected 1): " + solver.maxSubArray(test2));

        int[] test3 = {5, 4, -1, 7, 8};
        System.out.println("Test 3 (Expected 23): " + solver.maxSubArray(test3));

        int[] test4 = {-5, -2, -8, -1};
        System.out.println("Test 4 (All negative, Expected -1): " + solver.maxSubArray(test4));
    }
}