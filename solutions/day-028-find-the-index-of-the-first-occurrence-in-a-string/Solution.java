/**
 * Day 028: Find the Index of the First Occurrence in a String
 * LeetCode #28: https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
 */
public class Solution {
    /**
     * Finds the index of the first occurrence of needle in haystack.
     * Uses a sliding window comparison approach.
     *
     * @param haystack The main text string
     * @param needle   The substring pattern to find
     * @return The 0-based index of the first character of needle, or -1 if not found
     */
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        if (m > n) {
            return -1;
        }

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String h1 = "sadbutsad", n1 = "sad";
        String h2 = "leetcode", n2 = "leeto";
        String h3 = "hello", n3 = "ll";
        String h4 = "a", n4 = "a";

        System.out.println("Input: haystack = \"sadbutsad\", needle = \"sad\" | Output: " + sol.strStr(h1, n1) + "  | Expected: 0");
        System.out.println("Input: haystack = \"leetcode\", needle = \"leeto\"  | Output: " + sol.strStr(h2, n2) + " | Expected: -1");
        System.out.println("Input: haystack = \"hello\", needle = \"ll\"        | Output: " + sol.strStr(h3, n3) + "  | Expected: 2");
        System.out.println("Input: haystack = \"a\", needle = \"a\"            | Output: " + sol.strStr(h4, n4) + "  | Expected: 0");
    }
}