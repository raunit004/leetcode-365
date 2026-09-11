/**
 * Day 006: Zigzag Conversion
 * LeetCode #6: https://leetcode.com/problems/zigzag-conversion/
 */
public class Solution {
    /**
     * Converts a string into a zigzag pattern across a specified number of rows,
     * then reads off characters row by row using direct index jumping.
     *
     * @param s       Input string
     * @param numRows Number of rows for the zigzag pattern
     * @return Converted zigzag string
     */
    public String convert(String s, int numRows) {
        if (numRows <= 1 || s == null || s.length() <= numRows) {
            return s;
        }

        int n = s.length();
        char[] r = new char[n];
        int rp = 0;
        int charsBetweenFirstRow = numRows + Math.max(0, numRows - 2);

        // First Row
        for (int i = 0; i < n; i += charsBetweenFirstRow) {
            r[rp++] = s.charAt(i);
        }

        // Middle Rows
        for (int row = 2; row < numRows; row++) {
            for (int i = row - 1; i < n && rp < n; i += charsBetweenFirstRow) {
                r[rp++] = s.charAt(i);
                int zag = i + (numRows - row) * 2;
                if (zag < n) {
                    r[rp++] = s.charAt(zag);
                }
            }
        }

        // Last Row
        for (int i = numRows - 1; i < n && rp < n; i += charsBetweenFirstRow) {
            r[rp++] = s.charAt(i);
        }

        return String.valueOf(r);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String test1 = "PAYPALISHIRING";
        int rows1 = 3;
        System.out.println("Output: \"" + sol.convert(test1, rows1) + "\" | Expected: \"PAHNAPLSIIGYIR\"");

        int rows2 = 4;
        System.out.println("Output: \"" + sol.convert(test1, rows2) + "\" | Expected: \"PINALSIGYAHRPI\"");

        String test3 = "A";
        int rows3 = 1;
        System.out.println("Output: \"" + sol.convert(test3, rows3) + "\" | Expected: \"A\"");
    }
}