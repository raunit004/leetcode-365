# Day 039: Find First and Last Position of Element in Sorted Array

- **Platform:** LeetCode #34[cite: 9]
- **Difficulty:** Medium[cite: 9]
- **Topic:** Array, Binary Search
- **Problem Link:** [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)[cite: 9]
- **Language:** Java[cite: 9]
- **Time Complexity:** $O(\log N)$[cite: 9]
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Given an array of integers `nums` sorted in non-decreasing order, find the starting and ending index of a target value `target`[cite: 9].

If `target` does not appear in `nums`, return `[-1, -1]`[cite: 9]. The algorithm must run within $O(\log N)$ time complexity[cite: 9].

---

### 2. Intuition & Approach

#### Two-Pass Binary Search (First and Last Boundary Detection):
A standard binary search stops as soon as `nums[mid] == target`[cite: 9]. Because elements can repeat, multiple occurrences of `target` form a contiguous segment[cite: 9]. We locate the exact boundaries with two binary search passes:

1. **Lower Bound (`isFirst = true`):**
   - When `nums[mid] == target`, record `mid` as a candidate start index[cite: 9].
   - Continue searching the left subarray (`right = mid - 1`) to check for an earlier occurrence of `target`[cite: 9].
2. **Early Exit Check:**
   - If the first pass returns `-1` (meaning `target` is entirely absent), immediately return `[-1, -1]` without running the second search[cite: 9].
3. **Upper Bound (`isFirst = false`):**
   - When `nums[mid] == target`, record `mid` as a candidate end index[cite: 9].
   - Continue searching the right subarray (`left = mid + 1`) to check for a later occurrence of `target`[cite: 9].

---

### 3. Execution Trace

#### Sample Input: `nums = [5, 7, 7, 8, 8, 10]`, `target = 8`[cite: 9]

#### Pass 1: Lower Bound (`isFirst = true`)[cite: 9]

| Iteration | `left` | `right` | `mid` | `nums[mid]` | Condition | Bound Updated | Next Search Range |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 0 | 5 | 2 | `7` | $7 < 8$ | `-1` | `left = 3` |
| 2 | 3 | 5 | 4 | `8` | $8 == 8$ | `4` | `right = 3` |
| 3 | 3 | 3 | 3 | `8` | $8 == 8$ | `3` | `right = 2` |

- Result for start bound: **3**[cite: 9]

#### Pass 2: Upper Bound (`isFirst = false`)[cite: 9]

| Iteration | `left` | `right` | `mid` | `nums[mid]` | Condition | Bound Updated | Next Search Range |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 0 | 5 | 2 | `7` | $7 < 8$ | `-1` | `left = 3` |
| 2 | 3 | 5 | 4 | `8` | $8 == 8$ | `4` | `left = 5` |
| 3 | 5 | 5 | 5 | `10` | $10 > 8$ | `4` | `right = 4` |

- Result for end bound: **4**[cite: 9]

**Final Result:** `[3, 4]`[cite: 9]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\log N)$
  - Executing two independent binary search passes over an array of size $N$ takes $2 \times O(\log N) = O(\log N)$ time[cite: 9].
- **Space Complexity:** $O(1)$ auxiliary space
  - The pointers and bounds are computed in-place using scalar variables[cite: 9].

---

### 5. Edge Cases & Key Takeaways

- **Empty Array:** Guarded at the start (`nums.length == 0`), returning `[-1, -1]` in $O(1)$ time[cite: 9].
- **Target Missing:** If `target` is not found during the first pass, skipping the second pass avoids redundant binary searches[cite: 9].
- **Single Element Match:** Correctly identifies `[0, 0]` when `nums = [1]` and `target = 1`.
- **All Elements Identical:** If all elements equal `target`, the searches converge correctly to `[0, nums.length - 1]`.