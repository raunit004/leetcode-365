# Day 007: Reverse Integer

- **Platform:** LeetCode #7
- **Difficulty:** Medium
- **Topic:** Math
- **Problem Link:** [Reverse Integer](https://leetcode.com/problems/reverse-integer/)
- **Language:** Java

---

### 1. Problem Statement
Given a signed 32-bit integer `x`, return `x` with its digits reversed. If reversing `x` causes the value to go outside the signed 32-bit integer range $[-2^{31}, 2^{31} - 1]$, return `0`.

- Assume the environment does not allow storing 64-bit integers (signed or unsigned).
- $-2^{31} \le x \le 2^{31} - 1$

---

### 2. Intuition & Approach

#### Digit Extraction and Look-Ahead Overflow Detection
To reverse digits mathematically without converting to a string:
1. Extract the least significant digit: `pop = x % 10`.
2. Truncate the original integer: `x /= 10`.
3. Append `pop` to the reversed accumulator: `rev = rev * 10 + pop`.

Because 64-bit integers (`long`) are disallowed by problem constraints, checking for overflow *after* computing `rev * 10 + pop` causes integer overflow undefined behavior. We must inspect `rev` *before* multiplying by 10.

#### 32-Bit Range Boundaries
- $\text{Integer.MAX\_VALUE} = 2,147,483,647$
- $\text{Integer.MIN\_VALUE} = -2,147,483,648$

Overflow occurs if:
- `rev > Integer.MAX_VALUE / 10`
- `rev == Integer.MAX_VALUE / 10 && pop > 7`
- `rev < Integer.MIN_VALUE / 10`
- `rev == Integer.MIN_VALUE / 10 && pop < -8`

---

### 3. Execution Trace

**Input:** `x = 123`

| Iteration | `x` before | `pop = x % 10` | `x` after | Boundary Check Passed? | `rev` calculation | `rev` after |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **1** | `123` | `3` | `12` | Yes | `0 * 10 + 3` | `3` |
| **2** | `12` | `2` | `1` | Yes | `3 * 10 + 2` | `32` |
| **3** | `1` | `1` | `0` | Yes | `32 * 10 + 1` | `321` |

**Final Result:** `321`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\log_{10}|x|)$
  - The number of digits in $x$ is approximately $\log_{10}|x|$. A 32-bit integer has at most 10 digits, making the while loop run at most 10 times ($O(1)$ constant operations).
- **Space Complexity:** $O(1)$ auxiliary space
  - Only two 32-bit integer variables (`rev`, `pop`) are stored.

---

### 5. Edge Cases & Key Takeaways

- **Negative Numbers:** In Java, `-123 % 10` yields `-3`, and `-123 / 10` yields `-12`. The negative sign remains attached during arithmetic without needing separate absolute value conversion.
- **Trailing Zeros (`120`):** The first extracted digit is `0`, which initializes `rev = 0 * 10 + 0 = 0`. The subsequent digit `2` shifts it to `2`, naturally dropping the leading zero to yield `21`.
- **Pre-emptive Overflow Check:** Checking `rev > MAX / 10` prevents arithmetic wraparound that produces silently corrupted negative values in Java integers.