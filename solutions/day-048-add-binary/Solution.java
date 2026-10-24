/**
 * Day 048: Add Binary
 * LeetCode #67: https://leetcode.com/problems/add-binary/
 */
public class Solution {

    /**
     * Computes the binary sum of two binary strings.
     * Simulates column-by-column binary addition from least to most significant bit.
     *
     * Time Complexity: O(max(N, M)) where N and M are the lengths of strings a and b
     * Space Complexity: O(max(N, M)) auxiliary space for the result builder
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry != 0) {
            int total = carry;

            if (i >= 0) {
                total += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                total += b.charAt(j) - '0';
                j--;
            }

            sb.append(total % 2);
            carry = total / 2;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        System.out.println("Test 1 (Expected 100): " + solver.addBinary("11", "1"));
        System.out.println("Test 2 (Expected 10101): " + solver.addBinary("1010", "1011"));
        System.out.println("Test 3 (Expected 0): " + solver.addBinary("0", "0"));
    }
}