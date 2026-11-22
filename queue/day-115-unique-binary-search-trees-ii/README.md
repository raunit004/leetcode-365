# Day 115: Unique Binary Search Trees II

- **Platform:** LeetCode #95
- **Difficulty:** Medium
- **Topic:** Dynamic Programming, Backtracking, Tree, Binary Search Tree
- **Problem Link:** [Unique Binary Search Trees II](https://leetcode.com/problems/unique-binary-search-trees-ii/)
- **Language:** Java
- **Time Complexity:** $O(n \cdot C_n)$
- **Space Complexity:** $O(n \cdot C_n)$

---

### 1. Problem Overview
Return all structurally unique BSTs with `n` nodes of unique values from `1` to `n`.

---

### 2. Intuition & Approach
Memoized divide-and-conquer over value ranges `[start, end]`, combining all left `[start, r - 1]` and right `[r + 1, end]` subtrees for each root `r`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(n \cdot C_n)$
- **Space Complexity:** $O(n \cdot C_n)$

---

### 4. Edge Cases & Key Takeaways
Returning `Collections.singletonList(null)` for `start > end` ensures empty subtrees pair cleanly.
