# Day 062: Group Anagrams

- **Platform:** LeetCode #49[cite: 22]
- **Difficulty:** Medium[cite: 22]
- **Topic:** Array, Hash Table, String, Sorting
- **Problem Link:** [Group Anagrams](https://leetcode.com/problems/group-anagrams/)
- **Language:** Java[cite: 22]
- **Time Complexity:** $O(N \cdot K \log K)$ where $N$ is `strs.length` and $K$ is the maximum length of a string in `strs`[cite: 22]
- **Space Complexity:** $O(N \cdot K)$ auxiliary space to store strings inside the hash map[cite: 22]

---

### 1. Problem Overview
Given an array of strings `strs`, group the **anagrams** together[cite: 22]. You can return the answer in **any order**[cite: 22].

An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once[cite: 22].

---

### 2. Intuition & Approach

#### Canonical Key Generation via Character Sorting:
Two strings are anagrams if and only if they contain the identical set of characters with identical frequencies[cite: 22]:

1. **Canonical Signature:**
   - Sorting the characters of any anagram transforms it into a unique canonical key[cite: 22].
   - For example, `"eat"`, `"tea"`, and `"ate"` all sort to `"aet"`[cite: 22].
2. **Hash Map Categorization:**
   - Maintain a `Map<String, List<String>> map` mapping each sorted signature to its list of matching original words[cite: 22].
   - For each string `s`:
     - Convert `s` to a character array: `char[] chars = s.toCharArray()`[cite: 22].
     - Sort characters: `Arrays.sort(chars)`[cite: 22].
     - Derive the lookup key: `String key = String.valueOf(chars)`[cite: 22].
     - Append `s` to the mapped list: `map.computeIfAbsent(key, k -> new ArrayList<>()).add(s)`[cite: 22].
3. **Collect Results:**
   - Return a new `ArrayList` wrapping all collected values: `new ArrayList<>(map.values())`[cite: 22].

---

### 3. Execution Trace

#### Sample Input: `strs = ["eat", "tea", "tan", "ate", "nat", "bat"]`[cite: 22]

| String `s`[cite: 22] | Sorted Array `chars`[cite: 22] | Derived `key`[cite: 22] | Action on Map[cite: 22] | Current Map Entries |
| :---: | :---: | :---: | :---: | :--- |
| `"eat"`[cite: 22] | `['a', 'e', 't']`[cite: 22] | `"aet"`[cite: 22] | Insert new list[cite: 22] | `{"aet": ["eat"]}`[cite: 22] |
| `"tea"`[cite: 22] | `['a', 'e', 't']`[cite: 22] | `"aet"`[cite: 22] | Append to `"aet"`[cite: 22] | `{"aet": ["eat", "tea"]}`[cite: 22] |
| `"tan"`[cite: 22] | `['a', 'n', 't']`[cite: 22] | `"ant"`[cite: 22] | Insert new list[cite: 22] | `{"aet": [...], "ant": ["tan"]}`[cite: 22] |
| `"ate"`[cite: 22] | `['a', 'e', 't']`[cite: 22] | `"aet"`[cite: 22] | Append to `"aet"`[cite: 22] | `{"aet": ["eat", "tea", "ate"], ...}`[cite: 22] |
| `"nat"`[cite: 22] | `['a', 'n', 't']`[cite: 22] | `"ant"`[cite: 22] | Append to `"ant"`[cite: 22] | `{"ant": ["tan", "nat"], ...}`[cite: 22] |
| `"bat"`[cite: 22] | `['a', 'b', 't']`[cite: 22] | `"abt"`[cite: 22] | Insert new list[cite: 22] | `{"abt": ["bat"], ...}`[cite: 22] |

- **Final Groups:** `[["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]`[cite: 22]

---

### 4. Complexity Analysis

- **Time Complexity:** $O(N \cdot K \log K)$
  - Processing $N$ strings involves sorting an array of length at most $K$, taking $O(K \log K)$ per string[cite: 22]. Inserting into and looking up in the hash map takes $O(K)$ string hashing time on average[cite: 22].
- **Space Complexity:** $O(N \cdot K)$ auxiliary space
  - The hash map stores all $N$ original strings across grouped lists, plus their corresponding sorted key representations of length $K$[cite: 22].

---

### 5. Edge Cases & Key Takeaways

- **Empty String (`[""]`):** `chars` is empty, generating key `""` and mapping to `[[""]]` seamlessly.
- **Single Character Strings (`["a"]`):** Sorts in $O(1)$ time, forming `[["a"]]`.
- **Idiomatic Java Usage:** Employing `map.computeIfAbsent(key, k -> new ArrayList<>())` eliminates redundant boilerplate `containsKey` checks and keeps insertion atomic and concise[cite: 22].