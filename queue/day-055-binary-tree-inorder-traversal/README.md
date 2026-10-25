# Day 055: Binary Tree Inorder Traversal

- **Platform:** LeetCode #94[cite: 19]
- **Difficulty:** Easy[cite: 19]
- **Topic:** Tree, Depth-First Search, Binary Tree
- **Problem Link:** [Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)
- **Language:** Java[cite: 19]
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(H)$ auxiliary recursion stack space, where $H$ is the tree height ($O(N)$ worst-case)

---

### 1. Problem Overview
Given the `root` of a binary tree, return the **inorder traversal** of its nodes' values[cite: 19].

---

### 2. Intuition & Approach

#### Depth-First Recursive Inorder (Left $\rightarrow$ Root $\rightarrow$ Right):
An inorder traversal visits the left subtree, processes the current root node, and then visits the right subtree:

1. **Base Case:**
   - If the current node is `null`, terminate the branch and return[cite: 19].
2. **Left Subtree Exploration:**
   - Recurse on `node.left` to fully process all nodes in the left subtree first[cite: 19].
3. **Current Node Ingestion:**
   - Append `node.val` to the `result` collector list[cite: 19].
4. **Right Subtree Exploration:**
   - Recurse on `node.right` to process the remaining right subtree[cite: 19].

---

### 3. Execution Trace

#### Sample Input: `root = [1, null, 2, 3]`[cite: 19]

Tree Topology:
```text
  1
   \
    2
   /
  3
```

| Step | Current Subtree / Call | Action Taken | State of `result` |
| :---: | :---: | :---: | :---: |
| 1 | `inorder(Node 1)`[cite: 19] | Calls `inorder(Node 1.left)`[cite: 19] | `[]`[cite: 19] |
| 2 | `inorder(null)` | Returns immediately (`node == null`)[cite: 19] | `[]`[cite: 19] |
| 3 | `inorder(Node 1)`[cite: 19] | Appends `1`, calls `inorder(Node 1.right)`[cite: 19] | `[1]`[cite: 19] |
| 4 | `inorder(Node 2)`[cite: 19] | Calls `inorder(Node 2.left)`[cite: 19] | `[1]`[cite: 19] |
| 5 | `inorder(Node 3)`[cite: 19] | Calls `inorder(Node 3.left)` (`null`), appends `3`[cite: 19] | `[1, 3]`[cite: 19] |
| 6 | `inorder(Node 3)`[cite: 19] | Calls `inorder(Node 3.right)` (`null`), returns[cite: 19] | `[1, 3]`[cite: 19] |
| 7 | `inorder(Node 2)`[cite: 19] | Appends `2`, calls `inorder(Node 2.right)` (`null`)[cite: 19] | `[1, 3, 2]`[cite: 19] |

- Final output: **`[1, 3, 2]`**[cite: 19]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Every node in the binary tree is visited exactly once, performing $O(1)$ constant operations per visit.
- **Space Complexity:** $O(H)$ auxiliary space
  - The recursion stack consumes memory proportional to the tree height $H$. For a balanced binary tree, $H = O(\log N)$; in the worst case (a degenerate skewed tree), $H = O(N)$.

---

### 5. Edge Cases & Key Takeaways

- **Empty Tree (`root == null`):** Guarded at the base step[cite: 19], instantly returning an empty list without throwing a `NullPointerException`.
- **Single Node Tree:** Directly appends the root value and returns `[val]`.
- **Binary Search Tree Property:** When applied to a Binary Search Tree (BST), inorder traversal produces node values strictly in ascending sorted order.