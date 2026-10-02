import java.util.Arrays;

/**
 * Day 027: Remove Element
 * LeetCode #27: https://leetcode.com/problems/remove-element/
 */
public class Solution {
    /**
     * Removes all occurrences of val in nums in place using a two-pointer approach.
     * Retains all elements not equal to val in the first k positions of nums.
     *
     * @param nums Input integer array
     * @param val  Target value to remove
     * @return Number of elements k not equal to val
     */
    public int removeElement(int[] nums, int val) {
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != val) {
                nums[write] = nums[read];
                write++;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int[] test1 = {3, 2, 2, 3};
        int k1 = sol.removeElement(test1, 3);
        System.out.println("Output k: " + k1 + " | Array prefix: " + Arrays.toString(Arrays.copyOf(test1, k1)) + " | Expected: k = 2, [2, 2]");

        int[] test2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int k2 = sol.removeElement(test2, 2);
        System.out.println("Output k: " + k2 + " | Array prefix: " + Arrays.toString(Arrays.copyOf(test2, k2)) + " | Expected: k = 5, [0, 1, 3, 0, 4]");

        int[] test3 = {};
        int k3 = sol.removeElement(test3, 0);
        System.out.println("Output k: " + k3 + " | Array prefix: " + Arrays.toString(Arrays.copyOf(test3, k3)) + " | Expected: k = 0, []");
    }
}