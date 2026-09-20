# Day 015: 3Sum

- **Platform:** LeetCode #15[cite: 13]
- **Difficulty:** Medium[cite: 13]
- **Topic:** Array, Two Pointers, Sorting[cite: 13]
- **Problem Link:** [3Sum](https://leetcode.com/problems/3sum/)
- **Language:** Java[cite: 13]

---

### 1. Problem Statement
Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that $i \ne j$, $i \ne k$, and $j \ne k$, and $\text{nums}[i] + \text{nums}[j] + \text{nums}[k] == 0$[cite: 13].

Notice that the solution set must not contain duplicate triplets[cite: 13].

- $3 \le \text{nums.length} \le 3000$[cite: 13]
- $-10^5 \le \text{nums}[i] \le 10^5$[cite: 13]

---

### 2. Intuition & Approach

#### Sorting + Two Pointers ($O(N^2)$ Time, $O(1)$ Auxiliary Space)
A brute-force solution checks every combination of three numbers in $O(N^3)$ time and requires heavy hash sets to avoid duplicates. By sorting the array first in $O(N \log N)$ time, we can fix one element and use a two-pointer scan for the remaining two[cite: 13]:

1. **Early Termination:**
   - Because `nums` is sorted in ascending order, if `nums[i] > 0`, any triplet starting at `nums[i]` will have a sum $> 0$[cite: 13]. We can safely break early[cite: 13].
2. **First Element Duplicate Skipping:**
   - If $i > 0$ and $\text{nums}[i] == \text{nums}[i - 1]$, skip this index with `continue` to prevent duplicate combinations[cite: 13].
3. **Two-Pointer Squeeze:**
   - Set `left = i + 1` and `right = n - 1`[cite: 13].
   - Calculate $\text{sum} = \text{nums}[i] + \text{nums}[\text{left}] + \text{nums}[\text{right}]$[cite: 13].
   - If $\text{sum} == 0$, record the triplet[cite: 13]. Advance `left` past any duplicate values of `nums[left]`, and decrement `right` past duplicate values of `nums[right]` before shrinking the window[cite: 13].
   - If $\text{sum} < 0$, we need a larger value $\rightarrow$ `left++`[cite: 13].
   - If $\text{sum} > 0$, we need a smaller value $\rightarrow$ `right--`[cite: 13].

---

### 3. Execution Trace

**Input:** `nums = [-1, 0, 1, 2, -1, -4]`[cite: 13]  
**Sorted:** `nums = [-4, -1, -1, 0, 1, 2]`

| `i` | `nums[i]` | `left` | `right` | $\text{sum} = \text{nums}[i] + \text{nums}[l] + \text{nums}[r]$ | Action | Found Triplets |
| :---: | :---: | :---: | :---: | :---: | :--- | :--- |
| `0` | `-4` | `1` (-1) | `5` (2) | $-4 + (-1) + 2 = -3 < 0$ | `left++` $\rightarrow 2$ | None |
| `0` | `-4` | `2` (-1) | `5` (2) | $-4 + (-1) + 2 = -3 < 0$ | `left++` $\rightarrow 3$ | None |
| `0` | `-4` | `3` (0) | `5` (2) | $-4 + 0 + 2 = -2 < 0$ | `left++` $\rightarrow 4$ | None |
| `0` | `-4` | `4` (1) | `5` (2) | $-4 + 1 + 2 = -1 < 0$ | `left++` meets `right` | None |
| `1` | `-1` | `2` (-1) | `5` (2) | $-1 + (-1) + 2 = 0$ | **Match found**, skip dups, shrink | `[-1, -1, 2]`[cite: 13] |
| `1` | `-1` | `3` (0) | `4` (1) | $-1 + 0 + 1 = 0$ | **Match found**, skip dups, shrink | `[-1, 0, 1]`[cite: 13] |
| `2` | `-1` | — | — | $\text{nums}[2] == \text{nums}[1]$ | **Duplicate skip** (`continue`)[cite: 13] | — |
| `3` | `0` | `4` (1) | `5` (2) | $0 + 1 + 2 = 3 > 0$ | `right--` meets `left` | — |
| `4` | `1` | — | — | $\text{nums}[4] > 0$ | **Break early**[cite: 13] | — |

**Final Result:** `[[-1, -1, 2], [-1, 0, 1]]`[cite: 13]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N^2)$
  - Sorting takes $O(N \log N)$. The outer loop runs $N$ times, and the two-pointer inner scan takes at most $O(N)$ comparisons per iteration, yielding $O(N^2)$ total operations.
- **Space Complexity:** $O(1)$ auxiliary space (ignoring sorting recursion stack and the output list)[cite: 13].

---

### 5. Edge Cases & Key Takeaways

- **All Zeros (`[0, 0, 0]`):** The duplicate skipping loops ensure that only one `[0, 0, 0]` triplet is recorded without duplicates[cite: 13].
- **No Solution (`[0, 1, 1]`):** Returns an empty list immediately without error[cite: 13].
- **Pruning with `nums[i] > 0`:** Exiting when the fixed anchor exceeds 0 skips redundant operations on strictly positive partitions[cite: 13].