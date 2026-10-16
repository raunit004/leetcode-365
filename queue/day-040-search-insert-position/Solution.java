/**
 * Day 040: Search Insert Position
 * LeetCode #35: https://leetcode.com/problems/search-insert-position/
 */
public class Solution {

    /**
     * Finds the index of target if present, or the index where it would be
     * inserted in order within a sorted array of distinct integers.
     *
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] nums = {1, 3, 5, 6};
        System.out.println("Test 1 (Target 5, Expected 2): " + solver.searchInsert(nums, 5));
        System.out.println("Test 2 (Target 2, Expected 1): " + solver.searchInsert(nums, 2));
        System.out.println("Test 3 (Target 7, Expected 4): " + solver.searchInsert(nums, 7));
        System.out.println("Test 4 (Target 0, Expected 0): " + solver.searchInsert(nums, 0));
    }
}