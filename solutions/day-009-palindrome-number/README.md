# Day 009: Palindrome Number

- **Platform:** LeetCode #9
- **Difficulty:** Easy
- **Topic:** Math
- **Problem Link:** [Palindrome Number](https://leetcode.com/problems/palindrome-number/)
- **Language:** Java

---

### 1. Problem Statement
Given an integer `x`, return `true` if `x` is a palindrome, and `false` otherwise.

- An integer is a palindrome when it reads the same forward and backward.
- $-2^{31} \le x \le 2^{31} - 1$

---

### 2. Intuition & Approach

#### Reversing the Half Number ($O(\log_{10} x)$ Time, $O(1)$ Space)
Converting the integer to a string requires $O(\log_{10} x)$ auxiliary heap memory. Reversing the entire integer introduces the risk of 32-bit overflow when $x$ is close to `Integer.MAX_VALUE`.

Instead, we can reverse only the **tail half** of the number and compare it with the front half:
1. **Immediate Disqualification:**
   - Any negative number is not a palindrome due to the leading `-` sign (e.g., `-121`).
   - Any positive number ending in `0` cannot be a palindrome unless the number is `0` itself (e.g., `10` reversed starts with `0`).
2. **Reversing the Back Half:**
   - Repeatedly pop the last digit of `x` (`x % 10`) and push it onto `revertedNumber` (`revertedNumber * 10 + x % 10`).
   - Divide `x` by 10 in each step.
   - Stop as soon as `x <= revertedNumber`, which signifies that we have processed at least half of the digits.
3. **Equivalence Comparison:**
   - **Even Digits (e.g., `1221`):** At loop termination, `x == revertedNumber` (`12 == 12`).
   - **Odd Digits (e.g., `121`):** At loop termination, `x = 1` and `revertedNumber = 12`. The middle digit does not affect symmetry, so we compare `x == revertedNumber / 10`.

---

### 3. Execution Trace

**Input:** `x = 1221`

| Iteration | Initial `x` | `x % 10` | `revertedNumber` calculation | `x /= 10` | Condition (`x > revertedNumber`) |
| :---: | :---: | :---: | :---: | :---: | :---: |
| **0** | `1221` | - | `0` | `1221` | $1221 > 0$ (True) |
| **1** | `1221` | `1` | $0 \times 10 + 1 = 1$ | `122` | $122 > 1$ (True) |
| **2** | `122` | `2` | $1 \times 10 + 2 = 12$ | `12` | $12 > 12$ (**False**, Loop Ends) |

**Comparison:** `x == revertedNumber` $\rightarrow 12 == 12 \rightarrow$ **`true`**

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\log_{10} x)$
  - We divide the input number by 10 on every iteration. Since we stop at the midpoint, the loop runs for at most $\frac{1}{2} \log_{10} x$ steps.
- **Space Complexity:** $O(1)$ auxiliary space
  - Only two primitive integer variables (`x`, `revertedNumber`) are maintained.

---

### 5. Edge Cases & Key Takeaways

- **Single Digit ($0 \le x \le 9$):** Any single digit is trivially symmetric and evaluates to `true`.
- **Zero Suffix (`x = 10`):** The guard `x % 10 == 0 && x != 0` immediately returns `false` without looping.
- **Overflow Immunity:** Reversing only half of the digits guarantees that `revertedNumber` never exceeds $\approx \sqrt{x}$, entirely removing the possibility of 32-bit integer overflow.