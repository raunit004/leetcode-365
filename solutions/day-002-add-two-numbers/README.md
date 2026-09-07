# Day 002: Add Two Numbers

- **Platform:** LeetCode #2
- **Difficulty:** Medium
- **Topic:** Linked List, Math
- **Problem Link:** [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/)
- **Language:** Java

---

### 1. Problem Statement
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in **reverse order**, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

- The two numbers do not contain any leading zero, except the number $0$ itself.
- Inputs are guaranteed to represent valid non-negative integers.

---

### 2. Intuition & Approach

#### Elementary Addition Simulation ($O(\max(M, N))$)
Because the digits are arranged in reverse order (least significant digit at the head), we can process the addition exactly like manual column-wise arithmetic from right to left:

1. **Dummy Head Pointer:** Using a `dummyHead` node avoids redundant boundary checks for the first node of the result list.
2. **Simultaneous Traversal:** Maintain pointers across `l1` and `l2` alongside an integer variable `carry`.
3. **Carry Propagation:** 
   - Sum at current position: $\text{sum} = \text{val1} + \text{val2} + \text{carry}$
   - Next carry: $\text{carry} = \lfloor \frac{\text{sum}}{10} \rfloor$
   - Node value to append: $\text{node\_val} = \text{sum} \pmod{10}$
4. **Loop Condition:** Continue as long as `l1 != null`, `l2 != null`, or `carry != 0`. Including `carry != 0` ensures a final leftover carry (e.g., $99 + 1 = 100$) creates an extra node automatically.

---

### 3. Execution Trace

**Input:** `l1 = [2, 4, 3]` ($342$), `l2 = [5, 6, 4]` ($465$)

| Step | `l1.val` | `l2.val` | Incoming Carry | `sum` | Node Value (`sum % 10`) | Outgoing Carry | Result List |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :--- |
| **1** | `2` | `5` | `0` | `7` | `7` | `0` | `[7]` |
| **2** | `4` | `6` | `0` | `10` | `0` | `1` | `[7 -> 0]` |
| **3** | `3` | `4` | `1` | `8` | `8` | `0` | `[7 -> 0 -> 8]` |

**Result:** Linked list `7 -> 0 -> 8` (representing $807$).

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\max(M, N))$
  - Where $M$ and $N$ represent the lengths of lists `l1` and `l2`.
  - The loop iterates at most $\max(M, N) + 1$ times to handle the longest list and any final overflow carry.
- **Space Complexity:** $O(\max(M, N))$
  - Auxiliary space for logic is $O(1)$ since only scalar variables (`carry`, `val1`, `val2`) are used.
  - The returned output linked list requires $\max(M, N) + 1$ nodes in the worst case.

---

### 5. Edge Cases & Key Takeaways

- **Unequal List Lengths:** When one list exhausts earlier than the other (e.g., `[9, 9]` and `[1]`), ternary operators fallback missing values to `0` cleanly.
- **Trailing Carry Overflow:** When two single digits sum to $\ge 10$ (e.g., `[5] + [5] = [0, 1]`), the condition `carry != 0` appends the final node without requiring post-loop checks.
- **Dummy Node Utility:** Retaining `dummyHead` eliminates null-pointer checks during head node creation and allows immediate return via `dummyHead.next`.