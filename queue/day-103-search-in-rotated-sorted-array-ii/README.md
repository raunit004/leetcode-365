# Day 103: Search in Rotated Sorted Array II

- **Platform:** LeetCode #81
- **Difficulty:** Medium
- **Topic:** Array, Binary Search
- **Problem Link:** [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/)
- **Language:** Java
- **Time Complexity:** $O(\log N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Search for `target` in a rotated sorted array that may contain duplicate values.

---

### 2. Intuition & Approach
Modified binary search: shrink `low++` and `high--` when `nums[low] == nums[mid] == nums[high]`, otherwise determine the sorted half and discard the other.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(\log N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Handles arrays with heavy duplicates like `[1, 0, 1, 1, 1]` (worst-case $O(N)$).
