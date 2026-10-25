# Day 057: Permutations

- **Platform:** LeetCode #46[cite: 21]
- **Difficulty:** Medium[cite: 21]
- **Topic:** Array, Backtracking[cite: 21]
- **Problem Link:** [Permutations](https://leetcode.com/problems/permutations/)
- **Language:** Java[cite: 21]
- **Time Complexity:** $O(N \cdot N!)$ where $N$ is the length of `nums`[cite: 21]
- **Space Complexity:** $O(N)$ auxiliary space for recursion depth and visited tracking[cite: 21]

---

### 1. Problem Overview
Given an array `nums` of distinct integers, return all the possible permutations[cite: 21]. You may return the answer in any order[cite: 21].

---

### 2. Intuition & Approach

#### Backtracking with Visited Tracking:
A permutation is an ordered arrangement where each of the $N$ distinct elements must appear exactly once[cite: 21]:

1. **State Tracking (`used[]`):**
   - Maintain a boolean array `used` of size $N$ to track which elements are currently included in the active permutation path[cite: 21].
   - Checking `!used[i]` provides $O(1)$ verification of an unused element[cite: 21].
2. **Recursive Assembly:**
   - Loop through indices $i \in [0, N - 1]$ at every decision step[cite: 21].
   - If index $i$ has not been visited (`!used[i]`):
     - Mark `used[i] = true`[cite: 21].
     - Append `nums[i]` to the `current` path list[cite: 21].
     - Recurse via `backtrack(...)` to select the next position[cite: 21].
     - Backtrack state after returning: remove the last added element (`current.remove(current.size() - 1)`) and reset `used[i] = false`[cite: 21].
3. **Base Case:**
   - When `current.size() == nums.length`, a full permutation has been assembled[cite: 21].
   - Store a deep copy (`new ArrayList<>(current)`) into `result` and return[cite: 21].

---

### 3. Execution Trace

#### Sample Input: `nums = [1, 2, 3]`[cite: 21]

- Total expected permutations: $3! = 6$[cite: 21]

| Recursion Level | Active Candidate | `current` State | `used` State | Action Taken |
| :---: | :---: | :---: | :---: | :--- |
| Level 1 | `nums[0] = 1`[cite: 21] | `[1]` | `[T, F, F]` | Recurse to Level 2[cite: 21] |
| Level 2 | `nums[1] = 2`[cite: 21] | `[1, 2]` | `[T, T, F]` | Recurse to Level 3[cite: 21] |
| Level 3 | `nums[2] = 3`[cite: 21] | `[1, 2, 3]`[cite: 21] | `[T, T, T]` | **Base case reached**: Add `[1, 2, 3]`[cite: 21] |
| Level 2 | Backtrack 3 & 2 | `[1]` | `[T, F, F]` | Try next unused index (`i = 2`)[cite: 21] |
| Level 2 | `nums[2] = 3`[cite: 21] | `[1, 3]` | `[T, F, T]` | Recurse to Level 3[cite: 21] |
| Level 3 | `nums[1] = 2`[cite: 21] | `[1, 3, 2]`[cite: 21] | `[T, T, T]` | **Base case reached**: Add `[1, 3, 2]`[cite: 21] |

- Repeating for branches starting with `2` and `3` constructs the full output set: `[[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]]`[cite: 21].

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N \cdot N!)$
  - There are $N!$ leaf permutations generated[cite: 21]. Each leaf takes $O(N)$ time to copy `current` into a new list instance (`new ArrayList<>(current)`)[cite: 21]. The total number of nodes in the recursion tree is bounded by $\sum_{k=1}^N \frac{N!}{(N-k)!} \le e \cdot N!$, confirming overall $O(N \cdot N!)$ time.
- **Space Complexity:** $O(N)$ auxiliary space
  - The maximum depth of the recursion tree is $N$[cite: 21].
  - The `current` list and `used` boolean array each take $O(N)$ auxiliary memory[cite: 21].

---

### 5. Edge Cases & Key Takeaways

- **Single Element (`nums = [1]`):** Recursion terminates after depth 1, returning `[[1]]` in $O(1)$ steps[cite: 21].
- **Zero Allocations for Membership Check:** Using primitive array `boolean[] used` avoids $O(N)$ lookup checks like `current.contains(nums[i])`, which would otherwise inflate runtime to $O(N^2 \cdot N!)$.
- **Deep Copy Requirement:** Adding `new ArrayList<>(current)` rather than `result.add(current)` avoids storing references to a mutating list[cite: 21].