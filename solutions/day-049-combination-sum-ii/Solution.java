import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Day 049: Combination Sum II
 * LeetCode #40: https://leetcode.com/problems/combination-sum-ii/
 */
public class Solution {

    /**
     * Finds all unique combinations in candidates where numbers sum to target.
     * Each number in candidates may only be used once per combination.
     * Uses sorting to prune branches early and bypass duplicate sibling choices.
     *
     * Time Complexity: O(2^N) where N is candidates.length
     * Space Complexity: O(N) auxiliary recursion stack depth
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> current, List<List<Integer>> result) {
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Early pruning: subsequent candidates will exceed the remainder
            if (candidates[i] > remain) {
                break;
            }

            // Skip duplicate sibling branches at the same decision level
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            current.add(candidates[i]);
            // Recurse with i + 1 to enforce single-use per element
            backtrack(candidates, remain - candidates[i], i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        System.out.println("Test 1 (Target 8): " + solver.combinationSum2(candidates1, 8));

        int[] candidates2 = {2, 5, 2, 1, 2};
        System.out.println("Test 2 (Target 5): " + solver.combinationSum2(candidates2, 5));
    }
}