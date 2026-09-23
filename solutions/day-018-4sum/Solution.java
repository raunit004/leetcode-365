import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Day 018: 4Sum
 * LeetCode #18: https://leetcode.com/problems/4sum/
 */
public class Solution {
    /**
     * Finds all unique quadruplets in nums that sum up to target.
     * Uses sorting, two outer anchor loops, and a two-pointer inward scan with 64-bit overflow protection.
     *
     * @param nums   The input integer array
     * @param target The target sum
     * @return List of unique integer quadruplets
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // Skip duplicates for the first element
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates for the second element
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // Cast to long to prevent 32-bit signed integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicate values for the third and fourth elements
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int[] test1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;

        int[] test2 = {2, 2, 2, 2, 2};
        int target2 = 8;

        int[] test3 = {1000000000, 1000000000, 1000000000, 1000000000};
        int target3 = -294967296; // Overflow test case

        System.out.println("Input: nums = [1, 0, -1, 0, -2, 2], target = 0 | Output: " + sol.fourSum(test1, target1) + " | Expected: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]");
        System.out.println("Input: nums = [2, 2, 2, 2, 2], target = 8       | Output: " + sol.fourSum(test2, target2) + " | Expected: [[2, 2, 2, 2]]");
        System.out.println("Input: nums = [10^9, ...], target = -294967296   | Output: " + sol.fourSum(test3, target3) + " | Expected: []");
    }
}