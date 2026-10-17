/**
 * Day 041: Valid Sudoku
 * LeetCode #36: https://leetcode.com/problems/valid-sudoku/
 */
public class Solution {

    /**
     * Determines whether a 9 x 9 Sudoku board is valid according to row,
     * column, and 3 x 3 sub-box uniqueness constraints.
     * Uses bit manipulation to track seen digits in O(1) auxiliary space.
     *
     * Time Complexity: O(1) for a fixed 9 x 9 grid
     * Space Complexity: O(1) auxiliary space
     */
    public boolean isValidSudoku(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') {
                    continue;
                }

                int val = ch - '1';
                int mask = 1 << val;
                int boxIndex = (r / 3) * 3 + (c / 3);

                // Verify if the digit was previously recorded in row, col, or 3x3 box
                if ((rows[r] & mask) != 0 || (cols[c] & mask) != 0 || (boxes[boxIndex] & mask) != 0) {
                    return false;
                }

                // Register the digit in all three tracking masks
                rows[r] |= mask;
                cols[c] |= mask;
                boxes[boxIndex] |= mask;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        char[][] validBoard = {
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

        System.out.println("Test 1 (Expected true): " + solver.isValidSudoku(validBoard));

        // Invalid board with duplicate '8' in column 0
        char[][] invalidBoard = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println("Test 2 (Expected false): " + solver.isValidSudoku(invalidBoard));
    }
}