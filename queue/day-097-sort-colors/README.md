# Day 097: Sort Colors

- **Platform:** LeetCode #75
- **Difficulty:** Medium
- **Topic:** Array, Two Pointers, Sorting
- **Problem Link:** [Sort Colors](https://leetcode.com/problems/sort-colors/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Sort an array containing `0`s, `1`s, and `2`s in-place in a single pass without using the library sort function.

---

### 2. Intuition & Approach
Dutch National Flag three-pointer partitioning (`low`, `mid`, `high`): swap `0`s to `low++` (advancing `mid++`), leave `1`s (`mid++`), and swap `2`s to `high--` without advancing `mid`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Do not increment `mid` after swapping with `high` because the incoming element from `high` has not been inspected yet.
