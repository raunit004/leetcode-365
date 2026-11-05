import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Day 060: Permutations II
 * LeetCode #47: https://leetcode.com/problems/permutations-ii/
 */
public class Solution {

    /**
     * Generates all unique permutations of a collection of numbers that may contain duplicates.
     * Sorts the array and skips duplicate elements at the same recursion level if their preceding
     * identical neighbor was not included in the active branch.
     *
     * Time Complexity: O(N * N!) where N is nums.length
     * Space Complexity: O(N) auxiliary space for the recursion stack and boolean tracking array
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, new ArrayList<>(), new boolean[nums.length], result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // Skip duplicate sibling branches: if the identical previous element was not used,
            // picking nums[i] now would create an identical permutation prefix.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, current, used, result);
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] test1 = {1, 1, 2};
        System.out.println("Test 1 (Expected 3 unique permutations): " + solver.permuteUnique(test1));

        int[] test2 = {1, 2, 3};
        System.out.println("Test 2 (Expected 6 unique permutations): " + solver.permuteUnique(test2));

        int[] test3 = {2, 2, 1, 1};
        System.out.println("Test 3 (Expected 6 unique permutations): " + solver.permuteUnique(test3));
    }
}