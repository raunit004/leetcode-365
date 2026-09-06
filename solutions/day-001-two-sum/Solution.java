import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Day 001: Two Sum
 * LeetCode #1: https://leetcode.com/problems/two-sum/
 */
public class Solution {
    /**
     * Finds indices of the two numbers such that they add up to target.
     *
     * @param nums   Array of integers
     * @param target Integer target sum
     * @return Indices of the two numbers that add up to target
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Test: nums = [2, 7, 11, 15], target = 9
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] result = sol.twoSum(nums, target);

        System.out.println("Output: " + Arrays.toString(result) + " | Expected: [0, 1]");
    }
}