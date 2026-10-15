import java.util.Arrays;

/**
 * Day 039: Find First and Last Position of Element in Sorted Array
 * LeetCode #34: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class Solution {

    /**
     * Finds the starting and ending index of a target value within a sorted array.
     * Uses two distinct binary search passes to locate the lower and upper bounds.
     *
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        result[0] = findBound(nums, target, true);
        if (result[0] == -1) {
            return result;
        }

        result[1] = findBound(nums, target, false);
        return result;
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    // Search leftward for the starting position
                    right = mid - 1;
                } else {
                    // Search rightward for the ending position
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return bound;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] test1 = {5, 7, 7, 8, 8, 10};
        System.out.println("Test 1 (Target 8, Expected [3, 4]): " + Arrays.toString(solver.searchRange(test1, 8)));

        int[] test2 = {5, 7, 7, 8, 8, 10};
        System.out.println("Test 2 (Target 6, Expected [-1, -1]): " + Arrays.toString(solver.searchRange(test2, 6)));

        int[] test3 = {};
        System.out.println("Test 3 (Target 0, Expected [-1, -1]): " + Arrays.toString(solver.searchRange(test3, 0)));
    }
}