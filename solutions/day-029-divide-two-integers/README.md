# Day 029: Divide Two Integers

- **Platform:** LeetCode #29[cite: 15]
- **Difficulty:** Medium[cite: 15]
- **Topic:** Math, Bit Manipulation
- **Problem Link:** [Divide Two Integers](https://leetcode.com/problems/divide-two-integers/)[cite: 15]
- **Language:** Java[cite: 15]
- **Time Complexity:** $O(\log^2 N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Statement
Given two integers `dividend` and `divisor`, divide two integers **without** using multiplication, division, and mod operator[cite: 15].

The integer division should truncate toward zero, which means losing its fractional part[cite: 15]. For example, `8.345` would be truncated to `8`, and `-2.7335` would be truncated to `-2`[cite: 15].

Return the **quotient** after dividing `dividend` by `divisor`[cite: 15].

**Note:** Assume we are dealing with an environment that could only store integers within the **32-bit** signed integer range: $[-2^{31}, 2^{31} - 1]$[cite: 15]. For this problem, if the quotient is **strictly greater than** $2^{31} - 1$, then return $2^{31} - 1$, and if the quotient is **strictly less than** $-2^{31}$, then return $-2^{31}$[cite: 15].

- $-2^{31} \le \text{dividend}, \text{divisor} \le 2^{31} - 1$[cite: 15]
- $\text{divisor} \ne 0$[cite: 15]

---

### 2. Intuition & Approach

#### Exponential Subtraction in Negative Space ($O(\log^2 N)$ Time, $O(1)$ Space)
Repeated subtraction of `divisor` from `dividend` runs in $O(N)$ time, causing Time Limit Exceeded when dividing large numbers like $2^{31} - 1$ by $1$. Instead, we double the divisor exponentially using addition (`temp += temp`)[cite: 15]:

1. **Overflow Edge Case:**
   - In 32-bit signed arithmetic, $\text{Integer.MIN\_VALUE} = -2147483648$ and $\text{Integer.MAX\_VALUE} = 2147483647$[cite: 15].
   - Dividing $\text{Integer.MIN\_VALUE}$ by $-1$ results in $2147483648$, which exceeds the 32-bit limit; handle this directly by returning $\text{Integer.MAX\_VALUE}$[cite: 15].
2. **Working in Negative Domain:**
   - Converting numbers to positive coordinates overflows if the input is $-2^{31}$, because $|-2^{31}| = 2^{31} > \text{Integer.MAX\_VALUE}$[cite: 15].
   - Converting positive numbers to negative coordinates (`-dividend`, `-divisor`) is safe because the negative range is strictly larger[cite: 15].
3. **Exponential Doubling Search:**
   - While `dividend <= divisor` (in negative space, smaller or equal means greater absolute magnitude)[cite: 15]:
     - Double `temp` (`temp += temp`) and `multiple` (`multiple += multiple`) until doubling would surpass `dividend` or underflow `Integer.MIN_VALUE` (checked using `HALF_INT_MIN = -1073741824`)[cite: 15].
     - Subtract the doubled chunk from `dividend` and add `multiple` to `quotient`[cite: 15].
4. **Sign Restoration:**
   - If exactly one input was originally negative (`negatives == 1`), return the calculated negative quotient directly; otherwise, negate it (`-quotient`)[cite: 15].

---

### 3. Execution Trace

**Input:** `dividend = 10`, `divisor = 3`[cite: 15]  
Both converted to negative space: `dividend = -10`, `divisor = -3`, `negatives = 0` (both positive)[cite: 15].

| Outer Iteration | Initial `dividend` | `temp` Doubling Progression | Largest `temp` Subtracted | Subtracted `multiple` | Updated `dividend` | Total `quotient` |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **1** | `-10` | $-3 \rightarrow -6$ (cannot reach $-12$)[cite: 15] | `-6` | `-2` | $-10 - (-6) = -4$[cite: 15] | `-2` |
| **2** | `-4` | $-3$ (cannot reach $-6$)[cite: 15] | `-3` | `-1` | $-4 - (-3) = -1$[cite: 15] | `-2 + (-1) = -3` |
| **End** | `-1` | `dividend > divisor` ($-1 > -3$)[cite: 15] | — | — | Loop ends[cite: 15] | `-3` |

Restoring sign (`negatives == 0`): `-(-3) = 3`[cite: 15].  
**Final Result:** `3`[cite: 15]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\log^2 N)$
  - In each outer iteration, we double `divisor` up to at most $\log N$ times[cite: 15]. The dividend is reduced by at least half each time, leading to at most $O(\log N)$ outer loops. Total operations are bounded by $O(\log^2 N)$, executing in $< 1\text{ ms}$.
- **Space Complexity:** $O(1)$ auxiliary space
  - Operates using only scalar integer variables without extra dynamic heap structures[cite: 15].

---

### 5. Edge Cases & Key Takeaways

- **Integer Overflow:** Explicitly catches $\text{MIN\_VALUE} / -1$ to avoid 32-bit arithmetic wraparound[cite: 15].
- **Underflow Guards:** Comparing `temp >= HALF_INT_MIN` ensures `temp + temp` never underflows past $-2^{31}$[cite: 15].
- **No Disallowed Operators:** Performs arithmetic purely with addition (`+`), subtraction (`-`), and bit-level comparisons[cite: 15].