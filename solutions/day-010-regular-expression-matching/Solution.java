/**
 * Day 010: Regular Expression Matching
 * LeetCode #10: https://leetcode.com/problems/regular-expression-matching/
 */
public class Solution {
    /**
     * Implements regular expression matching with support for '.' and '*'.
     * Uses 2D bottom-up dynamic programming.
     *
     * @param s The input string
     * @param p The pattern string
     * @return true if pattern matches the entire string, false otherwise
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // Base case: patterns like a*, a*b*, a*b*c* matching an empty string
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                char sc = s.charAt(i - 1);

                if (pc == '*') {
                    char prevP = p.charAt(j - 2);
                    // Match zero occurrences of preceding character
                    dp[i][j] = dp[i][j - 2];
                    // Match one or more occurrences if preceding character matches
                    if (prevP == '.' || prevP == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String s1 = "aa", p1 = "a";
        String s2 = "aa", p2 = "a*";
        String s3 = "ab", p3 = ".*";
        String s4 = "aab", p4 = "c*a*b";
        String s5 = "mississippi", p5 = "mis*is*p*.";

        System.out.println("Input: s = \"" + s1 + "\", p = \"" + p1 + "\" | Output: " + sol.isMatch(s1, p1) + " | Expected: false");
        System.out.println("Input: s = \"" + s2 + "\", p = \"" + p2 + "\" | Output: " + sol.isMatch(s2, p2) + " | Expected: true");
        System.out.println("Input: s = \"" + s3 + "\", p = \"" + p3 + "\" | Output: " + sol.isMatch(s3, p3) + " | Expected: true");
        System.out.println("Input: s = \"" + s4 + "\", p = \"" + p4 + "\" | Output: " + sol.isMatch(s4, p4) + " | Expected: true");
        System.out.println("Input: s = \"" + s5 + "\", p = \"" + p5 + "\" | Output: " + sol.isMatch(s5, p5) + " | Expected: false");
    }
}