/**
 * Day 038: Search in Rotated Sorted Array
 * LeetCode #33: https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
public class Solution {

    /**
     * Searches for a target value within an ascending array rotated at an unknown pivot.
     * Divides the search space in half each iteration by identifying the sorted subarray.
     *
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Determine if the left half is normally ordered
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // Otherwise, the right half must be normally ordered
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] test1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 1 (Target 0, Expected 4): " + solver.search(test1, 0));

        int[] test2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test 2 (Target 3, Expected -1): " + solver.search(test2, 3));

        int[] test3 = {1};
        System.out.println("Test 3 (Target 0, Expected -1): " + solver.search(test3, 0));
    }
}