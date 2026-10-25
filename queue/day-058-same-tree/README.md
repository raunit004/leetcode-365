# Day 058: Same Tree

- **Platform:** LeetCode #100[cite: 22]
- **Difficulty:** Easy[cite: 22]
- **Topic:** Tree, Depth-First Search, Breadth-First Search, Binary Tree
- **Problem Link:** [Same Tree](https://leetcode.com/problems/same-tree/)
- **Language:** Java[cite: 22]
- **Time Complexity:** $O(\min(N, M))$ where $N$ and $M$ are the node counts of trees $p$ and $q$
- **Space Complexity:** $O(\min(H_p, H_q))$ auxiliary recursion stack space ($O(N)$ worst-case for degenerate trees)

---

### 1. Problem Overview
Given the roots of two binary trees `p` and `q`, write a function to check if they are the same or not[cite: 22].

Two binary trees are considered the same if they are structurally identical, and the corresponding nodes share identical values[cite: 22].

---

### 2. Intuition & Approach

#### Simultaneous Recursive Traversal:
Compare both trees node-by-node simultaneously:

1. **Both Nodes Null:**
   - If both `p == null` and `q == null`, both branches have terminated without discrepancies; return `true`[cite: 22].
2. **Structural or Value Divergence:**
   - If only one node is `null` (`p == null || q == null`), the tree topologies differ[cite: 22].
   - If both exist but their stored values differ (`p.val != q.val`), values mismatch[cite: 22].
   - In either scenario, return `false` immediately[cite: 22].
3. **Subtree Recurrence:**
   - If the current node values match, recursively verify both child branches[cite: 22]:
     $$\text{isSameTree}(p, q) = \text{isSameTree}(p.\text{left}, q.\text{left}) \land \text{isSameTree}(p.\text{right}, q.\text{right})$$[cite: 22]

---

### 3. Execution Trace

#### Sample Input: `p = [1, 2, 3]`, `q = [1, 2, 3]`

| Step | Call | State of Nodes | Comparison / Condition[cite: 22] | Outcome |
| :---: | :---: | :---: | :---: | :---: |
| 1 | `isSameTree(p, q)`[cite: 22] | $p.\text{val} = 1, q.\text{val} = 1$ | $1 == 1$, both non-null[cite: 22] | Recurse to left children[cite: 22] |
| 2 | `isSameTree(p.left, q.left)`[cite: 22] | $p.\text{val} = 2, q.\text{val} = 2$ | $2 == 2$, both non-null[cite: 22] | Recurse to left children[cite: 22] |
| 3 | `isSameTree(null, null)` | Both `null` | `p == null && q == null`[cite: 22] | Returns `true`[cite: 22] |
| 4 | `isSameTree(null, null)` | Both `null` | `p == null && q == null`[cite: 22] | Returns `true`[cite: 22] |
| 5 | Left subtree evaluation | — | `true && true` | Returns `true` for left child[cite: 22] |
| 6 | `isSameTree(p.right, q.right)`[cite: 22] | $p.\text{val} = 3, q.\text{val} = 3$ | $3 == 3$, both non-null[cite: 22] | Recurse to children[cite: 22] |
| 7 | Right subtree evaluation | — | `true && true` | Returns `true` for right child[cite: 22] |

- Final result: `true && true` $\implies$ **`true`**[cite: 22].

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\min(N, M))$
  - The algorithm traverses nodes synchronously and terminates as soon as a mismatch or boundary is encountered. At most, it visits every node in the smaller tree.
- **Space Complexity:** $O(\min(H_p, H_q))$ auxiliary space
  - The recursion stack consumes memory proportional to the height of the smaller tree. In the worst-case of a completely skewed tree, this becomes $O(N)$.

---

### 5. Edge Cases & Key Takeaways

- **Both Trees Empty:** If `p == null` and `q == null`, returns `true` on line 4 without executing field accesses[cite: 22].
- **Asymmetric Structure:** When one tree has a child and the other does not (e.g., `[1, 2]` vs `[1, null, 2]`), the condition `p == null || q == null` flags structural divergence immediately[cite: 22].
- **Short-Circuit Evaluation:** Logical `&&` skips checking right subtrees if the left branch returns `false`, reducing unnecessary tree traversal[cite: 22].