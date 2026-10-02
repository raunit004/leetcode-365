# Day 027: Remove Element

- **Platform:** LeetCode #27[cite: 21]
- **Difficulty:** Easy[cite: 21]
- **Topic:** Array, Two Pointers[cite: 21]
- **Problem Link:** [Remove Element](https://leetcode.com/problems/remove-element/)
- **Language:** Java[cite: 21]

---

### 1. Problem Statement
Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` in-place[cite: 21]. The order of the elements may be changed[cite: 21]. Then return the number of elements in `nums` which are not equal to `val`[cite: 21].

Consider the number of elements in `nums` which are not equal to `val` to be `k`[cite: 21]. To get accepted, you need to do the following things[cite: 21]:
- Change the array `nums` such that the first `k` elements of `nums` contain the elements which are not equal to `val`[cite: 21]. The remaining elements of `nums` are not important as well as the size of `nums`[cite: 21].
- Return `k`[cite: 21].

---

### 2. Intuition & Approach

#### Two-Pointer Read/Write Compression ($O(N)$ Time, $O(1)$ Space)
The problem requires modifying the array in place without allocating extra storage for another list or buffer[cite: 21]:

1. **Write Pointer (`write`):**
   - Initialize `write = 0` to mark the next available index for retaining a valid element[cite: 21].
2. **Read Pointer (`read`):**
   - Traverse through the array from `read = 0` to `nums.length - 1`[cite: 21].
   - If `nums[read] != val`, the current element must be retained: copy `nums[read]` into `nums[write]` and increment `write`[cite: 21].
   - If `nums[read] == val`, skip the element without advancing `write`[cite: 21].
3. **Return Length:**
   - When the traversal finishes, `write` directly represents the total count of retained elements $k$[cite: 21].

---

### 3. Execution Trace

**Input:** `nums = [0, 1, 2, 2, 3, 0, 4, 2]`, `val = 2`

| `read` | `nums[read]` | Condition (`nums[read] != 2`)[cite: 21] | Action Taken[cite: 21] | Prefix at `nums[0..write-1]` | Next `write` |
| :---: | :---: | :---: | :--- | :--- | :---: |
| `0` | `0` | **True**[cite: 21] | `nums[0] = 0`, `write++`[cite: 21] | `[0]` | `1` |
| `1` | `1` | **True**[cite: 21] | `nums[1] = 1`, `write++`[cite: 21] | `[0, 1]` | `2` |
| `2` | `2` | False[cite: 21] | Skip[cite: 21] | `[0, 1]` | `2` |
| `3` | `2` | False[cite: 21] | Skip[cite: 21] | `[0, 1]` | `2` |
| `4` | `3` | **True**[cite: 21] | `nums[2] = 3`, `write++`[cite: 21] | `[0, 1, 3]` | `3` |
| `5` | `0` | **True**[cite: 21] | `nums[3] = 0`, `write++`[cite: 21] | `[0, 1, 3, 0]` | `4` |
| `6` | `4` | **True**[cite: 21] | `nums[4] = 4`, `write++`[cite: 21] | `[0, 1, 3, 0, 4]` | `5` |
| `7` | `2` | False[cite: 21] | Skip[cite: 21] | `[0, 1, 3, 0, 4]` | `5` |

**Final Returned $k$:** `5`[cite: 21]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - A single pass through the array visits each element exactly once[cite: 21].
- **Space Complexity:** $O(1)$ auxiliary space
  - Overwrites values in-place using two scalar pointers without allocating extra memory[cite: 21].

---

### 5. Edge Cases & Key Takeaways

- **Empty Array (`nums = []`):** The loop terminates immediately and returns `0` cleanly[cite: 21].
- **No Occurrences of `val`:** `write` advances at every step alongside `read`, keeping all elements and returning $N$[cite: 21].
- **All Elements Match `val`:** `write` remains `0` throughout the traversal, safely returning `0`[cite: 21].