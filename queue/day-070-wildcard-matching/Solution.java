/**
 * Day 070: Wildcard Matching
 * LeetCode #44: https://leetcode.com/problems/wildcard-matching/
 */
public class Solution {

    /**
     * Implements wildcard pattern matching with support for '?' and '*'.
     * Uses a greedy two-pointer approach tracking the most recent '*' position
     * to backtrack when characters mismatch, avoiding full DP grid allocation.
     *
     * Time Complexity: O(S * P) worst-case, O(S + P) average-case where S = s.length(), P = p.length()
     * Space Complexity: O(1) auxiliary space
     */
    public boolean isMatch(String s, String p) {
        int sIdx = 0;
        int pIdx = 0;
        int starIdx = -1;
        int sMatch = 0;
        int sLen = s.length();
        int pLen = p.length();

        while (sIdx < sLen) {
            // Direct character match or single-character wildcard '?'
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                sIdx++;
                pIdx++;
            }
            // Asterisk wildcard '*': record checkpoint and assume empty sequence initially
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                sMatch = sIdx;
                pIdx++;
            }
            // Mismatch: backtrack to the last '*' and expand its matched sequence by one character
            else if (starIdx != -1) {
                pIdx = starIdx + 1;
                sMatch++;
                sIdx = sMatch;
            }
            // Mismatch with no preceding '*' to absorb characters
            else {
                return false;
            }
        }

        // Consume any trailing '*' characters in the pattern
        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            pIdx++;
        }

        return pIdx == pLen;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        System.out.println("Test 1 (s = 'aa', p = 'a', Expected false): " + solver.isMatch("aa", "a"));
        System.out.println("Test 2 (s = 'aa', p = '*', Expected true): " + solver.isMatch("aa", "*"));
        System.out.println("Test 3 (s = 'cb', p = '?a', Expected false): " + solver.isMatch("cb", "?a"));
        System.out.println("Test 4 (s = 'adceb', p = '*a*b', Expected true): " + solver.isMatch("adceb", "*a*b"));
        System.out.println("Test 5 (s = 'acdcb', p = 'a*c?b', Expected false): " + solver.isMatch("acdcb", "a*c?b"));
    }
}