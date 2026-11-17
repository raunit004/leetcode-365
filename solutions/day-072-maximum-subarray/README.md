# Day 072: Maximum Subarray

- **Platform:** LeetCode #53[cite: 23]
- **Difficulty:** Medium[cite: 23]
- **Topic:** Array, Divide and Conquer, Dynamic Programming
- **Problem Link:** [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)
- **Language:** Java
- **Time Complexity:** $O(N)$ where $N$ is `nums.length`
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
Given an integer array `nums`, find the subarray with the largest sum, and return its sum[cite: 23].

A **subarray** is a contiguous non-empty sequence of elements within an array.

---

### 2. Intuition & Approach

#### Kadane's Algorithm:
For any position $i$, a contiguous subarray ending at index $i$ either:
1. Extends the maximum subarray ending at index $i - 1$ by appending `nums[i]`[cite: 23].
2. Resets and starts fresh at index $i$ with `nums[i]` as the sole element[cite: 23].

This yields the dynamic programming recurrence:
$$\text{currentSum}[i] = \max(\text{nums}[i], \text{currentSum}[i - 1] + \text{nums}[i])$$[cite: 23]

1. **State Initialization:**
   - Set both `currentSum` and `maxSum` to `nums[0]`[cite: 23]. This ensures valid baseline values even when all numbers are negative[cite: 23].
2. **Linear Iteration:**
   - Loop from $i = 1$ to $N - 1$[cite: 23].
   - If `currentSum + nums[i] < nums[i]` (which occurs whenever `currentSum < 0`), carrying forward the prior sum only reduces the potential total[cite: 23]. Discard it and reset `currentSum = nums[i]`[cite: 23].
   - Update `maxSum = Math.max(maxSum, currentSum)` to maintain the global maximum observed so far[cite: 23].
3. **Space Compression:**
   - Because each state depends only on the immediately preceding value, tracking the running sum with two primitive integers achieves $O(1)$ auxiliary space without allocating an extra DP array[cite: 23].

---

### 3. Execution Trace

#### Sample Input: `nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]`[cite: 23]

- Initial: `currentSum = -2`, `maxSum = -2`[cite: 23]

| Step ($i$) | `nums[i]`[cite: 23] | Choice: $\max(\text{nums}[i], \text{currentSum} + \text{nums}[i])$[cite: 23] | `currentSum`[cite: 23] | Global `maxSum`[cite: 23] |
| :---: | :---: | :---: | :---: | :---: |
| 1 | `1`[cite: 23] | $\max(1, -2 + 1) = 1$[cite: 23] | 1 | $\max(-2, 1) = 1$[cite: 23] |
| 2 | `-3`[cite: 23] | $\max(-3, 1 - 3) = -2$[cite: 23] | -2 | $\max(1, -2) = 1$[cite: 23] |
| 3 | `4`[cite: 23] | $\max(4, -2 + 4) = 4$[cite: 23] | 4 | $\max(1, 4) = 4$[cite: 23] |
| 4 | `-1`[cite: 23] | $\max(-1, 4 - 1) = 3$[cite: 23] | 3 | $\max(4, 3) = 4$[cite: 23] |
| 5 | `2`[cite: 23] | $\max(2, 3 + 2) = 5$[cite: 23] | 5 | $\max(4, 5) = 5$[cite: 23] |
| 6 | `1`[cite: 23] | $\max(1, 5 + 1) = 6$[cite: 23] | 6 | $\max(5, 6) = 6$[cite: 23] |
| 7 | `-5`[cite: 23] | $\max(-5, 6 - 5) = 1$[cite: 23] | 1 | $\max(6, 1) = 6$[cite: 23] |
| 8 | `4`[cite: 23] | $\max(4, 1 + 4) = 5$[cite: 23] | 5 | $\max(6, 5) = 6$[cite: 23] |

- Final output: **`6`** (from contiguous subarray `[4, -1, 2, 1]`)[cite: 23].

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - A single pass through the array from index $1$ to $N - 1$ performs constant $O(1)$ arithmetic and comparison operations per element[cite: 23].
- **Space Complexity:** $O(1)$ auxiliary space
  - Solved in-place using two scalar integer variables (`currentSum`, `maxSum`)[cite: 23].

---

### 5. Edge Cases & Key Takeaways

- **Single Element (`nums = [x]`):** Loop bounds $i = 1 < 1$ evaluate to false immediately, directly returning `nums[0]` in $O(1)$ time[cite: 23].
- **All Negative Numbers (e.g., `[-5, -2, -8, -1]`):** Initializing `maxSum = nums[0]` rather than `0` ensures the algorithm correctly identifies the least negative single element (`-1`)[cite: 23].
- **Zero-Sum Buffering:** Any negative prefix is dropped automatically since adding a negative number lowers the sum of subsequent contiguous subarrays[cite: 23].