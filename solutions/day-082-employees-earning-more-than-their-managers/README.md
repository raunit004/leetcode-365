# Day 082: Employees Earning More Than Their Managers

- **Platform:** LeetCode #181[cite: 25]
- **Difficulty:** Easy[cite: 25]
- **Topic:** Database[cite: 25]
- **Problem Link:** [Employees Earning More Than Their Managers](https://leetcode.com/problems/employees-earning-more-than-their-managers/)
- **Language:** SQL (Oracle / MySQL / PostgreSQL)[cite: 25]
- **Time Complexity:** $O(N)$ with primary key index lookup on `id` ($O(N^2)$ worst-case unindexed nested loop)[cite: 25]
- **Space Complexity:** $O(N)$ auxiliary space for intermediate join processing

---

### 1. Problem Overview

Table: `Employee`[cite: 25]

```text
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
```
[cite: 25]

`id` is the primary key (column with unique values) for this table[cite: 25]. Each row indicates the ID of an employee, their name, salary, and the ID of their manager[cite: 25].

Write a solution to find the employees who earn more than their managers[cite: 25]. Return the result table in **any order** with the output header labeled `Employee`[cite: 25].

---

### 2. Intuition & Approach

#### Relational Self-Join:
Because an employee and their manager both reside within the identical `Employee` table, we treat the table as two distinct logical relations via aliasing[cite: 25]:
1. Alias `e` representing the subordinate employee[cite: 25].
2. Alias `m` representing the corresponding manager[cite: 25].

#### Query Logic:
1. **Join Condition:**
   - Link each employee record to their respective manager using `e.managerId = m.id`[cite: 25].
   - Employees with `managerId IS NULL` (top-level executives) are naturally excluded from an `INNER JOIN` because `NULL = m.id` evaluates to `UNKNOWN` in three-valued SQL logic.
2. **Predicate Filter:**
   - Retain only joined pairs where the employee's salary strictly exceeds the manager's salary: `WHERE e.salary > m.salary`[cite: 25].
3. **Projection:**
   - Select the employee's name aliased to `Employee`: `SELECT e.name AS Employee`[cite: 25].

---

### 3. Execution Trace

#### Sample Input Data:

```text
+----+-------+--------+-----------+
| id | name  | salary | managerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | Null      |
| 4  | Max   | 90000  | Null      |
+----+-------+--------+-----------+
```

#### Step 1: Evaluate `INNER JOIN ON e.managerId = m.id`[cite: 25]

| `e.id` | `e.name`[cite: 25] | `e.salary`[cite: 25] | `e.managerId`[cite: 25] | `m.id`[cite: 25] | `m.name` | `m.salary`[cite: 25] |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 1 | Joe[cite: 25] | 70000[cite: 25] | 3[cite: 25] | 3[cite: 25] | Sam | 60000[cite: 25] |
| 2 | Henry[cite: 25] | 80000[cite: 25] | 4[cite: 25] | 4[cite: 25] | Max | 90000[cite: 25] |

*(Rows 3 and 4 have `managerId = NULL` and drop out of the join)*

#### Step 2: Evaluate `WHERE e.salary > m.salary`[cite: 25]

- For Joe: $70000 > 60000 \implies$ **True** (Kept)[cite: 25]
- For Henry: $80000 > 90000 \implies$ **False** (Filtered out)[cite: 25]

#### Final Result:

```text
+----------+
| Employee |
+----------+
| Joe      |
+----------+
```
[cite: 25]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Because `id` is the primary key of the table, the database optimizer utilizes a clustered B-tree index lookup when resolving `m.id = e.managerId`[cite: 25]. For each of the $N$ employee rows, finding the manager takes $O(1)$ to $O(\log N)$ index operations.
- **Space Complexity:** $O(N)$ auxiliary space
  - The database query engine creates an intermediate hash table or sort buffer proportional to $N$ during join execution.

---

### 5. Edge Cases & Key Takeaways

- **NULL `managerId`:** Unassigned manager IDs evaluate to `UNKNOWN` in equality comparisons, preventing null reference errors without needing explicit `IS NOT NULL` guards.
- **Equal Salaries:** The strict inequality `>` ensures employees with salaries identical to their managers are not returned[cite: 25].
- **Index Optimization:** In production databases, adding a non-clustered index on `managerId` allows index-only or hash-join resolution, avoiding full table scans.