# Day 059: Symmetric Tree

- **Platform:** LeetCode #101[cite: 22]
- **Difficulty:** Easy[cite: 22]
- **Topic:** Tree, Depth-First Search, Breadth-First Search, Binary Tree
- **Problem Link:** [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)
- **Language:** Java[cite: 22]
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(H)$ auxiliary recursion stack space ($O(N)$ worst-case for degenerate trees)

---

### 1. Problem Overview
Given the `root` of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its vertical center line)[cite: 22].

---

### 2. Intuition & Approach

#### Synchronous Mirror Recursion:
Two binary trees are mirror reflections of each other if and only if[cite: 22]:
1. Their root nodes share identical values (`t1.val == t2.val`)[cite: 22].
2. The left child of tree 1 is a mirror reflection of the right child of tree 2 (`isMirror(t1.left, t2.right)`)[cite: 22].
3. The right child of tree 1 is a mirror reflection of the left child of tree 2 (`isMirror(t1.right, t2.left)`)[cite: 22].

#### Recursive Invariant:
- **Base Case 1 (Both Empty):** If `t1 == null && t2 == null`, symmetry holds across both empty branches; return `true`[cite: 22].
- **Base Case 2 (Asymmetry / Value Mismatch):** If only one node is `null` (`t1 == null || t2 == null`) or their node values diverge (`t1.val != t2.val`), the mirror condition fails; return `false`[cite: 22].
- **Inductive Step:** Recurse across opposing diagonal pairs simultaneously:
  $$\text{isMirror}(t_1, t_2) = \text{isMirror}(t_1.\text{left}, t_2.\text{right}) \land \text{isMirror}(t_1.\text{right}, t_2.\text{left})$$
[cite: 22]

---

### 3. Execution Trace

#### Sample Input: `root = [1, 2, 2, 3, 4, 4, 3]`[cite: 22]

Tree Representation:
```text
        1
      /   \
     2     2
    / \   / \
   3   4 4   3
```

| Step | Call (`t1`, `t2`)[cite: 22] | Values Checked | Validation Check | Action Taken |
| :---: | :---: | :---: | :---: | :--- |
| 1 | `isMirror(Node 2_L, Node 2_R)`[cite: 22] | $2 == 2$[cite: 22] | Valid | Recurse outer pair `(2_L.left, 2_R.right)`[cite: 22] |
| 2 | `isMirror(Node 3_L, Node 3_R)`[cite: 22] | $3 == 3$[cite: 22] | Valid | Leaves have `null` children $\implies$ returns `true`[cite: 22] |
| 3 | `isMirror(Node 4_L, Node 4_R)`[cite: 22] | $4 == 4$[cite: 22] | Valid | Leaves have `null` children $\implies$ returns `true`[cite: 22] |
| 4 | Join results | `true && true` | Valid | Subtrees at depth 1 match mirrors perfectly |

- Final output: **`true`**[cite: 22]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Traverses the entire tree by comparing pairs of nodes. Because each node is visited at most once, total time is bounded linearly by the total node count $N$.
- **Space Complexity:** $O(H)$ auxiliary space
  - The recursion stack takes memory proportional to the tree height $H$. In a balanced tree, $H = O(\log N)$. In the worst case of an elongated, skewed tree, $H = O(N)$.

---

### 5. Edge Cases & Key Takeaways

- **Empty Tree (`root == null`):** Evaluated by `root == null || ...` to immediately return `true`[cite: 22].
- **Single Node (`root.left == null && root.right == null`):** Direct call `isMirror(null, null)` yields `true` in $O(1)$ operations[cite: 22].
- **Structural Asymmetry:** Inputs like `[1, 2, 2, null, 3, null, 3]` are intercepted on line 9 (`t1 == null || t2 == null`) because one side provides a node where the other provides `null`[cite: 22].