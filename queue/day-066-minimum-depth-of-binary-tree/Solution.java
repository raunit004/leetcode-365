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
 * Day 066: Minimum Depth of Binary Tree
 * LeetCode #111: https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class Solution {

    /**
     * Finds the minimum depth of a binary tree.
     * Evaluates the shortest path from the root down to the nearest leaf node.
     * Ensures paths that terminate prematurely due to a single missing child
     * are not mistaken for leaf nodes.
     *
     * Time Complexity: O(N) where N is the total number of nodes in the binary tree
     * Space Complexity: O(H) auxiliary recursion stack space, where H is the height of the tree
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null) {
            return 1 + minDepth(root.right);
        }

        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: root = [3, 9, 20, null, null, 15, 7] -> Expected: 2
        TreeNode root1 = new TreeNode(3,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Test 1 (Expected 2): " + solver.minDepth(root1));

        // Smoke Test 2: Skewed tree [2, null, 3, null, 4, null, 5, null, 6] -> Expected: 5
        TreeNode root2 = new TreeNode(2, null,
            new TreeNode(3, null,
                new TreeNode(4, null,
                    new TreeNode(5, null,
                        new TreeNode(6)
                    )
                )
            )
        );
        System.out.println("Test 2 (Expected 5): " + solver.minDepth(root2));

        // Smoke Test 3: root = [] -> Expected: 0
        System.out.println("Test 3 (Expected 0): " + solver.minDepth(null));
    }
}