# Day 061: Rotate Image

- **Platform:** LeetCode #48[cite: 22]
- **Difficulty:** Medium[cite: 22]
- **Topic:** Array, Math, Matrix[cite: 22]
- **Problem Link:** [Rotate Image](https://leetcode.com/problems/rotate-image/)
- **Language:** Java[cite: 22]
- **Time Complexity:** $O(N^2)$ where $N$ is the side length of the matrix[cite: 22]
- **Space Complexity:** $O(1)$ auxiliary space (in-place modification)[cite: 22]

---

### 1. Problem Overview
You are given an $n \times n$ 2D matrix representing an image[cite: 22]. Rotate the image by **90 degrees clockwise**[cite: 22].

You must rotate the image **in-place**, directly modifying the input matrix without allocating a secondary 2D matrix[cite: 22].

---

### 2. Intuition & Approach

#### Two-Step Linear Transformation:
A $90^\circ$ clockwise rotation maps the cell at index $(i, j)$ to $(j, n - 1 - i)$[cite: 22]. Directly updating cells in cyclic chains of 4 requires intricate boundary indexing. Decomposing this mapping into two successive linear operations achieves the identical result with cleaner symmetry[cite: 22]:

1. **Matrix Transpose (Reflection across the Main Diagonal):**
   - Swap `matrix[i][j]` with `matrix[j][i]` for all $j > i$[cite: 22].
   - This converts row $i$ into column $i$[cite: 22]:
     $$\begin{bmatrix} 1 & 2 & 3 \\ 4 & 5 & 6 \\ 7 & 8 & 9 \end{bmatrix} \xrightarrow{\text{Transpose}} \begin{bmatrix} 1 & 4 & 7 \\ 2 & 5 & 8 \\ 3 & 6 & 9 \end{bmatrix}$$
[cite: 22]

2. **Row Reflection (Horizontal Flip):**
   - For every row $i$, swap elements symmetrically across the vertical midline: `matrix[i][j]` with `matrix[i][n - 1 - j]` for $j \in [0, \lfloor n / 2 \rfloor - 1]$[cite: 22]:
     $$\begin{bmatrix} 1 & 4 & 7 \\ 2 & 5 & 8 \\ 3 & 6 & 9 \end{bmatrix} \xrightarrow{\text{Horizontal Flip}} \begin{bmatrix} 7 & 4 & 1 \\ 8 & 5 & 2 \\ 9 & 6 & 3 \end{bmatrix}$$
[cite: 22]

---

### 3. Execution Trace

#### Sample Input: $3 \times 3$ Matrix[cite: 22]
```text
Row 0: [1, 2, 3]
Row 1: [4, 5, 6]
Row 2: [7, 8, 9]
```[cite: 22]

#### Step 1: Transpose (`j > i`)[cite: 22]
- Swap $(0, 1)$ and $(1, 0)$: `2` $\leftrightarrow$ `4`[cite: 22]
- Swap $(0, 2)$ and $(2, 0)$: `3` $\leftrightarrow$ `7`[cite: 22]
- Swap $(1, 2)$ and $(2, 1)$: `6` $\leftrightarrow$ `8`[cite: 22]

State after transpose[cite: 22]:
```text
[1, 4, 7]
[2, 5, 8]
[3, 6, 9]
```

#### Step 2: Horizontal Row Inversion ($j < n / 2$)[cite: 22]
- Row 0: swap `matrix[0][0]` and `matrix[0][2]` (`1` $\leftrightarrow$ `7`)[cite: 22] $\rightarrow$ `[7, 4, 1]`[cite: 22]
- Row 1: swap `matrix[1][0]` and `matrix[1][2]` (`2` $\leftrightarrow$ `8`)[cite: 22] $\rightarrow$ `[8, 5, 2]`[cite: 22]
- Row 2: swap `matrix[2][0]` and `matrix[2][2]` (`3` $\leftrightarrow$ `9`)[cite: 22] $\rightarrow$ `[9, 6, 3]`[cite: 22]

**Final Output:** `[[7, 4, 1], [8, 5, 2], [9, 6, 3]]`[cite: 22]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N^2)$
  - Transposing evaluates the upper triangle consisting of $\frac{N(N - 1)}{2}$ swaps[cite: 22].
  - Horizontal reversal performs $N \times \lfloor \frac{N}{2} \rfloor$ swaps[cite: 22].
  - The sum of swaps is bounded strictly by $\approx N^2$ operations.
- **Space Complexity:** $O(1)$ auxiliary space
  - Swapping values using a single scalar variable `temp` satisfies the strict in-place modification requirement without extra array allocation[cite: 22].

---

### 5. Edge Cases & Key Takeaways

- **Strict In-Place Constraint:** Starting the transpose inner loop at `j = i + 1` prevents double-swapping cells back into their original locations[cite: 22].
- **Center Axis Retention:** The midline for odd $N$ (e.g., column index 1 in a $3 \times 3$ grid) is left unswapped during the horizontal flip because $j < n / 2$ bounds evaluation strictly before the center column[cite: 22].
- **$1 \times 1$ Matrix:** Loops for both transpose and horizontal flip do not execute when $n = 1$, leaving the single cell unchanged in $O(1)$ operations[cite: 22].