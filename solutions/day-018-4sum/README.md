# Day 018: 4Sum

- **Platform:** LeetCode #18[cite: 14]
- **Difficulty:** Medium[cite: 14]
- **Topic:** Array, Two Pointers, Sorting[cite: 14]
- **Problem Link:** [4Sum](https://leetcode.com/problems/4sum/)
- **Language:** Java[cite: 14]

---

### 1. Problem Statement
Given an array `nums` of `n` integers, return an array of all the **unique** quadruplets `[nums[a], nums[b], nums[c], nums[d]]` such that[cite: 14]:
- $0 \le a, b, c, d < n$[cite: 14]
- $a, b, c$, and $d$ are **distinct**[cite: 14]
- $\text{nums}[a] + \text{nums}[b] + \text{nums}[c] + \text{nums}[d] == \text{target}$[cite: 14]

You may return the answer in any order[cite: 14].

- $1 \le \text{nums.length} \le 200$[cite: 14]
- $-10^9 \le \text{nums}[i] \le 10^9$[cite: 14]
- $-10^9 \le \text{target} \le 10^9$[cite: 14]

---

### 2. Intuition & Approach

#### Nested Anchors with Two-Pointer Squeeze ($O(N^3)$ Time, $O(1)$ Auxiliary Space)
Generalizing the 3Sum pattern, 4Sum fixes two elements using nested loops and searches for the remaining two elements using an inward two-pointer scan[cite: 14]:

1. **Array Sorting:**
   Sort `nums` ascendingly ($O(N \log N)$) to allow duplicate skipping and two-pointer directionality[cite: 14].
2. **First Anchor (`i`):**
   Iterate $i$ from $0$ to $n - 4$[cite: 14]. Skip identical consecutive values with `if (i > 0 && nums[i] == nums[i - 1]) continue;`[cite: 14].
3. **Second Anchor (`j`):**
   Iterate $j$ from $i + 1$ to $n - 3$[cite: 14]. Skip identical consecutive values with `if (j > i + 1 && nums[j] == nums[j - 1]) continue;`[cite: 14].
4. **Two Pointers (`left` and `right`):**
   - Initialize `left = j + 1` and `right = n - 1`[cite: 14].
   - **64-bit Overflow Prevention:** Because individual elements reach up to $10^9$, summing four values can yield $4 \times 10^9$, overflowing a signed 32-bit integer (`Integer.MAX_VALUE = 2.147 \times 10^9`). Casting `(long) nums[i] + nums[j] + nums[left] + nums[right]` prevents wraparound bugs[cite: 14].
   - If `sum == target`, capture the quadruplet, advance `left` past duplicates, decrement `right` past duplicates, and contract both pointers[cite: 14].
   - If `sum < target`, increment `left`[cite: 14].
   - If `sum > target`, decrement `right`[cite: 14].

---

### 3. Execution Trace

**Input:** `nums = [1, 0, -1, 0, -2, 2]`, `target = 0`[cite: 14]  
**Sorted Array:** `nums = [-2, -1, 0, 0, 1, 2]`

| `i` | `nums[i]` | `j` | `nums[j]` | `left` | `right` | Quadruplet Sum | Action | Result Set |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :--- | :--- |
| `0` | `-2` | `1` | `-1` | `2` (0) | `5` (2) | $-2 + (-1) + 0 + 2 = -1 < 0$ | `left++` | — |
| `0` | `-2` | `1` | `-1` | `4` (1) | `5` (2) | $-2 + (-1) + 1 + 2 = 0$ | **Match Found** | `[[-2, -1, 1, 2]]`[cite: 14] |
| `0` | `-2` | `2` | `0` | `3` (0) | `5` (2) | $-2 + 0 + 0 + 2 = 0$ | **Match Found** | `[[-2, -1, 1, 2], [-2, 0, 0, 2]]`[cite: 14] |
| `1` | `-1` | `2` | `0` | `3` (0) | `4` (1) | $-1 + 0 + 0 + 1 = 0$ | **Match Found** | `[..., [-1, 0, 0, 1]]`[cite: 14] |

**Final Result:** `[[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]`[cite: 14]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N^3)$
  - Sorting takes $O(N \log N)$[cite: 14]. The two nested loops run in $O(N^2)$ iterations, and the inner two-pointer while loop runs in $O(N)$ amortized steps per anchor pair, resulting in $O(N^3)$ total runtime[cite: 14]. Given $N \le 200$, $N^3 \approx 8 \times 10^6$ operations, executing well within 20ms[cite: 14].
- **Space Complexity:** $O(1)$ auxiliary space
  - Operates purely in place (ignoring the memory required for the output list and internal sorting call stack)[cite: 14].

---

### 5. Edge Cases & Key Takeaways

- **Integer Overflow:** Four values of $10^9$ sum to $4 \times 10^9$. Calculating using `(long)` avoids arithmetic wraparound into negative numbers[cite: 14].
- **Duplicate Suppression at All Levels:** Deduplication must be applied to all four positions (`i`, `j`, `left`, `right`) to guarantee unique quadruplets without relying on a `Set<List<Integer>>`[cite: 14].
- **Early Size Check:** Inputs with length $< 4$ immediately return an empty list[cite: 14].