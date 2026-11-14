# Day 069: Trapping Rain Water

- **Platform:** LeetCode #42[cite: 23]
- **Difficulty:** Hard[cite: 23]
- **Topic:** Array, Two Pointers, Dynamic Programming, Stack, Monotonic Stack
- **Problem Link:** [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
- **Language:** Java[cite: 23]
- **Time Complexity:** $O(N)$ where $N$ is `height.length`
- **Space Complexity:** $O(1)$ auxiliary space[cite: 23]

---

### 1. Problem Overview
Given `n` non-negative integers representing an elevation map where the width of each bar is $1$, compute how much water it can trap after raining[cite: 23].

---

### 2. Intuition & Approach

#### Two-Pointer Bounded Capacity:
The volume of water trapped above any bar at index $i$ is constrained by the shorter of the tallest boundaries to its left and right:
$$\text{water}[i] = \max(0, \min(\text{leftMax}, \text{rightMax}) - \text{height}[i])$$

Rather than allocating two auxiliary arrays of size $N$ to precompute prefix and suffix maximums, we use two pointers (`left` and `right`) converging toward the center[cite: 23]:

1. **Bottleneck Determination:**
   - If `height[left] < height[right]`, the water level at `left` is strictly bounded by `leftMax`, because there exists a known bar on the right (`height[right]`) that is taller than `height[left]`[cite: 23].
   - If `height[left] >= leftMax`, update `leftMax = height[left]` (no water can be trapped at a new peak)[cite: 23].
   - Otherwise, the trapped water contribution is `leftMax - height[left]`[cite: 23].
   - Advance `left++`[cite: 23].
2. **Symmetric Right Boundary:**
   - If `height[left] >= height[right]`, the water level at `right` is strictly bounded by `rightMax`[cite: 23].
   - If `height[right] >= rightMax`, update `rightMax = height[right]`[cite: 23].
   - Otherwise, trap `rightMax - height[right]` units of water[cite: 23].
   - Decrement `right--`[cite: 23].
3. **Termination:**
   - The loop finishes once `left == right`, having accounted for water trapped at every index without extra memory[cite: 23].

---

### 3. Execution Trace

#### Sample Input: `height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]`[cite: 23]

- Initial: `left = 0`, `right = 11`, `leftMax = 0`, `rightMax = 0`, `trappedWater = 0`[cite: 23]

| Step | `left` (`h[l]`) | `right` (`h[r]`) | Shorter Side | Updated Boundary | Water Trapped Added | `trappedWater` Accumulator |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 0 (`0`)[cite: 23] | 11 (`1`)[cite: 23] | Left ($0 < 1$)[cite: 23] | `leftMax = 0`[cite: 23] | $0$ | 0 |
| 2 | 1 (`1`)[cite: 23] | 11 (`1`)[cite: 23] | Right ($1 \ge 1$)[cite: 23] | `rightMax = 1`[cite: 23] | $0$ | 0 |
| 3 | 1 (`1`)[cite: 23] | 10 (`2`)[cite: 23] | Left ($1 < 2$)[cite: 23] | `leftMax = 1`[cite: 23] | $0$ | 0 |
| 4 | 2 (`0`)[cite: 23] | 10 (`2`)[cite: 23] | Left ($0 < 2$)[cite: 23] | `leftMax = 1`[cite: 23] | $1 - 0 = 1$[cite: 23] | 1 |
| 5 | 3 (`2`)[cite: 23] | 10 (`2`)[cite: 23] | Right ($2 \ge 2$)[cite: 23] | `rightMax = 2`[cite: 23] | $0$ | 1 |
| 6 | 3 (`2`)[cite: 23] | 9 (`1`)[cite: 23] | Right ($1 < 2$)[cite: 23] | `rightMax = 2`[cite: 23] | $2 - 1 = 1$[cite: 23] | 2 |
| 7 | 3 (`2`)[cite: 23] | 8 (`2`)[cite: 23] | Right ($2 \ge 2$)[cite: 23] | `rightMax = 2`[cite: 23] | $0$ | 2 |
| 8 | 3 (`2`)[cite: 23] | 7 (`3`)[cite: 23] | Left ($2 < 3$)[cite: 23] | `leftMax = 2`[cite: 23] | $0$ | 2 |
| 9 | 4 (`1`)[cite: 23] | 7 (`3`)[cite: 23] | Left ($1 < 3$)[cite: 23] | `leftMax = 2`[cite: 23] | $2 - 1 = 1$[cite: 23] | 3 |
| 10 | 5 (`0`)[cite: 23] | 7 (`3`)[cite: 23] | Left ($0 < 3$)[cite: 23] | `leftMax = 2`[cite: 23] | $2 - 0 = 2$[cite: 23] | 5 |
| 11 | 6 (`1`)[cite: 23] | 7 (`3`)[cite: 23] | Left ($1 < 3$)[cite: 23] | `leftMax = 2`[cite: 23] | $2 - 1 = 1$[cite: 23] | **6**[cite: 23] |

- Pointers meet at index 7 (`left == right == 7`)[cite: 23].
- Return `trappedWater = 6`[cite: 23].

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Each step increments `left` or decrements `right`[cite: 23]. The loop runs at most $N$ times, visiting each bar exactly once.
- **Space Complexity:** $O(1)$ auxiliary space
  - Evaluated using five scalar variables (`left`, `right`, `leftMax`, `rightMax`, `trappedWater`) without allocating auxiliary arrays[cite: 23].

---

### 5. Edge Cases & Key Takeaways

- **Fewer than 3 Bars:** When `height.length < 3`, water cannot be enclosed; the loop exits without trapping water and correctly returns `0`.
- **Monotonically Increasing/Decreasing:** Pointers advance smoothly while updating running maximums; no water is trapped because bar heights continuously exceed previous maximums.
- **Independent Boundary Bounds:** Deciding which pointer to advance based strictly on `height[left] < height[right]` ensures the lower boundary always controls the calculation, maintaining correctness without global knowledge of the opposite side[cite: 23].