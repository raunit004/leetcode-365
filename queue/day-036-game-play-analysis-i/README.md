# Day 036: Game Play Analysis I

- **Platform:** LeetCode #511[cite: 6]
- **Difficulty:** Easy[cite: 6]
- **Topic:** Database[cite: 6]
- **Problem Link:** [Game Play Analysis I](https://leetcode.com/problems/game-play-analysis-i/)[cite: 6]
- **Language:** MySQL / Oracle SQL[cite: 6]
- **Time Complexity:** $O(N)$
- **Space Complexity:** $O(K)$ auxiliary space (where $K$ is the number of distinct players)

---

### 1. Problem Overview
The `Activity` table records individual player sessions with columns: `player_id`, `device_id`, `event_date`, and `games_played`[cite: 6]. The combination of `(player_id, event_date)` serves as the primary key[cite: 6].

The objective is to find and report each player's initial login date (`first_login`)[cite: 6]. The result table may be returned in any order[cite: 6].

---

### 2. Intuition & Approach

#### Group Aggregation (`GROUP BY` + `MIN`):
1. **Partitioning:** Grouping records by `player_id` bundles all gameplay events for each user into dedicated buckets[cite: 6].
2. **Earliest Record Extraction:** Applying the `MIN()` aggregate function on `event_date` picks the chronologically earliest date for each user group[cite: 6].
3. **Aliasing:** Alias `MIN(event_date)` as `first_login` to align with the required output schema[cite: 6].

---

### 3. Execution Trace

#### Sample Input: `Activity` Table

| player_id[cite: 6] | device_id[cite: 6] | event_date[cite: 6] | games_played[cite: 6] |
| :---: | :---: | :---: | :---: |
| 1 | 2 | 2016-03-01 | 5 |
| 1 | 2 | 2016-05-02 | 6 |
| 2 | 3 | 2017-06-25 | 1 |
| 3 | 1 | 2016-03-02 | 0 |
| 3 | 4 | 2018-07-03 | 5 |

#### Aggregation by `player_id`:

| `player_id`[cite: 6] | Date Records Evaluated | `MIN(event_date)`[cite: 6] | `first_login`[cite: 6] |
| :---: | :--- | :---: | :---: |
| 1 | `2016-03-01`, `2016-05-02` | `2016-03-01` | **2016-03-01** |
| 2 | `2017-06-25` | `2017-06-25` | **2017-06-25** |
| 3 | `2016-03-02`, `2018-07-03` | `2016-03-02` | **2016-03-02** |

#### Final Output:

| player_id[cite: 6] | first_login[cite: 6] |
| :---: | :---: |
| 1 | 2016-03-01 |
| 2 | 2017-06-25 |
| 3 | 2016-03-02 |

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N)$
  - Scanning $N$ total rows in `Activity` and building aggregate hash buckets runs in linear time[cite: 6].
- **Space Complexity:** $O(K)$ auxiliary space
  - The query engine retains $K$ unique `player_id` entries in temporary hash/sort buffers during aggregation.

---

### 5. Edge Cases & Key Takeaways

- **Single Session Players:** If a player has only one entry in the table, `MIN(event_date)` correctly returns that solitary date[cite: 6].
- **Indexing:** If `(player_id, event_date)` is indexed (as is typical for composite primary keys)[cite: 6], index scans can satisfy the group aggregation without a full table sort.