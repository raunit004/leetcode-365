import java.util.ArrayList;
import java.util.List;

/**
 * Day 042: Sudoku Solver
 * LeetCode #37: https://leetcode.com/problems/sudoku-solver/
 */
public class Solution {
    private boolean[][] rows = new boolean[9][10];
    private boolean[][] cols = new boolean[9][10];
    private boolean[][] boxes = new boolean[9][10];
    private List<int[]> emptyCells = new ArrayList<>();

    /**
     * Solves a 9x9 Sudoku puzzle by filling empty cells in-place using backtracking.
     * Pre-indexes empty cells and maintains boolean lookup tables for O(1) validity checks.
     *
     * Time Complexity: O(9^M) where M <= 81 is the count of empty cells (O(1) for a fixed 9x9 grid)
     * Space Complexity: O(M) auxiliary space for recursion stack and empty cell list
     */
    public void solveSudoku(char[][] board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    emptyCells.add(new int[]{r, c});
                } else {
                    int d = board[r][c] - '0';
                    int b = (r / 3) * 3 + (c / 3);
                    rows[r][d] = true;
                    cols[c][d] = true;
                    boxes[b][d] = true;
                }
            }
        }
        backtrack(board, 0);
    }

    private boolean backtrack(char[][] board, int index) {
        if (index == emptyCells.size()) {
            return true;
        }

        int[] cell = emptyCells.get(index);
        int r = cell[0];
        int c = cell[1];
        int b = (r / 3) * 3 + (c / 3);

        for (int d = 1; d <= 9; d++) {
            if (!rows[r][d] && !cols[c][d] && !boxes[b][d]) {
                board[r][c] = (char) ('0' + d);
                rows[r][d] = true;
                cols[c][d] = true;
                boxes[b][d] = true;

                if (backtrack(board, index + 1)) {
                    return true;
                }

                // Backtrack state
                board[r][c] = '.';
                rows[r][d] = false;
                cols[c][d] = false;
                boxes[b][d] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        solver.solveSudoku(board);

        // Verification check
        System.out.println("Board solved successfully: " + (board[0][2] == '4' && board[8][8] == '9'));
    }
}