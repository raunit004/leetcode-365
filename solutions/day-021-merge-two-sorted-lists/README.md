# Day 021: Merge Two Sorted Lists

- **Platform:** LeetCode #21[cite: 17]
- **Difficulty:** Easy[cite: 17]
- **Topic:** Linked List, Recursion
- **Problem Link:** [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
- **Language:** Java[cite: 17]

---

### 1. Problem Statement
You are given the heads of two sorted linked lists `list1` and `list2`[cite: 17].

Merge the two lists into one **sorted** list[cite: 17]. The list should be made by splicing together the nodes of the first two lists[cite: 17].

Return the head of the merged linked list[cite: 17].

- The number of nodes in both lists is in the range $[0, 50]$.
- $-100 \le \text{Node.val} \le 100$
- Both `list1` and `list2` are sorted in non-decreasing order.

---

### 2. Intuition & Approach

#### Iterative In-Place Splicing with Sentinel Dummy ($O(M + N)$ Time, $O(1)$ Space)
Because both input lists are already sorted, we can build the merged list incrementally by selecting the node with the smaller value at each step[cite: 17]:

1. **Sentinel Dummy Node:**
   - Create a dummy node `new ListNode(-1)` to eliminate conditional checks for the initial head assignment[cite: 17].
   - Maintain a pointer `current` initialized to `dummy` to track the tail of the merged chain[cite: 17].
2. **Two-Pointer Traversal:**
   - While both `list1` and `list2` are not null[cite: 17]:
     - If `list1.val <= list2.val`, link `current.next` to `list1` and advance `list1`[cite: 17].
     - Otherwise, link `current.next` to `list2` and advance `list2`[cite: 17].
     - Advance `current = current.next`[cite: 17].
3. **Tail Splicing:**
   - When one list becomes null, the remaining list is already sorted and can be directly linked in $O(1)$:
     `current.next = (list1 != null) ? list1 : list2;`[cite: 17]
4. **Return Head:**
   - Return `dummy.next`, which points to the true start of the merged linked list[cite: 17].

---

### 3. Execution Trace

**Input:** `list1 = [1, 2, 4]`, `list2 = [1, 3, 4]`[cite: 17]

| Step | `list1.val` | `list2.val` | Comparison | Action Taken | Merged List Chain |
| :---: | :---: | :---: | :---: | :--- | :--- |
| **0** | `1` | `1` | $1 \le 1$[cite: 17] | Append `list1(1)`, advance `list1`[cite: 17] | `dummy -> 1` |
| **1** | `2` | `1` | $2 > 1$[cite: 17] | Append `list2(1)`, advance `list2`[cite: 17] | `dummy -> 1 -> 1` |
| **2** | `2` | `3` | $2 \le 3$[cite: 17] | Append `list1(2)`, advance `list1`[cite: 17] | `dummy -> 1 -> 1 -> 2` |
| **3** | `4` | `3` | $4 > 3$[cite: 17] | Append `list2(3)`, advance `list2`[cite: 17] | `dummy -> 1 -> 1 -> 2 -> 3` |
| **4** | `4` | `4` | $4 \le 4$[cite: 17] | Append `list1(4)`, advance `list1`[cite: 17] | `dummy -> 1 -> 1 -> 2 -> 3 -> 4` |
| **End** | `null` | `4` | `list1 == null`[cite: 17] | Append remaining `list2` (`4`)[cite: 17] | `dummy -> 1 -> 1 -> 2 -> 3 -> 4 -> 4` |

**Final Result:** `[1, 1, 2, 3, 4, 4]`[cite: 17]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(M + N)$
  - Each iteration appends exactly one node to the result. In the worst case, we make at most $M + N$ node comparisons, where $M$ and $N$ are the lengths of `list1` and `list2`.
- **Space Complexity:** $O(1)$ auxiliary space
  - The algorithm reuses existing list nodes in place by adjusting their `next` pointers[cite: 17]. No additional nodes are created on the heap except for a single sentinel node[cite: 17].

---

### 5. Edge Cases & Key Takeaways

- **One or Both Lists Empty:** If either list is null from the beginning, the loop is skipped and `current.next` immediately attaches the non-null list (or null if both are empty)[cite: 17].
- **Different List Lengths:** The final ternary assignment links the rest of the longer list in a single pointer operation without requiring further loops[cite: 17].
- **In-Place Splicing:** Relinking references avoids unnecessary object instantiation, saving memory and garbage collection overhead[cite: 17].