# Day 037: Second Highest Salary

- **Platform:** LeetCode #176[cite: 7]
- **Difficulty:** Medium[cite: 7]
- **Topic:** Database
- **Problem Link:** [Second Highest Salary](https://leetcode.com/problems/second-highest-salary/)
- **Language:** Oracle SQL / MySQL
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(1)$ auxiliary space

---

### 1. Problem Overview
The `Employee` table contains `id` (primary key) and `salary` (integer)[cite: 7].

The task is to find and report the second highest distinct salary from the table[cite: 7]. If there is no second highest salary (e.g., fewer than two distinct salary values exist), the query must return `null`[cite: 7].

---

### 2. Intuition & Approach

#### Subquery Filtering with Aggregate Fallback (`MAX` + `WHERE < MAX`):
1. **Find Global Maximum:** 
   - A nested subquery `(SELECT MAX(salary) FROM Employee)` finds the absolute highest salary in the dataset[cite: 7].
2. **Filter Out Top Tier:** 
   - The outer query filters for records where `salary < (SELECT MAX(salary) FROM Employee)`[cite: 7]. This effectively excludes the maximum salary and any duplicate rows sharing that same maximum.
3. **Select Next Highest:** 
   - Applying `MAX(salary)` over the filtered subset extracts the second highest distinct salary[cite: 7].
4. **Natural `NULL` Handling:** 
   - If the table has only one unique salary or is empty, the outer filter matches zero rows[cite: 7]. In standard SQL, applying an aggregate function like `MAX()` on an empty row set naturally returns `NULL` without needing additional `IFNULL()` or `NVL()` wrappers.

---

### 3. Execution Trace

#### Sample Input: `Employee` Table

| id[cite: 7] | salary[cite: 7] |
| :-: | :---: |
| 1 | 100 |
| 2 | 200 |
| 3 | 300 |

#### Query Evaluation:
1. **Subquery Execution:**  
   `SELECT MAX(salary) FROM Employee` $\rightarrow$ returns `300`.
2. **Outer Filter (`salary < 300`):**  
   Retains rows with salaries `[100, 200]`.
3. **Aggregate Selection:**  
   `MAX(salary)` over `[100, 200]` $\rightarrow$ returns `200`.

#### Final Output:

| SecondHighestSalary[cite: 7] |
| :---: |
| 200 |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Requires scanning the table to compute the global maximum, followed by an additional pass to filter and find the maximum of the remaining subset.
- **Space Complexity:** $O(1)$ auxiliary space
  - The aggregation computes scalar values in-place without storing intermediate row sets.

---

### 5. Edge Cases & Key Takeaways

- **Single Record / All Duplicate Salaries:** If the table has only one employee or multiple employees earning identical pay, the outer filter yields zero rows and `MAX()` evaluates directly to `NULL`[cite: 7].
- **Duplicate Second Highest Salaries:** Because `MAX()` operates on scalar values rather than limit-offset pagination (`LIMIT 1 OFFSET 1`), duplicate entries for the second highest salary do not produce redundant rows or offset misalignments.