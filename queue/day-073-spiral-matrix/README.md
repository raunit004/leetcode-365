# Day 073: Spiral Matrix

- **Platform:** LeetCode #54[cite: 23]
- **Difficulty:** Medium[cite: 23]
- **Topic:** Array, Matrix, Simulation
- **Problem Link:** [Spiral Matrix](https://leetcode.com/problems/spiral-matrix/)
- **Language:** Java[cite: 23]
- **Time Complexity:** $O(M \times N)$ where $M$ is row count and $N$ is column count[cite: 23]
- **Space Complexity:** $O(1)$ auxiliary space (excluding the output list)[cite: 23]

---

### 1. Problem Overview
Given an $m \times n$ `matrix`, return all elements of the `matrix` in **spiral order**[cite: 23].

Traversal proceeds clockwise, starting at the top-left coordinate $(0, 0)$ and circling inward layer by layer[cite: 23].

---

### 2. Intuition & Approach

#### Four-Boundary Contraction:
A spiral traversal unwraps concentric rectangular perimeters until no rows or columns remain[cite: 23]:

1. **Boundary Markers:**
   - `top = 0` (uppermost unvisited row)[cite: 23]
   - `bottom = matrix.length - 1` (lowermost unvisited row)[cite: 23]
   - `left = 0` (leftmost unvisited column)[cite: 23]
   - `right = matrix[0].length - 1` (rightmost unvisited column)[cite: 23]

2. **Perimeter Traversal Cycle:**
   - **Move Right:** Iterate column $c$ from `left` to `right` at row `top`, then increment `top++`[cite: 23].
   - **Move Down:** Iterate row $r$ from `top` to `bottom` at column `right`, then decrement `right--`[cite: 23].
   - **Move Left:** If `top <= bottom`, iterate column $c$ backwards from `right` to `left` at row `bottom`, then decrement `bottom--`[cite: 23].
   - **Move Up:** If `left <= right`, iterate row $r$ backwards from `bottom` to `top` at column `left`, then increment `left++`[cite: 23].

3. **Boundary Guard Invariant:**
   - In non-square rectangular grids ($m \neq n$), incrementing `top` or decrementing `right` during the first two phases can violate the active bounds (`top > bottom` or `left > right`)[cite: 23].
   - Explicit checks `if (top <= bottom)` and `if (left <= right)` prevent duplicate traversals across single-row or single-column center strips[cite: 23].

---

### 3. Execution Trace

#### Sample Input: `matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]`[cite: 23]

- Initial: `top = 0`, `bottom = 2`, `left = 0`, `right = 2`[cite: 23]

| Leg | Direction | Path Range | Elements Collected[cite: 23] | Updated Boundaries |
| :---: | :---: | :---: | :---: | :---: |
| 1 | Right | $c = 0 \rightarrow 2$ along row $0$[cite: 23] | `[1, 2, 3]`[cite: 23] | `top = 1`[cite: 23] |
| 2 | Down | $r = 1 \rightarrow 2$ along col $2$[cite: 23] | `[6, 9]`[cite: 23] | `right = 1`[cite: 23] |
| 3 | Left | $c = 1 \rightarrow 0$ along row $2$[cite: 23] | `[8, 7]`[cite: 23] | `bottom = 1`[cite: 23] |
| 4 | Up | $r = 1 \rightarrow 1$ along col $0$[cite: 23] | `[4]`[cite: 23] | `left = 1`[cite: 23] |
| 5 | Right | $c = 1 \rightarrow 1$ along row $1$[cite: 23] | `[5]`[cite: 23] | `top = 2`[cite: 23] |

- Check `top <= bottom` ($2 \le 1$) fails; outer while loop terminates[cite: 23].
- **Output:** `[1, 2, 3, 6, 9, 8, 7, 4, 5]`[cite: 23]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(M \times N)$
  - Every element in the $M \times N$ matrix is visited and appended to the output list exactly once[cite: 23].
- **Space Complexity:** $O(1)$ auxiliary space
  - The traversal state is maintained using four primitive boundary integers (`top`, `bottom`, `left`, `right`) without allocating a secondary matrix or visited table[cite: 23].

---

### 5. Edge Cases & Key Takeaways

- **Empty / Null Matrix:** Guarded at lines 4–6[cite: 23], safely returning an empty list in $O(1)$ time[cite: 23].
- **Single Row ($1 \times N$):** After traversing right, `top++` causes `top > bottom`[cite: 23]. The condition `if (top <= bottom)` prevents reading the same row in reverse[cite: 23].
- **Single Column ($M \times 1$):** After moving down, `right--` causes `left > right`[cite: 23]. The guard `if (left <= right)` prevents traversing back up the same column[cite: 23].