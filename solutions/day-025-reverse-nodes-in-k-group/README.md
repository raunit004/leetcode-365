# Day 025: Reverse Nodes in k-Group

- **Platform:** LeetCode #25[cite: 19]
- **Difficulty:** Hard[cite: 19]
- **Topic:** Linked List, Recursion
- **Problem Link:** [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group/)
- **Language:** Java[cite: 19]

---

### 1. Problem Statement
Given the `head` of a linked list, reverse the nodes of the list `k` at a time, and return the modified list[cite: 19].

- `k` is a positive integer and is less than or equal to the length of the linked list[cite: 19].
- If the number of nodes is not a multiple of `k` then left-out nodes, in the end, should remain as it is[cite: 19].
- You may not alter the values in the list's nodes, only nodes themselves may be changed[cite: 19].

---

### 2. Intuition & Approach

#### Iterative In-Place Reversal with Group Windowing ($O(N)$ Time, $O(1)$ Space)
To reverse nodes without allocating extra memory or modifying values, we partition the linked list into independent windows of size $k$[cite: 19]:

1. **Sentinel Dummy Node:**
   - Instantiate `dummy` where `dummy.next = head`[cite: 19].
   - Maintain `groupPrev = dummy` to represent the node directly preceding the active group to be reversed[cite: 19].
2. **Lookahead Boundary Check (`getKth`):**
   - Advance $k$ steps forward from `groupPrev` to find the $k^{\text{th}}$ node (`kth`)[cite: 19].
   - If `kth == null`, fewer than $k$ nodes remain[cite: 19]. The left-out tail should remain intact, so terminate the process[cite: 19].
3. **In-Place Segment Reversal:**
   - Store the boundary to which the reversed sublist must link: `groupNext = kth.next`[cite: 19].
   - Reverse nodes from `groupPrev.next` up to `groupNext` by iteratively redirecting `curr.next = prev`, initializing `prev = groupNext` to wire the tail directly[cite: 19].
4. **Relinking Outer Pointers:**
   - The original head of the group (accessed via `groupPrev.next`) is now the tail of this reversed group[cite: 19].
   - Connect the previous segment to the new group head: `groupPrev.next = kth`[cite: 19].
   - Update `groupPrev` to the new group tail to prepare for the subsequent group[cite: 19].

---

### 3. Execution Trace

**Input:** `head = [1, 2, 3, 4, 5]`, `k = 2`[cite: 19]

```text
Initial State:
[0 (dummy)] -> [1] -> [2] -> [3] -> [4] -> [5] -> null
 ^groupPrev
```

| Pass | `groupPrev` | Target Group | `kth` Node | Reversed Sublist | Connected Output | Next `groupPrev` |
| :---: | :---: | :---: | :---: | :---: | :--- | :--- |
| **1** | `dummy (0)`[cite: 19] | `[1, 2]`[cite: 19] | `2`[cite: 19] | `2 -> 1`[cite: 19] | `0 -> 2 -> 1 -> 3 -> 4 -> 5`[cite: 19] | Node `1`[cite: 19] |
| **2** | `1`[cite: 19] | `[3, 4]`[cite: 19] | `4`[cite: 19] | `4 -> 3`[cite: 19] | `... 1 -> 4 -> 3 -> 5`[cite: 19] | Node `3`[cite: 19] |
| **3** | `3`[cite: 19] | `[5]`[cite: 19] | `null` (only 1 node left)[cite: 19] | Left intact[cite: 19] | `... 3 -> 5 -> null`[cite: 19] | Loop breaks[cite: 19] |

**Final Result:** `[2, 1, 4, 3, 5]`[cite: 19]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Each node is traversed once to find the $k^{\text{th}}$ boundary and once to perform in-place pointer reversal[cite: 19]. Overall time remains $2N = O(N)$.
- **Space Complexity:** $O(1)$ auxiliary space
  - Reversals occur strictly through pointer swaps using scalar reference variables (`dummy`, `groupPrev`, `kth`, `curr`, `prev`)[cite: 19].

---

### 5. Edge Cases & Key Takeaways

- **Fewer than $k$ Nodes Remaining:** Verified via `kth == null` before initiating reversal, leaving the tail intact[cite: 19].
- **$k = 1$:** The window advances node-by-node without rearranging references, producing an unchanged list in $O(N)$.
- **Full Multiple of $k$:** When the list length is divisible by $k$, the last group is reversed and the loop safely breaks on the subsequent `null` check.