# Day 049: Combination Sum II

- **Platform:** LeetCode #40[cite: 14]
- **Difficulty:** Medium[cite: 14]
- **Topic:** Array, Backtracking[cite: 14]
- **Problem Link:** [Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)
- **Language:** Java[cite: 14]
- **Time Complexity:** $O(N \log N + 2^N)$ where $N$ is the number of candidates[cite: 14]
- **Space Complexity:** $O(N)$ auxiliary space for recursion stack and candidate combination buffer

---

### 1. Problem Overview
Given a collection of candidate numbers (`candidates`) and a target number (`target`), find all unique combinations in `candidates` where the candidate numbers sum to `target`[cite: 14].

Each number in `candidates` may only be used **once** in the combination[cite: 14]. The solution set must not contain duplicate combinations[cite: 14].

---

### 2. Intuition & Approach

#### Backtracking with Duplicate Pruning:
Unlike Combination Sum I (where candidates are distinct and reusable indefinitely), this problem contains duplicate values and restricts each index to single-use[cite: 14]:

1. **Ascending Sort Preprocessing:**
   - Sorting `candidates` groups identical numbers consecutively and establishes monotonic ordering[cite: 14].
2. **Early Pruning via Ordered Property (`break`):**
   - Because the array is sorted, if `candidates[i] > remain`, every subsequent candidate $j > i$ will also satisfy `candidates[j] > remain`[cite: 14]. Breaking the loop avoids exploring branches guaranteed to exceed the sum[cite: 14].
3. **Sibling Deduplication (`continue`):**
   - When picking a candidate at index `i` at the current recursion depth:
     - If $i > \text{start}$ and `candidates[i] == candidates[i - 1]`, choosing `candidates[i]` would generate a combination set identical to what `candidates[i - 1]` already produced[cite: 14].
     - Skipping via `continue` suppresses redundant subtrees without missing combinations that legitimately use repeated values from deeper levels (since `i == start` remains permitted)[cite: 14].
4. **Single-Use Enforcement:**
   - Advance the start index to `i + 1` in recursive calls (`backtrack(..., i + 1, ...)`)[cite: 14], ensuring each specific instance is consumed at most once[cite: 14].

---

### 3. Execution Trace

#### Sample Input: `candidates = [10, 1, 2, 7, 6, 1, 5]`, `target = 8`[cite: 14]

Sorted: `[1, 1, 2, 5, 6, 7, 10]`

| Level | `start` | `i` | Value | Duplicate Check (`i > start && c[i] == c[i-1]`) | Action | `remain` After Pick |
| :---: | :---: | :---: | :---: | :---: | :--- | :---: |
| 1 | 0 | 0 | `1` | False ($i == \text{start}$) | Pick `1`, explore `start = 1` | 7 |
| 2 | 1 | 1 | `1` | False ($i == \text{start}$) | Pick second `1`, explore `start = 2` | 6 |
| 3 | 2 | 2 | `2` | False ($i == \text{start}$) | Pick `2`, explore `start = 3` | 4 |
| 3 | 2 | 3 | `5` | False | Pick `5`, explore `start = 4` | 1 |
| 4 | 4 | 4 | `6` | False | $6 > 1 \implies$ `break`[cite: 14] | — |
| 3 | 2 | 4 | `6` | False | Pick `6` $\implies$ **Match `[1, 1, 6]`** | 0 |
| 1 | 0 | 1 | `1` | **True** ($1 > 0$ and `c[1] == c[0]`)[cite: 14] | **Skip via `continue`** (avoids duplicate sets)[cite: 14] | — |
| 1 | 0 | 2 | `2` | False | Pick `2`, explore `start = 3` | 6 |
| 2 | 3 | 5 | `6` | False | Pick `6` $\implies$ **Match `[2, 6]`** | 0 |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N \log N + 2^N)$
  - Sorting takes $O(N \log N)$[cite: 14]. Each distinct subset can be either included or excluded, leading to an upper bound of $2^N$ states in the decision tree. Sorting combined with branch pruning (`break`) and duplicate skipping (`continue`) significantly cuts down practical operations[cite: 14].
- **Space Complexity:** $O(N)$ auxiliary space
  - The recursion stack reaches a maximum depth of $N$ levels[cite: 14]. The temporary tracking list `current` stores at most $N$ elements at any moment[cite: 14].

---

### 5. Edge Cases & Key Takeaways

- **Sibling Duplicates vs. Parent-Child Duplicates:** Checking `i > start` rather than `i > 0` allows duplicates across separate recursion depths (e.g., using both copies of `1` to build `[1, 1, 6]`) while prohibiting duplicate choices across identical horizontal branching positions[cite: 14].
- **Early Termination:** Sorting ensures non-decreasing order so that `candidates[i] > remain` acts as an absolute stopping condition for that loop level[cite: 14].