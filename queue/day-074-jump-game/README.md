# Day 074: Jump Game

- **Platform:** LeetCode #55[cite: 24]
- **Difficulty:** Medium[cite: 24]
- **Topic:** Array, Dynamic Programming, Greedy
- **Problem Link:** [Jump Game](https://leetcode.com/problems/jump-game/)
- **Language:** Java[cite: 24]
- **Time Complexity:** $O(N)$ where $N$ is `nums.length`[cite: 24]
- **Space Complexity:** $O(1)$ auxiliary space[cite: 24]

---

### 1. Problem Overview
You are given an integer array `nums`[cite: 24]. You are initially positioned at the array's first index ($0$), and each element in the array represents your maximum jump length from that position[cite: 24].

Return `true` if you can reach the last index, or `false` otherwise[cite: 24].

---

### 2. Intuition & Approach

#### Greedy Reachable Horizon:
Instead of exploring all jump permutations recursively, we maintain a running frontier of the farthest index reachable so far (`maxReach`)[cite: 24]:

1. **Unreachable Index Guard:**
   - Iterate through the array index $i$ from $0$ to $n - 1$[cite: 24].
   - If $i > \text{maxReach}$, the current index cannot be reached from any earlier jump positions[cite: 24]. Traversal stops immediately: return `false`[cite: 24].
2. **Extend the Horizon:**
   - At each reachable index $i$, update the farthest boundary:
     $$\text{maxReach} = \max(\text{maxReach}, i + \text{nums}[i])$$[cite: 24]
3. **Early Termination:**
   - If $\text{maxReach} \ge n - 1$, the last index is already reachable[cite: 24]. Return `true` without processing the remaining elements[cite: 24].

---

### 3. Execution Trace

#### Sample Input 1: `nums = [2, 3, 1, 1, 4]`[cite: 24] ($n = 5$[cite: 24])

- Initial: `maxReach = 0`[cite: 24]

| Step ($i$) | `nums[i]`[cite: 24] | Reach Check ($i \le \text{maxReach}$)[cite: 24] | $\max(\text{maxReach}, i + \text{nums}[i])$[cite: 24] | New `maxReach`[cite: 24] | Target Reached ($\ge n - 1$)[cite: 24] |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 0 | 2[cite: 24] | $0 \le 0$ (Valid)[cite: 24] | $\max(0, 0 + 2) = 2$[cite: 24] | 2[cite: 24] | $2 \ge 4$ (False)[cite: 24] |
| 1 | 3[cite: 24] | $1 \le 2$ (Valid)[cite: 24] | $\max(2, 1 + 3) = 4$[cite: 24] | 4[cite: 24] | $4 \ge 4$ (True)[cite: 24] |

- Early termination at $i = 1$: returns **`true`**[cite: 24].

#### Sample Input 2: `nums = [3, 2, 1, 0, 4]`[cite: 24] ($n = 5$[cite: 24])

| Step ($i$) | `nums[i]`[cite: 24] | Reach Check ($i \le \text{maxReach}$)[cite: 24] | $\max(\text{maxReach}, i + \text{nums}[i])$[cite: 24] | New `maxReach`[cite: 24] | Target Reached ($\ge n - 1$)[cite: 24] |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 0 | 3[cite: 24] | $0 \le 0$ (Valid)[cite: 24] | $\max(0, 0 + 3) = 3$[cite: 24] | 3[cite: 24] | $3 \ge 4$ (False)[cite: 24] |
| 1 | 2[cite: 24] | $1 \le 3$ (Valid)[cite: 24] | $\max(3, 1 + 2) = 3$[cite: 24] | 3[cite: 24] | $3 \ge 4$ (False)[cite: 24] |
| 2 | 1[cite: 24] | $2 \le 3$ (Valid)[cite: 24] | $\max(3, 2 + 1) = 3$[cite: 24] | 3[cite: 24] | $3 \ge 4$ (False)[cite: 24] |
| 3 | 0[cite: 24] | $3 \le 3$ (Valid)[cite: 24] | $\max(3, 3 + 0) = 3$[cite: 24] | 3[cite: 24] | $3 \ge 4$ (False)[cite: 24] |
| 4 | 4[cite: 24] | $4 > 3$ (Invalid)[cite: 24] | — | — | Returns **`false`**[cite: 24] |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - A single pass through the array performs constant $O(1)$ arithmetic and comparisons per element[cite: 24]. Early return halts the scan immediately once the target index is covered[cite: 24].
- **Space Complexity:** $O(1)$ auxiliary space
  - Solved in-place using two scalar integer variables (`maxReach`, `n`)[cite: 24] without allocating additional memory[cite: 24].

---

### 5. Edge Cases & Key Takeaways

- **Single Element (`nums = [0]`):** When $n = 1$, `maxReach = 0 >= n - 1` (0 >= 0) is true on the very first iteration[cite: 24], returning `true` correctly.
- **Trapped by Zeros:** The check `if (i > maxReach) return false;` catches situations where all paths terminate at zero-valued cells before reaching the end[cite: 24].