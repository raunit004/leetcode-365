/**
 * Day 068: First Missing Positive
 * LeetCode #41: https://leetcode.com/problems/first-missing-positive/
 */
public class Solution {

    /**
     * Finds the smallest positive integer not present in an unsorted array.
     * Uses cycle sort (in-place bucket placement) to map each value x in the
     * range [1, n] to index x - 1, achieving linear time without auxiliary storage.
     *
     * Time Complexity: O(N) where N is nums.length
     * Space Complexity: O(1) auxiliary space
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] test1 = {1, 2, 0};
        System.out.println("Test 1 (Expected 3): " + solver.firstMissingPositive(test1));

        int[] test2 = {3, 4, -1, 1};
        System.out.println("Test 2 (Expected 2): " + solver.firstMissingPositive(test2));

        int[] test3 = {7, 8, 9, 11, 12};
        System.out.println("Test 3 (Expected 1): " + solver.firstMissingPositive(test3));

        int[] test4 = {1};
        System.out.println("Test 4 (Expected 2): " + solver.firstMissingPositive(test4));
    }
}