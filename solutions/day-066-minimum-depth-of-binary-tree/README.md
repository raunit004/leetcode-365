# Day 066: Minimum Depth of Binary Tree

- **Platform:** LeetCode #111[cite: 21]
- **Difficulty:** Easy[cite: 21]
- **Topic:** Tree, Depth-First Search, Breadth-First Search, Binary Tree
- **Problem Link:** [Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)
- **Language:** Java[cite: 21]
- **Time Complexity:** $O(N)$ where $N$ is the number of nodes in the binary tree
- **Space Complexity:** $O(H)$ auxiliary call stack space, where $H$ is the tree height ($O(N)$ worst-case)

---

### 1. Problem Overview
Given a binary tree, find its **minimum depth**[cite: 21].

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node[cite: 21].

> **Note:** A leaf is a node with no children (`left == null && right == null`)[cite: 21].

---

### 2. Intuition & Approach

#### Handling Skewed Paths vs. True Leaves:
Unlike Maximum Depth (which indiscriminately computes $1 + \max(\text{left}, \text{right})$), Minimum Depth requires reaching an authentic **leaf node**[cite: 21]. If a node has only one child, the path cannot stop at the absent child because a `null` child is not a leaf node[cite: 21].

1. **Base Case (Empty Subtree):**
   - If `root == null`, return `0`[cite: 21].
2. **Missing Left Child:**
   - If `root.left == null`, the path must proceed down the right subtree[cite: 21]:
     $$\text{return } 1 + \text{minDepth}(\text{root.right})$$
[cite: 21]
3. **Missing Right Child:**
   - If `root.right == null`, the path must proceed down the left subtree[cite: 21]:
     $$\text{return } 1 + \text{minDepth}(\text{root.left})$$
[cite: 21]
4. **Two Children Present:**
   - When both children exist, take the minimum of both subtree depths plus $1$ for the current node[cite: 21]:
     $$\text{return } 1 + \min(\text{minDepth}(\text{root.left}), \text{minDepth}(\text{root.right}))$$
[cite: 21]

---

### 3. Execution Trace

#### Sample Input: `root = [3, 9, 20, null, null, 15, 7]`

Topology:
```text
      3
     / \
    9   20
       /  \
      15   7
```

| Step | Node Evaluated | Children State | Subtree Calculation | Result Returned |
| :---: | :---: | :---: | :---: | :---: |
| 1 | `Node(9)` | Both `null` | Evaluates leaf depth | $1 + \min(0, 0) = 1$[cite: 21] |
| 2 | `Node(15)` | Both `null` | Evaluates leaf depth | $1 + \min(0, 0) = 1$[cite: 21] |
| 3 | `Node(7)` | Both `null` | Evaluates leaf depth | $1 + \min(0, 0) = 1$[cite: 21] |
| 4 | `Node(20)` | Both exist | $1 + \min(\text{depth}(15), \text{depth}(7))$[cite: 21] | $1 + \min(1, 1) = 2$[cite: 21] |
| 5 | `Node(3)` | Both exist | $1 + \min(\text{depth}(9), \text{depth}(20))$[cite: 21] | $1 + \min(1, 2) =$ **`2`**[cite: 21] |

**Output:** `2` (Path: `3 -> 9`)

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - In the worst case (e.g., a completely balanced tree where the leaf is at the bottom, or a linear degenerate tree), every node is visited once.
- **Space Complexity:** $O(H)$ auxiliary space
  - The recursion stack consumes memory proportional to the tree height $H$. For balanced trees, $H = O(\log N)$; for skewed degenerate trees, $H = O(N)$.

---

### 5. Edge Cases & Key Takeaways

- **Empty Tree:** `root == null` returns `0` immediately on line 4[cite: 21].
- **Degenerate / Skewed Chain (e.g., `[1, 2, null, 3]`):** Without explicit checks for `root.left == null` or `root.right == null`[cite: 21], a naive `Math.min(minDepth(left), minDepth(right))` would choose the absent side (`0`) and incorrectly return `1` at every step. The branching checks guarantee the search continues until an actual leaf is reached[cite: 21].