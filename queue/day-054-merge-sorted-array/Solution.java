import java.util.Arrays;

/**
 * Day 054: Merge Sorted Array
 * LeetCode #88: https://leetcode.com/problems/merge-sorted-array/
 */
public class Solution {

    /**
     * Merges nums2 into nums1 as one sorted array in non-decreasing order.
     * Uses a three-pointer approach starting from the back to merge in-place
     * without overwriting existing unmerged elements in nums1.
     *
     * Time Complexity: O(M + N)
     * Space Complexity: O(1) auxiliary space
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p--] = nums1[p1--];
            } else {
                nums1[p--] = nums2[p2--];
            }
        }
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        int[] nums1_a = {1, 2, 3, 0, 0, 0};
        int[] nums2_a = {2, 5, 6};
        solver.merge(nums1_a, 3, nums2_a, 3);
        System.out.println("Test 1 (Expected [1, 2, 2, 3, 5, 6]): " + Arrays.toString(nums1_a));

        // Smoke Test 2: nums1 = [1], m = 1, nums2 = [], n = 0
        int[] nums1_b = {1};
        int[] nums2_b = {};
        solver.merge(nums1_b, 1, nums2_b, 0);
        System.out.println("Test 2 (Expected [1]): " + Arrays.toString(nums1_b));

        // Smoke Test 3: nums1 = [0], m = 0, nums2 = [1], n = 1
        int[] nums1_c = {0};
        int[] nums2_c = {1};
        solver.merge(nums1_c, 0, nums2_c, 1);
        System.out.println("Test 3 (Expected [1]): " + Arrays.toString(nums1_c));
    }
}