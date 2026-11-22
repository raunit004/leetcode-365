/**
 * Day 106: Maximal Rectangle
 * LeetCode #85: https://leetcode.com/problems/maximal-rectangle/
 */
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int n = matrix[0].length, maxArea = 0;
        int[] heights = new int[n];
        for (char[] row : matrix) {
            for (int j = 0; j < n; j++) {
                heights[j] = (row[j] == '1') ? heights[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n + 1];
        int top = -1, maxArea = 0;
        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (top != -1 && heights[stack[top]] >= h) {
                int height = heights[stack[top--]];
                int width = (top == -1) ? i : i - stack[top] - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack[++top] = i;
        }
        return maxArea;
    }
}
