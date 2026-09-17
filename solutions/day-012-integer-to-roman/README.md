# Day 012: Integer to Roman

- **Platform:** LeetCode #12
- **Difficulty:** Medium
- **Topic:** Hash Table, Math, String
- **Problem Link:** [Integer to Roman](https://leetcode.com/problems/integer-to-roman/)
- **Language:** Java

---

### 1. Problem Statement
Seven different symbols represent Roman numerals:
- `I`: 1
- `V`: 5
- `X`: 10
- `L`: 50
- `C`: 100
- `D`: 500
- `M`: 1000

Roman numerals are formed by appending the conversions of decimal place values from highest to lowest. Converting a decimal place value has the following rules:
- If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol, subtract its value, and continue with the remainder.
- If the value starts with 4 or 9, use the subtractive form: `4` (`IV`), `9` (`IX`), `40` (`XL`), `90` (`XC`), `400` (`CD`), and `900` (`CM`).
- Only powers of 10 (`I`, `X`, `C`, `M`) can be appended consecutively at most 3 times.

Given an integer `num` ($1 \le \text{num} \le 3999$), convert it to a Roman numeral.

---

### 2. Intuition & Approach

#### Greedy Decomposition ($O(1)$ Time, $O(1)$ Space)
Roman numerals follow a greedy base system. By hardcoding both the standard additive symbols and the six subtractive edge cases into descending lookup arrays, the conversion simplifies into finding the largest value that fits into `num`:

1. **Predefined Table of 13 Mappings:**
   - Values: `[1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]`
   - Symbols: `["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]`
2. **Greedy Subtraction:**
   - Iterate through the values array from largest to smallest.
   - While `num >= values[i]`, subtract `values[i]` from `num` and append `symbols[i]` to our `StringBuilder`.
   - Break early when `num == 0`.

Because subtractive forms (`CM`, `CD`, `XC`, `XL`, `IX`, `IV`) are prioritized before their smaller additive counterparts, no special conditional branches are needed during iteration.

---

### 3. Execution Trace

**Input:** `num = 1994`

| Step | Current `num` | Matched `values[i]` | Matched `symbols[i]` | Remaining `num` | Accumulated String |
| :---: | :---: | :---: | :---: | :---: | :--- |
| **1** | 1994 | 1000 | `"M"` | 994 | `"M"` |
| **2** | 994 | 900 | `"CM"` | 94 | `"MCM"` |
| **3** | 94 | 90 | `"XC"` | 4 | `"MCMXC"` |
| **4** | 4 | 4 | `"IV"` | 0 | `"MCMXCIV"` |

**Final Result:** `"MCMXCIV"`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(1)$
  - The lookup tables have a fixed length of 13 entries. Because the problem constraint caps `num <= 3999`, the maximum number of loop iterations is strictly bounded (at most 15 appends for `3888 = MMMDCCCLXXXVIII`), making execution time strictly constant.
- **Space Complexity:** $O(1)$
  - Constant auxiliary memory; uses two static immutable lookup arrays and a `StringBuilder` holding at most 15 characters.

---

### 5. Edge Cases & Key Takeaways

- **Subtractive Forms:** Hardcoding entries like 900 (`CM`) and 40 (`XL`) directly eliminates complex lookahead or post-processing logic.
- **Input Boundaries ($1 \le \text{num} \le 3999$):** Roman numerals do not have a zero symbol; starting at 1 and capping at 3999 avoids ambiguity and prevents unbounded loops.
- **Cache-Friendly Direct Parallel Arrays:** Using two parallel primitive arrays (`int[]` and `String[]`) incurs zero boxing overhead compared to object maps or dynamic hash lookups.