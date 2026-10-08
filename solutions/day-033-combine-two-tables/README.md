# Day 033: Combine Two Tables

- **Platform:** LeetCode #175
- **Difficulty:** Easy
- **Topic:** Database
- **Problem Link:** [Combine Two Tables](https://leetcode.com/problems/combine-two-tables/)
- **Language:** Oracle SQL / MySQL
- **Time Complexity:** $O(N + M)$
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
We have two relational tables: `Person` (storing `personId`, `firstName`, and `lastName`) and `Address` (storing `addressId`, `personId`, `city`, and `state`).

The objective is to retrieve the first name, last name, city, and state for every individual in the `Person` table. If a person does not have a corresponding entry in the `Address` table, their city and state should appear as `null`. Results can be returned in any order.

---

### 2. Intuition & Approach

#### Outer Join Strategy (`LEFT JOIN`)
- An `INNER JOIN` only returns rows where matching keys exist in both tables. If a person lacks an address entry, an inner join drops them from the result set entirely.
- A `LEFT OUTER JOIN` preserves all records from the left table (`Person`) regardless of whether a matching record exists on the right (`Address`).
- For each row in `Person`, if a match on `p.personId = a.personId` is found, the associated `city` and `state` are populated. If no match is found, the database engine fills those columns with `NULL`.

---

### 3. Execution Trace

#### Sample Input

**Person Table:**

| personId | lastName | firstName |
| :---: | :---: | :---: |
| 1 | Wang | Allen |
| 2 | Alice | Bob |

**Address Table:**

| addressId | personId | city | state |
| :---: | :---: | :---: | :---: |
| 1 | 2 | New York City | New York |

#### Join Evaluation

1. `personId = 1` (`Allen Wang`): No matching row in `Address` $\rightarrow$ attributes become `NULL`.
2. `personId = 2` (`Bob Alice`): Matching row found in `Address` $\rightarrow$ `city = 'New York City'`, `state = 'New York'`.

#### Output

| firstName | lastName | city | state |
| :---: | :---: | :---: | :---: |
| Allen | Wang | *null* | *null* |
| Bob | Alice | New York City | New York |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N + M)$
  - With an index or hash join on `personId`, the query scans the $N$ rows of `Person` and performs lookups into the $M$ rows of `Address` in linear time.
- **Space Complexity:** $O(1)$ auxiliary space
  - The query produces output directly through streaming results without creating temporary intermediate tables.

---

### 5. Edge Cases & Key Takeaways

- **Missing Foreign Keys:** People without addresses are preserved with `NULL` via the left outer join.
- **Index Optimization:** Ensuring an index on `Address.personId` optimizes join performance on large tables.