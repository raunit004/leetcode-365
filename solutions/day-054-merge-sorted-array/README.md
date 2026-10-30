# Day 054: Merge Sorted Array

- **Platform:** LeetCode #88[cite: 18]
- **Difficulty:** Easy[cite: 18]
- **Topic:** Array, Two Pointers, Sorting
- **Problem Link:** [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)[cite: 18]
- **Language:** Java[cite: 18]
- **Time Complexity:** $O(M + N)$[cite: 18]
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the count of valid elements in `nums1` and `nums2` respectively[cite: 18].

Merge `nums1` and `nums2` into a single array sorted in non-decreasing order[cite: 18]. The final sorted array must not be returned by the function, but instead be stored inside `nums1` in-place[cite: 18]. To facilitate this, `nums1` has a total capacity of $m + n$, where the initial $m$ elements represent values to be merged and the trailing $n$ elements are set to $0$ as buffer space[cite: 18].

---

### 2. Intuition & Approach

#### Three-Pointer Backward Merging:
Merging from the front ($0$-th index forward) would require shifting elements in `nums1` to the right to prevent overwriting values before they are compared, degrading performance to $O((M + N)^2)$, or allocating an auxiliary array of size $M$ ($O(M)$ extra memory).

By merging from the **back** ($p = m + n - 1$), the empty buffer positions at the end of `nums1` are consumed first[cite: 18]:
1. **Three Pointers:**
   - `p1 = m - 1`: Tracks the largest unplaced element in `nums1`[cite: 18].
   - `p2 = n - 1`: Tracks the largest unplaced element in `nums2`[cite: 18].
   - `p = m + n - 1`: Points to the rightmost unoccupied slot in `nums1`[cite: 18].
2. **Backwards Comparison:**
   - Loop as long as `p2 >= 0`[cite: 18].
   - If `p1 >= 0 && nums1[p1] > nums2[p2]`, place `nums1[p1]` into `nums1[p]`, then decrement both `p1` and `p`[cite: 18].
   - Otherwise, place `nums2[p2]` into `nums1[p]`, then decrement both `p2` and `p`[cite: 18].
3. **Termination Invariant:**
   - Once `p2 < 0`, all elements of `nums2` are successfully placed[cite: 18]. Any remaining elements in `nums1` (`p1 >= 0`) are already in their correct, sorted positions, requiring no further moves[cite: 18].

---

### 3. Execution Trace

#### Sample Input: `nums1 = [1, 2, 3, 0, 0, 0]`, `m = 3`, `nums2 = [2, 5, 6]`, `n = 3`[cite: 18]

- Initial: `p1 = 2` (val `3`), `p2 = 2` (val `6`), `p = 5`[cite: 18]

| Step | `p1` (`nums1[p1]`) | `p2` (`nums2[p2]`) | Comparison | Element Written to `nums1[p]` | Next Pointers |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 2 (`3`) | 2 (`6`) | $3 > 6$ (False) | `nums1[5] = 6` | `p1 = 2`, `p2 = 1`, `p = 4` |
| 2 | 2 (`3`) | 1 (`5`) | $3 > 5$ (False) | `nums1[4] = 5` | `p1 = 2`, `p2 = 0`, `p = 3` |
| 3 | 2 (`3`) | 0 (`2`) | $3 > 2$ (True) | `nums1[3] = 3` | `p1 = 1`, `p2 = 0`, `p = 2` |
| 4 | 1 (`2`) | 0 (`2`) | $2 > 2$ (False) | `nums1[2] = 2` | `p1 = 1`, `p2 = -1`, `p = 1` |

- `p2 < 0`: Loop terminates[cite: 18]. Remaining elements `[1, 2]` in `nums1` are already sorted in indices `0..1`[cite: 18].
- **Final Result:** `[1, 2, 2, 3, 5, 6]`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(M + N)$
  - Each iteration places at least one element into its final sorted position in `nums1`[cite: 18]. The loop executes at most $m + n$ times[cite: 18].
- **Space Complexity:** $O(1)$ auxiliary space
  - Merging is completed in-place using existing buffer space at the tail of `nums1` without allocating additional structures[cite: 18].

---

### 5. Edge Cases & Key Takeaways

- **Empty Second Array ($n = 0$):** `p2` starts at `-1`[cite: 18], bypassing the `while` loop entirely and leaving `nums1` untouched in $O(1)$ operations[cite: 18].
- **Empty First Array ($m = 0$):** `p1` starts at `-1`[cite: 18], causing the loop to copy all values from `nums2` directly into `nums1` one by one[cite: 18].
- **No Overwrite Hazard:** Because `p` starts at $m + n - 1$ and `p1` starts at $m - 1$, pointer $p$ is always strictly ahead of $p1$ ($p \ge p1$), guaranteeing unread elements in `nums1` are never prematurely overwritten.