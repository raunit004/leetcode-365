# Day 104: Remove Duplicates from Sorted List II

- **Platform:** LeetCode #82
- **Difficulty:** Medium
- **Topic:** Linked List, Two Pointers
- **Problem Link:** [Remove Duplicates from Sorted List II](https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Delete all nodes that have duplicate numbers from a sorted linked list, leaving only distinct numbers.

---

### 2. Intuition & Approach
Use a sentinel `dummy` node and `prev` pointer to bypass entire contiguous duplicate runs while keeping `prev` stationary until a distinct node is confirmed.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Handles leading duplicates and lists where every node is duplicated.
