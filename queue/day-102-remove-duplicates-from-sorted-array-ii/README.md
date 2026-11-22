# Day 102: Remove Duplicates from Sorted Array II

- **Platform:** LeetCode #80
- **Difficulty:** Medium
- **Topic:** Array, Two Pointers
- **Problem Link:** [Remove Duplicates from Sorted Array II](https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Remove duplicates in-place from a sorted array so each unique element appears at most twice, returning the new length `k`.

---

### 2. Intuition & Approach
Maintain a slow write pointer `k` and accept `x` whenever `k < 2 || x != nums[k - 2]`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Arrays of length `<= 2` are returned unchanged.
