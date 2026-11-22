# Day 119: Recover Binary Search Tree

- **Platform:** LeetCode #99
- **Difficulty:** Medium
- **Topic:** Tree, Depth-First Search, Binary Search Tree, Binary Tree
- **Problem Link:** [Recover Binary Search Tree](https://leetcode.com/problems/recover-binary-search-tree/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Recover a BST where the values of exactly two nodes were swapped by mistake without changing its structure.

---

### 2. Intuition & Approach
Morris inorder traversal in $O(1)$ space to detect the 1 or 2 inorder inversions (`prev.val > curr.val`), then swap `first.val` and `second.val`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Handles both adjacent and non-adjacent swapped nodes; restores all temporary Morris threads.
