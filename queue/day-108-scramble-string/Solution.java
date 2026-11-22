/**
 * Day 108: Scramble String
 * LeetCode #87: https://leetcode.com/problems/scramble-string/
 */
public class Solution {
    private Boolean[][][] memo;
    private String s1, s2;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        this.s1 = s1;
        this.s2 = s2;
        this.memo = new Boolean[n][n][n + 1];
        return dfs(0, 0, n);
    }

    private boolean dfs(int i1, int i2, int len) {
        if (memo[i1][i2][len] != null) return memo[i1][i2][len];
        if (s1.regionMatches(i1, s2, i2, len)) return memo[i1][i2][len] = true;

        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            count[s1.charAt(i1 + i) - 'a']++;
            count[s2.charAt(i2 + i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) return memo[i1][i2][len] = false;
        }

        for (int k = 1; k < len; k++) {
            if (dfs(i1, i2, k) && dfs(i1 + k, i2 + k, len - k)) {
                return memo[i1][i2][len] = true;
            }
            if (dfs(i1, i2 + len - k, k) && dfs(i1 + k, i2, len - k)) {
                return memo[i1][i2][len] = true;
            }
        }
        return memo[i1][i2][len] = false;
    }
}
