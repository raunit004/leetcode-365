# Day 079: Unique Paths

- **Platform:** LeetCode #62[cite: 24]
- **Difficulty:** Medium[cite: 24]
- **Topic:** Math, Dynamic Programming, Combinatorics
- **Problem Link:** [Unique Paths](https://leetcode.com/problems/unique-paths/)
- **Language:** Java[cite: 24]
- **Time Complexity:** $O(m \times n)$[cite: 24]
- **Space Complexity:** $O(n)$ auxiliary space[cite: 24]

---

### 1. Problem Overview
There is a robot on an $m \times n$ grid initially located at the top-left corner (`grid[0][0]`)[cite: 24]. The robot attempts to reach the bottom-right corner (`grid[m - 1][n - 1]`)[cite: 24]. At any point in time, the robot can only move either down or right[cite: 24].

Given the two integers `m` and `n`, return the number of possible unique paths the robot can take to reach the bottom-right corner[cite: 24]. The test cases guarantee the answer fits within a standard 32-bit signed integer ($\le 2 \times 10^9$)[cite: 24].

---

### 2. Intuition & Approach

#### 1D Dynamic Programming State Compression:
To reach cell $(r, c)$, the robot must arrive either from the cell directly above $(r - 1, c)$ via a downward step, or from the cell directly to the left $(r, c - 1)$ via a rightward step[cite: 24]:
$$\text{paths}(r, c) = \text{paths}(r - 1, c) + \text{paths}(r, c - 1)$$

A standard 2D dynamic programming grid requires $O(m \times n)$ memory. Because computing row $r$ only depends on the previous row's value at the same column index and the newly computed value of the current row's preceding column, we can compress the state into a single 1D array of size $n$[cite: 24]:

1. **Base Row Initialization:**
   - There is exactly $1$ way to reach any cell in the first row ($r = 0$) by moving strictly rightward[cite: 24].
   - Initialize `int[] dp = new int[n]` and populate all entries with $1$: `Arrays.fill(dp, 1)`[cite: 24].
2. **Rolling Accumulation:**
   - Iterate rows from $r = 1$ to $m - 1$[cite: 24].
   - For columns $c = 1$ to $n - 1$, update the cell in-place[cite: 24]:
     $$\text{dp}[c] = \text{dp}[c] + \text{dp}[c - 1]$$[cite: 24]
     - Before the addition, `dp[c]` holds the path count from the cell directly above in row $r - 1$[cite: 24].
     - `dp[c - 1]` holds the newly calculated path count from the cell directly to the left in row $r$[cite: 24].
3. **Result:**
   - The final entry `dp[n - 1]` contains the total paths to the bottom-right corner[cite: 24].

---

### 3. Execution Trace

#### Sample Input: `m = 3`, `n = 3`[cite: 24]

- Initial Row 0: `dp = [1, 1, 1]`[cite: 24]

| Row ($r$)[cite: 24] | Col ($c$)[cite: 24] | Operation (`dp[c] += dp[c - 1]`)[cite: 24] | Updated `dp` Array State[cite: 24] |
| :---: | :---: | :---: | :---: |
| 1[cite: 24] | 1[cite: 24] | `dp[1] = 1 + 1 = 2`[cite: 24] | `[1, 2, 1]` |
| 1[cite: 24] | 2[cite: 24] | `dp[2] = 1 + 2 = 3`[cite: 24] | `[1, 2, 3]` |
| 2[cite: 24] | 1[cite: 24] | `dp[1] = 2 + 1 = 3`[cite: 24] | `[1, 3, 3]` |
| 2[cite: 24] | 2[cite: 24] | `dp[2] = 3 + 3 = 6`[cite: 24] | `[1, 3, 6]` |

- **Output:** `dp[2] = 6`[cite: 24]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(m \times n)$[cite: 24]
  - The nested loops execute $(m - 1) \times (n - 1)$ iterations, each executing a single $O(1)$ addition[cite: 24].
- **Space Complexity:** $O(n)$ auxiliary space[cite: 24]
  - Memory consumption is limited to the single 1D array of size $n$[cite: 24].

---

### 5. Edge Cases & Key Takeaways

- **Single Row / Single Column ($m = 1$ or $n = 1$):**
  - If $m = 1$, the outer row loop (`r = 1; r < m`) does not execute[cite: 24]. Returns `dp[n - 1] = 1` immediately in $O(1)$ time[cite: 24].
  - If $n = 1$, the inner loop does not execute, returning `dp[0] = 1`[cite: 24].
- **Combinatorial Equivalence:** The problem is mathematically equivalent to choosing $m - 1$ downward moves out of a total of $(m - 1) + (n - 1)$ moves:
  $$\binom{(m - 1) + (n - 1)}{m - 1} = \frac{(m + n - 2)!}{(m - 1)! \cdot (n - 1)!}$$
  The 1D dynamic programming approach computes this identical Pascal's triangle relationship additively, completely avoiding potential 64-bit factorial arithmetic overflow.