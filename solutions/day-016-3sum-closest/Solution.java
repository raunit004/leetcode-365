import java.util.Arrays;

/**
 * Day 016: 3Sum Closest
 * LeetCode #16: https://leetcode.com/problems/3sum-closest/
 */
public class Solution {
    /**
     * Finds three integers in nums such that the sum is closest to target.
     * Uses sorting followed by a two-pointer inward scan.
     *
     * @param nums   The input integer array
     * @param target The target sum
     * @return The sum of the three integers closest to target
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                if (currentSum == target) {
                    return target;
                }

                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int[] test1 = {-1, 2, 1, -4};
        int target1 = 1;

        int[] test2 = {0, 0, 0};
        int target2 = 1;

        int[] test3 = {1, 1, 1, 0};
        int target3 = -100;

        System.out.println("Input: nums = [-1, 2, 1, -4], target = 1    | Output: " + sol.threeSumClosest(test1, target1) + " | Expected: 2");
        System.out.println("Input: nums = [0, 0, 0], target = 1         | Output: " + sol.threeSumClosest(test2, target2) + " | Expected: 0");
        System.out.println("Input: nums = [1, 1, 1, 0], target = -100   | Output: " + sol.threeSumClosest(test3, target3) + " | Expected: 2");
    }
}