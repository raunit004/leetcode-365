import java.util.Arrays;

/**
 * Day 061: Rotate Image
 * LeetCode #48: https://leetcode.com/problems/rotate-image/
 */
public class Solution {

    /**
     * Rotates an n x n 2D matrix representing an image by 90 degrees clockwise in-place.
     * Accomplishes the rotation in two geometric transformations:
     * 1. Transpose the matrix across the main diagonal.
     * 2. Reverse each row horizontally.
     *
     * Time Complexity: O(N^2) where N is the dimension of the matrix
     * Space Complexity: O(1) auxiliary space
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix (swap matrix[i][j] with matrix[j][i])
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row horizontally
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: 3x3 matrix
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        solver.rotate(matrix1);
        System.out.println("Test 1 Result (Expected [[7, 4, 1], [8, 5, 2], [9, 6, 3]]):");
        System.out.println(Arrays.deepToString(matrix1));

        // Smoke Test 2: 4x4 matrix
        int[][] matrix2 = {
            { 5,  1,  9, 11},
            { 2,  4,  8, 10},
            {13,  3,  6,  7},
            {15, 14, 12, 16}
        };
        solver.rotate(matrix2);
        System.out.println("Test 2 Result (Expected [[15, 13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7, 10, 11]]):");
        System.out.println(Arrays.deepToString(matrix2));
    }
}