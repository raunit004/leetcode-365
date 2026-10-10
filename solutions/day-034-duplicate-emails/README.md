# Day 034: Duplicate Emails

- **Platform:** LeetCode #182[cite: 13]
- **Difficulty:** Easy[cite: 13]
- **Topic:** Database[cite: 13]
- **Problem Link:** [Duplicate Emails](https://leetcode.com/problems/duplicate-emails/)[cite: 13]
- **Language:** Oracle SQL / MySQL[cite: 13]
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
The `Person` table stores an `id` (primary key) and an `email` (lowercase strings, guaranteed non-null)[cite: 13].

The objective is to identify and report all duplicate email addresses—meaning any email that appears two or more times[cite: 13]. The result set can be returned in any order[cite: 13].

---

### 2. Intuition & Approach

#### Aggregation with Group Filter (`GROUP BY` + `HAVING`)[cite: 13]
- We group rows by the `email` column, which collapses all identical addresses into individual groups[cite: 13].
- A standard `WHERE` clause filters individual records before aggregation. To evaluate an aggregate count across a group, we use the `HAVING` clause[cite: 13].
- `HAVING COUNT(email) > 1` filters out unique emails (count = 1) and retains only emails that occur repeatedly[cite: 13].

---

### 3. Execution Trace

#### Sample Input: `Person` Table

| id | email |
| :---: | :--- |
| 1 | `a@b.com` |
| 2 | `c@d.com` |
| 3 | `a@b.com` |

#### Aggregation Stage (`GROUP BY email`)[cite: 13]:

| Group (`email`) | `COUNT(email)` | `HAVING COUNT(email) > 1`[cite: 13] | Status |
| :--- | :---: | :---: | :--- |
| `a@b.com` | 2 | $2 > 1$ (True)[cite: 13] | **Retained** |
| `c@d.com` | 1 | $1 > 1$ (False)[cite: 13] | Filtered out |

#### Final Output:

| email |
| :--- |
| `a@b.com` |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - The database performs a single scan over the $N$ rows in `Person`[cite: 13]. Building the hash map or sorting buckets for `GROUP BY` completes in linear time $O(N)$[cite: 13].
- **Space Complexity:** $O(N)$ auxiliary space
  - Intermediate grouping stores distinct email entries and their counts before filtering[cite: 13].

---

### 5. Edge Cases & Key Takeaways

- **Non-null Assurance:** Because the problem guarantees `email` is not null, `COUNT(email)` and `COUNT(*)` yield identical behavior[cite: 13].
- **No Duplicates:** If all emails are unique, the query returns an empty result set without errors.
- **Triplicate or Higher Frequencies:** Checking strictly `> 1` captures emails appearing 3, 4, or more times without redundant duplicate rows in the output[cite: 13].