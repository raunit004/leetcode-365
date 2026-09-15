# Day 010: Regular Expression Matching

- **Platform:** LeetCode #10
- **Difficulty:** Hard
- **Topic:** String, Dynamic Programming, Recursion
- **Problem Link:** [Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)
- **Language:** Java

---

### 1. Problem Statement
Given an input string `s` and a pattern `p`, implement regular expression matching with support for `'.'` and `'*'` where:
- `'.'` Matches any single character.
- `'*'` Matches zero or more of the preceding element.

The matching must cover the **entire** input string (not partial).

---

### 2. Intuition & Approach

#### 2D Dynamic Programming Table ($O(M \times N)$)
Let `dp[i][j]` represent whether the prefix `s[0...i-1]` matches the pattern prefix `p[0...j-1]`.

1. **Base Cases:**
   - `dp[0][0] = true`: Empty string matches empty pattern.
   - For an empty string ($i = 0$), pattern tokens with `*` can eliminate preceding elements:
     $$\text{if } p[j - 1] == \text{'*'}: \quad dp[0][j] = dp[0][j - 2]$$

2. **Transition Function:**
   For each cell `(i, j)` where $1 \le i \le m$ and $1 \le j \le n$:
   - **Case 1: Exact Match or Wildcard (`p[j - 1] == s[i - 1]` or `p[j - 1] == '.'`):**
     $$dp[i][j] = dp[i - 1][j - 1]$$
   - **Case 2: Star Quantifier (`p[j - 1] == '*'`):**
     - **Match 0 times:** Discard both `*` and its preceding character:
       $$dp[i][j] = dp[i][j - 2]$$
     - **Match 1 or more times:** If preceding character matches current string character (`p[j - 2] == s[i - 1]` or `p[j - 2] == '.'`):
       $$dp[i][j] = dp[i][j] \lor dp[i - 1][j]$$

---

### 3. Execution Trace

**Input:** `s = "aab"`, `p = "c*a*b"` ($m = 3, n = 5$)

| `i \ j` | `""` (0) | `c` (1) | `*` (2) | `a` (3) | `*` (4) | `b` (5) |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **`""` (0)** | **T** | F | **T** (`c*` $\rightarrow \epsilon$) | F | **T** (`a*` $\rightarrow \epsilon$) | F |
| **`a` (1)** | F | F | F | F | **T** (`a*` matches `a`) | F |
| **`a` (2)** | F | F | F | F | **T** (`a*` matches `aa`) | F |
| **`b` (3)** | F | F | F | F | F | **T** (`b` matches `b`) |

**Final Result:** `dp[3][5] = true`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(M \times N)$
  - Filling the 2D matrix of size $(M + 1) \times (N + 1)$ requires evaluating constant-time boolean expressions at each cell.
- **Space Complexity:** $O(M \times N)$
  - Requires an $(M + 1) \times (N + 1)$ boolean table.

---

### 5. Edge Cases & Key Takeaways

- **Empty String Matching Patterns:** Patterns like `a*b*c*` successfully match `""` via `dp[0][j] = dp[0][j-2]`.
- **Preceding Character Matching Any (`.*`):** `.*` can absorb any single character repeated arbitrary times by combining `prevP == '.'` with the recurrence `dp[i - 1][j]`.
- **Index Offsets:** Using 1-based indexing in the DP table cleanly accommodates empty string prefixes and avoids index-out-of-bounds guards.