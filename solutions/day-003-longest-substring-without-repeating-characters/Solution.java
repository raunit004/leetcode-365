/**
 * Day 003: Longest Substring Without Repeating Characters
 * LeetCode #3: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class Solution {
    /**
     * Calculates the length of the longest substring without repeating characters
     * using an optimized sliding window and direct-address ASCII table.
     *
     * @param s The input string
     * @return Length of the longest substring with all unique characters
     */
    public int lengthOfLongestSubstring(String s) {
        int[] lastIndex = new int[128];
        int maxLen = 0, left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            left = Math.max(left, lastIndex[c]);
            maxLen = Math.max(maxLen, right - left + 1);
            lastIndex[c] = right + 1;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String test1 = "abcabcbb";
        String test2 = "bbbbb";
        String test3 = "pwwkew";

        System.out.println("Input: \"" + test1 + "\" | Output: " + sol.lengthOfLongestSubstring(test1) + " | Expected: 3");
        System.out.println("Input: \"" + test2 + "\" | Output: " + sol.lengthOfLongestSubstring(test2) + " | Expected: 1");
        System.out.println("Input: \"" + test3 + "\" | Output: " + sol.lengthOfLongestSubstring(test3) + " | Expected: 3");
    }
}