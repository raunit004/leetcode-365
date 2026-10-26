/**
 * Day 050: Multiply Strings
 * LeetCode #43: https://leetcode.com/problems/multiply-strings/
 */
public class Solution {

    /**
     * Multiplies two non-negative integers represented as strings without using
     * any built-in BigInteger library or converting inputs directly to integers.
     * Simulates column-by-column vertical multiplication using an array of size m + n.
     *
     * Time Complexity: O(M * N) where M and N are lengths of num1 and num2
     * Space Complexity: O(M + N) auxiliary space for the product array
     */
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p2] = sum % 10;
                pos[p1] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        System.out.println("Test 1 (Expected '6'): " + solver.multiply("2", "3"));
        System.out.println("Test 2 (Expected '56088'): " + solver.multiply("123", "456"));
        System.out.println("Test 3 (Expected '0'): " + solver.multiply("0", "52"));
        System.out.println("Test 4 (Expected '0'): " + solver.multiply("9133", "0"));
    }
}