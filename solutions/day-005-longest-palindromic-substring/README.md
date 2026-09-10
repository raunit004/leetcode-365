# Day 005: Longest Palindromic Substring

- **Platform:** LeetCode #5
- **Difficulty:** Medium
- **Topic:** Two Pointers, String, Dynamic Programming
- **Problem Link:** [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)
- **Language:** Java

---

### 1. Problem Statement
Given a string `s`, return the longest palindromic substring in `s`.

- A palindrome is a string that reads the same forward and backward.
- Substrings are contiguous sequences of characters within the string.

---

### 2. Intuition & Approach

#### Expand Around Center ($O(N^2)$ Time, $O(1)$ Space)
A palindrome mirrors around its center. A string of length $N$ has $2N - 1$ potential centers:
- $N$ single-character centers (odd-length palindromes like `"aba"` centered at `'b'`).
- $N - 1$ between-character centers (even-length palindromes like `"abba"` centered between `'b'` and `'b'`).

#### Algorithm Mechanics
1. Iterate every index `i` from $0$ to $N - 1$.
2. For each index, consider two centers:
   - **Odd Length:** `expandAroundCenter(s, i, i)`
   - **Even Length:** `expandAroundCenter(s, i, i + 1)`
3. Expand outward as long as boundaries are respected and characters match:
   $$\text{s.charAt(left)} == \text{s.charAt(right)}$$
4. When the loop terminates, the expansion overshoots by $1$ on each side:
   $$\text{Valid Length} = (\text{right} - 1) - (\text{left} + 1) + 1 = \text{right} - \text{left} - 1$$
5. Track the longest observed palindrome length `maxLen` and compute its start index:
   $$\text{start} = i - \lfloor \frac{\text{len} - 1}{2} \rfloor$$
6. Extract and return `s.substring(start, start + maxLen)`.

---

### 3. Execution Trace

**Input:** `s = "babad"`

| `i` | Odd Expansion (`i, i`) | Even Expansion (`i, i + 1`) | `len` | Updated `maxLen` | Updated `start` | Current Best Palindrome |
| :---: | :---: | :---: | :---: | :---: | :---: | :--- |
| `0` | `"b"` (len 1) | `""` (len 0) | 1 | 1 | $0 - 0 = 0$ | `"b"` |
| `1` | `"bab"` (len 3) | `""` (len 0) | 3 | 3 | $1 - 1 = 0$ | `"bab"` |
| `2` | `"aba"` (len 3) | `""` (len 0) | 3 | 3 (no change) | 0 | `"bab"` |
| `3` | `"a"` (len 1) | `""` (len 0) | 1 | 3 (no change) | 0 | `"bab"` |
| `4` | `"d"` (len 1) | `""` (len 0) | 1 | 3 (no change) | 0 | `"bab"` |

**Result:** `"bab"` (or `"aba"`)

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N^2)$
  - There are $2N - 1$ centers. Expanding each center outward takes up to $O(N)$ comparisons in the worst case (e.g., all identical characters like `"aaaaa"`).
- **Space Complexity:** $O(1)$ auxiliary space
  - Only integer pointers (`start`, `maxLen`, `left`, `right`) are maintained.

---

### 5. Edge Cases & Key Takeaways

- **Single Character Input (`"a"`):** Loops once, computes `maxLen = 1`, and returns the single character.
- **Even-Length Palindromes (`"cbbd"`):** Center `i = 1, i + 1 = 2` expands around `"bb"` and outputs length `2`.
- **Start Calculation Invariant:** The formula `i - (len - 1) / 2` cleanly maps both odd and even palindrome lengths to the exact start index without conditional branching.