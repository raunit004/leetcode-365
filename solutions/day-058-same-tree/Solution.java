/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * Day 058: Same Tree
 * LeetCode #100: https://leetcode.com/problems/same-tree/
 */
public class Solution {

    /**
     * Determines if two binary trees are structurally identical and have equivalent node values.
     * Recursively traverses both trees synchronously in pre-order fashion.
     *
     * Time Complexity: O(min(N, M)) where N and M are the number of nodes in trees p and q
     * Space Complexity: O(min(H_p, H_q)) auxiliary recursion stack space
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: p = [1, 2, 3], q = [1, 2, 3] -> Expected true
        TreeNode p1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test 1 (Expected true): " + solver.isSameTree(p1, q1));

        // Smoke Test 2: p = [1, 2], q = [1, null, 2] -> Expected false
        TreeNode p2 = new TreeNode(1, new TreeNode(2), null);
        TreeNode q2 = new TreeNode(1, null, new TreeNode(2));
        System.out.println("Test 2 (Expected false): " + solver.isSameTree(p2, q2));

        // Smoke Test 3: p = [1, 2, 1], q = [1, 1, 2] -> Expected false
        TreeNode p3 = new TreeNode(1, new TreeNode(2), new TreeNode(1));
        TreeNode q3 = new TreeNode(1, new TreeNode(1), new TreeNode(2));
        System.out.println("Test 3 (Expected false): " + solver.isSameTree(p3, q3));
    }
}