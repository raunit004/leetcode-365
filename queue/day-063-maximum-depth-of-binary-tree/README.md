# Day 063: Maximum Depth of Binary Tree

- **Platform:** LeetCode #104[cite: 23]
- **Difficulty:** Easy[cite: 23]
- **Topic:** Tree, Depth-First Search, Breadth-First Search, Binary Tree
- **Problem Link:** [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
- **Language:** Java[cite: 23]
- **Time Complexity:** $O(N)$ where $N$ is the total number of nodes in the binary tree
- **Space Complexity:** $O(H)$ auxiliary space for call stack frames, where $H$ is the height of the tree ($O(N)$ worst-case)

---

### 1. Problem Overview
Given the `root` of a binary tree, return its **maximum depth**[cite: 23].

A binary tree's **maximum depth** is defined as the number of nodes along the longest path from the root node down to the farthest leaf node[cite: 23].

---

### 2. Intuition & Approach

#### Bottom-Up Recursive DFS (Post-Order Traversal):
The depth of any binary tree rooted at node $u$ can be framed in terms of the depths of its subtrees[cite: 23]:
$$\text{depth}(u) = 1 + \max(\text{depth}(u.\text{left}), \text{depth}(u.\text{right}))$$
[cite: 23]

1. **Base Case:**
   - If the current node is `null`, its depth contribution is `0`[cite: 23].
2. **Post-Order Subtree Evaluation:**
   - Recursively evaluate the maximum depth of the left subtree: `left = maxDepth(root.left)`[cite: 23].
   - Recursively evaluate the maximum depth of the right subtree: `right = maxDepth(root.right)`[cite: 23].
3. **Aggregate Current Node:**
   - Take the maximum of both child heights and add $1$ (for the current node itself): `1 + Math.max(left, right)`[cite: 23].

---

### 3. Execution Trace

#### Sample Input: `root = [3, 9, 20, null, null, 15, 7]`[cite: 23]

Tree Topology:
```text
      3
     / \
    9   20
       /  \
      15   7
```[cite: 23]

| Call / Subtree | Left Depth (`left`)[cite: 23] | Right Depth (`right`)[cite: 23] | Returned Value (`1 + max(left, right)`)[cite: 23] |
| :---: | :---: | :---: | :---: |
| `Node(15)`[cite: 23] | `0` (`null`)[cite: 23] | `0` (`null`)[cite: 23] | $1 + \max(0, 0) = 1$[cite: 23] |
| `Node(7)`[cite: 23] | `0` (`null`)[cite: 23] | `0` (`null`)[cite: 23] | $1 + \max(0, 0) = 1$[cite: 23] |
| `Node(20)`[cite: 23] | `1` (from Node 15)[cite: 23] | `1` (from Node 7)[cite: 23] | $1 + \max(1, 1) = 2$[cite: 23] |
| `Node(9)`[cite: 23] | `0` (`null`)[cite: 23] | `0` (`null`)[cite: 23] | $1 + \max(0, 0) = 1$[cite: 23] |
| `Node(3)` (Root)[cite: 23] | `1` (from Node 9)[cite: 23] | `2` (from Node 20)[cite: 23] | $1 + \max(1, 2) =$ **`3`**[cite: 23] |

**Output:** `3`[cite: 23]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Every node in the binary tree is visited exactly once, performing constant $O(1)$ operations per node[cite: 23].
- **Space Complexity:** $O(H)$ auxiliary space
  - The recursion stack consumes memory proportional to the height $H$ of the tree.
  - In a balanced tree, $H = O(\log N)$.
  - In a degenerate/skewed tree (e.g., linked-list-like hierarchy), $H = O(N)$.

---

### 5. Edge Cases & Key Takeaways

- **Empty Tree (`root == null`):** Guarded immediately by `if (root == null) return 0;`[cite: 23], returning `0` in $O(1)$ time without exceptions.
- **Single Node Tree:** Returns $1 + \max(0, 0) = 1$, correctly measuring a path consisting of solely the root node.
- **Asymmetric / Skewed Trees:** The `Math.max()` calculation ensures the longest branch dictates the overall tree depth regardless of imbalance across left and right sides[cite: 23].