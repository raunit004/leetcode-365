/**
 * Day 011: Container With Most Water
 * LeetCode #11: https://leetcode.com/problems/container-with-most-water/
 */
public class Solution {
    /**
     * Calculates the maximum amount of water a container can store
     * using an optimized two-pointer inward shrinkage approach.
     *
     * @param height Array representing line heights
     * @return Maximum water area possible
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int currentWater = (right - left) * h;
            if (currentWater > maxWater) {
                maxWater = currentWater;
            }

            // Skip all inner lines with height <= current bottleneck h
            while (left < right && height[left] <= h) {
                left++;
            }
            while (left < right && height[right] <= h) {
                right--;
            }
        }

        return maxWater;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int[] test1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] test2 = {1, 1};
        int[] test3 = {4, 3, 2, 1, 4};
        int[] test4 = {1, 2, 1};

        System.out.println("Output: " + sol.maxArea(test1) + " | Expected: 49");
        System.out.println("Output: " + sol.maxArea(test2) + " | Expected: 1");
        System.out.println("Output: " + sol.maxArea(test3) + " | Expected: 16");
        System.out.println("Output: " + sol.maxArea(test4) + " | Expected: 2");
    }
}