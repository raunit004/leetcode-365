# Day 060: Permutations II

- **Platform:** LeetCode #47[cite: 22]
- **Difficulty:** Medium[cite: 22]
- **Topic:** Array, Backtracking[cite: 22]
- **Problem Link:** [Permutations II](https://leetcode.com/problems/permutations-ii/)
- **Language:** Java[cite: 22]
- **Time Complexity:** $O(N \cdot N!)$ where $N$ is `nums.length`[cite: 22]
- **Space Complexity:** $O(N)$ auxiliary space for recursion stack and visited tracking[cite: 22]

---

### 1. Problem Overview
Given a collection of numbers, `nums`, that might contain duplicates, return all possible unique permutations in any order[cite: 22].

---

### 2. Intuition & Approach

#### Backtracking with Sibling Deduplication:
When duplicate elements are present in `nums`, standard permutation generation produces duplicate leaf combinations[cite: 22]. To prevent duplicate branches without relying on a `Set`:

1. **Sort Preprocessing:**
   - Call `Arrays.sort(nums)` to group duplicate values consecutively[cite: 22].
2. **Boolean Visited Tracking:**
   - Maintain a boolean array `used` of length $N$ to track elements chosen in the active branch[cite: 22].
   - If an element is already in use (`used[i] == true`), skip it[cite: 22].
3. **The Sibling Pruning Invariant:**
   - When evaluating index $i$, check:
     $$\text{if } (i > 0 \land \text{nums}[i] == \text{nums}[i - 1] \land \neg\text{used}[i - 1]) \implies \text{continue}$$[cite: 22]
   - **Why `!used[i - 1]`?**
     - If `used[i - 1]` is `true`, `nums[i - 1]` is an active ancestor in the current branch (e.g., placing the second `'1'` after the first `'1'`), which is allowed[cite: 22].
     - If `used[i - 1]` is `false`, `nums[i - 1]` was already evaluated and backtracked at the current decision level[cite: 22]. Choosing `nums[i]` would create a duplicate branch[cite: 22]. Skipping it enforces a strict relative order among identical numbers[cite: 22].
4. **Base Case:**
   - Once `current.size() == nums.length`, store a snapshot copy (`new ArrayList<>(current)`) into `result` and return[cite: 22].

---

### 3. Execution Trace

#### Sample Input: `nums = [1, 1, 2]`[cite: 22]

Sorted: `[1, 1, 2]`[cite: 22]

| Step | Index `i` | Element | `used` Array | Condition Check | Action |
| :---: | :---: | :---: | :---: | :---: | :--- |
| 1 | 0 | `1`[cite: 22] | `[T, F, F]` | Valid | Recurse with prefix `[1]`[cite: 22] |
| 2 | 1 | `1`[cite: 22] | `[T, T, F]` | `used[0] == true` $\implies$ valid child | Recurse with prefix `[1, 1]` |
| 3 | 2 | `2`[cite: 22] | `[T, T, T]` | Valid | Base case $\implies$ **Add `[1, 1, 2]`**[cite: 22] |
| 4 | — | — | — | Backtrack to Level 2 | Try next unused index at Level 2 |
| 5 | 2 | `2`[cite: 22] | `[T, F, T]` | Valid | Recurse with prefix `[1, 2]` |
| 6 | 1 | `1`[cite: 22] | `[T, T, T]` | Valid | Base case $\implies$ **Add `[1, 2, 1]`**[cite: 22] |
| 7 | — | — | — | Backtrack to Level 1 | Level 1 evaluates $i = 1$ |
| 8 | 1 | `1`[cite: 22] | `[F, F, F]` | `nums[1] == nums[0]` & `!used[0]`[cite: 22] | **Skip via `continue`** (prevents duplicate set)[cite: 22] |
| 9 | 2 | `2`[cite: 22] | `[F, F, T]` | Valid | Recurse with prefix `[2]` |
| 10 | 0 | `1`[cite: 22] | `[T, F, T]` | Valid | Recurse with prefix `[2, 1]` |
| 11 | 1 | `1`[cite: 22] | `[T, T, T]` | `used[0] == true` $\implies$ valid child | Base case $\implies$ **Add `[2, 1, 1]`**[cite: 22] |

- **Output:** `[[1, 1, 2], [1, 2, 1], [2, 1, 1]]`[cite: 22]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N \cdot N!)$
  - Sorting takes $O(N \log N)$[cite: 22]. The number of unique permutations generated is $\frac{N!}{\prod (k_i!)}$, where $k_i$ represents the frequency of each distinct value. Each generated permutation takes $O(N)$ time to copy into `result`[cite: 22].
- **Space Complexity:** $O(N)$ auxiliary space
  - The recursion stack reaches a maximum depth of $N$[cite: 22]. The `used` boolean array and `current` list consume $O(N)$ memory[cite: 22].

---

### 5. Edge Cases & Key Takeaways

- **All Elements Identical (`nums = [2, 2, 2]`):** The condition `nums[i] == nums[i - 1] && !used[i - 1]` prunes redundant branches at each level, generating only `[[2, 2, 2]]`[cite: 22].
- **Pre-sorted Relative Ordering:** Enforcing that identical values are consumed from left to right prevents duplicate permutations without hash set overhead[cite: 22].