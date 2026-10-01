# Day 026: Remove Duplicates from Sorted Array

- **Platform:** LeetCode #26[cite: 20]
- **Difficulty:** Easy[cite: 20]
- **Topic:** Array, Two Pointers[cite: 20]
- **Problem Link:** [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
- **Language:** Java[cite: 20]

---

### 1. Problem Statement
Given an integer array `nums` sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once[cite: 20]. The relative order of the elements should be kept the same[cite: 20].

Consider the number of unique elements in `nums` to be `k`[cite: 20]. After removing duplicates, return the number of unique elements `k`[cite: 20].

The first `k` elements of `nums` should contain the unique numbers in sorted order[cite: 20]. The remaining elements beyond index `k - 1` can be ignored[cite: 20].

---

### 2. Intuition & Approach

#### Fast-Slow (Read-Write) Pointer Technique ($O(N)$ Time, $O(1)$ Space)
Because `nums` is already sorted in non-decreasing order, all duplicate instances of any number are clustered contiguously[cite: 20]:

1. **Initial Boundary:**
   - The element at index `0` is always the first unique value[cite: 20].
   - Maintain a `write` pointer initialized to `1`, marking the array slot waiting to receive the next unique element[cite: 20].
2. **Sequential Scanning (`read` Pointer):**
   - Iterate a `read` pointer from index `1` to `nums.length - 1`[cite: 20].
   - Compare the current element `nums[read]` with its immediate predecessor `nums[read - 1]`[cite: 20]:
     - If `nums[read] != nums[read - 1]`, a new distinct number has been encountered[cite: 20].
     - Copy `nums[read]` to `nums[write]` and increment `write`[cite: 20].
     - If `nums[read] == nums[read - 1]`, it is a duplicate and is skipped[cite: 20].
3. **Return `write`:**
   - At loop completion, `write` equals the total count of unique elements $k$, and indices $0$ through $k - 1$ contain the deduped prefix[cite: 20].

---

### 3. Execution Trace

**Input:** `nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]`

| `read` | `nums[read]` | `nums[read - 1]` | `nums[read] != nums[read - 1]`?[cite: 20] | Action Taken | Array Prefix at `0..write-1` | Next `write` |
| :---: | :---: | :---: | :---: | :--- | :--- | :---: |
| `1` | `0` | `0` | False | Skip duplicate[cite: 20] | `[0]` | `1`[cite: 20] |
| `2` | `1` | `0` | **True**[cite: 20] | `nums[1] = 1`, `write++`[cite: 20] | `[0, 1]` | `2` |
| `3` | `1` | `1` | False | Skip duplicate[cite: 20] | `[0, 1]` | `2` |
| `4` | `1` | `1` | False | Skip duplicate[cite: 20] | `[0, 1]` | `2` |
| `5` | `2` | `1` | **True**[cite: 20] | `nums[2] = 2`, `write++`[cite: 20] | `[0, 1, 2]` | `3` |
| `6` | `2` | `2` | False | Skip duplicate[cite: 20] | `[0, 1, 2]` | `3` |
| `7` | `3` | `2` | **True**[cite: 20] | `nums[3] = 3`, `write++`[cite: 20] | `[0, 1, 2, 3]` | `4` |
| `8` | `3` | `3` | False | Skip duplicate[cite: 20] | `[0, 1, 2, 3]` | `4` |
| `9` | `4` | `3` | **True**[cite: 20] | `nums[4] = 4`, `write++`[cite: 20] | `[0, 1, 2, 3, 4]` | `5` |

**Final Returned $k$:** `5`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - The `read` pointer visits every element of the array of length $N$ exactly once[cite: 20].
- **Space Complexity:** $O(1)$ auxiliary space
  - Replaces values in-place without allocating auxiliary collections or arrays[cite: 20].

---

### 5. Edge Cases & Key Takeaways

- **Single Element Array (`[1]`):** The loop condition `read < nums.length` does not execute; returns `1` immediately without array index out-of-bounds errors[cite: 20].
- **Strictly Distinct Array (`[1, 2, 3]`):** Every step passes the condition and rewrites elements into their identical slots while incrementing `write` smoothly[cite: 20].
- **All Identical Elements (`[7, 7, 7, 7]`):** Condition never matches; returns `1` with only the first element retained[cite: 20].