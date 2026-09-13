# Day 008: String to Integer (atoi)

- **Platform:** LeetCode #8
- **Difficulty:** Medium
- **Topic:** String
- **Problem Link:** [String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/)
- **Language:** Java

---

### 1. Problem Statement
Implement the `myAtoi(string s)` function, which converts a string to a 32-bit signed integer.

The algorithm for `myAtoi(string s)` is as follows:
1. **Whitespace:** Ignore any leading whitespace (`" "`).
2. **Signedness:** Determine the sign by checking if the next character is `'-'` or `'+'`, assuming positive if neither is present.
3. **Conversion:** Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, return `0`.
4. **Rounding:** If the integer is out of the 32-bit signed integer range $[-2^{31}, 2^{31} - 1]$, clamp the integer to remain in range. Specifically, integers $< -2^{31}$ clamp to $-2^{31}$ (`Integer.MIN_VALUE`), and integers $> 2^{31} - 1$ clamp to $2^{31} - 1$ (`Integer.MAX_VALUE`).

---

### 2. Intuition & Approach

#### Linear Scan with In-Flight Clamping
The parsing process maps to a deterministic sequential state machine:
1. **Leading Whitespace Skip:** Advance pointer `i` until reaching the first non-space character. If string ends, return `0`.
2. **Sign Detection:** If character at `i` is `'+'` or `'-'`, record the polarity multiplier (`1` or `-1`) and advance `i` by 1.
3. **Digit Accumulation & Clamping:**
   - For every character between `'0'` and `'9'`, extract numeric value `digit = c - '0'`.
   - Prevent 32-bit overflow *before* calculating `total * 10 + digit`:
     - If `total > Integer.MAX_VALUE / 10`, multiplying by 10 will definitely overflow.
     - If `total == Integer.MAX_VALUE / 10` and `digit > 7`, adding the digit exceeds `2147483647` (or `-2147483648` when negative).
     - In either overflow state, immediately return `Integer.MAX_VALUE` or `Integer.MIN_VALUE` based on the active sign.
4. **Non-Digit Termination:** Any non-digit character encountered during numeric parsing immediately breaks the loop and returns the accumulated total.

---

### 3. Execution Trace

**Input:** `s = "   -042"`

| Step | `i` | Char `s.charAt(i)` | Action | State / Accumulator |
| :---: | :---: | :---: | :--- | :--- |
| **1** | `0..2` | `' '` | Skip whitespace | `i` advances to `3` |
| **2** | `3` | `'-'` | Record sign | `sign = -1`, `i` advances to `4` |
| **3** | `4` | `'0'` | Accumulate digit $0$ | `total = 0 * 10 + 0 = 0` |
| **4** | `5` | `'4'` | Accumulate digit $4$ | `total = 0 * 10 + 4 = 4` |
| **5** | `6` | `'2'` | Accumulate digit $2$ | `total = 4 * 10 + 2 = 42` |
| **End**| `7` | End of string | Multiply by sign | `42 * -1 = -42` |

**Final Output:** `-42`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - A single linear pass traverses each character of the string at most once.
- **Space Complexity:** $O(1)$
  - Constant auxiliary space; uses scalar primitive variables (`i`, `sign`, `total`, `threshold`).

---

### 5. Edge Cases & Key Takeaways

- **Empty / Whitespace-Only:** Strings such as `""` or `"    "` cleanly terminate early returning `0`.
- **Invalid Prefix:** Input starting with invalid letters (e.g., `"words and 987"`) fails the initial digit check and returns `0`.
- **Mid-String Interruption:** Inputs like `"0-1"` or `"1337c0d3"` read up to the invalid character and return the parsed prefix (`0` and `1337` respectively).
- **Asymmetric 32-bit Limits:** Positive boundary ends in `7` (`2147483647`), negative ends in `8` (`-2147483648`). If `total == threshold && digit > 7`, clamping handles both bounds cleanly.