import java.util.ArrayList;
import java.util.List;

/**
 * Day 057: Permutations
 * LeetCode #46: https://leetcode.com/problems/permutations/
 */
public class Solution {

    /**
     * Generates all possible permutations of an array of distinct integers.
     * Employs backtracking with a boolean visited table to assemble permutations depth-first.
     *
     * Time Complexity: O(N * N!) where N is nums.length
     * Space Complexity: O(N) auxiliary space for the recursion stack and boolean tracking array
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), new boolean[nums.length], result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(nums[i]);
                backtrack(nums, current, used, result);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] test1 = {1, 2, 3};
        System.out.println("Test 1 (Expected 6 permutations): " + solver.permute(test1));

        int[] test2 = {0, 1};
        System.out.println("Test 2 (Expected [[0, 1], [1, 0]]): " + solver.permute(test2));

        int[] test3 = {1};
        System.out.println("Test 3 (Expected [[1]]): " + solver.permute(test3));
    }
}