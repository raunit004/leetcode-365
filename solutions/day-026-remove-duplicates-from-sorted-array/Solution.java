import java.util.Arrays;

/**
 * Day 026: Remove Duplicates from Sorted Array
 * LeetCode #26: https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
public class Solution {
    /**
     * Removes duplicates from a sorted array in place using a two-pointer approach.
     * Retains unique elements in their original relative sorted order.
     *
     * @param nums Sorted integer array
     * @return Number of unique elements k
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int write = 1;
        for (int read = 1; read < nums.length; read++) {
            if (nums[read] != nums[read - 1]) {
                nums[write] = nums[read];
                write++;
            }
        }

        return write;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int[] test1 = {1, 1, 2};
        int k1 = sol.removeDuplicates(test1);
        System.out.println("Output k: " + k1 + " | Array prefix: " + Arrays.toString(Arrays.copyOf(test1, k1)) + " | Expected: k = 2, [1, 2]");

        int[] test2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = sol.removeDuplicates(test2);
        System.out.println("Output k: " + k2 + " | Array prefix: " + Arrays.toString(Arrays.copyOf(test2, k2)) + " | Expected: k = 5, [0, 1, 2, 3, 4]");

        int[] test3 = {1};
        int k3 = sol.removeDuplicates(test3);
        System.out.println("Output k: " + k3 + " | Array prefix: " + Arrays.toString(Arrays.copyOf(test3, k3)) + " | Expected: k = 1, [1]");
    }
}