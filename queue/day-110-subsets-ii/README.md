# Day 110: Subsets II

- **Platform:** LeetCode #90
- **Difficulty:** Medium
- **Topic:** Array, Backtracking, Bit Manipulation
- **Problem Link:** [Subsets II](https://leetcode.com/problems/subsets-ii/)
- **Language:** Java
- **Time Complexity:** $O(N \cdot 2^N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Return all unique subsets of an integer array `nums` that may contain duplicates.

---

### 2. Intuition & Approach
Sort `nums` first, then backtrack while skipping duplicates at the same recursion depth (`if (i > start && nums[i] == nums[i - 1]) continue;`).

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N \cdot 2^N)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
Checking `i > start` (not `i > 0`) allows duplicates across deeper recursion levels.
