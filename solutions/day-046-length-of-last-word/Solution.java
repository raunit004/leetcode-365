/**
 * Day 046: Length of Last Word
 * LeetCode #58: https://leetcode.com/problems/length-of-last-word/
 */
public class Solution {

    /**
     * Computes the length of the last word in a string by scanning backwards.
     * Skips trailing spaces and counts characters until the next space or start of string.
     *
     * Time Complexity: O(N) where N is the length of string s
     * Space Complexity: O(1) auxiliary space
     */
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        int length = 0;

        // Skip trailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Count characters of the last word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }

        return length;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        System.out.println("Test 1 (Expected 5): " + solver.lengthOfLastWord("Hello World"));
        System.out.println("Test 2 (Expected 4): " + solver.lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println("Test 3 (Expected 6): " + solver.lengthOfLastWord("luffy is still joyboy"));
        System.out.println("Test 4 (Expected 1): " + solver.lengthOfLastWord("a"));
    }
}