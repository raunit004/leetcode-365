/**
 * Day 089: N-Queens II
 * LeetCode #52: https://leetcode.com/problems/n-queens-ii/
 */
public class Solution {
    public int totalNQueens(int n) {
        return dfs(0, n, 0, 0, 0);
    }

    private int dfs(int row, int n, int cols, int d1, int d2) {
        if (row == n) return 1;
        int count = 0;
        int available = ((1 << n) - 1) & ~(cols | d1 | d2);
        while (available != 0) {
            int bit = available & -available;
            available &= available - 1;
            count += dfs(row + 1, n, cols | bit, (d1 | bit) << 1, (d2 | bit) >> 1);
        }
        return count;
    }
}
