/**
 * Day 074: Jump Game
 * LeetCode #55: https://leetcode.com/problems/jump-game/
 */
public class Solution {

    /**
     * Determines whether you can reach the last index of the array.
     * Uses a greedy approach tracking the farthest reachable index so far.
     *
     * Time Complexity: O(N) where N is nums.length
     * Space Complexity: O(1) auxiliary space
     */
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= n - 1) {
                return true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] test1 = {2, 3, 1, 1, 4};
        System.out.println("Test 1 (Expected true): " + solver.canJump(test1));

        int[] test2 = {3, 2, 1, 0, 4};
        System.out.println("Test 2 (Expected false): " + solver.canJump(test2));

        int[] test3 = {0};
        System.out.println("Test 3 (Expected true): " + solver.canJump(test3));

        int[] test4 = {2, 0, 0};
        System.out.println("Test 4 (Expected true): " + solver.canJump(test4));
    }
}