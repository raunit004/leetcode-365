# Day 024: Swap Nodes in Pairs

- **Platform:** LeetCode #24[cite: 18]
- **Difficulty:** Medium[cite: 18]
- **Topic:** Linked List, Recursion
- **Problem Link:** [Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/)
- **Language:** Java[cite: 18]

---

### 1. Problem Statement
Given a linked list, swap every two adjacent nodes and return its head[cite: 18]. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed)[cite: 18].

- The number of nodes in the list is in the range $[0, 100]$.
- $0 \le \text{Node.val} \le 100$

---

### 2. Intuition & Approach

#### Iterative In-Place Pointer Manipulation ($O(N)$ Time, $O(1)$ Space)
Rather than swapping the numerical values inside nodes (which violates the problem constraints[cite: 18]), we rearrange the references connecting pairs of nodes in place[cite: 18]:

1. **Sentinel Dummy Node:**
   - Instantiate `dummy` pointing to `head` (`dummy.next = head`)[cite: 18].
   - Maintain a `prev` pointer initialized to `dummy`[cite: 18]. This node acts as the tail of the already processed portion of the list[cite: 18].
2. **Pair Identification:**
   - At each step, verify whether a complete pair exists: `prev.next != null && prev.next.next != null`[cite: 18].
   - Label the pair nodes: `first = prev.next` and `second = prev.next.next`[cite: 18].
3. **Three-Way Pointer Realignment:**
   - Point `first.next` to the node following the pair: `first.next = second.next`[cite: 18].
   - Invert the pair direction: `second.next = first`[cite: 18].
   - Connect the preceding list to the new head of the pair: `prev.next = second`[cite: 18].
4. **Advance State:**
   - Advance `prev` to `first` (which is now the second node of the swapped pair) to prepare for the subsequent pair[cite: 18].
5. **Return Result:**
   - Return `dummy.next` as the updated list head[cite: 18].

---

### 3. Execution Trace

**Input:** `head = [1, 2, 3, 4]`[cite: 18]

```text
Initial Setup:
[0 (dummy)] -> [1] -> [2] -> [3] -> [4] -> null
 ^prev          ^first ^second
```

| Iteration | `prev` Position | `first` | `second` | Pointer Changes | Resulting Segment |
| :---: | :---: | :---: | :---: | :--- | :--- |
| **1** | `dummy (0)`[cite: 18] | `1`[cite: 18] | `2`[cite: 18] | `1.next = 3`, `2.next = 1`, `0.next = 2`[cite: 18] | `0 -> 2 -> 1 -> 3 -> 4` |
| **Next `prev`** | `1`[cite: 18] | `3`[cite: 18] | `4`[cite: 18] | — | Advance `prev = first`[cite: 18] |
| **2** | `1`[cite: 18] | `3`[cite: 18] | `4`[cite: 18] | `3.next = null`, `4.next = 3`, `1.next = 4`[cite: 18] | `... 1 -> 4 -> 3 -> null` |
| **Next `prev`** | `3`[cite: 18] | `null` | — | `prev.next == null` (loop terminates)[cite: 18] | Complete |

**Final Result:** `[2, 1, 4, 3]`[cite: 18]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - We traverse the list once, visiting and swapping nodes in groups of two in $O(1)$ pointer operations per pair.
- **Space Complexity:** $O(1)$ auxiliary space
  - The algorithm executes iteratively in place with constant memory, using only pointer references (`dummy`, `prev`, `first`, `second`) without recursion stack overhead[cite: 18].

---

### 5. Edge Cases & Key Takeaways

- **Empty List (`head == null`):** The condition `prev.next != null` evaluates to false immediately, returning `dummy.next == null`[cite: 18].
- **Single Node List (`[1]`):** `prev.next.next` is null, so the loop terminates without swaps, safely returning `[1]`[cite: 18].
- **Odd Length List (`[1, 2, 3]`):** Swaps `[1, 2]` to `[2, 1]` and leaves the trailing singleton `3` untouched.
- **Preserving Node References:** Direct link rewiring satisfies the constraint forbidding node value modifications[cite: 18].