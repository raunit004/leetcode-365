import java.util.Arrays;

/**
 * Day 031: Next Permutation
 * LeetCode #31: https://leetcode.com/problems/next-permutation/
 */
public class Solution {
    /**
     * Rearranges numbers into the lexicographically next greater permutation.
     * If no greater permutation exists, rearranges into ascending order.
     * Operates completely in place with O(1) auxiliary memory.
     *
     * @param nums Array of integers
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i = nums.length - 2;
        // Step 1: Find the first decreasing element from the right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If a pivot exists, find the rightmost successor larger than nums[i]
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: Reverse the suffix starting at i + 1 to make it non-decreasing
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int[] t1 = {1, 2, 3};
        sol.nextPermutation(t1);
        System.out.println("Output: " + Arrays.toString(t1) + " | Expected: [1, 3, 2]");

        int[] t2 = {3, 2, 1};
        sol.nextPermutation(t2);
        System.out.println("Output: " + Arrays.toString(t2) + " | Expected: [1, 2, 3]");

        int[] t3 = {1, 1, 5};
        sol.nextPermutation(t3);
        System.out.println("Output: " + Arrays.toString(t3) + " | Expected: [1, 5, 1]");

        int[] t4 = {1, 5, 8, 4, 7, 6, 5, 3, 1};
        sol.nextPermutation(t4);
        System.out.println("Output: " + Arrays.toString(t4) + " | Expected: [1, 5, 8, 5, 1, 3, 4, 6, 7]");
    }
}