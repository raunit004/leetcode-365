# Day 084: Delete Duplicate Emails

- **Platform:** LeetCode #196
- **Difficulty:** Easy
- **Topic:** Database
- **Problem Link:** [Delete Duplicate Emails](https://leetcode.com/problems/delete-duplicate-emails/)
- **Language:** Oracle SQL / MySQL
- **Time Complexity:** $O(N \log N)$
- **Space Complexity:** $O(N)$

---

### 1. Problem Overview
Delete all duplicate emails from the `Person` table, keeping only one unique email with the smallest `id`.

---

### 2. Intuition & Approach
Group rows by `email`, select `MIN(id)` for each distinct email, and delete all rows whose `id` is not in that minimum-ID set.

---

### 3. Complexity Analysis
- **Time Complexity:** $O(N \log N)$
- **Space Complexity:** $O(N)$

---

### 4. Edge Cases & Key Takeaways
Single-row table remains untouched; multiple duplicates of the same email retain only the smallest `id`.
