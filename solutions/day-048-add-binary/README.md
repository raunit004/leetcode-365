# Day 048: Add Binary

- **Platform:** LeetCode #67[cite: 13]
- **Difficulty:** Easy[cite: 13]
- **Topic:** Math, String, Bit Manipulation, Simulation
- **Problem Link:** [Add Binary](https://leetcode.com/problems/add-binary/)
- **Language:** Java[cite: 13]
- **Time Complexity:** $O(\max(N, M))$ where $N$ and $M$ are lengths of strings `a` and `b`[cite: 13]
- **Space Complexity:** $O(\max(N, M))$ auxiliary space to construct the result[cite: 13]

---

### 1. Problem Overview
Given two binary strings `a` and `b`, return their sum as a binary string[cite: 13].

---

### 2. Intuition & Approach

#### Column-by-Column Simulation with Carry:
Because binary strings can be arbitrarily long, converting them into standard integer primitives (`int` or `long`) would trigger numeric overflow. Instead, we simulate elementary bit addition from right to left[cite: 13]:

1. **Two Pointers:**
   - Initialize pointer `i = a.length() - 1` and `j = b.length() - 1` pointing to the least significant bits[cite: 13].
   - Maintain a scalar integer `carry = 0`[cite: 13].
2. **Bitwise Column Addition:**
   - Continue processing while `i >= 0`, `j >= 0`, or a non-zero `carry` remains[cite: 13].
   - Accumulate the current sum: `total = carry`[cite: 13].
   - If pointer `i >= 0`, extract digit `a.charAt(i) - '0'`, add to `total`, and decrement `i`[cite: 13].
   - If pointer `j >= 0`, extract digit `b.charAt(j) - '0'`, add to `total`, and decrement `j`[cite: 13].
3. **Record Bit & Next Carry:**
   - The resulting bit placed in the current position is `total % 2`[cite: 13].
   - The carry forward to the next higher significance column is `total / 2`[cite: 13].
   - Append `total % 2` to `StringBuilder`[cite: 13].
4. **Final Formatting:**
   - Since bits were collected from least significant to most significant, call `sb.reverse().toString()` before returning[cite: 13].

---

### 3. Execution Trace

#### Sample Input: `a = "11"`, `b = "1"`[cite: 13]

| Iteration | `i` | `j` | `a[i]` | `b[j]` | Previous `carry` | `total` | Current Bit (`total % 2`) | Next `carry` (`total / 2`) | `sb` Content |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 1 | 0 | `'1'` | `'1'` | 0 | $0 + 1 + 1 = 2$ | 0 | 1 | `"0"` |
| 2 | 0 | -1 | `'1'` | — | 1 | $1 + 1 = 2$ | 0 | 1 | `"00"` |
| 3 | -1 | -1 | — | — | 1 | $1$ | 1 | 0 | `"001"` |

- Reversing `"001"` yields **`"100"`**[cite: 13].

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\max(N, M))$
  - The loop iterates $\max(N, M) + 1$ times in the worst case where a final carry propagates[cite: 13]. Reversing the string runs in linear time relative to output length[cite: 13].
- **Space Complexity:** $O(\max(N, M))$ auxiliary space
  - The `StringBuilder` stores the resulting string of length at most $\max(N, M) + 1$[cite: 13].

---

### 5. Edge Cases & Key Takeaways

- **Unequal String Lengths:** Handled cleanly by guarding individual bit reads with `if (i >= 0)` and `if (j >= 0)`[cite: 13].
- **Final Carry:** The loop condition `carry != 0` ensures that a remaining carry bit at the most significant position (e.g., `"11"` + `"1"` $\rightarrow$ `"100"`) is appended before exiting[cite: 13].
- **Zero Input Values:** When `a = "0"` and `b = "0"`, evaluates in one step producing `"0"` without unnecessary trailing padding[cite: 13].