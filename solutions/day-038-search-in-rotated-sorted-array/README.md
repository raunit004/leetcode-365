# Day 038: Search in Rotated Sorted Array

- **Platform:** LeetCode #33[cite: 8]
- **Difficulty:** Medium[cite: 8]
- **Topic:** Array, Binary Search[cite: 8]
- **Problem Link:** [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)[cite: 8]
- **Language:** Java[cite: 8]
- **Time Complexity:** $O(\log N)$[cite: 8]
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
An integer array `nums` of distinct values was originally sorted in ascending order and then rotated at an unknown pivot index $k$ ($1 \le k < \text{nums.length}$)[cite: 8].

Given the rotated array `nums` and a target integer `target`, return the index of `target` if present, or `-1` if it is not found[cite: 8]. The algorithm must run with $O(\log N)$ runtime complexity[cite: 8].

---

### 2. Intuition & Approach

#### Modified Binary Search:
A standard binary search cannot be applied uniformly because the array is split into two sorted segments. However, any split point (`mid`) will always leave at least one half of the array strictly ordered[cite: 8]:

1. **Check Equality:**
   - If `nums[mid] == target`, return `mid` immediately[cite: 8].

2. **Identify the Sorted Half:**
   - **Left Half Sorted (`nums[left] <= nums[mid]`):**[cite: 8]
     - If `nums[left] <= target && target < nums[mid]`, the target lies within the clean left interval; narrow the search window leftward (`right = mid - 1`)[cite: 8].
     - Otherwise, the target must exist in the right half (`left = mid + 1`)[cite: 8].
   - **Right Half Sorted (`nums[left] > nums[mid]`):**[cite: 8]
     - If `nums[mid] < target && target <= nums[right]`, the target lies within the clean right interval; narrow the search window rightward (`left = mid + 1`)[cite: 8].
     - Otherwise, search the left half (`right = mid - 1`)[cite: 8].

3. **Termination:**
   - If `left > right` without matching `target`, return `-1`[cite: 8].

---

### 3. Execution Trace

#### Sample Input: `nums = [4, 5, 6, 7, 0, 1, 2]`, `target = 0`[cite: 8]

| Iteration | `left` | `right` | `mid` | `nums[mid]` | Sorted Half | In Range? | New Bounds |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 0 (`4`) | 6 (`2`) | 3 | `7` | Left (`[4..7]`)[cite: 8] | $4 \le 0 < 7$ (False)[cite: 8] | `left = 4` |
| 2 | 4 (`0`) | 6 (`2`) | 5 | `1` | Left (`[0..1]`)[cite: 8] | $0 \le 0 < 1$ (True)[cite: 8] | `right = 4` |
| 3 | 4 (`0`) | 4 (`0`) | 4 | `0` | Match (`nums[4] == 0`)[cite: 8] | **Return 4**[cite: 8] | — |

**Output:** `4`[cite: 8]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\log N)$
  - At every step, the search interval is cut strictly in half, preserving standard binary search logarithmic convergence[cite: 8].
- **Space Complexity:** $O(1)$
  - Pointers are maintained using scalar variables with zero auxiliary allocations.

---

### 5. Edge Cases & Key Takeaways

- **Single-Element Arrays:** Correctly handled without bounds overflow when `left == right == 0`[cite: 8].
- **Strictly Distinct Values:** Because the array values are guaranteed to be distinct, `nums[left] <= nums[mid]` conclusively confirms the left segment is sorted[cite: 8].
- **Unrotated Array:** If $k = 0$ (no rotation), the left branch consistently evaluates as sorted, degrading smoothly to standard binary search.