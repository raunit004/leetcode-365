# Day 046: Length of Last Word

- **Platform:** LeetCode #58[cite: 12]
- **Difficulty:** Easy[cite: 12]
- **Topic:** String
- **Problem Link:** [Length of Last Word](https://leetcode.com/problems/length-of-last-word/)
- **Language:** Java[cite: 12]
- **Time Complexity:** $O(N)$ (bounded in practice by trailing spaces plus the final word length)[cite: 12]
- **Space Complexity:** $O(1)$ auxiliary space[cite: 12]

---

### 1. Problem Overview
Given a string `s` consisting of words and spaces, return the length of the **last** word in the string[cite: 12].

A **word** is defined as a maximal substring consisting of non-space characters only[cite: 12].

---

### 2. Intuition & Approach

#### Backward Traversal (Two-Phase Scan):
Instead of splitting the entire string with `s.split(" ")` or allocating a trimmed copy via `s.trim()`, scan the string from right to left using a single index pointer[cite: 12]:

1. **Phase 1 — Skip Trailing Whitespace:**
   - Initialize pointer `i = s.length() - 1`[cite: 12].
   - Decrement `i` as long as `i >= 0` and `s.charAt(i) == ' '`[cite: 12].
   - This moves the pointer directly to the final character of the last word[cite: 12].
2. **Phase 2 — Count Word Characters:**
   - Continue decrementing `i` while `i >= 0` and `s.charAt(i) != ' '`[cite: 12].
   - Increment `length` for every non-space character encountered[cite: 12].
3. **Termination:**
   - The loop stops upon encountering an inner space delimiter or reaching before index `0`[cite: 12].
   - Return `length`[cite: 12].

---

### 3. Execution Trace

#### Sample Input: `s = "Hello World"`[cite: 12]

- Initial: `i = 10`, `length = 0`[cite: 12]

#### Phase 1: Trailing Space Check
- `s.charAt(10) == 'd'` $\neq$ `' '` $\implies$ skips immediately[cite: 12].

#### Phase 2: Counting Characters

| Step | Index `i` | Character `s.charAt(i)`[cite: 12] | Action | `length`[cite: 12] |
| :---: | :---: | :---: | :---: | :---: |
| 1 | 10 | `'d'`[cite: 12] | `length++`, `i--`[cite: 12] | 1 |
| 2 | 9 | `'l'`[cite: 12] | `length++`, `i--`[cite: 12] | 2 |
| 3 | 8 | `'r'`[cite: 12] | `length++`, `i--`[cite: 12] | 3 |
| 4 | 7 | `'o'`[cite: 12] | `length++`, `i--`[cite: 12] | 4 |
| 5 | 6 | `'W'`[cite: 12] | `length++`, `i--`[cite: 12] | 5 |
| 6 | 5 | `' '`[cite: 12] | `s.charAt(5) == ' '` $\implies$ Loop terminates[cite: 12] | **5**[cite: 12] |

**Output:** `5`[cite: 12]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - In the worst case (where the string contains a single word or consists largely of spaces), the pointer traverses up to $N$ characters[cite: 12]. In typical cases, it inspects only the suffix of the string and terminates early.
- **Space Complexity:** $O(1)$ auxiliary space
  - Evaluates characters in-place using two primitive integer variables (`i`, `length`) with zero string allocations or object instantiation[cite: 12].

---

### 5. Edge Cases & Key Takeaways

- **Trailing Spaces:** Handled by Phase 1, which discards any number of trailing whitespace characters before counting begins[cite: 12].
- **Single Word String:** If the string contains no spaces (e.g., `"world"`), Phase 2 proceeds until `i == -1` and returns `s.length()` correctly.
- **Single Character Word:** Correctly increments `length` to `1` before hitting boundary conditions.