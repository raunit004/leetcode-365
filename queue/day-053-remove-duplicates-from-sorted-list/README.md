# Day 053: Remove Duplicates from Sorted List

- **Platform:** LeetCode #83[cite: 18]
- **Difficulty:** Easy[cite: 18]
- **Topic:** Linked List
- **Problem Link:** [Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)
- **Language:** Java[cite: 18]
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
Given the `head` of a sorted linked list, delete all duplicates such that each element appears only once[cite: 18]. Return the linked list sorted as well[cite: 18].

---

### 2. Intuition & Approach

#### In-Place Adjacent Comparison:
Because the linked list is sorted in non-decreasing order, all duplicate elements appear strictly adjacent to one another[cite: 18]:

1. **Pointer Traversal:**
   - Initialize pointer `curr = head`[cite: 18].
   - Loop while both `curr` and `curr.next` are non-null[cite: 18].
2. **Duplicate Detection & Deletion:**
   - If `curr.val == curr.next.val`, bypass the adjacent duplicate node by resetting the forward link: `curr.next = curr.next.next`[cite: 18].
   - **Crucial Invariant:** Do not advance `curr` when a duplicate is removed[cite: 18]. There may be three or more consecutive identical values (e.g., `1 -> 1 -> 1`), and `curr` must remain at the current distinct node until all adjacent duplicates have been severed[cite: 18].
3. **Advance Pointer:**
   - When `curr.val != curr.next.val`, the next node is confirmed unique; move forward: `curr = curr.next`[cite: 18].
4. **Return Head:**
   - The head of the list never changes because the very first occurrence of the initial value is always retained[cite: 18]. Return `head` directly[cite: 18].

---

### 3. Execution Trace

#### Sample Input: `head = [1, 1, 2]`[cite: 18]

- Initial: `curr` at node `1` (first index), `curr.next` at node `1` (second index)[cite: 18].

| Iteration | `curr.val`[cite: 18] | `curr.next.val`[cite: 18] | Condition (`curr.val == curr.next.val`)[cite: 18] | Action | Resulting List Shape |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | `1`[cite: 18] | `1`[cite: 18] | `true` ($1 == 1$)[cite: 18] | `curr.next = curr.next.next`[cite: 18] | `1 -> 2`[cite: 18] |
| 2 | `1`[cite: 18] | `2`[cite: 18] | `false` ($1 \neq 2$)[cite: 18] | `curr = curr.next`[cite: 18] | `1 -> 2` (pointer moves to `2`)[cite: 18] |

- `curr.next == null`: Loop terminates[cite: 18].
- Return `head`: **`[1, 2]`**[cite: 18]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Each node in the list of length $N$ is evaluated at most once. Either a duplicate node is removed or `curr` advances to the next distinct node[cite: 18].
- **Space Complexity:** $O(1)$ auxiliary space
  - Node links are updated in-place using a single pointer variable (`curr`) without allocating new nodes or auxiliary data structures[cite: 18].

---

### 5. Edge Cases & Key Takeaways

- **Empty List or Single Node:** If `head == null` or `head.next == null`, the loop condition `curr != null && curr.next != null` fails immediately and returns `head` unchanged in $O(1)$ time[cite: 18].
- **All Identical Nodes:** For an input like `[1, 1, 1, 1]`, `curr` stays anchored at the first node while repeatedly reassigning `curr.next` until `curr.next == null`, correctly producing `[1]`.
- **No Duplicates:** If all elements are distinct, the loop simply traverses forward step-by-step with `curr = curr.next` without altering pointers[cite: 18].