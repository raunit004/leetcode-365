# Day 078: Rotate List

- **Platform:** LeetCode #61[cite: 23]
- **Difficulty:** Medium[cite: 23]
- **Topic:** Linked List, Two Pointers
- **Problem Link:** [Rotate List](https://leetcode.com/problems/rotate-list/)
- **Language:** Java[cite: 23]
- **Time Complexity:** $O(N)$ where $N$ is the number of nodes in the linked list
- **Space Complexity:** $O(1)$ auxiliary space[cite: 23]

---

### 1. Problem Overview
Given the `head` of a linked list, rotate the list to the right by `k` places[cite: 23].

---

### 2. Intuition & Approach

#### Circular Ring Transformation & Severance:
Rotating a list of length $N$ to the right by $k$ places means the last $k \pmod N$ nodes shift to the front of the list, while the preceding $N - (k \pmod N)$ nodes shift to the back[cite: 23]. Rather than shifting nodes one by one ($O(k \cdot N)$), we can join the list into a circle and make a single cut[cite: 23]:

1. **Edge Conditions:**
   - If `head == null`, `head.next == null`, or `k == 0`, no rotation is required; return `head` directly[cite: 23].
2. **Length Measurement & Tail Discovery:**
   - Traverse to the end of the list with a pointer `tail` to compute the total node count `length`[cite: 23].
3. **Effective Rotation Count:**
   - Compute `k = k % length`[cite: 23].
   - If $k = 0$, the list completes a full cycle and retains its original configuration; return `head`[cite: 23].
4. **Form Circular Ring:**
   - Connect the current tail to the head: `tail.next = head`[cite: 23].
5. **Locate New Breakpoint:**
   - The new tail resides at position `stepsToNewTail = length - k` (1-indexed from `head`)[cite: 23].
   - Traverse `stepsToNewTail - 1` steps starting from `head` to identify `newTail`[cite: 23].
   - Assign the node directly following `newTail` as the new head: `newHead = newTail.next`[cite: 23].
   - Break the circular link: `newTail.next = null`[cite: 23].
6. **Return `newHead`**[cite: 23].

---

### 3. Execution Trace

#### Sample Input: `head = [1, 2, 3, 4, 5]`, `k = 2`[cite: 23]

- Traverse to find length: `length = 5`, `tail = Node(5)`[cite: 23]
- Effective rotation: $k = 2 \pmod 5 = 2$[cite: 23]
- Connect tail to head: `Node(5).next = Node(1)` (forms ring: `1 -> 2 -> 3 -> 4 -> 5 -> 1...`)[cite: 23]
- Calculate breakpoint: `stepsToNewTail = length - k = 5 - 2 = 3`[cite: 23]

| Step ($i$) | `newTail` Position | Pointer Node | Action |
| :---: | :---: | :---: | :--- |
| Init | 1 | `Node(1)`[cite: 23] | Start traversal at `head`[cite: 23] |
| $i = 1$ | 2 | `Node(2)` | `newTail = newTail.next`[cite: 23] |
| $i = 2$ | 3 | `Node(3)` | `newTail = newTail.next`[cite: 23] |

- Identify new head: `newHead = newTail.next` $\implies$ `Node(4)`[cite: 23]
- Cut the ring: `newTail.next = null` (`Node(3).next = null`)[cite: 23]

**Resulting Linked List:** `4 -> 5 -> 1 -> 2 -> 3 -> null`[cite: 23]  
**Output:** `[4, 5, 1, 2, 3]`[cite: 23]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - First pass scans $N - 1$ steps to determine `length` and locate `tail`[cite: 23]. The second pass advances at most $N - 1$ steps to reach `newTail`[cite: 23]. Total operations are bounded by $\le 2N$, running in linear time.
- **Space Complexity:** $O(1)$ auxiliary space
  - The pointers are rearranged in-place using scalar reference variables (`tail`, `newTail`, `newHead`)[cite: 23] without allocating new heap nodes or recursive call frames[cite: 23].

---

### 5. Edge Cases & Key Takeaways

- **$k$ Greater Than Length ($k \ge N$):** The modulo operation `k % length` eliminates redundant full rotations, preventing unneeded traversal when $k$ is large (e.g., $k = 2 \times 10^9$)[cite: 23].
- **Zero Rotation ($k \pmod N == 0$):** Caught immediately at line 13, returning the original list without altering links[cite: 23].
- **Single Node List:** Handled cleanly by `head.next == null`, terminating in $O(1)$ operations[cite: 23].