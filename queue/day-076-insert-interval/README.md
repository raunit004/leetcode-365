# Day 076: Insert Interval

- **Platform:** LeetCode #57[cite: 21]
- **Difficulty:** Medium[cite: 21]
- **Topic:** Array
- **Problem Link:** [Insert Interval](https://leetcode.com/problems/insert-interval/)
- **Language:** Java[cite: 21]
- **Time Complexity:** $O(N)$ where $N$ is `intervals.length`[cite: 21]
- **Space Complexity:** $O(N)$ to build and return the merged array[cite: 21]

---

### 1. Problem Overview
You are given an array of non-overlapping intervals `intervals` where `intervals[i] = [start_i, end_i]` represent the start and end of the $i^{\text{th}}$ interval, sorted in ascending order by `start_i`[cite: 21]. You are also given an interval `newInterval = [start, end]` representing another interval[cite: 21].

Two intervals are considered overlapping if they share at least one point[cite: 21].

Insert `newInterval` into `intervals` such that `intervals` is still sorted in ascending order by `start_i` and does not have any overlapping intervals (merging overlapping intervals if necessary)[cite: 21]. You do not need to modify `intervals` in-place[cite: 21].

---

### 2. Intuition & Approach

#### Three-Phase Linear Sweep:
Because `intervals` is already sorted by start time and initially non-overlapping, any overlap with `newInterval` must be contiguous[cite: 21]. The entire insertion can be executed in three chronological phases in a single pass[cite: 21]:

1. **Before Overlap:**
   - Any interval ending strictly before `newInterval` begins (`intervals[i][1] < newInterval[0]`) has no overlap[cite: 21].
   - Add these intervals directly to `result`[cite: 21].
2. **During Overlap:**
   - As long as `intervals[i]` starts before or when `newInterval` ends (`intervals[i][0] <= newInterval[1]`), the intervals overlap[cite: 21].
   - Merge `intervals[i]` into `newInterval` by expanding its boundaries[cite: 21]:
     - `newInterval[0] = Math.min(newInterval[0], intervals[i][0])`[cite: 21]
     - `newInterval[1] = Math.max(newInterval[1], intervals[i][1])`[cite: 21]
   - Advance `i`[cite: 21].
   - Once no further overlapping intervals remain, add the final merged `newInterval` to `result`[cite: 21].
3. **After Overlap:**
   - All subsequent intervals start strictly after `newInterval` ends[cite: 21].
   - Append the remaining intervals directly to `result` without alteration[cite: 21].

---

### 3. Execution Trace

#### Sample Input: `intervals = [[1, 3], [6, 9]]`, `newInterval = [2, 5]`[cite: 21]

- Total intervals: $n = 2$, Pointer $i = 0$[cite: 21]

| Phase | Current Interval (`intervals[i]`)[cite: 21] | Condition Evaluated[cite: 21] | Action Taken[cite: 21] | Result State[cite: 21] |
| :---: | :---: | :---: | :--- | :--- |
| Phase 1 | `[1, 3]`[cite: 21] | `intervals[0][1] < newInterval[0]` ($3 < 2$) is false[cite: 21] | Phase 1 ends ($i = 0$)[cite: 21] | `[]`[cite: 21] |
| Phase 2 | `[1, 3]`[cite: 21] | `intervals[0][0] <= newInterval[1]` ($1 \le 5$) is true[cite: 21] | Merge: `newInterval = [min(2,1), max(5,3)] = [1, 5]`, advance $i = 1$[cite: 21] | `[]`[cite: 21] |
| Phase 2 | `[6, 9]`[cite: 21] | `intervals[1][0] <= newInterval[1]` ($6 \le 5$) is false[cite: 21] | Overlap ends; add `[1, 5]` to `result`[cite: 21] | `[[1, 5]]`[cite: 21] |
| Phase 3 | `[6, 9]`[cite: 21] | $i < n$ ($1 < 2$)[cite: 21] | Add `intervals[1]` directly to `result`, advance $i = 2$[cite: 21] | `[[1, 5], [6, 9]]`[cite: 21] |

- **Output:** `[[1, 5], [6, 9]]`[cite: 21]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Pointer $i$ advances monotonically from $0$ to $N$ across the three while-loops[cite: 21]. Each interval is inspected and appended to the output list once, running in linear time without sorting[cite: 21].
- **Space Complexity:** $O(N)$
  - Output list `result` stores at most $N + 1$ intervals[cite: 21]. Auxiliary working memory is $O(1)$[cite: 21].

---

### 5. Edge Cases & Key Takeaways

- **Empty Input (`intervals = []`):** Phase 1 and Phase 2 loops do not execute; `newInterval` is appended and returned immediately as `[[start, end]]`[cite: 21].
- **Insert at Front or Back:** If `newInterval` does not overlap any interval and lies entirely before or after all elements, Phase 1 or Phase 3 appends all elements naturally while Phase 2 commits `newInterval`[cite: 21].
- **Linear Advantage Over Merge Intervals:** Unlike LeetCode #56 where intervals were unsorted and required $O(N \log N)$ sorting, #57 leverages the pre-sorted input guarantee to execute in clean $O(N)$ time[cite: 20, 21].