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
 * Day 065: Balanced Binary Tree
 * LeetCode #110: https://leetcode.com/problems/balanced-binary-tree/
 */
public class Solution {

    /**
     * Determines whether a binary tree is height-balanced.
     * Uses a bottom-up depth-first traversal with a -1 sentinel value to short-circuit
     * immediately once any subtree is found to be unbalanced.
     *
     * Time Complexity: O(N) where N is the number of nodes in the binary tree
     * Space Complexity: O(H) auxiliary recursion stack space, where H is the height of the tree
     */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: root = [3, 9, 20, null, null, 15, 7] -> Expected: true
        TreeNode test1 = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Test 1 (Expected true): " + solver.isBalanced(test1));

        // Smoke Test 2: root = [1, 2, 2, 3, 3, null, null, 4, 4] -> Expected: false
        TreeNode test2 = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(3, new TreeNode(4), new TreeNode(4)),
                new TreeNode(3)
            ),
            new TreeNode(2)
        );
        System.out.println("Test 2 (Expected false): " + solver.isBalanced(test2));

        // Smoke Test 3: root = [] -> Expected: true
        System.out.println("Test 3 (Expected true): " + solver.isBalanced(null));
    }
}