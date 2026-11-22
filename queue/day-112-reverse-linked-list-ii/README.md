# Day 112: Reverse Linked List II

- **Platform:** LeetCode #92
- **Difficulty:** Medium
- **Topic:** Linked List
- **Problem Link:** [Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Reverse the nodes of a singly linked list from 1-indexed position `left` to `right` in one pass.

---

### 2. Intuition & Approach
Position `prev` before `left` using a dummy node, anchor `curr = prev.next`, and splice `curr.next` immediately after `prev` for `right - left` iterations.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 4. Edge Cases & Key Takeaways
`left == right` returns `head` immediately; `left == 1` is handled cleanly by `dummy`.
