# Day 013: Roman to Integer

- **Platform:** LeetCode #13
- **Difficulty:** Easy
- **Topic:** Hash Table, Math, String
- **Problem Link:** [Roman to Integer](https://leetcode.com/problems/roman-to-integer/)
- **Language:** Java

---

### 1. Problem Statement
Roman numerals are represented by seven different symbols:
- `I`: 1
- `V`: 5
- `X`: 10
- `L`: 50
- `C`: 100
- `D`: 500
- `M`: 1000

Numerals are typically written largest to smallest from left to right. When a smaller value symbol appears before a larger one, subtraction applies:
- `I` before `V` (5) and `X` (10) forms `4` and `9`.
- `X` before `L` (50) and `C` (100) forms `40` and `90`.
- `C` before `D` (500) and `M` (1000) forms `400` and `900`.

Given a valid Roman numeral string `s`, convert it to an integer.

---

### 2. Intuition & Approach

#### Look-Ahead Comparison ($O(N)$ Time, $O(1)$ Space)
Instead of parsing multi-character tokens (`IV`, `IX`, etc.) using regex or substring slicing, compare adjacent characters linearly:

1. **Subtractive Rule:**
   For any symbol at index `i`, check its neighbor at `i + 1`:
   - If $\text{value}(s[i]) < \text{value}(s[i + 1])$, the symbol is part of a subtractive pair $\rightarrow$ subtract $\text{value}(s[i])$ from the running sum.
   - If $\text{value}(s[i]) \ge \text{value}(s[i + 1])$, the symbol is standard additive $\rightarrow$ add $\text{value}(s[i])$ to the running sum.
2. **Terminal Element:**
   The final character at index $n - 1$ has no subsequent neighbor to trigger subtraction, so its value is unconditionally added.
3. **Switch-Case Over HashMap:**
   Using a private helper method with a Java primitive `switch` statement resolves character values at CPU branch level, bypassing hash collisions and object allocation overhead.

---

### 3. Execution Trace

**Input:** `s = "MCMXCIV"` ($n = 7$)

| Index `i` | Char `s[i]` | Next `s[i+1]` | `curr` | `next` | Comparison | Action | Running Total |
| :---: | :---: | :---: | :---: | :---: | :---: | :--- | :--- |
| `0` | `'M'` | `'C'` | 1000 | 100 | $1000 \ge 100$ | Add 1000 | `1000` |
| `1` | `'C'` | `'M'` | 100 | 1000 | $100 < 1000$ | Subtract 100 | `900` |
| `2` | `'M'` | `'X'` | 1000 | 10 | $1000 \ge 10$ | Add 1000 | `1900` |
| `3` | `'X'` | `'C'` | 10 | 100 | $10 < 100$ | Subtract 10 | `1890` |
| `4` | `'C'` | `'I'` | 100 | 1 | $100 \ge 1$ | Add 100 | `1990` |
| `5` | `'I'` | `'V'` | 1 | 5 | $1 < 5$ | Subtract 1 | `1989` |
| `6` (last) | `'V'` | — | 5 | — | Terminal | Add 5 | **`1994`** |

**Final Result:** `1994`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - The single pass evaluates each character of string `s` exactly once. Because the problem bounds input length to $N \le 15$, execution time is effectively $O(1)$.
- **Space Complexity:** $O(1)$
  - Operates purely on primitive scalar counters; zero heap allocations or dynamic collections.

---

### 5. Edge Cases & Key Takeaways

- **Uniform Additive Strings (`"III"`, `"VIII"`):** Every left-to-right check satisfies `curr >= next`, cleanly accumulating the sum.
- **Single Character Input (`"D"`):** The loop condition `i < n - 1` does not execute; the single character is directly processed by the final terminal addition step.
- **No Look-Ahead Array Bound Exceeded:** Looping up to `n - 2` prevents `StringIndexOutOfBoundsException` on `i + 1`.