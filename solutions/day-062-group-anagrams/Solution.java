import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Day 062: Group Anagrams
 * LeetCode #49: https://leetcode.com/problems/group-anagrams/
 */
public class Solution {

    /**
     * Groups anagrams together from an array of strings.
     * Sorts the character array of each string to generate a canonical hash key,
     * grouping anagrams into a hash map.
     *
     * Time Complexity: O(N * K log K) where N is strs.length and K is the maximum string length
     * Space Complexity: O(N * K) auxiliary space for the hash map storage
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1
        String[] test1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("Test 1 Result: " + solver.groupAnagrams(test1));

        // Smoke Test 2
        String[] test2 = {""};
        System.out.println("Test 2 Result (Expected [[\"\"]]): " + solver.groupAnagrams(test2));

        // Smoke Test 3
        String[] test3 = {"a"};
        System.out.println("Test 3 Result (Expected [[\"a\"]]): " + solver.groupAnagrams(test3));
    }
}