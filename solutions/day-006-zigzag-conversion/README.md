# Day 006: Zigzag Conversion

- **Platform:** LeetCode #6
- **Difficulty:** Medium
- **Topic:** String
- **Problem Link:** [Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/)
- **Language:** Java

---

### 1. Problem Statement
The string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows like this:

```text
P   A   H   N
A P L S I I G
Y   I   R
```

And then read line by line: `"PAHNAPLSIIGYIR"`.

Write the code that will take a string and make this conversion given a specific row count.

---

### 2. Intuition & Approach

#### Mathematical Direct Indexing ($O(N)$ Time, $O(1)$ Extra Space)
Rather than allocating multiple `StringBuilder` buckets or a 2D matrix, the characters can be gathered row by row by calculating the cyclic index jump pattern directly.

1. **Cycle Length Formula:**
   A full vertical-down and diagonal-up cycle spans:
   $$\text{cycle} = \text{numRows} + (\text{numRows} - 2) = 2 \times \text{numRows} - 2$$
2. **First and Last Rows:**
   - The first row contains characters at indices: $0, \text{cycle}, 2 \times \text{cycle}, \dots$
   - The last row contains characters at indices: $\text{numRows} - 1, (\text{numRows} - 1) + \text{cycle}, \dots$
3. **Internal (Middle) Rows:**
   Every middle row ($1 \le \text{row} < \text{numRows} - 1$) contains two indices per cycle:
   - Primary downward character: $i$
   - Intermediate diagonal character: $i + 2 \times (\text{numRows} - \text{row})$
4. **Zero Heap Allocation Overhead:** 
   Populating a fixed-size `char[]` buffer directly avoids the continuous reallocation overhead of dynamic collections.

---

### 3. Execution Trace

**Input:** `s = "PAYPALISHIRING"`, `numRows = 3`  
- $\text{Length } N = 14$
- $\text{cycle} = 3 + \max(0, 1) = 4$

| Row | Cycle Step | Selected Indices | Collected Characters | Buffer Output So Far |
| :---: | :---: | :---: | :---: | :--- |
| **Row 0** | Step $4$ | `0, 4, 8, 12` | `'P', 'A', 'H', 'N'` | `"PAHN"` |
| **Row 1** | Primary + Zag | `1, 3, 5, 7, 9, 11, 13` | `'A', 'P', 'L', 'S', 'I', 'I', 'G'` | `"PAHNAPLSIIG"` |
| **Row 2** | Step $4$ | `2, 6, 10` | `'Y', 'I', 'R'` | `"PAHNAPLSIIGYIR"` |

**Final Result:** `"PAHNAPLSIIGYIR"`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Every character index in the string is calculated and visited exactly once.
- **Space Complexity:** $O(1)$ auxiliary space
  - The algorithm writes directly into a pre-sized character buffer of size $N$ without intermediate string concatenations or hash structures.

---

### 5. Edge Cases & Key Takeaways

- **Single Row / Out of Bounds (`numRows <= 1` or `numRows >= s.length()`):** No zigzag can form; returning the input string immediately avoids division by zero or negative step counts.
- **Direct Address Traversal:** Bypassing intermediate container allocations (`List<StringBuilder>`) yields zero GC pressure and near-zero runtime latency.