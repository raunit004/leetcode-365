# Day 083: Customers Who Never Order

- **Platform:** LeetCode #183[cite: 25]
- **Difficulty:** Easy[cite: 25]
- **Topic:** Database[cite: 25]
- **Problem Link:** [Customers Who Never Order](https://leetcode.com/problems/customers-who-never-order/)
- **Language:** SQL (Oracle / MySQL / PostgreSQL / MS SQL Server)[cite: 25]
- **Time Complexity:** $O(C + O_{\text{cnt}})$ with indexed foreign key lookup / hash anti-join
- **Space Complexity:** $O(C + O_{\text{cnt}})$ auxiliary space for intermediate join buffering

---

### 1. Problem Overview

Table: `Customers`[cite: 25]

```text
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
```
[cite: 25]

`id` is the primary key (column with unique values) for this table[cite: 25]. Each row indicates the ID and name of a customer[cite: 25].

Table: `Orders`[cite: 25]

```text
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| customerId  | int  |
+-------------+------+
```
[cite: 25]

`id` is the primary key (column with unique values) for this table[cite: 25]. `customerId` is a foreign key referencing the `id` from the `Customers` table[cite: 25]. Each row indicates the ID of an order and the customer who placed it[cite: 25].

Write a solution to find all customers who never order anything[cite: 25]. Return the result table in **any order** with the column alias `Customers`[cite: 25].

---

### 2. Intuition & Approach

#### Left Anti-Join Technique:
To find records in relation $A$ that have no matching records in relation $B$, standard relational algebra utilizes an **Anti-Join**[cite: 25]:

1. **`LEFT JOIN` Alignment:**
   - Joining `Customers c` with `Orders o` on `c.id = o.customerId` preserves **all** records from `Customers`, regardless of whether a corresponding purchase exists[cite: 25].
2. **`NULL` Sentinel Identification:**
   - For customers who have never placed an order, all projected attributes belonging to the `Orders` relation are populated with `NULL`[cite: 25].
3. **Filtering Unmatched Customers:**
   - Filtering with `WHERE o.id IS NULL` filters out customers with active orders, keeping only those with zero purchase records[cite: 25].
4. **Column Projection:**
   - Select `c.name` and alias it as `Customers`[cite: 25].

---

### 3. Execution Trace

#### Sample Input Data:

`Customers`:
```text
+----+-------+
| id | name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
```

`Orders`:
```text
+----+------------+
| id | customerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
```

#### Step 1: `Customers c LEFT JOIN Orders o ON c.id = o.customerId`[cite: 25]

| `c.id`[cite: 25] | `c.name`[cite: 25] | `o.id`[cite: 25] | `o.customerId`[cite: 25] |
| :---: | :---: | :---: | :---: |
| 1 | Joe | 2 | 1 |
| 2 | Henry | `NULL`[cite: 25] | `NULL`[cite: 25] |
| 3 | Sam | 1 | 3 |
| 4 | Max | `NULL`[cite: 25] | `NULL`[cite: 25] |

#### Step 2: Apply `WHERE o.id IS NULL`[cite: 25]

- Joe: `o.id = 2` $\implies$ Filtered out
- **Henry:** `o.id IS NULL` $\implies$ **Retained**[cite: 25]
- Sam: `o.id = 1` $\implies$ Filtered out
- **Max:** `o.id IS NULL` $\implies$ **Retained**[cite: 25]

#### Final Result:

```text
+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+
```

---

### 4. Complexity Analysis

- **Time Complexity:** $O(C + O_{\text{cnt}})$
  - If an index exists on `Orders(customerId)`, the query engine evaluates matches via index probing in $O(C \log O_{\text{cnt}})$. With Hash Anti-Join optimizations in modern relational engines (e.g., Oracle, PostgreSQL), the join completes in linear time $O(C + O_{\text{cnt}})$.
- **Space Complexity:** $O(C + O_{\text{cnt}})$ auxiliary space
  - Working memory allocated for the hash table or merge-join buffer during query execution.

---

### 5. Edge Cases & Key Takeaways

- **Performance Over `NOT IN`:** Using `LEFT JOIN ... WHERE o.id IS NULL` avoids performance pitfalls associated with `NOT IN (SELECT customerId FROM Orders)`. In SQL, if any row in `Orders.customerId` evaluates to `NULL`, a `NOT IN` subquery returns an empty result set because `x NOT IN (..., NULL)` resolves to `UNKNOWN`.
- **Primary Key Check:** Checking `o.id IS NULL` is guaranteed to indicate a missing record because `o.id` is the primary key of `Orders` and cannot inherently contain `NULL` within an existing row[cite: 25].