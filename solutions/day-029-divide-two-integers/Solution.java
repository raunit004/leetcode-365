/**
 * Day 029: Divide Two Integers
 * LeetCode #29: https://leetcode.com/problems/divide-two-integers/
 */
public class Solution {
    /**
     * Divides two integers without using multiplication, division, or mod operators.
     * Uses exponential search (doubling) in the negative integer space to prevent 32-bit overflow.
     *
     * @param dividend The integer to be divided
     * @param divisor  The integer to divide by
     * @return The truncated quotient bounded within [Integer.MIN_VALUE, Integer.MAX_VALUE]
     */
    public int divide(int dividend, int divisor) {
        // Overflow condition: Integer.MIN_VALUE / -1 = 2147483648 (> Integer.MAX_VALUE)
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Count negative inputs and convert both values to negative space
        // (Negative space accommodates -2^31, whereas positive max is only 2^31 - 1)
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        final int HALF_INT_MIN = -1073741824; // Integer.MIN_VALUE / 2
        int quotient = 0;

        // Exponential subtraction while dividend has room for at least one divisor
        while (dividend <= divisor) {
            int temp = divisor;
            int multiple = -1;

            // Double divisor and multiple while avoiding 32-bit underflow
            while (temp >= HALF_INT_MIN && dividend <= (temp + temp)) {
                temp += temp;
                multiple += multiple;
            }

            dividend -= temp;
            quotient += multiple;
        }

        // If exactly one operand was negative, quotient remains negative
        return (negatives == 1) ? quotient : -quotient;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        System.out.println("Input: dividend = 10, divisor = 3   | Output: " + sol.divide(10, 3) + " | Expected: 3");
        System.out.println("Input: dividend = 7, divisor = -3  | Output: " + sol.divide(7, -3) + " | Expected: -2");
        System.out.println("Input: dividend = -2147483648, -1 | Output: " + sol.divide(Integer.MIN_VALUE, -1) + " | Expected: 2147483647");
        System.out.println("Input: dividend = -1, divisor = 1   | Output: " + sol.divide(-1, 1) + " | Expected: -1");
    }
}