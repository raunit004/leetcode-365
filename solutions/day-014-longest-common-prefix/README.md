# Day 014: Longest Common Prefix

- **Platform:** LeetCode #14[cite: 12]
- **Difficulty:** Easy[cite: 12]
- **Topic:** String, Trie[cite: 12]
- **Problem Link:** [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/)
- **Language:** Java[cite: 12]

---

### 1. Problem Statement
Write a function to find the longest common prefix string amongst an array of strings[cite: 12].

If there is no common prefix, return an empty string `""`[cite: 12].

- $1 \le \text{strs.length} \le 200$[cite: 12]
- $0 \le \text{strs}[i]\text{.length} \le 200$[cite: 12]
- $\text{strs}[i]$ consists of only lowercase English letters if it is non-empty[cite: 12].

---

### 2. Intuition & Approach

#### Vertical Character Scanning ($O(S)$ Time, $O(1)$ Space)
Instead of comparing strings horizontally (comparing string 1 with 2, then the result with 3), vertical scanning examines characters column-by-column across all strings at the same index:

1. **Pivot Character Selection:**
   Use the first string `strs[0]` as the reference template[cite: 12]. Iterate index `i` from $0$ to `strs[0].length() - 1`[cite: 12].
2. **Column Consistency Check:**
   For each index `i`, iterate through all other strings `strs[j]` ($j \ge 1$)[cite: 12]:
   - If index `i` reaches the end of string `strs[j]` (`i == strs[j].length()`), no longer common prefix can exist[cite: 12].
   - If the character at `strs[j].charAt(i)` differs from `c = strs[0].charAt(i)`, mismatch occurs[cite: 12].
3. **Immediate Truncation:**
   Upon encountering either condition, immediately return `strs[0].substring(0, i)`[cite: 12].
4. **Complete Match Fallback:**
   If the outer loop completes without any mismatch, `strs[0]` is itself a common prefix across all strings[cite: 12].

This approach terminates early as soon as a mismatch is found in any word, avoiding reading the full length of later strings when the prefix is short.

---

### 3. Execution Trace

**Input:** `strs = ["flower", "flow", "flight"]`[cite: 12]

| Column `i` | Char `c` (`strs[0][i]`) | Word `j = 1` (`"flow"`) | Word `j = 2` (`"flight"`) | Match? | Action |
| :---: | :---: | :---: | :---: | :---: | :--- |
| `0` | `'f'` | `'f'` | `'f'` | Yes | Advance column |
| `1` | `'l'` | `'l'` | `'l'` | Yes | Advance column |
| `2` | `'o'` | `'o'` | `'i'` | **Mismatch** | Return `strs[0].substring(0, 2)` $\rightarrow$ `"fl"` |

**Final Result:** `"fl"`[cite: 12]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(S)$
  - In the worst case where all $N$ strings are identical of length $M$, it takes $O(S)$ comparisons, where $S = N \times M$ is the sum of all characters across all strings. In early mismatch scenarios, it runs in $O(\text{minLen} \times N)$.
- **Space Complexity:** $O(1)$ auxiliary space
  - Evaluates characters directly via index pointers without allocating additional character buffers or sets.

---

### 5. Edge Cases & Key Takeaways

- **Single String Input (`strs = ["a"]`):** Loop completes and directly returns `strs[0]`.
- **First String Empty (`strs = ["", "b"]`):** Outer loop condition `i < strs[0].length()` immediately evaluates to false, returning `""`.
- **Short Intermediate String (`strs = ["ab", "a"]`):** Condition `i == strs[j].length()` triggers at index `1`, preventing `StringIndexOutOfBoundsException`.