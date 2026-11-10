# Day 065: Balanced Binary Tree

- **Platform:** LeetCode #110[cite: 20]
- **Difficulty:** Easy[cite: 20]
- **Topic:** Tree, Depth-First Search, Binary Tree
- **Problem Link:** [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)
- **Language:** Java[cite: 20]
- **Time Complexity:** $O(N)$ where $N$ is the number of nodes in the binary tree
- **Space Complexity:** $O(H)$ auxiliary space for call stack frames, where $H$ is the height of the tree ($O(N)$ worst-case)

---

### 1. Problem Overview
Given a binary tree, determine if it is **height-balanced**[cite: 20].

A **height-balanced** binary tree is defined as a binary tree in which the left and right subtrees of every node differ in height by no more than $1$.

---

### 2. Intuition & Approach

#### Bottom-Up Post-Order DFS with Early Stopping:
A naive top-down check evaluates the height of both subtrees for every node from the root downward, causing repeated visits and an unoptimized $O(N^2)$ runtime. 

By calculating heights **bottom-up** via post-order DFS, we compute each subtree's height once and propagate imbalance signals upward immediately[cite: 20]:

1. **Height & Sentinel Encoding:**
   - Define a helper function `checkHeight(node)` that returns the integer height of the subtree if balanced, or a sentinel value `-1` if it is unbalanced[cite: 20].
2. **Base Case:**
   - If `node == null`, the height is `0`[cite: 20].
3. **Subtree Evaluation & Short-Circuiting:**
   - Recursively evaluate `leftHeight = checkHeight(node.left)`[cite: 20]. If `leftHeight == -1`, return `-1` immediately to prune further exploration[cite: 20].
   - Recursively evaluate `rightHeight = checkHeight(node.right)`[cite: 20]. If `rightHeight == -1`, return `-1` immediately[cite: 20].
4. **Balance Verification:**
   - If $\vert{} \text{leftHeight} - \text{rightHeight} \vert{} > 1$, the tree rooted at the current node violates the balance condition; return `-1`[cite: 20].
   - Otherwise, return the subtree's actual height: `1 + Math.max(leftHeight, rightHeight)`[cite: 20].
5. **Final Result:**
   - Check if `checkHeight(root) != -1`[cite: 20].

---

### 3. Execution Trace

#### Sample Input: `root = [3, 9, 20, null, null, 15, 7]`[cite: 20]

Tree Topology:
```text
      3
     / \
    9   20
       /  \
      15   7
```[cite: 20]

| Subtree Node | `leftHeight`[cite: 20] | `rightHeight`[cite: 20] | $| \text{diff} | \le 1$?[cite: 20] | Returned Value[cite: 20] |
| :---: | :---: | :---: | :---: | :---: |
| `Node(15)`[cite: 20] | `0` (`null`)[cite: 20] | `0` (`null`)[cite: 20] | $|0 - 0| \le 1$ (True)[cite: 20] | $1 + \max(0, 0) = 1$[cite: 20] |
| `Node(7)`[cite: 20] | `0` (`null`)[cite: 20] | `0` (`null`)[cite: 20] | $|0 - 0| \le 1$ (True)[cite: 20] | $1 + \max(0, 0) = 1$[cite: 20] |
| `Node(20)`[cite: 20] | `1` (from Node 15)[cite: 20] | `1` (from Node 7)[cite: 20] | $|1 - 1| \le 1$ (True)[cite: 20] | $1 + \max(1, 1) = 2$[cite: 20] |
| `Node(9)`[cite: 20] | `0` (`null`)[cite: 20] | `0` (`null`)[cite: 20] | $|0 - 0| \le 1$ (True)[cite: 20] | $1 + \max(0, 0) = 1$[cite: 20] |
| `Node(3)` (Root)[cite: 20] | `1` (from Node 9)[cite: 20] | `2` (from Node 20)[cite: 20] | $|1 - 2| \le 1$ (True)[cite: 20] | $1 + \max(1, 2) = 3$[cite: 20] |

- Final output: `checkHeight(root) = 3 != -1` $\implies$ **`true`**[cite: 20].

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Each node in the tree is visited at most once during the post-order DFS traversal[cite: 20]. Returning `-1` enables immediate short-circuiting as soon as an unbalanced subtree is discovered[cite: 20].
- **Space Complexity:** $O(H)$ auxiliary space
  - The call stack depth is determined by the height of the tree $H$. For balanced trees, $H = O(\log N)$; for skewed degenerate trees, $H = O(N)$.

---

### 5. Edge Cases & Key Takeaways

- **Empty Tree (`root == null`):** Evaluates `checkHeight(null) == 0` on line 6[cite: 20], correctly returning `true` in $O(1)$ operations[cite: 20].
- **Early Exit Sentinel:** Propagating `-1` prevents redundant traversal of the entire tree when an imbalance occurs deep in a subtree[cite: 20].