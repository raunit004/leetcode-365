# Day 081: Minimum Path Sum

- **Platform:** LeetCode #64[cite: 25]
- **Difficulty:** Medium[cite: 25]
- **Topic:** Array, Dynamic Programming, Matrix
- **Problem Link:** [Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/)
- **Language:** Java[cite: 25]
- **Time Complexity:** $O(m \times n)$ where $m$ is row count and $n$ is column count[cite: 25]
- **Space Complexity:** $O(n)$ auxiliary space[cite: 25]

---

### 1. Problem Overview
Given an $m \times n$ `grid` filled with non-negative numbers, find a path from top-left to bottom-right which minimizes the sum of all numbers along its path[cite: 25].

> **Note:** You can only move either down or right at any point in time[cite: 25].

---

### 2. Intuition & Approach

#### 1D Dynamic Programming State Compression:
To arrive at cell $(r, c)$, movement must originate from either the cell directly above $(r - 1, c)$ or the cell directly to the left $(r, c - 1)$[cite: 25]. The minimum cost to reach $(r, c)$ satisfies the optimal substructure[cite: 25]:
$$\text{minPath}(r, c) = \text{grid}[r][c] + \min(\text{minPath}(r - 1, c), \text{minPath}(r, c - 1))$$[cite: 25]

Rather than maintaining a full $m \times n$ matrix, computing row $r$ only requires values from row $r - 1$ at the same column index and the newly updated value from column $c - 1$ in row $r$[cite: 25]. We compress the state into a 1D array of size $n$[cite: 25]:

1. **First Row Initialization:**
   - Any cell in the top row ($r = 0$) can only be accessed from its left neighbor[cite: 25].
   - Set `dp[0] = grid[0][0]` and compute prefix sums: `dp[c] = dp[c - 1] + grid[0][c]` for $c \in [1, n - 1]$[cite: 25].
2. **Row-by-Row Transition:**
   - For every row from $r = 1$ to $m - 1$[cite: 25]:
     - The first column (`c = 0`) can only be entered by moving downward: `dp[0] += grid[r][0]`[cite: 25].
     - For columns $c \in [1, n - 1]$:
       $$\text{dp}[c] = \text{grid}[r][c] + \min(\text{dp}[c], \text{dp}[c - 1])$$[cite: 25]
       - Prior to the update, `dp[c]` holds the minimum cost to reach $(r - 1, c)$ from above[cite: 25].
       - `dp[c - 1]` contains the already computed minimum cost to reach $(r, c - 1)$ from the left[cite: 25].
3. **Result Extraction:**
   - `dp[n - 1]` provides the minimal cost path reaching the bottom-right terminal cell[cite: 25].

---

### 3. Execution Trace

#### Sample Input: `grid = [[1, 3, 1], [1, 5, 1], [4, 2, 1]]`[cite: 25]

- Initial Row 0 Prefix Sums:
  - `dp[0] = 1`[cite: 25]
  - `dp[1] = 1 + 3 = 4`[cite: 25]
  - `dp[2] = 4 + 1 = 5`[cite: 25]
  - State: `dp = [1, 4, 5]`[cite: 25]

| Row ($r$)[cite: 25] | Col ($c$)[cite: 25] | Current Cell Value[cite: 25] | Calculation (`grid[r][c] + min(dp[c], dp[c-1])`)[cite: 25] | Updated `dp` Array State[cite: 25] |
| :---: | :---: | :---: | :---: | :---: |
| 1[cite: 25] | 0[cite: 25] | 1[cite: 25] | `dp[0] += 1` $\implies 1 + 1 = 2$[cite: 25] | `[2, 4, 5]`[cite: 25] |
| 1[cite: 25] | 1[cite: 25] | 5[cite: 25] | $5 + \min(4, 2) = 5 + 2 = 7$[cite: 25] | `[2, 7, 5]`[cite: 25] |
| 1[cite: 25] | 2[cite: 25] | 1[cite: 25] | $1 + \min(5, 7) = 1 + 5 = 6$[cite: 25] | `[2, 7, 6]`[cite: 25] |
| 2[cite: 25] | 0[cite: 25] | 4[cite: 25] | `dp[0] += 4` $\implies 2 + 4 = 6$[cite: 25] | `[6, 7, 6]`[cite: 25] |
| 2[cite: 25] | 1[cite: 25] | 2[cite: 25] | $2 + \min(7, 6) = 2 + 6 = 8$[cite: 25] | `[6, 8, 6]`[cite: 25] |
| 2[cite: 25] | 2[cite: 25] | 1[cite: 25] | $1 + \min(6, 8) = 1 + 6 = 7$[cite: 25] | `[6, 8, 7]`[cite: 25] |

- **Output:** `dp[2] = 7`[cite: 25]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(m \times n)$[cite: 25]
  - Every cell in the matrix is evaluated exactly once across two nested loops, executing constant $O(1)$ arithmetic operations per cell[cite: 25].
- **Space Complexity:** $O(n)$ auxiliary space[cite: 25]
  - Storing only the rolling row values in a 1D array of length $n$ avoids mutating the original matrix while eliminating the need for an $O(m \times n)$ allocation[cite: 25].

---

### 5. Edge Cases & Key Takeaways

- **Single Row ($1 \times n$):** The outer row loop does not execute[cite: 25]; the first row prefix sum returns `dp[n - 1]` directly in $O(n)$ operations[cite: 25].
- **Single Column ($m \times 1$):** The inner column loops do not run[cite: 25]; each iteration simply accumulates `dp[0] += grid[r][0]`, returning the column sum in $O(m)$ operations[cite: 25].
- **Preserving Immutability:** Many in-place DP solutions overwrite `grid[r][c]`. Using a single $O(n)$ auxiliary array protects the caller's input matrix from mutation while maintaining negligible memory usage[cite: 25].