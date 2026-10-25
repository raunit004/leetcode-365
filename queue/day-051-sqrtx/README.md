# Day 051: Sqrt(x)

- **Platform:** LeetCode #69[cite: 17]
- **Difficulty:** Easy[cite: 17]
- **Topic:** Math, Binary Search
- **Problem Link:** [Sqrt(x)](https://leetcode.com/problems/sqrtx/)
- **Language:** Java[cite: 17]
- **Time Complexity:** $O(\log x)$
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
Given a non-negative integer `x`, compute and return the square root of `x` rounded down to the nearest integer[cite: 17]. The returned integer must be non-negative[cite: 17].

You must not use any built-in exponent functions or operators (such as `Math.pow(x, 0.5)`)[cite: 17].

---

### 2. Intuition & Approach

#### Binary Search on Monotonic Domain:
The function $f(k) = k^2$ is monotonically non-decreasing for $k \ge 0$. This allows determining $\lfloor \sqrt{x} \rfloor$ via binary search:

1. **Base Cases ($x < 2$):**
   - For $x = 0$ and $x = 1$, $\lfloor \sqrt{x} \rfloor = x$ directly[cite: 17]. Returning early handles edge boundaries without initialization hazards[cite: 17].
2. **Search Range Optimization:**
   - For any integer $x \ge 4$, the square root satisfies $\sqrt{x} \le \frac{x}{2}$.
   - We safely constrain the search boundaries to `left = 1` and `right = x / 2`[cite: 17].
3. **Overflow-Safe Comparison:**
   - Evaluating $mid \times mid \le x$ can cause 32-bit integer overflow when $mid > 46,340$.
   - Using the mathematically equivalent algebraic expression `mid <= x / mid` guarantees arithmetic remains strictly within valid 32-bit signed integer limits[cite: 17].
4. **Boundary Narrowing:**
   - If `mid <= x / mid`: `mid` is a valid floor candidate[cite: 17]. Record `ans = mid` and move right (`left = mid + 1`) to check if a larger valid integer exists[cite: 17].
   - If `mid > x / mid`: `mid` squared exceeds $x$. Search the smaller left interval (`right = mid - 1`)[cite: 17].

---

### 3. Execution Trace

#### Sample Input: `x = 8`[cite: 17]
- Initial: `left = 1`, `right = 8 / 2 = 4`, `ans = 0`[cite: 17]

| Iteration | `left` | `right` | `mid` | Condition (`mid <= 8 / mid`) | Action | Updated `ans` |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 1 | 4 | 2 | $2 \le 8 / 2 \implies 2 \le 4$ (True)[cite: 17] | `ans = 2`, `left = 3`[cite: 17] | 2[cite: 17] |
| 2 | 3 | 4 | 3 | $3 \le 8 / 3 \implies 3 \le 2$ (False)[cite: 17] | `right = 2`[cite: 17] | 2[cite: 17] |

- `left > right` ($3 > 2$): Loop terminates[cite: 17].
- Return `ans = 2`[cite: 17].

**Output:** `2`[cite: 17]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\log x)$
  - The binary search divides the search interval $[1, x / 2]$ in half each iteration[cite: 17], taking at most $\approx \log_2(x/2) \le 31$ operations.
- **Space Complexity:** $O(1)$ auxiliary space
  - Solved in-place using scalar variables (`left`, `right`, `mid`, `ans`) without dynamic heap allocations[cite: 17].

---

### 5. Edge Cases & Key Takeaways

- **Integer Overflow Protection:** Formulating the check as `mid <= x / mid` completely eliminates numerical overflow on extreme test cases like `Integer.MAX_VALUE`[cite: 17].
- **Zero and One Handling:** Guarding $x < 2$ avoids division-by-zero during search interval evaluation (`x / mid`)[cite: 17].