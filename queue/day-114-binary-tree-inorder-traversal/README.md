# Day 114: Binary Tree Inorder Traversal

- **Platform:** LeetCode #94
- **Difficulty:** Easy
- **Topic:** Tree, Depth-First Search, Binary Tree
- **Problem Link:** [Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(H)$

---

### 1. Problem Overview
Return the inorder traversal (`Left -> Root -> Right`) of a binary tree's node values.

---

### 2. Intuition & Approach
Recursive DFS visiting `node.left`, appending `node.val`, and visiting `node.right`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(H)$

---

### 4. Edge Cases & Key Takeaways
Empty tree (`root == null`) returns `[]`.
