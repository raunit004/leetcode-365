# Day 040: Search Insert Position

- **Platform:** LeetCode #35[cite: 10]
- **Difficulty:** Easy[cite: 10]
- **Topic:** Array, Binary Search
- **Problem Link:** [Search Insert Position](https://leetcode.com/problems/search-insert-position/)[cite: 10]
- **Language:** Java[cite: 10]
- **Time Complexity:** $O(\log N)$[cite: 10]
- **Space Complexity:** $O(1)$

---

### 1. Problem Overview
Given a sorted array of distinct integers `nums` and a target value `target`, return the index if `target` is found[cite: 10]. If not, return the index where it would be placed if it were inserted in sorted order[cite: 10].

The algorithm must run with an $O(\log N)$ runtime complexity[cite: 10].

---

### 2. Intuition & Approach

#### Classical Binary Search with Insertion Invariant:
1. **Search Interval:** Maintain two pointers `left = 0` and `right = nums.length - 1`[cite: 10].
2. **Midpoint Inspection:** Compute `mid = left + (right - left) / 2` to prevent potential integer overflow[cite: 10].
   - If `nums[mid] == target`, the value is present; return `mid` immediately[cite: 10].
   - If `nums[mid] < target`, the target must reside to the right; advance `left = mid + 1`[cite: 10].
   - If `nums[mid] > target`, the target must reside to the left; pull `right = mid - 1`[cite: 10].
3. **Insertion Index Invariant:**
   - When the loop terminates (`left > right`), the target was not found in the array.
   - At termination, `left` points to the smallest index whose value is strictly greater than `target` (or `nums.length` if `target` is greater than every element). Thus, `left` represents the correct insertion position[cite: 10].

---

### 3. Execution Trace

#### Sample Input: `nums = [1, 3, 5, 6]`, `target = 2`[cite: 10]

| Iteration | `left` | `right` | `mid` | `nums[mid]` | Condition | Pointer Shift |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 0 | 3 | 1 | `3` | $3 > 2$ | `right = mid - 1 = 0` |
| 2 | 0 | 0 | 0 | `1` | $1 < 2$ | `left = mid + 1 = 1` |

- Loop condition `left <= right` ($1 \le 0$) fails and terminates[cite: 10].
- Return `left = 1`[cite: 10].

**Output:** `1`[cite: 10]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\log N)$
  - Standard binary search repeatedly divides the search space by half until convergence, adhering to the required logarithmic runtime[cite: 10].
- **Space Complexity:** $O(1)$
  - Only scalar index variables (`left`, `right`, `mid`) are used, requiring constant auxiliary memory[cite: 10].

---

### 5. Edge Cases & Key Takeaways

- **Target Smaller Than Minimum:** If `target < nums[0]`, `right` reduces to `-1` and `left` remains `0` (correct insertion index).
- **Target Greater Than Maximum:** If `target > nums[nums.length - 1]`, `left` increments past `right` to index `nums.length` (appends to end)[cite: 10].
- **Exact Match:** When `nums[mid] == target`, returns the matching index in $O(\log N)$ or faster[cite: 10].