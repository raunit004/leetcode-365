# Day 045: Combination Sum

- **Platform:** LeetCode #39[cite: 11]
- **Difficulty:** Medium[cite: 11]
- **Topic:** Array, Backtracking[cite: 11]
- **Problem Link:** [Combination Sum](https://leetcode.com/problems/combination-sum/)
- **Language:** Java[cite: 11]
- **Time Complexity:** $O(N \log N + N^{\frac{T}{M}})$ where $N$ is array length, $T$ is target, and $M$ is $\min(\text{candidates})$
- **Space Complexity:** $O(\frac{T}{M})$ auxiliary recursion stack depth

---

### 1. Problem Overview
Given an array of distinct integers `candidates` and a target integer `target`, return a list of all unique combinations of `candidates` where the chosen numbers sum to `target`[cite: 11]. Combinations may be returned in any order[cite: 11].

The same number may be chosen from `candidates` an unlimited number of times[cite: 11]. Two combinations are unique if the frequency of at least one chosen number differs[cite: 11].

---

### 2. Intuition & Approach

#### Backtracking with Sort-Based Early Pruning:
A straightforward backtracking approach creates an exploration tree by trying every candidate repeatedly[cite: 11]. To optimize branching and prevent exploring invalid states:

1. **Ascending Sort Preprocessing:**
   - Sorting `candidates` ensures elements appear in non-decreasing order[cite: 11].
2. **Early Termination (`break`):**
   - If `candidates[i] > remain`, every subsequent candidate in the sorted array will also exceed `remain`[cite: 11]. We terminate the loop immediately via `break` instead of continuing through dead ends[cite: 11].
3. **Candidate Reuse Without Duplicate Permutations:**
   - When descending into recursive calls, we pass the current loop index `i` as the next `start` position: `backtrack(candidates, remain - candidates[i], i, current, result)`[cite: 11].
   - Passing `i` rather than `i + 1` allows unlimited reuse of `candidates[i]`[cite: 11].
   - Restricting iterations to indices $\ge i$ guarantees combinations are generated in non-decreasing order, naturally avoiding duplicate permutations like `[2, 3, 2]` vs `[2, 2, 3]`[cite: 11].
4. **Base Case:**
   - If `remain == 0`, a valid combination has been formed[cite: 11]. A deep snapshot copy (`new ArrayList<>(current)`) is stored into `result`[cite: 11].

---

### 3. Execution Trace

#### Sample Input: `candidates = [2, 3, 6, 7]`, `target = 7`[cite: 11]

Sorted: `[2, 3, 6, 7]`[cite: 11]

| Recursion Depth | `remain` | Candidate Picked | `current` List | Action / State |
| :---: | :---: | :---: | :---: | :--- |
| Depth 0 | 7 | 2 | `[2]` | Recurse with `remain = 5`, `start = 0`[cite: 11] |
| Depth 1 | 5 | 2 | `[2, 2]` | Recurse with `remain = 3`, `start = 0`[cite: 11] |
| Depth 2 | 3 | 2 | `[2, 2, 2]` | Recurse with `remain = 1`, `start = 0` |
| Depth 3 | 1 | 2 | — | $2 > 1 \implies$ `break`, backtrack to Depth 2[cite: 11] |
| Depth 2 | 3 | 3 | `[2, 2, 3]` | `remain = 0` $\implies$ **Add `[2, 2, 3]` to result**[cite: 11] |
| Depth 0 | 7 | 3 | `[3]` | Recurse with `remain = 4`, `start = 1` |
| Depth 1 | 4 | 3 | `[3, 3]` | Recurse with `remain = 1`, `start = 1` |
| Depth 2 | 1 | 3 | — | $3 > 1 \implies$ `break`, backtrack |
| Depth 0 | 7 | 6 | `[6]` | Recurse with `remain = 1`, `start = 2` |
| Depth 1 | 1 | 6 | — | $6 > 1 \implies$ `break`, backtrack |
| Depth 0 | 7 | 7 | `[7]` | `remain = 0` $\implies$ **Add `[7]` to result**[cite: 11] |

**Output:** `[[2, 2, 3], [7]]`[cite: 11]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N \log N + N^{\frac{T}{M}})$
  - Sorting takes $O(N \log N)$ where $N$ is the number of candidates[cite: 11].
  - The maximum depth of the recursion tree is $\frac{T}{M}$ where $T$ is `target` and $M = \min(\text{candidates})$. At each level, the branching factor is up to $N$. Early pruning with `break` reduces the search space compared to the theoretical upper bound[cite: 11].
- **Space Complexity:** $O(\frac{T}{M})$ auxiliary space
  - The recursion call stack and `current` list store at most $\frac{T}{M}$ elements before unwinding[cite: 11].

---

### 5. Edge Cases & Key Takeaways

- **Target Smaller Than Minimum Candidate:** Handled by loop condition `candidates[i] > remain` on the very first element, instantly returning `[]`[cite: 11].
- **Unlimited Reuse of Elements:** Passing `i` rather than `i + 1` enables selecting the same element consecutively without infinite recursion, as `remain` decreases monotonically[cite: 11].
- **No Duplicate Combinations:** Enforcing `i = start` prevents unordered index traversals[cite: 11].