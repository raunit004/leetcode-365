# Day 075: Merge Intervals

- **Platform:** LeetCode #56[cite: 20]
- **Difficulty:** Medium[cite: 20]
- **Topic:** Array, Sorting
- **Problem Link:** [Merge Intervals](https://leetcode.com/problems/merge-intervals/)
- **Language:** Java[cite: 20]
- **Time Complexity:** $O(N \log N)$ where $N$ is `intervals.length`[cite: 20]
- **Space Complexity:** $O(N)$ for sorting stack overhead and storing output intervals[cite: 20]

---

### 1. Problem Overview
Given an array of `intervals` where `intervals[i] = [start_i, end_i]`, merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input[cite: 20].

---

### 2. Intuition & Approach

#### Sort-and-Extend Greedy Sweep:
If intervals are processed in arbitrary order, identifying all overlapping pairs requires pairwise comparisons ($O(N^2)$). Sorting the intervals primarily by their starting coordinates reduces this to a single linear sweep[cite: 20]:

1. **Sort by Start Bound:**
   - Sort `intervals` in ascending order based on `intervals[i][0]`[cite: 20]:
     `Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));`[cite: 20]
   - Sorting ensures that for any subsequent interval $j > i$, `nextInterval[0] >= currentInterval[0]` holds guaranteed[cite: 20].
2. **Overlap Resolution:**
   - Initialize `currentInterval = intervals[0]` and add it to `merged`[cite: 20].
   - For every subsequent interval:
     - **Overlapping Case (`nextInterval[0] <= currentInterval[1]`):** The next interval begins before or exactly at the current interval's end boundary[cite: 20]. Merge them by stretching the upper bound:
       `currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);`[cite: 20]
       Because Java passes object references, modifying `currentInterval[1]` directly updates the element already inside the `merged` list[cite: 20].
     - **Disjoint Case (`nextInterval[0] > currentInterval[1]`):** The intervals cannot overlap[cite: 20]. Update `currentInterval = nextInterval` and append it to `merged`[cite: 20].
3. **Format Result:**
   - Convert the dynamic list back into a 2D array: `merged.toArray(new int[merged.size()][]);`[cite: 20]

---

### 3. Execution Trace

#### Sample Input: `intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]`[cite: 20]

- After Sorting: `[[1, 3], [2, 6], [8, 10], [15, 18]]`[cite: 20]
- Initial State: `currentInterval = [1, 3]`, `merged = [[1, 3]]`[cite: 20]

| Step ($i$) | `nextInterval`[cite: 20] | Condition (`next[0] <= current[1]`)[cite: 20] | Evaluation | Action Taken[cite: 20] | `merged` List State |
| :---: | :---: | :---: | :---: | :--- | :--- |
| 1 | `[2, 6]`[cite: 20] | $2 \le 3$ (True)[cite: 20] | Overlap[cite: 20] | `current[1] = max(3, 6) = 6`[cite: 20] | `[[1, 6]]`[cite: 20] |
| 2 | `[8, 10]`[cite: 20] | $8 \le 6$ (False)[cite: 20] | Disjoint[cite: 20] | `currentInterval = [8, 10]`, add to list[cite: 20] | `[[1, 6], [8, 10]]`[cite: 20] |
| 3 | `[15, 18]`[cite: 20] | $15 \le 10$ (False)[cite: 20] | Disjoint[cite: 20] | `currentInterval = [15, 18]`, add to list[cite: 20] | `[[1, 6], [8, 10], [15, 18]]`[cite: 20] |

- **Output:** `[[1, 6], [8, 10], [15, 18]]`[cite: 20]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N \log N)$
  - Sorting an array of $N$ intervals takes $O(N \log N)$ time[cite: 20]. The subsequent pass iterates across $N - 1$ elements in $O(N)$ operations[cite: 20], making sorting the dominant factor.
- **Space Complexity:** $O(N)$ auxiliary space
  - The `merged` dynamic list stores at most $N$ intervals in the worst case (when no intervals overlap)[cite: 20]. The internal TimSort in Java additionally utilizes $O(N)$ space.

---

### 5. Edge Cases & Key Takeaways

- **Single Interval or Empty:** If `intervals.length <= 1`, the array is already merged; returning immediately handles inputs without extra overhead[cite: 20].
- **Touching Boundaries:** Intervals like `[1, 4]` and `[4, 5]` share a single point ($4 = 4$) and are considered overlapping per problem specifications[cite: 20]. The non-strict check `nextInterval[0] <= currentInterval[1]` handles this scenario directly[cite: 20].
- **Reference Mutation:** In-place modification of `currentInterval[1]` avoids calling `merged.remove(merged.size() - 1)` prior to re-insertion, minimizing list operations[cite: 20].