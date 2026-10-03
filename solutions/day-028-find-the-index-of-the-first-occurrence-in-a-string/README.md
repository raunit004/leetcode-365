# Day 028: Find the Index of the First Occurrence in a String

- **Platform:** LeetCode #28[cite: 15]
- **Difficulty:** Easy[cite: 15]
- **Topic:** Two Pointers, String, String Matching
- **Problem Link:** [Find the Index of the First Occurrence in a String](https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/)[cite: 15]
- **Language:** Java[cite: 15]
- **Time Complexity:** $O((n - m + 1) \times m)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Statement
Given two strings `needle` and `haystack`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`[cite: 15].

- $1 \le \text{haystack.length}, \text{needle.length} \le 10^4$[cite: 15]
- `haystack` and `needle` consist of only lowercase English characters[cite: 15].

---

### 2. Intuition & Approach

#### Sliding Window Character Match ($O((n - m + 1) \times m)$ Time, $O(1)$ Space)
The problem asks for exact substring matching:

1. **Length Pre-check:**
   - If pattern length $m$ exceeds target length $n$ ($m > n$), `needle` cannot fit inside `haystack`; return `-1` immediately[cite: 15].
2. **Window Sliding:**
   - Slide an anchor pointer $i$ from $0$ up to $n - m$[cite: 15]. Any anchor past $n - m$ would not have enough remaining characters to form `needle`[cite: 15].
3. **Internal Character Comparison:**
   - For each start position $i$, scan with pointer $j$ from $0$ to $m - 1$[cite: 15].
   - Compare `haystack.charAt(i + j)` with `needle.charAt(j)`[cite: 15].
   - If a mismatch occurs, break and advance $i$[cite: 15].
   - If $j == m$, all characters matched sequentially; return the starting index $i$[cite: 15].
4. **Fallback:**
   - If no complete match is found across all candidate start indices, return `-1`[cite: 15].

---

### 3. Execution Trace

**Input:** `haystack = "sadbutsad"`, `needle = "sad"` ($n = 9, m = 3$)[cite: 15]

| Candidate Index `i` | Current Window | `j` comparisons | Match Status | Action |
| :---: | :---: | :---: | :---: | :--- |
| `0` | `"sad"` | `j=0` ('s' == 's')<br>`j=1` ('a' == 'a')<br>`j=2` ('d' == 'd')[cite: 15] | Full Match ($j == 3$)[cite: 15] | Return index `0`[cite: 15] |

**Final Result:** `0`[cite: 15]

---

### 4. Complexity Analysis

- **Time Complexity:** $O((n - m + 1) \times m)$
  - In the worst case (e.g., `haystack = "aaaaaaaaab"`, `needle = "aaab"`), we check $m$ characters for each of the $n - m + 1$ window positions. Given average English texts, mismatches terminate after 1-2 characters, running in near $O(n)$ time.
- **Space Complexity:** $O(1)$ auxiliary space
  - Operates using index pointers directly without creating substrings or allocating auxiliary memory[cite: 15].

---

### 5. Edge Cases & Key Takeaways

- **Needle Longer Than Haystack:** Handled in $O(1)$ by checking `m > n` before executing any loop[cite: 15].
- **Single Character Match:** Works seamlessly when both $n = 1$ and $m = 1$.
- **Window Boundary Limit:** Checking up to $i \le n - m$ eliminates unnecessary iterations and prevents `IndexOutOfBoundsException`[cite: 15].