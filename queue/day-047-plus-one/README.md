# Day 047: Plus One

- **Platform:** LeetCode #66[cite: 13]
- **Difficulty:** Easy[cite: 13]
- **Topic:** Array, Math
- **Problem Link:** [Plus One](https://leetcode.com/problems/plus-one/)[cite: 13]
- **Language:** Java[cite: 13]
- **Time Complexity:** $O(N)$ worst-case, $O(1)$ best/average-case[cite: 13]
- **Space Complexity:** $O(1)$ auxiliary space ($O(N)$ allocation only on all-9 overflow)[cite: 13]

---

### 1. Problem Overview
Given a large integer represented as an integer array `digits`, where each `digits[i]` represents the $i$-th digit of the integer ordered from most significant to least significant in left-to-right order[cite: 13]. The input does not contain leading zeros[cite: 13].

Increment the integer by one and return the resulting array of digits[cite: 13].

---

### 2. Intuition & Approach

#### Right-to-Left Carry Propagation:
Adding 1 affects the least significant digit first (at index `digits.length - 1`)[cite: 13]:

1. **Traverse Backwards:**
   - Scan from the last index down to `0`[cite: 13].
2. **Early Exit on Non-Nine Digits:**
   - If `digits[i] < 9`, simply increment `digits[i]++` and return `digits` immediately[cite: 13]. No carry propagates further to higher-order digits[cite: 13].
3. **Carry Transition:**
   - If `digits[i] == 9`, incrementing rolls it over to $10$. Set `digits[i] = 0` and allow the carry of $1$ to cascade into the next iteration to the left (`i - 1`)[cite: 13].
4. **All-Nine Overflow Condition:**
   - If the loop completes without returning, every digit was originally `9` (e.g., `[9, 9, 9]` turned into `[0, 0, 0]`)[cite: 13].
   - Allocate a new array of size `digits.length + 1`[cite: 13].
   - Set the leading digit `result[0] = 1`[cite: 13]. All subsequent elements default to `0` in Java, accurately producing `1000`[cite: 13].

---

### 3. Execution Trace

#### Sample Input: `digits = [1, 2, 9]`

| Step | Index `i` | Current Digit `digits[i]` | Condition | New Value | Return Triggered? |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 2 | `9` | $9 \not< 9$ | `0` | No (carry continues)[cite: 13] |
| 2 | 1 | `2` | $2 < 9$ | `3` | **Yes** (return `[1, 3, 0]`)[cite: 13] |

#### Overflow Case: `digits = [9, 9]`

| Step | Index `i` | Current Digit `digits[i]` | Condition | New Value | State |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | 1 | `9` | $9 \not< 9$ | `0` | Carry to `i = 0`[cite: 13] |
| 2 | 0 | `9` | $9 \not< 9$ | `0` | Carry exceeds bounds[cite: 13] |

- Array allocation: `result = new int[3]` $\rightarrow$ `[0, 0, 0]`[cite: 13]
- Set most significant digit: `result[0] = 1` $\rightarrow$ **`[1, 0, 0]`**[cite: 13]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - In the worst case (all 9s), every digit is visited once ($O(N)$)[cite: 13]. For any number not ending in 9, it terminates in $O(1)$ operations on the first loop step[cite: 13].
- **Space Complexity:** $O(1)$ auxiliary space
  - The array modification is performed in-place[cite: 13]. Only when all digits roll over is a single new array of size $N + 1$ allocated to hold the result[cite: 13].

---

### 5. Edge Cases & Key Takeaways

- **Default Primitive Initialization:** When allocating `new int[digits.length + 1]`, Java automatically fills all positions with `0`, requiring only `result[0] = 1` to be assigned explicitly[cite: 13].
- **Arbitrary Precision:** By processing digits sequentially in an array, this approach avoids 32-bit or 64-bit integer overflow issues that occur when parsing numbers into `int` or `long`.