# Day 041: Valid Sudoku

- **Platform:** LeetCode #36[cite: 11]
- **Difficulty:** Medium[cite: 11]
- **Topic:** Array, Hash Table, Matrix[cite: 11]
- **Problem Link:** [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/)
- **Language:** Java[cite: 11]
- **Time Complexity:** $O(1)$ (fixed $9 \times 9$ board)
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
Determine if a $9 \times 9$ Sudoku board is valid[cite: 11]. Only filled cells containing digits `1` through `9` must be evaluated against three constraints[cite: 11]:
1. Each row must contain the digits `1-9` without repetition[cite: 11].
2. Each column must contain the digits `1-9` without repetition[cite: 11].
3. Each of the nine $3 \times 3$ sub-boxes must contain the digits `1-9` without repetition[cite: 11].

> Note: A valid board is not required to be solvable; it only needs to satisfy non-conflicting placement for currently occupied cells[cite: 11].

---

### 2. Intuition & Approach

#### Bitmask State Tracking:
Rather than allocating heavy `HashSet<Character>` objects across rows, columns, and sub-boxes, each digit `1`–`9` can be tracked as a single bit flag within an integer[cite: 11]:
1. **Bit Indexing:**
   - Map character `ch` to an offset $[0, 8]$: `val = ch - '1'`[cite: 11].
   - Construct a single bitmask flag: `mask = 1 << val`[cite: 11].
2. **Sub-box Partitioning:**
   - Map coordinates `(r, c)` to one of nine sub-boxes using integer division:
     $$\text{boxIndex} = \left(\lfloor r / 3 \rfloor \times 3\right) + \lfloor c / 3 \rfloor$$[cite: 11]
3. **Collision Detection:**
   - Perform bitwise AND checks: `(rows[r] & mask) != 0`, `(cols[c] & mask) != 0`, or `(boxes[boxIndex] & mask) != 0`[cite: 11].
   - If any bitwise check returns non-zero, the digit already exists in that entity, violating Sudoku rules[cite: 11].
4. **State Update:**
   - Register the observed digit via bitwise OR: `rows[r] |= mask`, `cols[c] |= mask`, and `boxes[boxIndex] |= mask`[cite: 11].

---

### 3. Execution Trace

#### Cell Evaluation: `board[0][0] = '5'`

- Value: `val = '5' - '1' = 4`
- Mask: `1 << 4 = 16` (`000010000` in binary)
- Target Indices:
  - Row index $r = 0$
  - Column index $c = 0$
  - Box index: $\lfloor 0 / 3 \rfloor \times 3 + \lfloor 0 / 3 \rfloor = 0$

| Entity | Previous Mask | Bitwise Check (`& mask`) | Collision? | New Mask (`|= mask`) |
| :--- | :---: | :---: | :---: | :---: |
| `rows[0]` | `0` | $0 \ \& \ 16 = 0$ | No | `16` (`...10000`) |
| `cols[0]` | `0` | $0 \ \& \ 16 = 0$ | No | `16` (`...10000`) |
| `boxes[0]` | `0` | $0 \ \& \ 16 = 0$ | No | `16` (`...10000`) |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(1)$
  - The grid dimensions are fixed at exactly $9 \times 9 = 81$ cells[cite: 11]. Iterating over 81 cells with $O(1)$ bitwise operations requires a constant number of CPU cycles (generalized to $O(N^2)$ for an $N \times N$ board).
- **Space Complexity:** $O(1)$ auxiliary space
  - Tracked via three static integer arrays of size 9 (`rows`, `cols`, `boxes`), requiring zero dynamic heap allocations[cite: 11].

---

### 5. Edge Cases & Key Takeaways

- **Empty Cells (`'.'`):** Bypassed immediately via `continue` without triggering state mutations[cite: 11].
- **Sparse vs. Dense Boards:** Operates with zero memory overhead regardless of how many cells are pre-filled.
- **Bitwise Performance:** Using 9 bits out of a 32-bit primitive integer avoids the hashing and memory overhead of collection classes.