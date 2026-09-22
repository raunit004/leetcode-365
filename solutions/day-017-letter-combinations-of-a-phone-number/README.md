# Day 017: Letter Combinations of a Phone Number

- **Platform:** LeetCode #17[cite: 13]
- **Difficulty:** Medium[cite: 13]
- **Topic:** Hash Table, String, Backtracking[cite: 13]
- **Problem Link:** [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number/)
- **Language:** Java[cite: 13]

---

### 1. Problem Statement
Given a string containing digits from `2-9` inclusive, return all possible letter combinations that the number could represent[cite: 13]. Return the answer in any order[cite: 13].

A mapping of digits to letters (just like on telephone buttons) is given below[cite: 13]. Note that `1` does not map to any letters[cite: 13].

- `2`: `"abc"`[cite: 13]
- `3`: `"def"`[cite: 13]
- `4`: `"ghi"`[cite: 13]
- `5`: `"jkl"`[cite: 13]
- `6`: `"mno"`[cite: 13]
- `7`: `"pqrs"`[cite: 13]
- `8`: `"tuv"`[cite: 13]
- `9`: `"wxyz"`[cite: 13]

Constraints:
- $1 \le \text{digits.length} \le 4$[cite: 13]
- $\text{digits}[i]$ is a digit in the range `['2', '9']`[cite: 13]

---

### 2. Intuition & Approach

#### Backtracking / Depth-First Search ($O(4^N \times N)$ Time, $O(N)$ Space)
The problem represents generating the Cartesian product of letter sets corresponding to each digit in `digits`[cite: 13]. This forms a decision tree where each level corresponds to an index in `digits`[cite: 13]:

1. **Lookup Table:**
   Use a direct-index static array `KEYPAD` where index maps directly to digit value (`digits.charAt(index) - '0'`)[cite: 13].
2. **Recursive Exploration:**
   - **Base Case:** When `index == digits.length()`, a complete combination of length $N$ has been formed; snapshot `current.toString()` into `result` and return[cite: 13].
   - **Branching:** Retrieve the candidate characters for the current digit[cite: 13]. Iterate through each character, append it to a shared `StringBuilder`, and recurse to `index + 1`[cite: 13].
3. **Backtracking Step:**
   Upon returning from recursion, delete the last appended character via `current.deleteCharAt(current.length() - 1)` to reset the buffer for the next branch[cite: 13].

---

### 3. Execution Trace

**Input:** `digits = "23"`[cite: 13]  
- `digits[0] = '2'` $\rightarrow$ letters: `"abc"`[cite: 13]
- `digits[1] = '3'` $\rightarrow$ letters: `"def"`[cite: 13]

```text
                     "" (index = 0)
         /                 |                 \
       'a'                'b'                'c'
     /  |  \            /  |  \            /  |  \
   'd' 'e' 'f'        'd' 'e' 'f'        'd' 'e' 'f'
    |   |   |          |   |   |          |   |   |
  "ad" "ae" "af"     "bd" "be" "bf"     "cd" "ce" "cf"
```

**Final Collected Combinations:** `["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]`[cite: 13]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(4^N \times N)$
  - In the worst case where digits contain only 7 or 9 (which map to 4 characters each), the decision tree has up to $4^N$ leaves. Building each string of length $N$ takes $O(N)$ time. For $N \le 4$, $4^4 \times 4 = 1024$ operations[cite: 13].
- **Space Complexity:** $O(N)$ auxiliary space
  - The maximum recursion depth is equal to the length of digits $N \le 4$[cite: 13]. The temporary `StringBuilder` holds at most $N$ characters.

---

### 5. Edge Cases & Key Takeaways

- **Empty Input (`""`):** Handled before entering recursion; returns an empty list `[]` instead of `[""]`[cite: 13].
- **Mutable Buffer Reuse:** Reusing a single `StringBuilder` with explicit backtracking eliminates unnecessary intermediate string object allocations during depth-first traversal[cite: 13].
- **Static Lookup Array:** Mapping digits via `digits.charAt(index) - '0'` avoids hash computation overhead compared to `java.util.Map`[cite: 13].