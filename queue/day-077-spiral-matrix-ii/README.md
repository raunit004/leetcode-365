# Day 077: Spiral Matrix II

- **Platform:** LeetCode #59[cite: 22]
- **Difficulty:** Medium[cite: 22]
- **Topic:** Array, Matrix, Simulation[cite: 22]
- **Problem Link:** [Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/)
- **Language:** Java[cite: 22]
- **Time Complexity:** $O(n^2)$ to populate all cells in the grid[cite: 22]
- **Space Complexity:** $O(1)$ auxiliary space (excluding the allocated $n \times n$ output matrix)[cite: 22]

---

### 1. Problem Overview
Given a positive integer `n`, generate an $n \times n$ matrix filled with elements from $1$ to $n^2$ in spiral order[cite: 22].

---

### 2. Intuition & Approach

#### Layer-by-Layer Boundary Contraction:
This problem mirrors the traversal logic of LeetCode #54 (*Spiral Matrix*), but writes values into an initially unpopulated $n \times n$ array instead of reading them[cite: 18, 22]:

1. **Boundary Initialization:**
   - `top = 0`, `bottom = n - 1`[cite: 22]
   - `left = 0`, `right = n - 1`[cite: 22]
   - `num = 1` (counter tracking the next integer to insert)[cite: 22]
2. **Four-Phase Perimeter Fill:**
   - **Top Edge (Left $\rightarrow$ Right):** Set `matrix[top][c] = num++` for $c \in [\text{left}, \text{right}]$, then increment `top++`[cite: 22].
   - **Right Edge (Top $\rightarrow$ Bottom):** Set `matrix[r][right] = num++` for $r \in [\text{top}, \text{bottom}]$, then decrement `right--`[cite: 22].
   - **Bottom Edge (Right $\rightarrow$ Left):** If `top <= bottom`, set `matrix[bottom][c] = num++` for $c \in [\text{right}, \text{left}]$ in decreasing order, then decrement `bottom--`[cite: 22].
   - **Left Edge (Bottom $\rightarrow$ Top):** If `left <= right`, set `matrix[r][left] = num++` for $r \in [\text{bottom}, \text{top}]$ in decreasing order, then increment `left++`[cite: 22].
3. **Loop Condition:**
   - Repeat the cycle while `top <= bottom && left <= right`[cite: 22]. Once boundaries cross, all $n^2$ values are set[cite: 22].

---

### 3. Execution Trace

#### Sample Input: `n = 3`[cite: 22]

- Initial: `top = 0`, `bottom = 2`, `left = 0`, `right = 2`, `num = 1`[cite: 22]

| Cycle | Direction | Boundary Traversed[cite: 22] | Values Written[cite: 22] | Updated Boundaries[cite: 22] |
| :---: | :---: | :---: | :---: | :---: |
| 1 | Right | Row `top = 0`, cols $0 \rightarrow 2$[cite: 22] | `matrix[0][0..2] = [1, 2, 3]`[cite: 22] | `top = 1`[cite: 22] |
| 1 | Down | Col `right = 2`, rows $1 \rightarrow 2$[cite: 22] | `matrix[1..2][2] = [4, 5]`[cite: 22] | `right = 1`[cite: 22] |
| 1 | Left | Row `bottom = 2`, cols $1 \rightarrow 0$[cite: 22] | `matrix[2][1..0] = [6, 7]`[cite: 22] | `bottom = 1`[cite: 22] |
| 1 | Up | Col `left = 0`, rows $1 \rightarrow 1$[cite: 22] | `matrix[1][0] = 8`[cite: 22] | `left = 1`[cite: 22] |
| 2 | Right | Row `top = 1`, cols $1 \rightarrow 1$[cite: 22] | `matrix[1][1] = 9`[cite: 22] | `top = 2`[cite: 22] |

- Following `top++`, `top <= bottom` ($2 \le 1$) is false; remaining legs skip and loop terminates[cite: 22].
- **Output:** `[[1, 2, 3], [8, 9, 4], [7, 6, 5]]`[cite: 22]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(n^2)$
  - Exactly $n^2$ cells are populated, each receiving an assignment in $O(1)$ operations[cite: 22].
- **Space Complexity:** $O(1)$ auxiliary space
  - Uses five primitive integer variables (`top`, `bottom`, `left`, `right`, `num`) without auxiliary arrays or recursive call stacks[cite: 22].

---

### 5. Edge Cases & Key Takeaways

- **Single Cell (`n = 1`):** The first loop fills `matrix[0][0] = 1`, increments `top`, and the loop exits immediately, producing `[[1]]`[cite: 22].
- **Center Element in Odd $n$:** The final center cell is filled cleanly during the final horizontal pass (`top == bottom && left == right`), after which the loop exits gracefully without out-of-bounds indexing[cite: 22].