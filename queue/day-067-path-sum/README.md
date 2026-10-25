# Day 067: Path Sum

- **Platform:** LeetCode #112[cite: 22]
- **Difficulty:** Easy[cite: 22]
- **Topic:** Tree, Depth-First Search, Breadth-First Search, Binary Tree
- **Problem Link:** [Path Sum](https://leetcode.com/problems/path-sum/)
- **Language:** Java[cite: 22]
- **Time Complexity:** $O(N)$ where $N$ is the number of nodes in the binary tree
- **Space Complexity:** $O(H)$ auxiliary call stack space, where $H$ is the tree height ($O(N)$ worst-case)

---

### 1. Problem Overview
Given the `root` of a binary tree and an integer `targetSum`, return `true` if the tree has a **root-to-leaf** path such that adding up all the values along the path equals `targetSum`[cite: 22].

A **leaf** is defined as a node with no children[cite: 22].

---

### 2. Intuition & Approach

#### Recursive Subtraction DFS:
Rather than tracking an accumulating prefix sum from the root down to each leaf, we can subtract the current node's value from `targetSum` at each recursion step[cite: 22]:

1. **Empty Tree / Subtree Base Case:**
   - If `root == null`, no path exists through this branch; return `false`[cite: 22].
2. **Leaf Node Verification:**
   - A node is a leaf if and only if `root.left == null && root.right == null`[cite: 22].
   - If reached, check if the remaining required sum exactly matches the leaf node's value: `return targetSum == root.val;`[cite: 22].
3. **Recursive Branch Search:**
   - If the current node is not a leaf, recurse down both the left and right subtrees with the reduced target `targetSum - root.val`[cite: 22].
   - If either child branch finds a valid root-to-leaf path (`||`), the function evaluates to `true`[cite: 22].

---

### 3. Execution Trace

#### Sample Input: `root = [5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1]`, `targetSum = 22`

Tracing the target path `5 -> 4 -> 11 -> 2`:

| Recursion Level | Current Node (`root.val`) | Incoming `targetSum` | Leaf Check (`left == null && right == null`) | Next `targetSum` (`targetSum - root.val`) | Result |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | `5` | 22 | False | $22 - 5 = 17$[cite: 22] | Recurse left (`Node 4`)[cite: 22] |
| 2 | `4` | 17 | False | $17 - 4 = 13$[cite: 22] | Recurse left (`Node 11`)[cite: 22] |
| 3 | `11` | 13 | False | $13 - 11 = 2$[cite: 22] | Recurse left (`7`), right (`2`)[cite: 22] |
| 4 (Left) | `7` | 2 | True[cite: 22] | Target check: $2 == 7$[cite: 22] | `false`[cite: 22] |
| 4 (Right) | `2` | 2 | True[cite: 22] | Target check: $2 == 2$[cite: 22] | **`true`**[cite: 22] |

- Since the right child at Level 4 evaluates to `true`, the logical OR propagates `true` upward to the root[cite: 22].

**Output:** `true`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - In the worst case (where no path matches or the valid path is checked last), the algorithm visits each node in the tree once[cite: 22]. Short-circuiting via `||` terminates early once a matching path is found[cite: 22].
- **Space Complexity:** $O(H)$ auxiliary space
  - The call stack depth corresponds to the height of the binary tree $H$. In a balanced tree, $H = O(\log N)$. In a degenerate skewed tree, $H = O(N)$.

---

### 5. Edge Cases & Key Takeaways

- **Empty Tree (`root == null`):** Guarded at lines 3–5[cite: 22], returning `false` immediately even when `targetSum == 0`[cite: 22].
- **Negative Node Values:** Because tree values and targets can be negative, search paths cannot be pruned early solely based on the current remainder being less than zero.
- **Strict Leaf Requirement:** Checking `root.left == null && root.right == null` ensures intermediate internal nodes with a single missing child are not incorrectly identified as path endpoints[cite: 22].