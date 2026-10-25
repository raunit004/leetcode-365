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
 * Day 067: Path Sum
 * LeetCode #112: https://leetcode.com/problems/path-sum/
 */
public class Solution {

    /**
     * Determines if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
     * Recursively subtracts the current node value from targetSum and validates at leaf nodes.
     *
     * Time Complexity: O(N) where N is the total number of nodes in the binary tree
     * Space Complexity: O(H) auxiliary recursion stack space, where H is the height of the tree
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val) ||
               hasPathSum(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22 -> Expected: true
        TreeNode leaf7 = new TreeNode(7);
        TreeNode leaf2 = new TreeNode(2);
        TreeNode leaf1 = new TreeNode(1);
        TreeNode node11 = new TreeNode(11, leaf7, leaf2);
        TreeNode node4Right = new TreeNode(4, null, leaf1);
        TreeNode node13 = new TreeNode(13);
        TreeNode node4Left = new TreeNode(4, node11, null);
        TreeNode node8 = new TreeNode(8, node13, node4Right);
        TreeNode root1 = new TreeNode(5, node4Left, node8);

        System.out.println("Test 1 (Target 22, Expected true): " + solver.hasPathSum(root1, 22));

        // Smoke Test 2: root = [1, 2, 3], targetSum = 5 -> Expected: false
        TreeNode root2 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test 2 (Target 5, Expected false): " + solver.hasPathSum(root2, 5));

        // Smoke Test 3: root = [], targetSum = 0 -> Expected: false
        System.out.println("Test 3 (Target 0, Expected false): " + solver.hasPathSum(null, 0));
    }
}