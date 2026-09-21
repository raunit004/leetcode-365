# Day 016: 3Sum Closest

- **Platform:** LeetCode #16[cite: 14]
- **Difficulty:** Medium[cite: 14]
- **Topic:** Array, Two Pointers, Sorting[cite: 14]
- **Problem Link:** [3Sum Closest](https://leetcode.com/problems/3sum-closest/)
- **Language:** Java[cite: 14]

---

### 1. Problem Statement
Given an integer array `nums` of length `n` and an integer `target`, find three integers at distinct indices in `nums` such that the sum is closest to `target`[cite: 14].

Return the sum of the three integers[cite: 14].

Assume that each input would have exactly one solution[cite: 14].

- $3 \le \text{nums.length} \le 500$[cite: 14]
- $-1000 \le \text{nums}[i] \le 1000$[cite: 14]
- $-10^4 \le \text{target} \le 10^4$[cite: 14]

---

### 2. Intuition & Approach

#### Sorting + Two-Pointer Search ($O(N^2)$ Time, $O(1)$ Space)
A brute-force evaluation of all triplets takes $O(N^3)$ time. Sorting the array upfront in $O(N \log N)$ allows using a two-pointer scan to evaluate candidate sums in $O(N^2)$ total time[cite: 14]:

1. **Initial Baseline:**
   Initialize `closestSum` with the sum of the first three elements `nums[0] + nums[1] + nums[2]`[cite: 14].
2. **Fixed Anchor Iteration:**
   Iterate `i` from $0$ to $n - 3$, fixing `nums[i]` as the first number in the triplet[cite: 14].
3. **Two-Pointer Scan:**
   - Set `left = i + 1` and `right = n - 1`[cite: 14].
   - Calculate $\text{currentSum} = \text{nums}[i] + \text{nums}[\text{left}] + \text{nums}[\text{right}]$[cite: 14].
   - If $\text{currentSum} == \text{target}$, the distance to target is 0, which is the closest possible; return `target` immediately[cite: 14].
   - If $|\text{currentSum} - \text{target}| < |\text{closestSum} - \text{target}|$, update `closestSum = currentSum`[cite: 14].
   - If $\text{currentSum} < \text{target}$, increment `left` to increase the sum[cite: 14].
   - If $\text{currentSum} > \text{target}$, decrement `right` to decrease the sum[cite: 14].

---

### 3. Execution Trace

**Input:** `nums = [-1, 2, 1, -4]`, `target = 1`[cite: 14]  
**Sorted Array:** `nums = [-4, -1, 1, 2]`

Initial `closestSum = -4 + (-1) + 1 = -4` ($|\text{diff}| = |-4 - 1| = 5$)

| `i` | `nums[i]` | `left` | `right` | `currentSum` | $|\text{currentSum} - \text{target}|$ | Best `closestSum` | Next Action |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :--- |
| `0` | `-4` | `1` (-1) | `3` (2) | $-4 + (-1) + 2 = -3$ | $|-3 - 1| = 4$ | `-3` | `currentSum < target` $\rightarrow$ `left++`[cite: 14] |
| `0` | `-4` | `2` (1) | `3` (2) | $-4 + 1 + 2 = -1$ | $|-1 - 1| = 2$ | `-1` | `currentSum < target` $\rightarrow$ `left++`[cite: 14] |
| `1` | `-1` | `2` (1) | `3` (2) | $-1 + 1 + 2 = 2$ | $|2 - 1| = 1$ | **`2`** | `currentSum > target` $\rightarrow$ `right--`[cite: 14] |

**Final Result:** `2`[cite: 14]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N^2)$
  - Sorting takes $O(N \log N)$ time[cite: 14]. The outer loop runs $N - 2$ times, and the inner two-pointer loop executes at most $N$ steps per anchor, resulting in $O(N^2)$ overall time.
- **Space Complexity:** $O(1)$ auxiliary space
  - Sorting operates in place (or $O(\log N)$ stack space for primitive dual-pivot quicksort), and only scalar pointer variables are used.

---

### 5. Edge Cases & Key Takeaways

- **Exact Target Match:** If any triplet sums exactly to `target`, exiting early with `return target` saves unnecessary computations[cite: 14].
- **Negative Numbers and Targets:** Using `Math.abs(currentSum - target)` normalizes distance calculations across negative and positive integer ranges[cite: 14].
- **Small Constraints:** With $N \le 500$, an $O(N^2)$ solution executes in $\approx 1.25 \times 10^5$ operations, well within typical runtime limits[cite: 14].