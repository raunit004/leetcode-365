import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Day 015: 3Sum
 * LeetCode #15: https://leetcode.com/problems/3sum/
 */
public class Solution {
    /**
     * Finds all unique triplets in an array that sum to zero.
     * Uses sorting followed by a two-pointer inward squeeze with duplicate avoidance.
     *
     * @param nums The input integer array
     * @return List of unique zero-sum integer triplets
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            // Since the array is sorted, if the smallest element is > 0, three positive numbers cannot sum to 0
            if (nums[i] > 0) break;

            // Skip duplicate values for the first element of the triplet
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicate values for the second and third elements
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int[] test1 = {-1, 0, 1, 2, -1, -4};
        int[] test2 = {0, 1, 1};
        int[] test3 = {0, 0, 0};

        System.out.println("Input: [-1, 0, 1, 2, -1, -4] | Output: " + sol.threeSum(test1) + " | Expected: [[-1, -1, 2], [-1, 0, 1]]");
        System.out.println("Input: [0, 1, 1]              | Output: " + sol.threeSum(test2) + " | Expected: []");
        System.out.println("Input: [0, 0, 0]              | Output: " + sol.threeSum(test3) + " | Expected: [[0, 0, 0]]");
    }
}