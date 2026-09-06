# Day 001: Two Sum

- **Platform:** LeetCode #1
- **Difficulty:** Easy
- **Topic:** Array, Hash Table
- **Problem Link:** [Two Sum](https://leetcode.com/problems/two-sum/)
- **Language:** Java

---

### 1. Problem Statement
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

- Exactly one valid solution exists for every input.
- The same element may not be used twice.
- The answer can be returned in any order.

---

### 2. Intuition & Approach

#### Naive Search vs. One-Pass Hash Table
- **Brute Force ($O(N^2)$):** Checking every distinct pair $(i, j)$ using nested loops takes quadratic time, which becomes slow for larger arrays.
- **One-Pass Hash Table ($O(N)$):** For any element `nums[i]`, the required matching value is:
  $$\text{complement} = \text{target} - \text{nums}[i]$$
  By maintaining a hash map storing `value -> index` of previously visited items, we reduce the lookup time for the complementary value from $O(N)$ down to $O(1)$ amortized time.

#### Execution Steps
1. Instantiate a `HashMap<Integer, Integer>` to track previously seen numbers and their indices.
2. Iterate through the array from left to right:
   - Calculate `complement = target - nums[i]`.
   - If the complement exists in the map, return an array containing `[map.get(complement), i]`.
   - Otherwise, register the current number: `map.put(nums[i], i)`.
3. Return an empty array `new int[] {}` if no valid pair is found.

---

### 3. Execution Trace

**Input:** `nums = [2, 7, 11, 15]`, `target = 9`

| Index `i` | Value `nums[i]` | Complement (`9 - nums[i]`) | Map Contains Key? | Action Taken | Map State |
| :---: | :---: | :---: | :---: | :--- | :--- |
| `0` | `2` | `7` | No | `map.put(2, 0)` | `{2=0}` |
| `1` | `7` | `2` | **Yes** | Return `[map.get(2), 1]` $\rightarrow$ **`[0, 1]`** | Terminate |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - A single pass over the array of length $N$.
  - Hash map operations (`containsKey`, `get`, `put`) operate in $O(1)$ average time.
- **Space Complexity:** $O(N)$
  - In the worst-case scenario, the hash map stores up to $N - 1$ elements before finding the match.

---

### 5. Edge Cases & Key Takeaways

- **Self-Pairing Guard:** By checking the map *before* inserting `nums[i]`, we avoid matching an element with itself (e.g., handling `target = 6` with `nums = [3, 4]`).
- **Duplicate Elements:** If identical values form the target (e.g., `nums = [3, 3]`, `target = 6`), the first `3` is already stored when the second `3` triggers the match.