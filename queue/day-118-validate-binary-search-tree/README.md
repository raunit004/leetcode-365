# Day 118: Validate Binary Search Tree

- **Platform:** LeetCode #98
- **Difficulty:** Medium
- **Topic:** Tree, Depth-First Search, Binary Search Tree, Binary Tree
- **Problem Link:** [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(H)$

---

### 1. Problem Overview
Determine if a binary tree is a valid Binary Search Tree (BST).

---

### 2. Intuition & Approach
Top-down DFS propagating strict `(min, max)` bounds widened to `long` (`Long.MIN_VALUE`, `Long.MAX_VALUE`).

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(H)$

---

### 4. Edge Cases & Key Takeaways
Using `long` bounds prevents collisions with `Integer.MIN_VALUE` and `Integer.MAX_VALUE`.
