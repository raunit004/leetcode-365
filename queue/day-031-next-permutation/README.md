# Day 031: Next Permutation

- **Platform:** LeetCode #31
- **Difficulty:** Medium
- **Topic:** Array, Two Pointers
- **Problem Link:** [Next Permutation](https://leetcode.com/problems/next-permutation/)
- **Language:** Java
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Given an array of integers `nums`, rearrange the values into the next lexicographically larger permutation in place. If the array is already arranged in the highest possible order (descending), rearrange it into the lowest possible order (ascending).

The transformation must be performed in place using only constant additional memory.

---

### 2. Intuition & Approach

#### Single-Pass Pivot & Suffix Inversion ($O(N)$ Time, $O(1)$ Space)
To produce the smallest possible increase in lexicographical value, modifications must be made as far to the right as possible:

1. **Locate the First Decreasing Element (Pivot):**
   - Traverse backward from the second-to-last index to find the first index $i$ where `nums[i] < nums[i + 1]`.
   - The entire suffix to the right of index $i$ is guaranteed to be in descending order.
2. **Find the Successor:**
   - If a valid pivot index $i \ge 0$ is found, scan backward from the end to find the first element `nums[j]` strictly greater than `nums[i]`.
   - Swap `nums[i]` and `nums[j]`.
3. **Invert the Suffix:**
   - Because the subarray from index $i + 1$ to the end remains strictly non-increasing, reversing it in place converts it into non-decreasing order. This guarantees the minimal possible magnitude for the trailing digits.
4. **Descending Edge Case:**
   - If no index satisfies `nums[i] < nums[i + 1]` ($i = -1$), the array is already at its maximum permutation (entirely descending). Skipping the swap and reversing the entire array resets it to the smallest ascending order.

---

### 3. Execution Trace

#### Trace 1: `nums = [1, 2, 3]`
- **Step 1:** Scanning backwards, index $i = 1$ is the first position where `nums[1] < nums[2]` ($2 < 3$).
- **Step 2:** From the end, the first element larger than `nums[1]` is `nums[2] = 3` ($j = 2$). Swap `nums[1]` and `nums[2]` $\rightarrow$ `[1, 3, 2]`.
- **Step 3:** Reverse suffix from index $2$ to $2$ $\rightarrow$ `[1, 3, 2]`.
- **Result:** `[1, 3, 2]`

#### Trace 2: `nums = [3, 2, 1]`
- **Step 1:** No element satisfies `nums[i] < nums[i + 1]` ($i = -1$).
- **Step 2:** Skip swap step.
- **Step 3:** Reverse entire array from index $0$ to $2$ $\rightarrow$ `[1, 2, 3]`.
- **Result:** `[1, 2, 3]`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Scanning for the pivot takes at most $N$ operations.
  - Scanning for the successor takes at most $N$ operations.
  - Reversing the suffix takes at most $N / 2$ swaps. Total runtime is bounded by $O(N)$.
- **Space Complexity:** $O(1)$ auxiliary space
  - All pointer manipulations and swaps happen strictly in place using scalar variables.

---

### 5. Edge Cases & Key Takeaways

- **Fully Descending Array:** Handled directly when $i$ decrements to $-1$, reversing the entire array to sorted order.
- **Duplicate Elements:** Using `>=` and `<=` ensures identical adjacent elements are skipped correctly without infinite loops.
- **Single Element or Empty Array:** Early exit check prevents out-of-bounds indexing.