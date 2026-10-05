# Day 030: Substring with Concatenation of All Words

- **Platform:** LeetCode #30[cite: 8]
- **Difficulty:** Hard[cite: 8]
- **Topic:** Hash Table, String, Sliding Window[cite: 8]
- **Problem Link:** [Substring with Concatenation of All Words](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)[cite: 8]
- **Language:** Java[cite: 8]
- **Time Complexity:** $O(N \times L)$
- **Space Complexity:** $O(M \times L)$

---

### 1. Problem Statement
You are given a string `s` and an array of strings `words`[cite: 8]. All the strings of `words` are of the **same length**[cite: 8].

A **concatenated string** is a string that exactly contains all the strings of any permutation of `words` concatenated[cite: 8].

Return an array of the starting indices of all the concatenated substrings in `s`[cite: 8]. You can return the answer in **any order**[cite: 8].

- $1 \le \text{s.length} \le 10^4$
- $1 \le \text{words.length} \le 5000$
- $1 \le \text{words}[i]\text{.length} \le 30$
- `s` and `words[i]` consist of lowercase English letters[cite: 8].

---

### 2. Intuition & Approach

#### Multi-Offset Sliding Window with Frequency Table ($O(N \times L)$ Time, $O(M \times L)$ Space)
Because every word in `words` shares an identical length $L = \text{wordLen}$, substrings can be partitioned into discrete word tokens[cite: 8]. Scanning every index $0 \le i \le N - M \times L$ independently can re-parse words redundantly:

1. **Partition by Remainder Offsets:**
   - Any valid concatenated window begins at some offset modulo $L$[cite: 8].
   - Running the sliding window across each `offset` $\in [0, L - 1]$ covers every possible starting position without redundant tokenization[cite: 8].
2. **Two-Pointer Token Window:**
   - For a fixed offset, maintain `left` and `right` indices initialized to `offset`[cite: 8].
   - Advance `right` in increments of $L$, slicing token `sub = s.substring(right, right + L)`[cite: 8].
3. **Frequency Verification:**
   - If `sub` is present in `targetCounts`:
     - Increment its frequency in `windowCounts` and increment `wordsMatched`[cite: 8].
     - If `windowCounts.get(sub) > targetCounts.get(sub)`, the word is duplicated beyond allowance: contract `left` forward by $L$, decrementing frequencies until the excess occurrence is evicted[cite: 8].
     - If `wordsMatched == words.length`, record `left` into the result list[cite: 8].
   - If `sub` is not in `targetCounts`:
     - The window is broken: clear `windowCounts`, reset `wordsMatched = 0`, and jump `left = right`[cite: 8].

---

### 3. Execution Trace

**Input:** `s = "barfoothefoobarman"`, `words = ["foo", "bar"]` ($L = 3$, $M = 2$, $\text{totalLen} = 6$)[cite: 8]  
`targetCounts = {"foo": 1, "bar": 1}`[cite: 8]

#### Trace at `offset = 0`:

| Step | `[left, right)` | Extracted Word `sub` | `targetCounts` Match?[cite: 8] | Window Counts | Matched Words | Action Taken |
| :---: | :---: | :---: | :---: | :---: | :---: | :--- |
| **1** | `[0, 3)` | `"bar"`[cite: 8] | Yes | `{"bar": 1}` | $1$ | Advance `right`[cite: 8] |
| **2** | `[0, 6)` | `"foo"`[cite: 8] | Yes | `{"bar": 1, "foo": 1}` | $2$ ($== M$)[cite: 8] | **Add index `0` to result**[cite: 8] |
| **3** | `[0, 9)` | `"the"`[cite: 8] | No | Empty | $0$ | Unknown word: reset `left = right = 9`[cite: 8] |
| **4** | `[9, 12)` | `"foo"`[cite: 8] | Yes | `{"foo": 1}` | $1$ | Advance `right`[cite: 8] |
| **5** | `[9, 15)` | `"bar"`[cite: 8] | Yes | `{"foo": 1, "bar": 1}` | $2$ ($== M$)[cite: 8] | **Add index `9` to result**[cite: 8] |
| **6** | `[9, 18)` | `"man"`[cite: 8] | No | Empty | $0$ | Unknown word: reset `left = right = 18` |

**Final Returned Indices:** `[0, 9]`[cite: 8]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N \times L)$
  - There are $L$ distinct offset runs[cite: 8]. In each offset run, `right` slides through $N / L$ word blocks, and `left` advances at most $N / L$ times[cite: 8]. Substring extraction and hash map lookups take $O(L)$ time each, yielding $L \times O((N / L) \times L) = O(N \times L)$.
- **Space Complexity:** $O(M \times L)$
  - The `targetCounts` and `windowCounts` maps hold at most $M$ distinct string keys of length $L$[cite: 8].

---

### 5. Edge Cases & Key Takeaways

- **Target Exceeds String Length ($s.\text{length}() < \text{totalLen}$):** Handled upfront in $O(1)$, returning an empty list immediately[cite: 8].
- **Duplicate Words in Target:** Tracked via integer frequency counters rather than sets, ensuring exact matching of repeated words (e.g., `["word", "word"]`)[cite: 8].
- **Word Overlaps:** Offset iteration across $0, 1, \dots, L - 1$ ensures no candidate window alignment is missed[cite: 8].