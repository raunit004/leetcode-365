import java.util.ArrayList;
import java.util.List;

/**
 * Day 073: Spiral Matrix
 * LeetCode #54: https://leetcode.com/problems/spiral-matrix/
 */
public class Solution {

    /**
     * Traverses an m x n matrix in clockwise spiral order.
     * Contracts four bounding pointers (top, bottom, left, right) after
     * sweeping each perimeter edge.
     *
     * Time Complexity: O(M * N) where M is rows and N is columns
     * Space Complexity: O(1) auxiliary space (excluding the output list)
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // 1. Traverse from left to right along top row
            for (int c = left; c <= right; c++) {
                result.add(matrix[top][c]);
            }
            top++;

            // 2. Traverse from top to bottom along right column
            for (int r = top; r <= bottom; r++) {
                result.add(matrix[r][right]);
            }
            right--;

            // 3. Traverse from right to left along bottom row (if row exists)
            if (top <= bottom) {
                for (int c = right; c >= left; c--) {
                    result.add(matrix[bottom][c]);
                }
                bottom--;
            }

            // 4. Traverse from bottom to top along left column (if col exists)
            if (left <= right) {
                for (int r = bottom; r >= top; r--) {
                    result.add(matrix[r][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: 3x3 matrix
        int[][] test1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Test 1 (Expected [1, 2, 3, 6, 9, 8, 7, 4, 5]): " + solver.spiralOrder(test1));

        // Smoke Test 2: 3x4 matrix
        int[][] test2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        System.out.println("Test 2 (Expected [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]): " + solver.spiralOrder(test2));

        // Smoke Test 3: 1x1 matrix
        int[][] test3 = {{1}};
        System.out.println("Test 3 (Expected [1]): " + solver.spiralOrder(test3));
    }
}