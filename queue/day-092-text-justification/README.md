# Day 092: Text Justification

- **Platform:** LeetCode #68
- **Difficulty:** Hard
- **Topic:** Array, String, Simulation
- **Problem Link:** [Text Justification](https://leetcode.com/problems/text-justification/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(W)$

---

### 1. Problem Overview
Pack `words` greedily into lines of exact width `maxWidth`, distributing extra spaces evenly (left slots get more) and left-justifying the final line or single-word lines.

---

### 2. Intuition & Approach
Greedily group as many words as fit within `maxWidth`. Left-justify the last line or single-word lines; otherwise distribute `totalSpaces / gaps` plus one extra space to the first `totalSpaces % gaps` gaps.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(W)$

---

### 4. Edge Cases & Key Takeaways
Single-word lines and the last line must be left-justified with trailing spaces.
