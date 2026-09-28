# Day 023: Merge k Sorted Lists

- **Platform:** LeetCode #23[cite: 17]
- **Difficulty:** Hard[cite: 17]
- **Topic:** Linked List, Divide and Conquer, Merge Sort[cite: 17]
- **Problem Link:** [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)
- **Language:** Java[cite: 17]

---

### 1. Problem Statement
You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order[cite: 17].

Merge all the linked-lists into one sorted linked-list and return it[cite: 17].

- $k == \text{lists.length}$
- $0 \le k \le 10^4$
- $0 \le \text{lists}[i]\text{.length} \le 500$
- $-10^4 \le \text{lists}[i][j] \le 10^4$
- `lists[i]` is sorted in ascending order[cite: 17].
- The sum of `lists[i].length` will not exceed $10^4$.

---

### 2. Intuition & Approach

#### Bottom-Up Divide and Conquer ($O(N \log k)$ Time, $O(1)$ Space)
Merging lists sequentially (merging list 0 with 1, then with 2, etc.) results in $O(k \cdot N)$ runtime, which triggers a Time Limit Exceeded (TLE) error for large $k$. 

Instead, we borrow the pairwise merging technique from Merge Sort:
1. **Interval Halving:**
   - Pair up lists that are `interval` apart: merge `lists[i]` with `lists[i + interval]`, storing the result back into `lists[i]`[cite: 17].
   - Double the interval after every round (`interval *= 2`)[cite: 17].
2. **Tree Reduction:**
   - Round 1 pairs: $(0, 1), (2, 3), (4, 5), \dots$
   - Round 2 pairs: $(0, 2), (4, 6), \dots$
   - Round 3 pairs: $(0, 4), \dots$
   - This reduces $k$ lists to 1 list in $\lceil \log_2 k \rceil$ levels[cite: 17].
3. **In-Place Array Updates:**
   - By reusing the input `lists` array to hold merged head references, this bottom-up iterative approach requires $O(1)$ extra heap memory, outperforming a `PriorityQueue` which requires $O(k)$ auxiliary space[cite: 17].

---

### 3. Execution Trace

**Input:** `lists = [L0, L1, L2]` where `L0 = [1,4,5]`, `L1 = [1,3,4]`, `L2 = [2,6]`[cite: 17]

```text
Round 1 (interval = 1):
  i = 0: merge(L0, L1) -> [1, 1, 3, 4, 4, 5] (stored at lists[0])
  i = 2: i + interval = 3 >= lists.length (L2 stays at lists[2])
  Lists state: [[1,1,3,4,4,5], L1, [2,6]]

Round 2 (interval = 2):
  i = 0: merge(lists[0], lists[2]) -> [1, 1, 2, 3, 4, 4, 5, 6] (stored at lists[0])
  Lists state: [[1,1,2,3,4,4,5,6], ...]

Loop terminates (interval = 4 >= lists.length).
```

**Final Result:** `[1, 1, 2, 3, 4, 4, 5, 6]`[cite: 17]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N \log k)$
  - There are $\log_2 k$ reduction levels[cite: 17]. At each level, every node across all $k$ lists is processed at most once during pairwise merging[cite: 17]. Thus, the total time is $O(N \log k)$, where $N$ is the total number of nodes across all lists.
- **Space Complexity:** $O(1)$ auxiliary space
  - Bottom-up iteration requires no recursion stack and reuses existing node links in place[cite: 17].

---

### 5. Edge Cases & Key Takeaways

- **Empty Array (`lists = []`):** Evaluates `lists.length == 0` immediately and returns `null`[cite: 17].
- **Lists with Empty Heads (`[[], [1]]`):** The standard two-way merge handles null components cleanly via `(l1 != null) ? l1 : l2`[cite: 17].
- **Odd Number of Lists:** The inner loop condition `i + interval < lists.length` automatically leaves unpaired tail lists intact for subsequent rounds without needing bounds padding[cite: 17].