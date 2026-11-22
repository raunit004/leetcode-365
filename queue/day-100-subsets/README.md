# Day 100: Subsets

- **Platform:** LeetCode #78
- **Difficulty:** Medium
- **Topic:** Array, Backtracking, Bit Manipulation
- **Problem Link:** [Subsets](https://leetcode.com/problems/subsets/)
- **Language:** Java
- **Time Complexity:** $O(N \cdot 2^N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Given an integer array `nums` of unique elements, return all possible subsets (the power set).

---

### 2. Intuition & Approach
Pre-order backtracking starting at index `0`, snapshotting `new ArrayList<>(curr)` at every node and advancing `start` to `i + 1`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N \cdot 2^N)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
Includes the empty subset `[]` and handles single-element arrays.
