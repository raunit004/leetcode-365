# Day 022: Generate Parentheses

- **Platform:** LeetCode #22[cite: 16]
- **Difficulty:** Medium[cite: 16]
- **Topic:** String, Dynamic Programming, Backtracking
- **Problem Link:** [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)
- **Language:** Java[cite: 16]

---

### 1. Problem Statement
Given `n` pairs of parentheses, write a function to generate all combinations of well-formed parentheses[cite: 16].

- Constraints: $1 \le n \le 8$[cite: 16]

---

### 2. Intuition & Approach

#### Constrained Backtracking ($O\left(\frac{4^n}{\sqrt{n}}\right)$ Time, $O(n)$ Space)
A brute-force generator would place '(' and ')' in all $2^{2n}$ positions and filter for validity using a stack, which quickly degrades performance. Instead, we can construct only valid prefixes on the fly using backtracking guided by two invariant rules[cite: 16]:

1. **Available Open Parentheses (`open < max`):**
   - We can place an opening bracket `'('` at any stage as long as we have not placed all `n` open brackets[cite: 16].
2. **Matching Close Parentheses (`close < open`):**
   - We can only place a closing bracket `')'` if the current number of closing brackets is strictly less than the open brackets already placed[cite: 16]. This prevents premature closure and guarantees prefix validity[cite: 16].
3. **Termination Condition (`current.length() == max * 2`):**
   - Once the accumulator reaches $2n$ characters, it forms a complete and valid sequence[cite: 16]. Add it to the result list and return[cite: 16].
4. **Buffer Restoration:**
   - After each recursive branch returns, prune the last character via `current.deleteCharAt(current.length() - 1)` to preserve buffer reuse[cite: 16].

---

### 3. Execution Trace

**Input:** `n = 2` (Target length: 4)

```text
                       "" (open=0, close=0)
                               |
                              "(" (open=1, close=0)
                       /               \
             "((" (open=2, close=0)     "()" (open=1, close=1)
                     |                           |
            "(()" (open=2, close=1)     "()(" (open=2, close=1)
                     |                           |
           "(())" [Valid Leaf]          "()()" [Valid Leaf]
```

| Step | Current Path | `open` | `close` | Valid Choices | Result Collection |
| :---: | :---: | :---: | :---: | :---: | :--- |
| **1** | `"("` | 1 | 0 | `open < 2` or `close < 1`[cite: 16] | — |
| **2** | `"(("` | 2 | 0 | `close < 2` only[cite: 16] | — |
| **3** | `"(()"` | 2 | 1 | `close < 2` only[cite: 16] | — |
| **4** | `"(())"` | 2 | 2 | Length is 4[cite: 16] | **Added `"(())"`** |
| **5** | `"()"` | 1 | 1 | `open < 2` only[cite: 16] | — |
| **6** | `"()("` | 2 | 1 | `close < 2` only[cite: 16] | — |
| **7** | `"()()"` | 2 | 2 | Length is 4[cite: 16] | **Added `"()()"`** |

**Final Output:** `["(())", "()()"]`

---

### 4. Complexity Analysis

- **Time Complexity:** $O\left(\frac{4^n}{\sqrt{n}}\right)$
  - The number of valid balanced parenthesis sequences of length $2n$ is governed by the $n^{\text{th}}$ Catalan number:
    $$C_n = \frac{1}{n+1}\binom{2n}{n} \approx \frac{4^n}{n\sqrt{\pi n}}$$
  - Since generating each combination of length $2n$ takes $O(n)$ time to convert to a string, total time is bounded by $O\left(\frac{4^n}{\sqrt{n}}\right)$. For $n \le 8$, $C_8 = 1430$ strings[cite: 16].
- **Space Complexity:** $O(n)$ auxiliary space
  - The recursion tree has a maximum depth of $2n$ frames[cite: 16]. The shared `StringBuilder` stores at most $2n$ characters[cite: 16].

---

### 5. Edge Cases & Key Takeaways

- **Minimal Input ($n = 1$):** Immediately emits `["()"]` across 2 steps[cite: 16].
- **Maximum Bound ($n = 8$):** Runs within $\approx 2$ milliseconds due to strict pruning, completely avoiding dead-end branches[cite: 16].
- **In-Place StringBuilder Manipulation:** Modifying a single mutable buffer via `append` and `deleteCharAt` eliminates redundant string allocations during recursion[cite: 16].