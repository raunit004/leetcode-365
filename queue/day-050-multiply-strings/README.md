# Day 050: Multiply Strings

- **Platform:** LeetCode #43[cite: 16]
- **Difficulty:** Medium[cite: 16]
- **Topic:** Math, String, Simulation
- **Problem Link:** [Multiply Strings](https://leetcode.com/problems/multiply-strings/)
- **Language:** Java[cite: 16]
- **Time Complexity:** $O(M \times N)$ where $M$ and $N$ are the lengths of `num1` and `num2`[cite: 16]
- **Space Complexity:** $O(M + N)$ auxiliary space for the position array[cite: 16]

---

### 1. Problem Overview
Given two non-negative integers `num1` and `num2` represented as strings, return the product of `num1` and `num2`, also represented as a string[cite: 16].

You must not use any built-in `BigInteger` library or convert the inputs directly into integer types[cite: 16].

---

### 2. Intuition & Approach

#### Array-Based Long Multiplication Simulation:
For numbers with lengths up to 200 digits, the product can take up to $M + N$ digits[cite: 16]:
1. **Base Case Check:**
   - If either string is `"0"`, immediately return `"0"`[cite: 16].
2. **Digit Placement Invariant:**
   - When multiplying the digit at `num1[i]` with `num2[j]`, the resulting two-digit product maps directly to indices `p1 = i + j` (tens place / carry) and `p2 = i + j + 1` (units place) in the product array `pos` of length $M + N$[cite: 16].
3. **Iterative Accumulation:**
   - Traverse backwards from the least significant digits of both inputs (`i` from $M - 1$ down to 0, `j` from $N - 1$ down to 0)[cite: 16].
   - Compute `sum = (num1[i] * num2[j]) + pos[p2]`[cite: 16].
   - Update `pos[p2] = sum % 10` and carry over `pos[p1] += sum / 10`[cite: 16].
4. **Trimming Leading Zeros:**
   - Convert the array into a string using `StringBuilder` while skipping any leading zeros (`sb.length() == 0 && p == 0`)[cite: 16].
   - Return `"0"` if the builder remains empty, otherwise return the string representation[cite: 16].

---

### 3. Execution Trace

#### Sample Input: `num1 = "12"`, `num2 = "3"`

- $M = 2$, $N = 1 \implies$ `pos` array length = $2 + 1 = 3$ (`[0, 0, 0]`)

| Step | `i` (`num1[i]`) | `j` (`num2[j]`) | `p1, p2` | `mul` | `sum = mul + pos[p2]` | `pos[p2] = sum % 10` | `pos[p1] += sum / 10` | `pos` State |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 1 (`'2'`) | 0 (`'3'`) | 1, 2 | 6 | $6 + 0 = 6$ | 6 | $0 + 0 = 0$ | `[0, 0, 6]` |
| 2 | 0 (`'1'`) | 0 (`'3'`) | 0, 1 | 3 | $3 + 0 = 3$ | 3 | $0 + 0 = 0$ | `[0, 3, 6]` |

- Skip leading `0` at index 0[cite: 16].
- Collect digits: `'3'`, `'6'` $\implies$ **`"36"`**

---

### 4. Complexity Analysis

- **Time Complexity:** $O(M \times N)$
  - Uses nested loops where each digit of `num1` is multiplied by each digit of `num2`[cite: 16]. Each multiplication and carry addition runs in $O(1)$ arithmetic operations[cite: 16].
- **Space Complexity:** $O(M + N)$ auxiliary space
  - The intermediate digits are stored in an integer array `pos` of size $M + N$[cite: 16].

---

### 5. Edge Cases & Key Takeaways

- **Multiplication by Zero:** Guarding `"0".equals(num1) || "0".equals(num2)` short-circuits in $O(1)$ time, avoiding unnecessary loop executions and string conversions[cite: 16].
- **Index Alignment:** The invariant `p1 = i + j` and `p2 = i + j + 1` naturally handles carry propagation across columns without separate shifting or alignment logic[cite: 16].
- **Arbitrary Size Handling:** Multiplying character-by-character allows working with numbers hundreds of digits long without hitting primitive integer limits[cite: 16].