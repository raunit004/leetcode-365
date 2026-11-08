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
 * Day 063: Maximum Depth of Binary Tree
 * LeetCode #104: https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
public class Solution {

    /**
     * Computes the maximum depth of a binary tree.
     * Uses post-order depth-first traversal to compute the depth of left and right
     * subtrees recursively, returning 1 plus the larger subtree depth.
     *
     * Time Complexity: O(N) where N is the total number of nodes in the binary tree
     * Space Complexity: O(H) auxiliary recursion stack space, where H is the height of the tree
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: root = [3, 9, 20, null, null, 15, 7] -> Expected: 3
        TreeNode root1 = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Test 1 (Expected 3): " + solver.maxDepth(root1));

        // Smoke Test 2: root = [1, null, 2] -> Expected: 2
        TreeNode root2 = new TreeNode(1, null, new TreeNode(2));
        System.out.println("Test 2 (Expected 2): " + solver.maxDepth(root2));

        // Smoke Test 3: root = [] -> Expected: 0
        System.out.println("Test 3 (Expected 0): " + solver.maxDepth(null));
    }
}