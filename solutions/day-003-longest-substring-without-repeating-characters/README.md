# Day 003: Longest Substring Without Repeating Characters

- **Platform:** LeetCode #3
- **Difficulty:** Medium
- **Topic:** Hash Table, String, Sliding Window
- **Problem Link:** [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
- **Language:** Java

---

### 1. Problem Statement
Given a string `s`, find the length of the **longest substring** without duplicate characters.

- A **substring** is a contiguous non-empty sequence of characters within a string.
- `s` consists of English letters, digits, symbols, and spaces.

---

### 2. Intuition & Approach

#### Sliding Window with Direct Address Array
A naive sliding window expands a right pointer and contracts the left pointer one step at a time when a duplicate is found, leading to up to $2N$ pointer steps. 

We can optimize this to a strict single-pass $O(N)$ jump strategy:
1. **Direct ASCII Mapping:** Instead of a generic `HashMap`, an integer array of size $128$ (`int[] lastIndex = new int[128]`) maps standard ASCII character values directly to their most recent position.
2. **1-Based Stored Index:** We store `right + 1` in `lastIndex[c]`. This allows us to use `0` as the default "unseen" state without explicitly filling the array with `-1`.
3. **Monotonic Left Pointer Advancement:** When a character `c` is encountered at index `right`, we update the left boundary using:
   $$\text{left} = \max(\text{left}, \text{lastIndex}[c])$$
   The `Math.max` check ensures `left` never moves backward when encountering a previously seen character whose last recorded index lies outside the current active window.
4. **Dynamic Window Sizing:** The current valid substring length is $(\text{right} - \text{left} + 1)$. We track the maximum observed window size at each iteration.

---

### 3. Execution Trace

**Input:** `s = "abcabcbb"`

| `right` | `c` | `lastIndex[c]` before update | Updated `left` | Window Substring | Current Window Length | `maxLen` | `lastIndex[c]` after update |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| `0` | `'a'` | `0` | $\max(0, 0) = 0$ | `"a"` | $0 - 0 + 1 = 1$ | **`1`** | `lastIndex['a'] = 1` |
| `1` | `'b'` | `0` | $\max(0, 0) = 0$ | `"ab"` | $1 - 0 + 1 = 2$ | **`2`** | `lastIndex['b'] = 2` |
| `2` | `'c'` | `0` | $\max(0, 0) = 0$ | `"abc"` | $2 - 0 + 1 = 3$ | **`3`** | `lastIndex['c'] = 3` |
| `3` | `'a'` | `1` | $\max(0, 1) = 1$ | `"bca"` | $3 - 1 + 1 = 3$ | **`3`** | `lastIndex['a'] = 4` |
| `4` | `'b'` | `2` | $\max(1, 2) = 2$ | `"cab"` | $4 - 2 + 1 = 3$ | **`3`** | `lastIndex['b'] = 5` |
| `5` | `'c'` | `3` | $\max(2, 3) = 3$ | `"abc"` | $5 - 3 + 1 = 3$ | **`3`** | `lastIndex['c'] = 6` |
| `6` | `'b'` | `5` | $\max(3, 5) = 5$ | `"cb"` | $6 - 5 + 1 = 2$ | **`3`** | `lastIndex['b'] = 7` |
| `7` | `'b'` | `7` | $\max(5, 7) = 7$ | `"b"` | $7 - 7 + 1 = 1$ | **`3`** | `lastIndex['b'] = 8` |

**Final Result:** `3`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - The right pointer iterates across the string of length $N$ exactly once.
  - Character array lookups and arithmetic operations execute in $O(1)$ constant time.
- **Space Complexity:** $O(1)$ auxiliary space
  - The direct address table is fixed at 128 integer positions regardless of input string size $N$.

---

### 5. Edge Cases & Key Takeaways

- **Empty String (`""`):** The loop condition `0 < 0` immediately terminates and returns `maxLen = 0`.
- **All Identical Characters (`"bbbbb"`):** The left pointer jumps forward on every single step, maintaining a consistent window length of `1`.
- **Backward Jump Prevention:** The condition `left = Math.max(left, lastIndex[c])` prevents invalid backward moves. For example, in `"abba"`, when reaching the second `'a'`, `lastIndex['a'] = 1`, but `left` is already at index `2` due to the duplicate `'b'`. Taking the maximum keeps `left` anchored at `2`.