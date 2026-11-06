# Day 080: Unique Paths II

- **Platform:** LeetCode #63[cite: 25]
- **Difficulty:** Medium[cite: 25]
- **Topic:** Array, Dynamic Programming, Matrix
- **Problem Link:** [Unique Paths II](https://leetcode.com/problems/unique-paths-ii/)
- **Language:** Java[cite: 25]
- **Time Complexity:** $O(m \times n)$[cite: 25]
- **Space Complexity:** $O(n)$ auxiliary space[cite: 25]

---

### 1. Problem Overview
You are given an $m \times n$ integer array `obstacleGrid`[cite: 25]. There is a robot initially located at the **top-left corner** (`grid[0][0]`)[cite: 25]. The robot tries to move to the **bottom-right corner** (`grid[m - 1][n - 1]`)[cite: 25]. The robot can only move either down or right at any point in time[cite: 25].

An obstacle and space are marked as `1` or `0` respectively in `grid`[cite: 25]. A path that the robot takes cannot include any square that is an obstacle[cite: 25].

Return the number of possible unique paths that the robot can take to reach the bottom-right corner[cite: 25]. The test cases guarantee the answer fits within a 32-bit signed integer ($\le 2 \times 10^9$)[cite: 25].

---

### 2. Intuition & Approach

#### 1D DP State Compression with Obstacle Clearing:
As established in LeetCode #62 (*Unique Paths*), the paths to reach cell $(r, c)$ depend on $(r - 1, c)$ and $(r, c - 1)$[cite: 24, 25]:
$$\text{dp}(r, c) = \text{dp}(r - 1, c) + \text{dp}(r, c - 1)$$

When obstacles are introduced[cite: 25]:
1. **Blocked Cells:** If `obstacleGrid[r][c] == 1`, no valid path can pass through or originate from this square[cite: 25]. Therefore, its path count is zero:
   $$\text{dp}[c] = 0$$[cite: 25]
2. **Open Cells:** If `obstacleGrid[r][c] == 0`, it inherits paths from above and from the left[cite: 25]:
   - Before updating, `dp[c]` contains the number of paths from the cell directly above $(r - 1, c)$[cite: 24, 25].
   - If $c > 0$, `dp[c - 1]` contains the number of paths from the cell directly to the left in the current row[cite: 25].
   - We accumulate `dp[c] += dp[c - 1]`[cite: 25].
3. **Initialization & Guard Checks:**
   - If the origin itself is blocked (`obstacleGrid[0][0] == 1`), zero paths can begin; immediately return `0`[cite: 25].
   - Initialize `dp[0] = 1` representing the single starting configuration[cite: 25].

---

### 3. Execution Trace

#### Sample Input: `obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]`[cite: 25]

- Initial: `dp = [1, 0, 0]`[cite: 25]

| Row ($r$)[cite: 25] | Col ($c$)[cite: 25] | Grid Value (`obstacleGrid[r][c]`)[cite: 25] | Logic Applied[cite: 25] | `dp` State After Cell[cite: 25] |
| :---: | :---: | :---: | :---: | :---: |
| 0[cite: 25] | 0[cite: 25] | `0` (Free)[cite: 25] | $c = 0$, keep `dp[0] = 1`[cite: 25] | `[1, 0, 0]`[cite: 25] |
| 0[cite: 25] | 1[cite: 25] | `0` (Free)[cite: 25] | $c > 0 \implies \text{dp}[1] += \text{dp}[0]$ ($0 + 1 = 1$)[cite: 25] | `[1, 1, 0]`[cite: 25] |
| 0[cite: 25] | 2[cite: 25] | `0` (Free)[cite: 25] | $c > 0 \implies \text{dp}[2] += \text{dp}[1]$ ($0 + 1 = 1$)[cite: 25] | `[1, 1, 1]`[cite: 25] |
| 1[cite: 25] | 0[cite: 25] | `0` (Free)[cite: 25] | $c = 0$, keep `dp[0] = 1` (from above)[cite: 25] | `[1, 1, 1]`[cite: 25] |
| 1[cite: 25] | 1[cite: 25] | **`1` (Obstacle)**[cite: 25] | **Reset:** `dp[1] = 0`[cite: 25] | `[1, 0, 1]`[cite: 25] |
| 1[cite: 25] | 2[cite: 25] | `0` (Free)[cite: 25] | $c > 0 \implies \text{dp}[2] += \text{dp}[1]$ ($1 + 0 = 1$)[cite: 25] | `[1, 0, 1]`[cite: 25] |
| 2[cite: 25] | 0[cite: 25] | `0` (Free)[cite: 25] | $c = 0$, keep `dp[0] = 1` (from above)[cite: 25] | `[1, 0, 1]`[cite: 25] |
| 2[cite: 25] | 1[cite: 25] | `0` (Free)[cite: 25] | $c > 0 \implies \text{dp}[1] += \text{dp}[0]$ ($0 + 1 = 1$)[cite: 25] | `[1, 1, 1]`[cite: 25] |
| 2[cite: 25] | 2[cite: 25] | `0` (Free)[cite: 25] | $c > 0 \implies \text{dp}[2] += \text{dp}[1]$ ($1 + 1 = 2$)[cite: 25] | `[1, 1, 2]`[cite: 25] |

- **Output:** `dp[2] = 2`[cite: 25]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(m \times n)$[cite: 25]
  - Every cell in the grid is visited once across the nested row and column loops[cite: 25]. Constant $O(1)$ operations are executed per cell[cite: 25].
- **Space Complexity:** $O(n)$ auxiliary space[cite: 25]
  - Compressing the dynamic programming table down to a single dimension reduces auxiliary space from $O(m \times n)$ to $O(n)$[cite: 25].

---

### 5. Edge Cases & Key Takeaways

- **Origin Blocked (`obstacleGrid[0][0] == 1`):** Intercepted at line 3[cite: 25], returning `0` immediately in $O(1)$ time[cite: 25].
- **Destination Blocked (`obstacleGrid[m - 1][n - 1] == 1`):** When the inner loop reaches $(m - 1, n - 1)$, `dp[n - 1] = 0` is set directly, returning `0` paths[cite: 25].
- **Full Row / Column Disconnection:** If an entire row or column consists of obstacles, all corresponding `dp` entries zero out, preventing forward path propagation past the barrier[cite: 25].