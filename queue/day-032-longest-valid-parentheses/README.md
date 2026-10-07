# Day 032: Longest Valid Parentheses

- **Platform:** LeetCode #32[cite: 9]
- **Difficulty:** Hard[cite: 9]
- **Topic:** String, Dynamic Programming, Stack
- **Problem Link:** [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/)[cite: 9]
- **Language:** Java[cite: 9]
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Statement
Given a string containing just the characters `'('` and `')'`, return the length of the longest valid (well-formed) parentheses substring[cite: 9].

- $0 \le \text{s.length} \le 3 \times 10^4$[cite: 9]
- `s[i]` is `'('` or `')'`[cite: 9].

---

### 2. Intuition & Approach

#### Index Stack with Sentinel Boundary ($O(N)$ Time, $O(N)$ Space)
A valid parentheses substring requires continuous paired brackets without unmatched prefixes or suffixes[cite: 9]:

1. **Sentinel Base Initialization:**
   - Initialize the stack by pushing `-1`[cite: 9]. This index acts as the reference point before the start of any candidate valid substring[cite: 9].
2. **Pushing Opening Brackets:**
   - Whenever `'('` is encountered at index `i`, push `i` onto the stack[cite: 9].
3. **Resolving Closing Brackets:**
   - When `')'` is encountered, pop the top index from the stack[cite: 9]:
     - **Stack becomes empty:** The popped element was the base boundary, meaning the current `')'` has no matching opening bracket[cite: 9]. Push `i` onto the stack to establish a new base boundary for subsequent substrings[cite: 9].
     - **Stack remains non-empty:** The current `')'` successfully pairs with the popped `'('`[cite: 9]. The top of the stack (`stack.peek()`) represents the index right before the start of the valid substring[cite: 9]. Calculate the valid length as $i - \text{stack.peek()}$ and update `maxLength`[cite: 9].

---

### 3. Execution Trace

**Input:** `s = ")()())"`[cite: 9]  
**Initial State:** `stack = [-1]`, `maxLength = 0`[cite: 9]

| Index `i` | Char `s[i]`[cite: 9] | Action Taken[cite: 9] | Stack After Action[cite: 9] | Substring Length Computed[cite: 9] | `maxLength`[cite: 9] |
| :---: | :---: | :--- | :---: | :---: | :---: |
| `0` | `')'`[cite: 9] | Pop `-1` $\rightarrow$ Stack empty $\rightarrow$ Push `0`[cite: 9] | `[0]`[cite: 9] | — | `0`[cite: 9] |
| `1` | `'('`[cite: 9] | Push index `1`[cite: 9] | `[0, 1]`[cite: 9] | — | `0`[cite: 9] |
| `2` | `')'`[cite: 9] | Pop `1` $\rightarrow$ Stack top is `0`[cite: 9] | `[0]`[cite: 9] | $2 - 0 = 2$[cite: 9] | `2`[cite: 9] |
| `3` | `'('`[cite: 9] | Push index `3`[cite: 9] | `[0, 3]`[cite: 9] | — | `2`[cite: 9] |
| `4` | `')'`[cite: 9] | Pop `3` $\rightarrow$ Stack top is `0`[cite: 9] | `[0]`[cite: 9] | $4 - 0 = 4$[cite: 9] | **`4`**[cite: 9] |
| `5` | `')'`[cite: 9] | Pop `0` $\rightarrow$ Stack empty $\rightarrow$ Push `5`[cite: 9] | `[5]`[cite: 9] | — | `4`[cite: 9] |

**Final Result:** `4`[cite: 9]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Traverses the input string of length $N$ in a single pass[cite: 9]. Every character is pushed and popped from the stack at most once[cite: 9].
- **Space Complexity:** $O(N)$
  - In the worst case (e.g., `"(((((..."`), the stack stores up to $N + 1$ integer indices[cite: 9].

---

### 5. Edge Cases & Key Takeaways

- **Empty String (`""`):** Handled immediately without entering the loop, returning `0` safely[cite: 9].
- **Leading Unmatched Closing Brackets:** Popping the sentinel and pushing the index resets the baseline without throwing exceptions[cite: 9].
- **Nested and Concatenated Valid Pairs:** Calculating $i - \text{stack.peek()}$ seamlessly merges adjacent valid segments like `"()()"` as well as nested patterns like `"(())"`[cite: 9].