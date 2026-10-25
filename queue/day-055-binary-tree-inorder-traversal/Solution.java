import java.util.ArrayList;
import java.util.List;

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
 * Day 055: Binary Tree Inorder Traversal
 * LeetCode #94: https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
public class Solution {

    /**
     * Returns the inorder traversal of a binary tree's nodes' values.
     * Recursively traverses nodes following the Left -> Root -> Right sequence.
     *
     * Time Complexity: O(N) where N is the number of nodes in the binary tree
     * Space Complexity: O(H) auxiliary stack space, where H is the height of the tree
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: root = [1, null, 2, 3] -> Expected: [1, 3, 2]
        TreeNode root1 = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        System.out.println("Test 1 (Expected [1, 3, 2]): " + solver.inorderTraversal(root1));

        // Smoke Test 2: root = [] -> Expected: []
        System.out.println("Test 2 (Expected []): " + solver.inorderTraversal(null));

        // Smoke Test 3: root = [1] -> Expected: [1]
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test 3 (Expected [1]): " + solver.inorderTraversal(root3));
    }
}