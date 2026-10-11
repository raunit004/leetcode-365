# Day 035: Rising Temperature

- **Platform:** LeetCode #197
- **Difficulty:** Easy
- **Topic:** Database
- **Problem Link:** [Rising Temperature](https://leetcode.com/problems/rising-temperature/)
- **Language:** Oracle SQL / MySQL
- **Time Complexity:** $O(N)$ with an index on `recordDate` (or $O(N^2)$ unindexed nested-loop join)
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
The `Weather` table stores daily climate observations: `id` (primary key), `recordDate` (date), and `temperature` (int). There are no duplicate entries for the same date.

The objective is to identify and report the `id` of all dates where the temperature was strictly higher than the previous day's temperature (yesterday). Results can be returned in any order.

---

### 2. Intuition & Approach

#### Self-Join with Date Offset (`JOIN` on Consecutive Dates)
1. **Consecutive Day Pairing:**
   - Because dates in relational tables may have gaps or be out of chronological order, comparing consecutive rows requires matching each target record against a record whose date is exactly one day earlier (`yesterday`).
   - Self-join table `Weather w1` (representing today) with `Weather w2` (representing yesterday) using the condition:
     - In Oracle SQL: `w1.recordDate = w2.recordDate + 1`
     - In MySQL: `DATEDIFF(w1.recordDate, w2.recordDate) = 1`
2. **Temperature Comparison:**
   - Filter paired records with a `WHERE` clause requiring `w1.temperature > w2.temperature`.
   - Project `w1.id` into the final result set.

---

### 3. Execution Trace

#### Sample Input: `Weather` Table

| id | recordDate | temperature |
| :-: | :--------: | :---------: |
| 1 | 2015-01-01 | 10 |
| 2 | 2015-01-02 | 25 |
| 3 | 2015-01-03 | 20 |
| 4 | 2015-01-04 | 30 |

#### Self-Join Evaluation:

| `w1.id` (Today) | `w1.recordDate` | `w1.temp` | `w2.id` (Yesterday) | `w2.recordDate` | `w2.temp` | `w1.temp > w2.temp` | Included? |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| 2 | 2015-01-02 | 25 | 1 | 2015-01-01 | 10 | $25 > 10$ (True) | **Yes (id = 2)** |
| 3 | 2015-01-03 | 20 | 2 | 2015-01-02 | 25 | $20 > 25$ (False) | No |
| 4 | 2015-01-04 | 30 | 3 | 2015-01-03 | 20 | $30 > 20$ (True) | **Yes (id = 4)** |

#### Output:

| id |
| :-: |
| 2 |
| 4 |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$ with an index on `recordDate` (or $O(N^2)$ unindexed nested loop join)
  - Joining on date equivalence matches at most one preceding record per row.
- **Space Complexity:** $O(1)$ auxiliary memory
  - Results are filtered and streamed directly without temporary intermediate tables.

---

### 5. Edge Cases & Key Takeaways

- **Non-Continuous Dates:** Joining on `recordDate = yesterday.recordDate + 1` prevents false comparisons when an intermediate date is missing from the dataset.
- **Identical Temperatures:** Strictly checking `>` ensures ties (equal temperature) are excluded.