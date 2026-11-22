# Day 105: Largest Rectangle in Histogram

- **Platform:** LeetCode #84
- **Difficulty:** Hard
- **Topic:** Array, Stack, Monotonic Stack
- **Problem Link:** [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Given an array `heights` representing histogram bar heights of width `1`, return the area of the largest rectangle.

---

### 2. Intuition & Approach
Maintain a primitive array-backed monotonic increasing stack of indices with a trailing sentinel `h = 0` at `i == n` to flush all remaining bars in one pass.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
Strictly increasing histograms are flushed cleanly by the sentinel at `i == n`.
