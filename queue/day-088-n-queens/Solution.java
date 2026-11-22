import java.util.*;

/**
 * Day 088: N-Queens
 * LeetCode #51: https://leetcode.com/problems/n-queens/
 */
public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n];
        boolean[] diag2 = new boolean[2 * n];
        backtrack(0, n, board, cols, diag1, diag2, res);
        return res;
    }

    private void backtrack(int r, int n, char[][] board, boolean[] cols,
                           boolean[] diag1, boolean[] diag2, List<List<String>> res) {
        if (r == n) {
            List<String> copy = new ArrayList<>(n);
            for (char[] row : board) copy.add(new String(row));
            res.add(copy);
            return;
        }
        for (int c = 0; c < n; c++) {
            int d1 = r - c + n, d2 = r + c;
            if (cols[c] || diag1[d1] || diag2[d2]) continue;
            board[r][c] = 'Q';
            cols[c] = diag1[d1] = diag2[d2] = true;
            backtrack(r + 1, n, board, cols, diag1, diag2, res);
            board[r][c] = '.';
            cols[c] = diag1[d1] = diag2[d2] = false;
        }
    }
}
