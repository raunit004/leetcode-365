import java.util.*;

/**
 * Day 099: Combinations
 * LeetCode #77: https://leetcode.com/problems/combinations/
 */
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int start, int n, int k, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }
        int limit = n - (k - curr.size()) + 1;
        for (int i = start; i <= limit; i++) {
            curr.add(i);
            backtrack(i + 1, n, k, curr, res);
            curr.remove(curr.size() - 1);
        }
    }
}
