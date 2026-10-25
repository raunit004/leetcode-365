/**
 * Day 056: Jump Game II
 * LeetCode #45: https://leetcode.com/problems/jump-game-ii/
 */
public class Solution {

    /**
     * Calculates the minimum number of jumps to reach the last index.
     * Uses a greedy BFS-style interval strategy tracking the current jump range and farthest reachable index.
     *
     * Time Complexity: O(N) where N is nums.length
     * Space Complexity: O(1) auxiliary space
     */
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] test1 = {2, 3, 1, 1, 4};
        System.out.println("Test 1 (Expected 2): " + solver.jump(test1));

        int[] test2 = {2, 3, 0, 1, 4};
        System.out.println("Test 2 (Expected 2): " + solver.jump(test2));

        int[] test3 = {0};
        System.out.println("Test 3 (Expected 0): " + solver.jump(test3));

        int[] test4 = {1, 2, 3};
        System.out.println("Test 4 (Expected 2): " + solver.jump(test4));
    }
}