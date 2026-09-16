# Day 011: Container With Most Water

- **Platform:** LeetCode #11
- **Difficulty:** Medium
- **Topic:** Array, Two Pointers, Greedy
- **Problem Link:** [Container With Most Water](https://leetcode.com/problems/container-with-most-water/)
- **Language:** Java

---

### 1. Problem Statement
You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the $i^{\text{th}}$ line are $(i, 0)$ and $(i, \text{height}[i])$.

Find two lines that together with the x-axis form a container, such that````markdown
# Day 011: Container With Most Water

- **Platform:** LeetCode #11
- **Difficulty:** Medium
- **Topic:** Array, Two Pointers, Greedy
- **Problem Link:** [Container With Most Water](https://leetcode.com/problems/container-with-most-water/)
- **Language:** Java

---

### 1. Problem Summary
You are given an integer array `height` where each index represents a vertical line of height `height[i]` at coordinate $(i, \text{height}[i])$. 

Select two vertical lines that, together with the horizontal x-axis, form a rectangular container holding the largest possible volume of water. Return the maximum volume obtained.

- Containers cannot be slanted.
- Capacity between indices $i$ and $j$ ($i < j$) is defined as:
  $$\text{Area} = (j - i) \times \min(\text{height}[i], \text{height}[j])$$

---

### 2. Intuition & Approach

#### Two-Pointer Greedy Squeeze ($O(N)$ Time, $O(1)$ Space)
A brute-force check of every line pair requires $O(N^2)$ comparisons. We can reduce this to linear time using a two-pointer technique:

1. **Maximal Initial Base:**
   Place two pointers at the outer boundaries: `left = 0` and `right = height.length - 1`. This immediately evaluates the widest possible container.

2. **The Bottleneck Principle:**
   The water level is strictly constrained by the shorter of the two boundaries:
   $$h = \min(\text{height}[\text{left}], \text{height}[\text{right}])$$
   $$\text{currentWater} = (\text{right} - \text{left}) \times h$$

3. **Greedy Pointer Progression:**
   - If we shift the taller boundary inward, the width $(\text{right} - \text{left})$ decreases, while the effective height remains capped at $h$ (or lower). Hence, the volume is guaranteed to decrease.
   - To have any possibility of achieving a larger volume despite decreasing width, we **must** shift the pointer pointing to the shorter line inward in search of a taller barrier.

4. **Fast-Skip Optimization:**
   Instead of shifting pointers by one step at a time and performing redundant math, we immediately skip over any subsequent inner bars that are $\le h$. Because the width has decreased, any height less than or equal to $h$ cannot possibly beat `maxWater`.

---

### 3. Execution Trace

**Input:** `height = [1, 8, 6, 2, 5, 4, 8, 3, 7]`

| `left` | `right` | `height[left]` | `height[right]` | Limiting $h$ | Width | Area | Max Area So Far | Action Taken |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :--- |
| `0` | `8` | `1` | `7` | `1` | `8` | $8 \times 1 = 8$ | **8** | Skip `height[left] <= 1` $\rightarrow$ `left = 1` |
| `1` | `8` | `8` | `7` | `7` | `7` | $7 \times 7 = 49$ | **49** | Skip `height[right] <= 7` $\rightarrow$ `right = 6` |
| `1` | `6` | `8` | `8` | `8` | `5` | $5 \times 8 = 40$ | `49` | Skip both $\le 8$ $\rightarrow$ pointers meet |

**Final Result:** `49`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - The `left` pointer only increments and the `right` pointer only decrements. Each index is visited at most once across