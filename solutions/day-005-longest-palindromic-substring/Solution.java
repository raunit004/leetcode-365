/**
 * Day 005: Longest Palindromic Substring
 * LeetCode #5: https://leetcode.com/problems/longest-palindromic-substring/
 */
public class Solution {
    /**
     * Finds the longest palindromic substring using the expand-around-center approach.
     *
     * @param s The input string
     * @return The longest contiguous palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            int lenOdd = expandAroundCenter(s, i, i);
            int lenEven = expandAroundCenter(s, i, i + 1);
            int len = Math.max(lenOdd, lenEven);

            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    /**
     * Expands outward from the given center pointers while the substring remains a palindrome.
     *
     * @param s     The string
     * @param left  Left pointer
     * @param right Right pointer
     * @return Length of the valid palindrome expanded from the center
     */
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String test1 = "babad";
        String test2 = "cbbd";
        String test3 = "a";

        System.out.println("Input: \"" + test1 + "\" | Output: \"" + sol.longestPalindrome(test1) + "\" | Expected: \"bab\" or \"aba\"");
        System.out.println("Input: \"" + test2 + "\" | Output: \"" + sol.longestPalindrome(test2) + "\" | Expected: \"bb\"");
        System.out.println("Input: \"" + test3 + "\" | Output: \"" + sol.longestPalindrome(test3) + "\" | Expected: \"a\"");
    }
}