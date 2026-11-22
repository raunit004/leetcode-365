# Day 107: Partition List

- **Platform:** LeetCode #86
- **Difficulty:** Medium
- **Topic:** Linked List, Two Pointers
- **Problem Link:** [Partition List](https://leetcode.com/problems/partition-list/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Partition a linked list around `x` so all nodes `< x` precede nodes `>= x`, preserving relative order.

---

### 2. Intuition & Approach
Route nodes into two dummy-headed lists (`before` and `after`), terminate `after.next = null` to prevent cycles, and link `before.next = afterHead.next`.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
Setting `after.next = null` prevents cycles when the original tail moves to `before`.
