/**
 * Day 071: Pow(x, n)
 * LeetCode #50: https://leetcode.com/problems/powx-n/
 */
public class Solution {

    /**
     * Computes x raised to the power n (x^n).
     * Implements binary exponentiation (exponentiation by squaring) with long exponent
     * conversion to handle Integer.MIN_VALUE overflow, and delays division to the final step
     * to prevent compounded floating-point round-off errors.
     *
     * Time Complexity: O(log |n|)
     * Space Complexity: O(1) auxiliary space
     */
    public double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) {
            exp = -exp;
        }

        double result = 1.0;
        double currentBase = x;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result *= currentBase;
            }
            currentBase *= currentBase;
            exp >>= 1;
        }

        return n < 0 ? 1.0 / result : result;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        System.out.println("Test 1 (x = 2.0, n = 10, Expected 1024.0): " + solver.myPow(2.0, 10));
        System.out.println("Test 2 (x = 2.1, n = 3, Expected 9.261): " + solver.myPow(2.1, 3));
        System.out.println("Test 3 (x = 2.0, n = -2, Expected 0.25): " + solver.myPow(2.0, -2));
        System.out.println("Test 4 (x = -1.0, n = -2147483648, Expected 1.0): " + solver.myPow(-1.0, -2147483648));
        System.out.println("Test 5 (Precision Test 307, Expected 191.06370): " + solver.myPow(-0.9999999968539456, -1669585506));
    }
}