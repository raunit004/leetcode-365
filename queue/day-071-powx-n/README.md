# Day 071: Pow(x, n)

- **Platform:** LeetCode #50
- **Difficulty:** Medium
- **Topic:** Math, Recursion
- **Problem Link:** [Pow(x, n)](https://leetcode.com/problems/powx-n/)
- **Language:** Java[cite: 23]
- **Time Complexity:** $O(\log |n|)$
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
Implement `myPow(x, n)`, which calculates $x$ raised to the power $n$ ($x^n$).

- $-100.0 < x < 100.0$
- $-2^{31} \le n \le 2^{31} - 1$
- $n$ is an integer.
- Either $x$ is not zero, or $n > 0$.
- $-10^4 \le x^n \le 10^4$

---

### 2. Intuition & Approach

#### Binary Exponentiation (Exponentiation by Squaring):
A linear multiplication approach $O(|n|)$ leads to a Time Limit Exceeded (TLE) when $|n| \approx 2 \times 10^9$. Binary exponentiation reduces operations logarithmically:
$$x^n = \begin{cases} (x^2)^{n/2} & \text{if } n \text{ is even} \\ x \cdot (x^2)^{(n-1)/2} & \text{if } n \text{ is odd} \end{cases}$$

1. **Handling Integer Minimum (`Integer.MIN_VALUE`):**
   - $-2^{31}$ cannot be negated within a 32-bit signed `int` without integer overflow ($|-2^{31}| = 2^{31} > \text{Integer.MAX\_VALUE}$).
   - Cast `n` to a 64-bit `long exp = n` before negation (`exp = -exp`).
2. **Preventing Floating-Point Precision Drift:**
   - Inverting the base upfront (`x = 1.0 / x`) introduces a 1-ULP precision error into `x`. When magnified over large exponents ($|n| > 10^9$), this causes round-off discrepancies in the least significant digits.
   - Keep the original base intact throughout repeated squaring, computing $x^{|n|}$, and apply a single division at the end: `n < 0 ? 1.0 / result : result`.
3. **Bitwise Exponent Extraction:**
   - At each iteration, check the least significant bit (`exp & 1 == 1`). If set, accumulate `result *= currentBase`.
   - Square the base (`currentBase *= currentBase`) and right-shift the exponent (`exp >>= 1`).

---

### 3. Execution Trace

#### Sample Input: `x = 2.0`, `n = -2`

- Initial: `exp = |-2| = 2`, `result = 1.0`, `currentBase = 2.0`

| Iteration | `exp` | `exp & 1 == 1`? | `result` Accumulation | `currentBase` Squaring | Next `exp` (`exp >>= 1`) |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 2 (`0b10`) | No (0) | `1.0` | $2.0 \times 2.0 = 4.0$ | 1 (`0b01`) |
| 2 | 1 (`0b01`) | Yes (1) | $1.0 \times 4.0 = 4.0$ | $4.0 \times 4.0 = 16.0$ | 0 (`0b00`) |

- Exponent is zero; loop terminates.
- Negative exponent check: $n < 0 \implies \frac{1.0}{\text{result}} = \frac{1.0}{4.0} =$ **`0.25`**

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\log |n|)$
  - The exponent is halved at each step (`exp >>= 1`), executing at most $32$ iterations for 32-bit integer ranges.
- **Space Complexity:** $O(1)$ auxiliary space
  - Evaluated iteratively using primitive scalar variables without heap allocations or recursion call stack overhead.

---

### 5. Edge Cases & Key Takeaways

- **$n = \text{Integer.MIN\_VALUE}$ (`-2147483648`):** Safely handled using `long exp = n;` avoiding integer overflow.
- **$n = 0$:** The while-loop does not execute, returning `result = 1.0` in $O(1)$ time.
- **Precision Preservation:** Computing $x^{|n|}$ with the raw base and dividing once prevents the accumulation of reciprocal rounding errors.