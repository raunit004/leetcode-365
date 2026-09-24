# Day 019: Remove Nth Node From End of List

- **Platform:** LeetCode #19
- **Difficulty:** Medium
- **Topic:** Linked List, Two Pointers
- **Problem Link:** [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/)
- **Language:** Java

---

### 1. Problem Statement
Given the `head` of a linked list, remove the $n^{\text{th}}$ node from the end of the list and return its head.

- The number of nodes in the list is `sz`.
- $1 \le sz \le 30$
- $0 \le \text{Node.val} \le 100$
- $1 \le n \le sz$

---

### 2. Intuition & Approach

#### Single-Pass Two-Pointer Technique ($O(L)$ Time, $O(1)$ Space)
A naive approach requires two passes: first determining the list length $L$, then traversing $L - n$ nodes to delete the target. Using two pointers allows us to delete the node in a single traversal:

1. **Dummy Sentinel Node:**
   Prepend a `dummy` node pointing to `head` (`dummy.next = head`). This handles edge cases where the head itself must be deleted (e.g., removing the $1^{\text{st}}$ node from the end of a single-element list).
2. **Fixed Pointer Gap:**
   Advance the `fast` pointer $n + 1$ steps forward from `dummy`. This creates a distance of $n$ nodes between `slow` and `fast`.
3. **Simultaneous Traversal:**
   Advance both `slow` and `fast` one node at a time until `fast == null`. Because of the fixed $(n + 1)$ gap, when `fast` runs past the last node, `slow` lands directly on the node immediately preceding the target node.
4. **Pointer Bypass:**
   Reroute `slow.next = slow.next.next`, unlinking the target node and allowing garbage collection. Return `dummy.next`.

---

### 3. Execution Trace

**Input:** `head = [1, 2, 3, 4, 5]`, `n = 2`

```text
Initial setup with dummy:
[0 (dummy)] -> [1] -> [2] -> [3] -> [4] -> [5] -> null
 ^slow
 ^fast
```

1. **Advance `fast` by $n + 1 = 3$ steps:**
   - Step 0: `fast` at `0`
   - Step 1: `fast` at `1`
   - Step 2: `fast` at `2`
   - Step 3: `fast` at `3`
   - `slow` remains at `dummy (0)`.

2. **Advance both until `fast == null`:**

| Step | `slow` Position | `fast` Position |
| :---: | :---: | :---: |
| Initial | `0` (dummy) | `3` |
| 1 | `1` | `4` |
| 2 | `2` | `5` |
| 3 | `3` | `null` (loop ends) |

3. **Bypass node:**
   - `slow` is at node `3`.
   - `slow.next = slow.next.next` links `3` directly to `5`, removing `4`.

**Final List:** `[1, 2, 3, 5]`

---

### 4. Complexity Analysis

- **Time Complexity:** $O(L)$
  - The algorithm traverses the linked list containing $L$ nodes in a single pass.
- **Space Complexity:** $O(1)$ auxiliary space
  - Operates using only two pointer references (`slow`, `fast`) and a single sentinel node.

---

### 5. Edge Cases & Key Takeaways

- **Removing the Head Node ($n = sz$):** By starting both pointers at `dummy`, `slow` stays at `dummy`, cleanly deleting the head node via `dummy.next = dummy.next.next`.
- **Single Element List ($[1], n = 1$):** `fast` advances two steps to `null`. `slow` remains at `dummy`. `dummy.next` becomes `null`, correctly returning an empty list.
- **No Extra Memory Overhead:** The removal is performed in place via pointer manipulation without auxiliary storage arrays.