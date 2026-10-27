# Day 070: Wildcard Matching

- **Platform:** LeetCode #44[cite: 23]
- **Difficulty:** Hard[cite: 23]
- **Topic:** String, Dynamic Programming, Greedy, Backtracking
- **Problem Link:** [Wildcard Matching](https://leetcode.com/problems/wildcard-matching/)
- **Language:** Java[cite: 23]
- **Time Complexity:** $O(S \times P)$ worst-case, $O(S + P)$ average-case where $S$ is `s.length()` and $P$ is `p.length()`[cite: 23]
- **Space Complexity:** $O(1)$ auxiliary space[cite: 23]

---

### 1. Problem Overview
Given an input string `s` and a pattern `p`, implement wildcard pattern matching with support for `'?'` and `'*'` where[cite: 23]:
- `'?'` matches any single character[cite: 23].
- `'*'` matches any sequence of characters (including the empty sequence)[cite: 23].

The matching must cover the **entire** input string (not partial)[cite: 23].

---

### 2. Intuition & Approach

#### Greedy Two-Pointer Backtracking with Star Anchor:
While this can be solved using $O(S \times P)$ dynamic programming, a two-pointer approach tracking the most recent `'*'` runs in $O(1)$ auxiliary space and avoids constructing an explicit lookup matrix[cite: 23]:

1. **Direct Match / Wildcard `'?'`:**
   - If characters match (`p.charAt(pIdx) == s.charAt(sIdx)`) or the pattern provides `'?'`[cite: 23], advance both pointers: `sIdx++`, `pIdx++`[cite: 23].
2. **Wildcard `'*'` Discovery:**
   - When encountering `'*'`, record checkpoints: `starIdx = pIdx` and `sMatch = sIdx`[cite: 23].
   - Greedily test whether `'*'` can match **0 characters** by advancing the pattern pointer alone: `pIdx++`[cite: 23].
3. **Mismatch Recovery (Backtracking to `'*'`):**
   - If a mismatch occurs later and a prior `'*'` exists (`starIdx != -1`)[cite: 23]:
     - Increment `sMatch++` (force `'*'` to absorb one more character from `s`)[cite: 23].
     - Reset `sIdx = sMatch` and reset pattern inspection immediately after the star: `pIdx = starIdx + 1`[cite: 23].
4. **No Asterisk Fallback:**
   - If characters mismatch and no `'*'` is available to absorb the failure, matching fails immediately: return `false`[cite: 23].
5. **Pattern Exhaustion:**
   - After matching all characters in `s`, skip any remaining trailing `'*'` symbols in `p`[cite: 23].
   - If the pattern is fully consumed (`pIdx == pLen`), the match is valid[cite: 23].

---

### 3. Execution Trace

#### Sample Input: `s = "adceb"`, `p = "*a*b"`

| Step | `sIdx` (`s[sIdx]`) | `pIdx` (`p[pIdx]`) | Condition Triggered | `starIdx` | `sMatch` | Action Taken |
| :---: | :---: | :---: | :---: | :---: | :---: | :--- |
| 1 | 0 (`'a'`) | 0 (`'*'`) | `p[pIdx] == '*'`[cite: 23] | 0 | 0 | Anchor star: `starIdx = 0`, `sMatch = 0`, `pIdx = 1`[cite: 23] |
| 2 | 0 (`'a'`) | 1 (`'a'`) | `s[0] == p[1]` (`'a' == 'a'`)[cite: 23] | 0 | 0 | Advance both: `sIdx = 1`, `pIdx = 2`[cite: 23] |
| 3 | 1 (`'d'`) | 2 (`'*'`) | `p[pIdx] == '*'`[cite: 23] | 2 | 1 | Anchor new star: `starIdx = 2`, `sMatch = 1`, `pIdx = 3`[cite: 23] |
| 4 | 1 (`'d'`) | 3 (`'b'`) | Mismatch (`'d' != 'b'`), `starIdx != -1`[cite: 23] | 2 | 2 | Backtrack: `sMatch = 2`, `sIdx = 2`, `pIdx = 3`[cite: 23] |
| 5 | 2 (`'c'`) | 3 (`'b'`) | Mismatch (`'c' != 'b'`), `starIdx != -1`[cite: 23] | 2 | 3 | Backtrack: `sMatch = 3`, `sIdx = 3`, `pIdx = 3`[cite: 23] |
| 6 | 3 (`'e'`) | 3 (`'b'`) | Mismatch (`'e' != 'b'`), `starIdx != -1`[cite: 23] | 2 | 4 | Backtrack: `sMatch = 4`, `sIdx = 4`, `pIdx = 3`[cite: 23] |
| 7 | 4 (`'b'`) | 3 (`'b'`) | `s[4] == p[3]` (`'b' == 'b'`)[cite: 23] | 2 | 4 | Advance both: `sIdx = 5`, `pIdx = 4`[cite: 23] |

- `sIdx == sLen` (5 == 5), loop exits[cite: 23].
- Trailing `'*'` cleanup check: `pIdx == 4 == pLen`[cite: 23].
- Returns **`true`**[cite: 23].

---

### 4. Complexity Analysis

- **Time Complexity:** $O(S \times P)$ worst-case, $O(S + P)$ average-case
  - In pathological cases (such as `s = "aaaaaaaa"` with `p = "*aaaaa"`), every mismatch backtracks $pIdx$, giving $O(S \times P)$. In practice and for standard test suites, pointer advancement is predominantly linear, executing in $O(S + P)$ time.
- **Space Complexity:** $O(1)$ auxiliary space
  - Solved in-place using scalar index pointers (`sIdx`, `pIdx`, `starIdx`, `sMatch`) without allocating lookup tables or recursion frames[cite: 23].

---

### 5. Edge Cases & Key Takeaways

- **Empty String and Empty Pattern:** `sLen = 0, pLen = 0` bypasses the loop and returns `pIdx == pLen` $\implies$ `true`.
- **Pattern with Only Asterisks (`p = "*****"`):** Matches any input `s` of arbitrary length as `starIdx` resets and trailing stars are consumed[cite: 23].
- **Immediate Mismatch Without Stars:** When `s = "cb"` and `p = "?a"`[cite: 23], the second character mismatches with `starIdx == -1`[cite: 23], safely returning `false` on line 22 without infinite loop hazards[cite: 23].