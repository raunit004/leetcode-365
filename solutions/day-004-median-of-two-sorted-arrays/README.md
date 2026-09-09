# Day 004: Median of Two Sorted Arrays

- **Platform:** LeetCode #4
- **Difficulty:** Hard
- **Topic:** Array, Binary Search, Divide and Conquer
- **Problem Link:** [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)
- **Language:** Java

---

### 1. Problem Statement
Given two sorted arrays `nums1` and `nums2` of size `m` and `n` respectively, return the **median** of the two sorted arrays.

- The overall run time complexity must be $O(\log(m + n))$.
- Inputs are sorted in non-decreasing order.

---

### 2. Intuition & Approach

#### Linear Merge vs. Binary Search Partitioning
- **Two-Pointer Merge ($O(m + n)$):** Merging the arrays up to the median position takes linear time, which fails the strict $O(\log(m + n))$ requirement.
- **Binary Search on the Smaller Array ($O(\log(\min(m, n)))$):**
  Instead of merging, partition both arrays simultaneously such that:
  1. The left combined partition contains $\lfloor \frac{m + n + 1}{2} \rfloor$ elements.
  2. Every element in the left partition is $\le$ every element in the right partition.

#### Partition Invariant & Conditions
Always ensure $m \le n$ (swap inputs if $m > n$) to minimize binary search steps.

We partition `nums1` at index `partitionX` and `nums2` at `partitionY = (m + n + 1) / 2 - partitionX`.
A partition is valid when:
$$\text{maxLeftX} \le \text{minRightY} \quad \text{and} \quad \text{maxLeftY} \le \text{minRightX}$$

- If `maxLeftX > minRightY`: We partitioned too far right in `nums1` $\rightarrow$ `high = partitionX - 1`.
- If `maxLeftY > minRightX`: We partitioned too far left in `nums1` $\rightarrow$ `low = partitionX + 1`.

#### Handling Boundaries
When a partition cut falls at index `0` or the array length, use sentinel values:
- Left empty side: `Integer.MIN_VALUE`
- Right empty side: `Integer.MAX_VALUE`

---

### 3. Execution Trace

**Input:** `nums1 = [1, 2]`, `nums2 = [3, 4]` ($m = 2, n = 2$, total = $4$)

- Target left partition size: $(2 + 2 + 1) / 2 = 2$
- Range for binary search: `low = 0`, `high = 2`

| Iteration | `partitionX` | `partitionY` | `maxLeftX` | `minRightX` | `maxLeftY` | `minRightY` | Condition Met? | Next Step |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :--- |
| **1** | `1` | `1` | `1` | `2` | `3` | `4` | $1 \le 4$ (Yes), but $3 \le 2$ (**No**) | `maxLeftY > minRightX` $\rightarrow$ `low = 2` |
| **2** | `2` | `0` | `2` | $\infty$ | $-\infty$ | `3` | $2 \le 3$ (Yes), $-\infty \le \infty$ (Yes) | **Valid Partition Found** |

Since total length is even:
$$\text{Median} = \frac{\max(\text{maxLeftX}, \text{maxLeftY}) + \min(\text{minRightX}, \text{minRightY})}{2.0} = \frac{\max(2, -\infty) + \min(\infty, 3)}{2.0} = \frac{2 + 3}{2.0} = 2.5$$

---

### 4. Complexity Analysis

- **Time Complexity:** $O(\log(\min(m, n)))$
  - Binary search is exclusively executed over the shorter array, halving the search space each step.
- **Space Complexity:** $O(1)$
  - Only pointer indices and scalar boundary variables are maintained; no auxiliary arrays allocated.

---

### 5. Edge Cases & Key Takeaways

- **One Array is Empty:** Sentinel values `Integer.MIN_VALUE` and `Integer.MAX_VALUE` smoothly handle cuts at indices `0` or array lengths without index out-of-bounds exceptions.
- **Odd vs. Even Totals:** Integer division `(m + n + 1) / 2` guarantees that for odd total lengths, the median is always $\max(\text{maxLeftX}, \text{maxLeftY})$.