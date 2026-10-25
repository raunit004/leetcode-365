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
 * Day 064: Convert Sorted Array to Binary Search Tree
 * LeetCode #108: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class Solution {

    /**
     * Converts an array sorted in ascending order into a height-balanced BST.
     * Uses divide-and-conquer to choose the middle element as root, recursively building
     * balanced left and right subtrees from the divided partitions.
     *
     * Time Complexity: O(N) where N is nums.length
     * Space Complexity: O(log N) auxiliary recursion stack space
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: nums = [-10, -3, 0, 5, 9]
        int[] nums1 = {-10, -3, 0, 5, 9};
        TreeNode bst1 = solver.sortedArrayToBST(nums1);
        System.out.print("Test 1 Inorder Traversal (Expected [-10, -3, 0, 5, 9]): ");
        printInorder(bst1);
        System.out.println();

        // Smoke Test 2: nums = [1, 3]
        int[] nums2 = {1, 3};
        TreeNode bst2 = solver.sortedArrayToBST(nums2);
        System.out.print("Test 2 Inorder Traversal (Expected [1, 3]): ");
        printInorder(bst2);
        System.out.println();
    }

    private static void printInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        printInorder(node.left);
        System.out.print(node.val + " ");
        printInorder(node.right);
    }
}