# Day 056: Jump Game II

- **Platform:** LeetCode #45[cite: 20]
- **Difficulty:** Medium[cite: 20]
- **Topic:** Array, Dynamic Programming, Greedy
- **Problem Link:** [Jump Game II](https://leetcode.com/problems/jump-game-ii/)
- **Language:** Java[cite: 20]
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
You are given a 0-indexed integer array `nums` of length $n$, positioned initially at index $0$[cite: 20].

Each element `nums[i]` represents the maximum length of a forward jump from index $i$[cite: 20]. From index $i$, you can jump to any index $i + j$ where $0 \le j \le \text{nums}[i]$ and $i + j < n$[cite: 20].

Return the minimum number of jumps required to reach index $n - 1$[cite: 20]. All test cases are generated such that index $n - 1$ is always reachable[cite: 20].

---

### 2. Intuition & Approach

#### Greedy BFS Window Traversal:
The problem can be framed as discovering the minimum number of BFS level expansions needed to touch the last index:

1. **State Tracking:**
   - `jumps`: Total jump transitions committed so far[cite: 20].
   - `currentEnd`: The boundary edge of the current jump tier[cite: 20].
   - `farthest`: The maximum reachable index achievable from any node visited within the current tier[cite: 20].
2. **Greedy Expansion:**
   - Iterate index $i$ from $0$ up to `nums.length - 2`[cite: 20].
   - At each step, update the maximum reach: `farthest = Math.max(farthest, i + nums[i])`[cite: 20].
3. **Commit Jump at Window Boundary:**
   - When pointer $i$ reaches `currentEnd`, the range of the previous jump has been completely surveyed[cite: 20].
   - We must take another jump (`jumps++`) and advance the boundary: `currentEnd = farthest`[cite: 20].
4. **Loop Boundary Invariant:**
   - The loop stops at `nums.length - 2` because once $i$ reaches or passes into the range of $n - 1$, reaching the final index does not require committing another jump beyond it[cite: 20].

---

### 3. Execution Trace

#### Sample Input: `nums = [2, 3, 1, 1, 4]`

- Length $n = 5$, loop bounds: $i = 0$ to $i = 3$[cite: 20].
- Initial: `jumps = 0`, `currentEnd = 0`, `farthest = 0`[cite: 20].

| Step ($i$) | `nums[i]` | `farthest = Math.max(farthest, i + nums[i])`[cite: 20] | Boundary Reached (`i == currentEnd`)?[cite: 20] | `jumps`[cite: 20] | New `currentEnd`[cite: 20] |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 0 | 2 | $\max(0, 0 + 2) = 2$[cite: 20] | Yes ($0 == 0$)[cite: 20] | 1[cite: 20] | 2[cite: 20] |
| 1 | 3 | $\max(2, 1 + 3) = 4$[cite: 20] | No ($1 \neq 2$)[cite: 20] | 1 | 2 |
| 2 | 1 | $\max(4, 2 + 1) = 4$[cite: 20] | Yes ($2 == 2$)[cite: 20] | 2[cite: 20] | 4[cite: 20] |
| 3 | 1 | $\max(4, 3 + 1) = 4$[cite: 20] | No ($3 \neq 4$)[cite: 20] | 2 | 4 |

- Loop completes. Return `jumps = 2`[cite: 20].

**Output:** `2`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - A single linear pass evaluates each array index from $0$ up to $N - 2$ exactly once[cite: 20].
- **Space Complexity:** $O(1)$ auxiliary space
  - The greedy window is tracked using three primitive scalar integer variables (`jumps`, `currentEnd`, `farthest`) without auxiliary storage[cite: 20].

---

### 5. Edge Cases & Key Takeaways

- **Single Element Array ($n = 1$):** Loop condition `i < nums.length - 1` ($0 < 0$) is immediately false[cite: 20], returning `0` jumps correctly because index 0 is already the target.
- **Stopping at $N - 2$:** Bounding the loop at index $n - 2$ ensures that reaching the final destination does not trigger an unnecessary additional jump increment[cite: 20].