# Day 068: First Missing Positive

- **Platform:** LeetCode #41[cite: 23]
- **Difficulty:** Hard[cite: 23]
- **Topic:** Array, Hash Table
- **Problem Link:** [First Missing Positive](https://leetcode.com/problems/first-missing-positive/)
- **Language:** Java[cite: 23]
- **Time Complexity:** $O(N)$[cite: 23]
- **Space Complexity:** $O(1)$ auxiliary space[cite: 23]

---

### 1. Problem Overview
Given an unsorted integer array `nums`, return the smallest positive integer that is not present in `nums`[cite: 23].

You must implement an algorithm that runs in $O(N)$ time and uses $O(1)$ auxiliary space[cite: 23].

---

### 2. Intuition & Approach

#### In-Place Bucket / Cycle Sort:
For an array of size $n$, the smallest missing positive integer must fall strictly within the range $[1, n + 1]$:
- If all integers from $1$ through $n$ exist in the array, the answer is $n + 1$[cite: 23].
- Otherwise, at least one integer in the range $[1, n]$ is absent[cite: 23].

Because extra hash sets or boolean arrays are prohibited by the $O(1)$ auxiliary space constraint[cite: 23], we repurpose the input array itself as a hash table where value $x$ belongs at index $x - 1$[cite: 23]:

1. **Cycle Placement (`while` loop):**
   - For index $i$, inspect value `nums[i]`[cite: 23].
   - If `nums[i]` is in the valid target range $[1, n]$ and is not yet positioned at its target slot `nums[i] - 1` (checked via `nums[nums[i] - 1] != nums[i]`), swap `nums[i]` into index `nums[i] - 1`[cite: 23].
   - The `while` loop continues until the element residing at index $i$ is either non-positive ($\le 0$), exceeds $n$, or matches the element already residing at its destination index[cite: 23].
2. **First Discrepancy Scan:**
   - Iterate index $i$ from $0$ to $n - 1$[cite: 23].
   - The first index where `nums[i] != i + 1` identifies the missing positive integer `i + 1`[cite: 23].
3. **All Matches Found:**
   - If every index contains `nums[i] == i + 1`, the array contains all values $1$ through $n$[cite: 23]. Return `n + 1`[cite: 23].

---

### 3. Execution Trace

#### Sample Input: `nums = [3, 4, -1, 1]`[cite: 23] ($n = 4$[cite: 23])

| Step | Index `i` | Value `nums[i]` | Condition (`1 <= x <= n && nums[x-1] != x`) | Swap Action | Array State |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 0 | `3`[cite: 23] | $1 \le 3 \le 4$ and `nums[2] != 3` (`-1 != 3`)[cite: 23] | Swap `nums[0]` with `nums[2]`[cite: 23] | `[-1, 4, 3, 1]`[cite: 23] |
| 2 | 0 | `-1`[cite: 23] | `-1 > 0` is false[cite: 23] | Break `while`, advance $i$[cite: 23] | `[-1, 4, 3, 1]`[cite: 23] |
| 3 | 1 | `4`[cite: 23] | $1 \le 4 \le 4$ and `nums[3] != 4` (`1 != 4`)[cite: 23] | Swap `nums[1]` with `nums[3]`[cite: 23] | `[-1, 1, 3, 4]`[cite: 23] |
| 4 | 1 | `1`[cite: 23] | $1 \le 1 \le 4$ and `nums[0] != 1` (`-1 != 1`)[cite: 23] | Swap `nums[1]` with `nums[0]`[cite: 23] | `[1, -1, 3, 4]`[cite: 23] |
| 5 | 1 | `-1`[cite: 23] | `-1 > 0` is false[cite: 23] | Break `while`, advance $i$[cite: 23] | `[1, -1, 3, 4]`[cite: 23] |
| 6 | 2 | `3`[cite: 23] | `nums[2] == 3` (Already placed)[cite: 23] | Advance $i$[cite: 23] | `[1, -1, 3, 4]`[cite: 23] |
| 7 | 3 | `4`[cite: 23] | `nums[3] == 4` (Already placed)[cite: 23] | Advance $i$[cite: 23] | `[1, -1, 3, 4]`[cite: 23] |

**Verification Pass:**
- Index 0: `nums[0] == 1`[cite: 23]
- Index 1: `nums[1] == -1 != 2`[cite: 23] $\implies$ Returns $1 + 1 =$ **`2`**[cite: 23]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$[cite: 23]
  - Although there is a nested `while` loop[cite: 23], each swap places at least one number into its correct destination index ($x - 1$)[cite: 23]. Once placed, that number is never moved again. Therefore, the inner swap loop executes at most $N$ times across the entire runtime, keeping total operations linear[cite: 23].
- **Space Complexity:** $O(1)$ auxiliary space[cite: 23]
  - Array elements are reordered directly in-place without auxiliary collections or recursion frames[cite: 23].

---

### 5. Edge Cases & Key Takeaways

- **Duplicate Values:** Checking `nums[nums[i] - 1] != nums[i]` prevents infinite swapping loops when duplicate values belonging to the same bucket appear (e.g., `[1, 1]`)[cite: 23].
- **Out of Bound & Negative Values:** Non-positive values and values greater than $n$ are left in place because they cannot fill any bucket in the $[1, n]$ index range[cite: 23].
- **Consecutive Range Present:** For an input like `[1, 2, 3]`, the verification loop finishes completely, and the final statement returns `n + 1 = 4`[cite: 23].