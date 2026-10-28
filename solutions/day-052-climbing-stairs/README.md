# Day 052: Climbing Stairs

- **Platform:** LeetCode #70[cite: 18]
- **Difficulty:** Easy[cite: 18]
- **Topic:** Math, Dynamic Programming, Memoization
- **Problem Link:** [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
- **Language:** Java[cite: 18]
- **Time Complexity:** $O(N)$[cite: 18]
- **Space Complexity:** $O(1)$ auxiliary space[cite: 18]

---

### 1. Problem Overview
You are climbing a staircase that takes $n$ steps to reach the top[cite: 18]. Each time, you can climb either $1$ or $2$ steps[cite: 18].

Find the total number of distinct ways to reach the top[cite: 18].

---

### 2. Intuition & Approach

#### Dynamic Programming with Constant Space:
To arrive at step $i$, the final move must have come from either:
1. Step $i - 1$ (taking a single $1$-step jump)[cite: 18].
2. Step $i - 2$ (taking a $2$-step leap)[cite: 18].

This establishes the classical recurrence relation:
$$\text{ways}(i) = \text{ways}(i - 1) + \text{ways}(i - 2)$$

- **Base Cases:**
  - For $n = 1$: Only $1$ way (`1` step)[cite: 18].
  - For $n = 2$: Exactly $2$ ways (`1 + 1` or `2`)[cite: 18].
  - Returning $n$ for $n \le 2$ guards trivial boundaries immediately[cite: 18].
- **State Compression:**
  - Computing step $i$ requires only the preceding two values (`first` and `second`)[cite: 18].
  - Maintaining two scalar variables instead of an array of size $N + 1$ reduces auxiliary memory usage from $O(N)$ down to $O(1)$[cite: 18].

---

### 3. Execution Trace

#### Sample Input: $n = 5$

- Base initializations: `first = 1` (ways for $n = 1$), `second = 2` (ways for $n = 2$)[cite: 18]

| Step ($i$) | Calculation (`third = first + second`)[cite: 18] | New `first` (`second`)[cite: 18] | New `second` (`third`)[cite: 18] |
| :---: | :---: | :---: | :---: |
| 3 | $1 + 2 = 3$[cite: 18] | 2[cite: 18] | 3[cite: 18] |
| 4 | $2 + 3 = 5$ | 3 | 5 |
| 5 | $3 + 5 = 8$ | 5 | 8 |

- Loop completes at $i = n = 5$[cite: 18].
- Return `second = 8`[cite: 18].

**Output:** `8`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - A single loop iterates from $3$ up to $n$, performing constant-time additions and pointer updates at each iteration[cite: 18].
- **Space Complexity:** $O(1)$ auxiliary space
  - Solved in-place using primitive tracking variables (`first`, `second`, `third`) without allocating arrays or recursion call frames[cite: 18].

---

### 5. Edge Cases & Key Takeaways

- **Small Inputs ($n = 1, 2$):** Guard condition `if (n <= 2) return n;` executes in $O(1)$ time without loop overhead[cite: 18].
- **Fibonacci Correspondence:** The problem maps directly to the standard Fibonacci sequence shifted by one index ($\text{ways}(n) = F(n + 1)$).