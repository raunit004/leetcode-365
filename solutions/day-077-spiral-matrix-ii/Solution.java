import java.util.Arrays;

/**
 * Day 077: Spiral Matrix II
 * LeetCode #59: https://leetcode.com/problems/spiral-matrix-ii/
 */
public class Solution {

    /**
     * Generates an n x n matrix filled with elements from 1 to n^2 in spiral order.
     * Contracts four boundary pointers (top, bottom, left, right) inward while
     * populating the cells sequentially.
     *
     * Time Complexity: O(n^2) where n is the grid dimension
     * Space Complexity: O(1) auxiliary space (excluding the returned matrix)
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        int num = 1;

        while (top <= bottom && left <= right) {
            // 1. Fill top row from left to right
            for (int c = left; c <= right; c++) {
                matrix[top][c] = num++;
            }
            top++;

            // 2. Fill right column from top to bottom
            for (int r = top; r <= bottom; r++) {
                matrix[r][right] = num++;
            }
            right--;

            // 3. Fill bottom row from right to left (if valid)
            if (top <= bottom) {
                for (int c = right; c >= left; c--) {
                    matrix[bottom][c] = num++;
                }
                bottom--;
            }

            // 4. Fill left column from bottom to top (if valid)
            if (left <= right) {
                for (int r = bottom; r >= top; r--) {
                    matrix[r][left] = num++;
                }
                left++;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: n = 3 -> Expected [[1, 2, 3], [8, 9, 4], [7, 6, 5]]
        System.out.println("Test 1 (n = 3):");
        System.out.println(Arrays.deepToString(solver.generateMatrix(3)));

        // Smoke Test 2: n = 1 -> Expected [[1]]
        System.out.println("Test 2 (n = 1):");
        System.out.println(Arrays.deepToString(solver.generateMatrix(1)));

        // Smoke Test 3: n = 4
        System.out.println("Test 3 (n = 4):");
        System.out.println(Arrays.deepToString(solver.generateMatrix(4)));
    }
}