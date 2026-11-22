import java.util.*;

/**
 * Day 115: Unique Binary Search Trees II
 * LeetCode #95: https://leetcode.com/problems/unique-binary-search-trees-ii/
 */
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left = left; this.right = right; }
}

public class Solution {
    private List<TreeNode>[][] memo;

    @SuppressWarnings("unchecked")
    public List<TreeNode> generateTrees(int n) {
        memo = new List[n + 2][n + 2];
        return build(1, n);
    }

    private List<TreeNode> build(int start, int end) {
        if (start > end) return Collections.singletonList(null);
        if (memo[start][end] != null) return memo[start][end];
        List<TreeNode> trees = new ArrayList<>();
        for (int rootVal = start; rootVal <= end; rootVal++) {
            for (TreeNode left : build(start, rootVal - 1)) {
                for (TreeNode right : build(rootVal + 1, end)) {
                    trees.add(new TreeNode(rootVal, left, right));
                }
            }
        }
        return memo[start][end] = trees;
    }
}
