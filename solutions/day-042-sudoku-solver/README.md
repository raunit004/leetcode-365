# Day 042: Sudoku Solver

- **Platform:** LeetCode #37[cite: 12]
- **Difficulty:** Hard[cite: 12]
- **Topic:** Array, Hash Table, Backtracking, Matrix
- **Problem Link:** [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/)[cite: 12]
- **Language:** Java[cite: 12]
- **Time Complexity:** $O(9^M)$ where $M \le 81$ is the number of empty cells ($O(1)$ for fixed $9 \times 9$)
- **Space Complexity:** $O(M)$ auxiliary recursion depth and index list

---

### 1. Problem Overview
Write a program to solve a $9 \times 9$ Sudoku puzzle by filling empty cells (`'.'`) in-place[cite: 12].

A valid solution must satisfy three constraints:
1. Each of the digits `1-9` must occur exactly once in each row[cite: 12].
2. Each of the digits `1-9` must occur exactly once in each column[cite: 12].
3. Each of the digits `1-9` must occur exactly once in each of the nine $3 \times 3$ sub-boxes[cite: 12].

It is guaranteed that the input board has a unique valid solution[cite: 12].

---

### 2. Intuition & Approach

#### Optimized Constraint Tracking with Backtracking:
A naive backtracking implementation scans every cell repeatedly and checks row, column, and box validity across 27 cells per candidate digit. We eliminate redundant scans via two optimizations:

1. **Pre-filtering Empty Cells:**
   - Scan the board once initially. Push the coordinates `(r, c)` of all empty cells into a list `emptyCells`[cite: 12].
   - Backtracking recurses only over the indices of this list ($0$ to `emptyCells.size() - 1`), avoiding loops to search for unassigned cells[cite: 12].

2. **$O(1)$ State Lookup Tables:**
   - Maintain boolean matrices `rows[9][10]`, `cols[9][10]`, and `boxes[9][10]`[cite: 12].
   - Pre-populate these tables using the initial board clues[cite: 12].
   - Any placement validity check reduces to checking `!rows[r][d] && !cols[c][d] && !boxes[b][d]` in $O(1)$ time[cite: 12].

3. **Backtracking Cycle:**
   - For cell `emptyCells[index]`, iterate candidate digits $d \in [1, 9]$[cite: 12].
   - If digit $d$ violates no constraints, place `d`, mark its presence in `rows`, `cols`, and `boxes`, and recursively attempt `backtrack(index + 1)`[cite: 12].
   - If the branch returns `true`, propagate early termination immediately[cite: 12].
   - If the branch dead-ends, revert the cell to `'.'` and unmark the boolean tables before trying the next digit[cite: 12].

---

### 3. Execution Trace

#### Sample Input Board:
Row 0 begins with `['5', '3', '.', '.', '7', '.', '.', '.', '.']`[cite: 12].

#### Pre-Processing:
- Filled cells mark constraints:
  - `board[0][0] = '5'` $\rightarrow$ `rows[0][5] = true`, `cols[0][5] = true`, `boxes[0][5] = true`[cite: 12]
  - `board[0][1] = '3'` $\rightarrow$ `rows[0][3] = true`, `cols[1][3] = true`, `boxes[0][3] = true`[cite: 12]
- First empty cell logged at `emptyCells[0] = (0, 2)` (`b = 0`)[cite: 12].

#### Backtracking Step (`index = 0`, cell `(0, 2)`):
- Evaluate digits $d \in [1, 9]$ for cell `(0, 2)`:
  - $d = 1$: Valid $\rightarrow$ place `'1'`, branch to `index = 1`
  - If a subsequent empty cell encounters a conflict where no digits $1..9$ fit, unwind back to `(0, 2)`
  - Next valid candidate $d = 4$ is tested and correctly fits the globally solvable path (`board[0][2] = '4'`)[cite: 12].

---

### 4. Complexity Analysis

- **Time Complexity:** $O(9^M)$
  - In the worst case, each empty cell branches across up to 9 candidates. Because the board size is strictly bounded by $9 \times 9$, the maximum operations cannot exceed $9^{81}$, operating as $O(1)$ in practical runtime limits. With constraint pruning, recursion explores only a small fraction of permutations.
- **Space Complexity:** $O(M)$ auxiliary space
  - The recursion stack reaches a maximum depth equal to $M = \text{emptyCells.size()} \le 81$[cite: 12].
  - Lookup arrays `rows`, `cols`, and `boxes` consume a constant $3 \times 9 \times 10$ booleans ($O(1)$ memory)[cite: 12].

---

### 5. Edge Cases & Key Takeaways

- **Sub-box Index Calculation:** Formula `b = (r / 3) * 3 + (c / 3)` maps row and column coordinates to sub-grid indices $[0, 8]$ consistently[cite: 12].
- **Early Exit:** Returning a boolean from `backtrack()` enables immediate recursion unraveling the moment the final empty cell is filled (`index == emptyCells.size()`)[cite: 12], preventing unnecessary additional search work.