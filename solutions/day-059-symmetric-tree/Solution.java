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
 * Day 059: Symmetric Tree
 * LeetCode #101: https://leetcode.com/problems/symmetric-tree/
 */
public class Solution {

    /**
     * Checks whether a binary tree is a mirror of itself (symmetric around its center).
     * Compares the left and right subtrees symmetrically in depth-first order.
     *
     * Time Complexity: O(N) where N is the total number of nodes in the tree
     * Space Complexity: O(H) auxiliary recursion stack space, where H is the height of the tree
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null || t1.val != t2.val) {
            return false;
        }
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: root = [1, 2, 2, 3, 4, 4, 3] -> Expected: true
        TreeNode test1 = new TreeNode(1,
            new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(2, new TreeNode(4), new TreeNode(3))
        );
        System.out.println("Test 1 (Expected true): " + solver.isSymmetric(test1));

        // Smoke Test 2: root = [1, 2, 2, null, 3, null, 3] -> Expected: false
        TreeNode test2 = new TreeNode(1,
            new TreeNode(2, null, new TreeNode(3)),
            new TreeNode(2, null, new TreeNode(3))
        );
        System.out.println("Test 2 (Expected false): " + solver.isSymmetric(test2));

        // Smoke Test 3: root = [1] -> Expected: true
        TreeNode test3 = new TreeNode(1);
        System.out.println("Test 3 (Expected true): " + solver.isSymmetric(test3));
    }
}