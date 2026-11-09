# Day 064: Convert Sorted Array to Binary Search Tree

- **Platform:** LeetCode #108[cite: 23]
- **Difficulty:** Easy[cite: 23]
- **Topic:** Array, Divide and Conquer, Tree, Binary Search Tree, Binary Tree
- **Problem Link:** [Convert Sorted Array to Binary Search Tree](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/)
- **Language:** Java[cite: 23]
- **Time Complexity:** $O(N)$ where $N$ is `nums.length`
- **Space Complexity:** $O(\log N)$ auxiliary recursion stack space for a height-balanced tree

---

### 1. Problem Overview
Given an integer array `nums` where the elements are sorted in **ascending order**, convert it to a **height-balanced** binary search tree[cite: 23].

A **height-balanced** binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one[cite: 23].

---

### 2. Intuition & Approach

#### Divide and Conquer (Binary Search Midpoint Partitioning):
In an ascending sorted array, picking the central element as the root splits the remaining numbers into two halves of equal (or near-equal) size[cite: 23]:
- All elements strictly to the left of the midpoint are smaller than `nums[mid]`[cite: 23], satisfying the BST left-subtree property.
- All elements strictly to the right of the midpoint are greater than `nums[mid]`[cite: 23], satisfying the BST right-subtree property.
- Because both halves differ in count by at most 1, applying this partition recursively guarantees that the tree remains height-balanced[cite: 23].

#### Recursive Breakdown:
1. **Base Case:**
   - If `left > right`, the subarray segment is empty; return `null`[cite: 23].
2. **Select Root Node:**
   - Compute `mid = left + (right - left) / 2`[cite: 23].
   - Instantiate `TreeNode root = new TreeNode(nums[mid])`[cite: 23].
3. **Inductive Subtree Construction:**
   - Build left branch: `root.left = buildBST(nums, left, mid - 1)`[cite: 23].
   - Build right branch: `root.right = buildBST(nums, mid + 1, right)`[cite: 23].
4. **Return Root:**
   - Return `root` after both subtrees are linked[cite: 23].

---

### 3. Execution Trace

#### Sample Input: `nums = [-10, -3, 0, 5, 9]`[cite: 23]

- Initial Range: `left = 0`, `right = 4`[cite: 23]

| Call (`left`, `right`) | Subarray Evaluated | `mid` Index | Node Created (`nums[mid]`) | Recursive Left Call | Recursive Right Call |
| :---: | :---: | :---: | :---: | :---: | :---: |
| `buildBST(0, 4)`[cite: 23] | `[-10, -3, 0, 5, 9]`[cite: 23] | 2 | `Node(0)`[cite: 23] | `buildBST(0, 1)`[cite: 23] | `buildBST(3, 4)`[cite: 23] |
| `buildBST(0, 1)`[cite: 23] | `[-10, -3]` | 0 | `Node(-10)` | `buildBST(0, -1)` $\rightarrow$ `null`[cite: 23] | `buildBST(1, 1)`[cite: 23] |
| `buildBST(1, 1)`[cite: 23] | `[-3]` | 1 | `Node(-3)`[cite: 23] | `null` | `null` |
| `buildBST(3, 4)`[cite: 23] | `[5, 9]` | 3 | `Node(5)`[cite: 23] | `buildBST(3, 2)` $\rightarrow$ `null`[cite: 23] | `buildBST(4, 4)`[cite: 23] |
| `buildBST(4, 4)`[cite: 23] | `[9]` | 4 | `Node(9)`[cite: 23] | `null` | `null` |

Resulting Tree:
```text
        0
      /   \
   -10     5
      \     \
      -3     9
```
*(Balanced BST: depths of subtrees at every node differ by $\le 1$)*[cite: 23].

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Each element in `nums` is visited exactly once to instantiate a `TreeNode`[cite: 23]. The recurrence $T(N) = 2T(N/2) + O(1)$ solves to $O(N)$ via the Master Theorem.
- **Space Complexity:** $O(\log N)$ auxiliary space
  - Because the array is partitioned evenly at each step, the recursion stack depth is bounded strictly by the height of the balanced tree: $\lceil \log_2 N \rceil$[cite: 23].

---

### 5. Edge Cases & Key Takeaways

- **Empty Array (`nums.length == 0`):** `left = 0` and `right = -1` triggers `left > right` immediately, safely returning `null`[cite: 23].
- **Single Element (`nums = [0]`):** Constructs `Node(0)` with `left = null` and `right = null` in $O(1)$ operations[cite: 23].
- **Multiple Valid Trees:** Both lower midpoint `left + (right - left) / 2` and upper midpoint `left + (right - left + 1) / 2` produce valid height-balanced BSTs[cite: 23].