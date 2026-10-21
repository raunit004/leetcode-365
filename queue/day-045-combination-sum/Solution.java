import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Day 045: Combination Sum
 * LeetCode #39: https://leetcode.com/problems/combination-sum/
 */
public class Solution {

    /**
     * Finds all unique combinations in candidates where numbers sum to target.
     * Sorts the array initially to enable early pruning when candidates exceed the remainder.
     *
     * Time Complexity: O(N^(T / M)) where N is candidates.length, T is target, and M is min(candidates)
     * Space Complexity: O(T / M) auxiliary recursion stack depth
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            // Early pruning: since candidates are sorted, larger elements cannot fit
            if (candidates[i] > remain) {
                break;
            }

            current.add(candidates[i]);
            // Re-pass index i allowing candidate reuse
            backtrack(candidates, remain - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] candidates1 = {2, 3, 6, 7};
        System.out.println("Test 1 (Target 7, Expected [[2, 2, 3], [7]]): " + solver.combinationSum(candidates1, 7));

        int[] candidates2 = {2, 3, 5};
        System.out.println("Test 2 (Target 8, Expected [[2, 2, 2, 2], [2, 3, 3], [3, 5]]): " + solver.combinationSum(candidates2, 8));

        int[] candidates3 = {2};
        System.out.println("Test 3 (Target 1, Expected []): " + solver.combinationSum(candidates3, 1));
    }
}