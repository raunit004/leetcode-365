# Day 020: Valid Parentheses

- **Platform:** LeetCode #20[cite: 16]
- **Difficulty:** Easy[cite: 16]
- **Topic:** String, Stack
- **Problem Link:** [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
- **Language:** Java[cite: 16]

---

### 1. Problem Statement
Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid[cite: 16].

An input string is valid if[cite: 16]:
1. Open brackets must be closed by the same type of brackets[cite: 16].
2. Open brackets must be closed in the correct order[cite: 16].
3. Every close bracket has a corresponding open bracket of the same type[cite: 16].

---

### 2. Intuition & Approach

#### LIFO Stack with Expected Closing Character Push ($O(N)$ Time, $O(N)$ Space)
Brackets follow a strict Last-In, First-Out (LIFO) pairing structure, making a stack the optimal data structure[cite: 16]:

1. **Parity Check:**
   - Any valid string must have an even length[cite: 16]. If `s.length() % 2 != 0`, immediately return `false`[cite: 16].
2. **Pushing Expected Counterparts:**
   - Whenever an opening bracket is encountered (`(`, `{`, `[`), push its matching closing bracket (`)`, `}`, `]`) onto the stack[cite: 16].
   - This simplifies the comparison logic: when a closing bracket arrives, the top element of the stack must equal the current character directly[cite: 16].
3. **Closing Bracket Evaluation:**
   - If the stack is empty (no open bracket preceded it) or `stack.pop() != c` (mismatched bracket type), the sequence is invalid; return `false`[cite: 16].
4. **Final Balanced State:**
   - After iterating through all characters, return `stack.isEmpty()`[cite: 16]. A non-empty stack indicates unclosed open brackets[cite: 16].

---

### 3. Execution Trace

**Input:** `s = "{[]}"`

| Step | Char `c` | Condition Matched | Stack Operation | Stack State (Top $\rightarrow$ Bottom) | Valid? |
| :---: | :---: | :---: | :---: | :---: | :---: |
| **0** | — | Initial state | — | `[]` | Yes |
| **1** | `'{'` | Open bracket | `stack.push('}')`[cite: 16] | `['}']` | Yes |
| **2** | `'['` | Open bracket | `stack.push(']')`[cite: 16] | `[']', '}']` | Yes |
| **3** | `']'` | Close bracket | `stack.pop() == ']'`[cite: 16] | `['}']` | Yes |
| **4** | `'}'` | Close bracket | `stack.pop() == '}'`[cite: 16] | `[]` | Yes |
| **End** | — | End of string | `stack.isEmpty()`[cite: 16] | `[]` | **True**[cite: 16] |

**Final Result:** `true`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - The algorithm iterates through the string of length $N$ once, performing $O(1)$ push and pop operations per character[cite: 16].
- **Space Complexity:** $O(N)$
  - In the worst case (e.g., `"(((((("`), the stack stores up to $N$ characters[cite: 16].

---

### 5. Edge Cases & Key Takeaways

- **Odd Length Input:** Checked in $O(1)$ before allocation, avoiding stack allocations for strings that cannot be paired[cite: 16].
- **Starting with Closing Bracket (`"]"`):** The stack is initially empty, causing `stack.isEmpty()` to trigger an immediate `false` return[cite: 16].
- **`ArrayDeque` Over Legacy `Stack`:** Using `ArrayDeque` in Java avoids the synchronized lock overhead of the legacy `java.util.Stack` class[cite: 16].