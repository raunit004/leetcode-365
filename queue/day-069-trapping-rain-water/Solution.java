/**
 * Day 069: Trapping Rain Water
 * LeetCode #42: https://leetcode.com/problems/trapping-rain-water/
 */
public class Solution {

    /**
     * Computes the total units of rain water trapped between bars after raining.
     * Uses a two-pointer technique converging inward from the boundaries while
     * maintaining the maximum height observed from each side.
     *
     * Time Complexity: O(N) where N is height.length
     * Space Complexity: O(1) auxiliary space
     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int trappedWater = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    trappedWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    trappedWater += rightMax - height[right];
                }
                right--;
            }
        }

        return trappedWater;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1] -> Expected: 6
        int[] test1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Test 1 (Expected 6): " + solver.trap(test1));

        // Smoke Test 2: height = [4, 2, 0, 3, 2, 5] -> Expected: 9
        int[] test2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Test 2 (Expected 9): " + solver.trap(test2));

        // Smoke Test 3: Flat or single elevation -> Expected: 0
        int[] test3 = {3};
        System.out.println("Test 3 (Expected 0): " + solver.trap(test3));
    }
}