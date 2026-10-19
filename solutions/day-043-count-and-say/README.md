# Day 043: Count and Say

- **Platform:** LeetCode #38[cite: 12]
- **Difficulty:** Medium[cite: 12]
- **Topic:** String, Two Pointers
- **Problem Link:** [Count and Say](https://leetcode.com/problems/count-and-say/)[cite: 12]
- **Language:** Java[cite: 12]
- **Time Complexity:** $O(L)$ where $L$ is the cumulative length of all generated sequences up to $n$
- **Space Complexity:** $O(M)$ auxiliary space where $M$ is the length of the $n$-th string

---

### 1. Problem Overview
The **count-and-say** sequence is a series of digit strings defined by the recursive recurrence:
- $\text{countAndSay}(1) = \text{"1"}$[cite: 12]
- $\text{countAndSay}(n)$ is the **run-length encoding (RLE)** of $\text{countAndSay}(n - 1)$[cite: 12]

Run-length encoding groups consecutive identical characters and replaces each block with the group's length followed by the digit character itself (e.g., `"3322251"` is encoded into `"23"` + `"32"` + `"15"` + `"11"` $\rightarrow$ `"23321511"`)[cite: 12].

Given an integer $n$, compute and return the $n$-th element of the sequence[cite: 12].

---

### 2. Intuition & Approach

#### Two-Pointer Run-Length Encoding (RLE):
1. **Base Value:** Initialize `current = "1"`[cite: 12].
2. **Sequential Simulation:** Iterate from step $2$ through $n$, updating `current` into its run-length representation at each phase[cite: 12].
3. **Contiguous Group Detection:**
   - Scan string `current` using pointer $i$[cite: 12].
   - Advance pointer $j$ starting at $i$ while characters match: `current.charAt(j) == current.charAt(i)`[cite: 12].
   - The contiguous segment length is $\text{count} = j - i$[cite: 12].
   - Append `count` followed by `current.charAt(i)` to a `StringBuilder`[cite: 12].
   - Shift $i \leftarrow j$ to jump directly to the start of the next contiguous group[cite: 12].
4. **Result:** After completing step $n$, return `current`[cite: 12].

---

### 3. Execution Trace

#### Step-by-Step Derivation up to $n = 4$[cite: 12]:

| Step ($k$) | Previous String | Contiguous Blocks | Encoded Representation | Output String |
| :---: | :---: | :---: | :---: | :---: |
| 1 | — | Base Case | `1` | `"1"`[cite: 12] |
| 2 | `"1"` | One `1` (`11`) | `1` copy of `'1'` | `"11"`[cite: 12] |
| 3 | `"11"` | Two `1`s (`21`) | `2` copies of `'1'` | `"21"`[cite: 12] |
| 4 | `"21"` | One `2`, One `1` (`1211`) | `1` copy of `'2'` + `1` copy of `'1'` | **`"1211"`**[cite: 12] |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(L)$
  - Each step scans the previous string in linear time using two pointers without redundant character revisits[cite: 12]. The total runtime is proportional to the sum of sequence lengths generated up to step $n$.
- **Space Complexity:** $O(M)$ auxiliary space
  - The `StringBuilder` allocates memory proportional to the length of the active sequence string being constructed[cite: 12].

---

### 5. Edge Cases & Key Takeaways

- **Base Step ($n = 1$):** Loop body is bypassed entirely when $n = 1$, instantly returning `"1"`[cite: 12].
- **String Construction Efficiency:** Using `StringBuilder` instead of repeated string concatenations avoids $O(K^2)$ intermediate string allocations per iteration.
- **Two-Pointer Traversal:** Advancing $j$ until character divergence ensures each character index in `current` is examined at most twice (once by $j$, once by $i$)[cite: 12].