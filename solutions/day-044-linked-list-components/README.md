# Day 044: Linked List Components

- **Platform:** LeetCode #817[cite: 10]
- **Difficulty:** Medium[cite: 10]
- **Topic:** Linked List, Hash Table[cite: 10]
- **Problem Link:** [Linked List Components](https://leetcode.com/problems/linked-list-components/)
- **Language:** Java[cite: 10]
- **Time Complexity:** $O(N + M)$
- **Space Complexity:** $O(M)$ auxiliary space

---

### 1. Problem Overview
Given the `head` of a linked list containing unique integer values and an integer array `nums` that forms a subset of the linked list values, find the number of **connected components** in `nums`[cite: 10].

A connected component is defined as a non-empty, maximal sequence of consecutive nodes in the linked list where every node's value belongs to `nums`[cite: 10].

---

### 2. Intuition & Approach

#### Boundary Detection (Component Tail Counting):
Instead of grouping or slicing nodes into dynamic sublists, count connected components by identifying their **endpoint transitions**[cite: 10]:

1. **$O(1)$ Membership Lookup:**
   - Store all elements of `nums` in a `HashSet<Integer>` so node value checks take $O(1)$ average time[cite: 10].
2. **Identifying the End of a Component:**
   - Traverse the linked list using pointer `curr`[cite: 10].
   - If `curr.val` is present in `set`, it belongs to a connected segment[cite: 10].
   - That segment concludes at `curr` if and only if:
     - `curr.next == null` (the list ends)[cite: 10], OR
     - `curr.next.val` is **not** present in `set` (the consecutive sequence breaks)[cite: 10].
3. **Increment Count:**
   - Every detected boundary signifies exactly one unique maximal connected component[cite: 10]. Increment `count` when this condition is satisfied[cite: 10].

---

### 3. Execution Trace

#### Sample Input: `head = [0, 1, 2, 3]`, `nums = [0, 1, 3]`[cite: 10]

- `set = {0, 1, 3}`[cite: 10]

| Node (`curr.val`)[cite: 10] | `set.contains(curr.val)` | Next Node (`curr.next.val`)[cite: 10] | `set.contains(curr.next.val)` | End of Component? | `count` |
| :---: | :---: | :---: | :---: | :---: | :---: |
| `0`[cite: 10] | `true` | `1`[cite: 10] | `true` | No (sequence continues) | 0 |
| `1`[cite: 10] | `true` | `2`[cite: 10] | `false` | **Yes** (transition to non-member)[cite: 10] | 1 |
| `2`[cite: 10] | `false` | `3`[cite: 10] | `true` | No (`curr` not in set) | 1 |
| `3`[cite: 10] | `true` | `null` | — | **Yes** (tail of list reached)[cite: 10] | **2**[cite: 10] |

**Output:** `2` (Components are `[0, 1]` and `[3]`)[cite: 10]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N + M)$
  - Building the hash set takes $O(M)$ time where $M$ is the length of `nums`[cite: 10].
  - Traversing the linked list of length $N$ performs $O(1)$ hash set lookups per node, taking $O(N)$ time[cite: 10].
- **Space Complexity:** $O(M)$ auxiliary space
  - The `HashSet` retains $M$ unique values from array `nums`[cite: 10].

---

### 5. Edge Cases & Key Takeaways

- **Whole List Connected:** If `nums` contains all nodes in the list, the boundary check triggers once at `curr.next == null`[cite: 10], returning `1`.
- **Isolated Elements:** Consecutive non-membership nodes cleanly separate individual single-node components without double-counting.
- **Short-Circuit Evaluation:** Checking `curr.next == null` before `curr.next.val` prevents `NullPointerException` when reaching the list tail[cite: 10].