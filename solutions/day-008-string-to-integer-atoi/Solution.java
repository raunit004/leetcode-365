/**
 * Day 008: String to Integer (atoi)
 * LeetCode #8: https://leetcode.com/problems/string-to-integer-atoi/
 */
public class Solution {
    /**
     * Converts a string to a 32-bit signed integer following the atoi specification.
     * Clamps values to [Integer.MIN_VALUE, Integer.MAX_VALUE] on overflow.
     *
     * @param s Input string
     * @return 32-bit signed integer value
     */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int i = 0;

        // 1. Discard leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i == n) return 0;

        // 2. Determine sign
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. Convert digits and check 32-bit overflow
        int total = 0;
        int threshold = Integer.MAX_VALUE / 10;

        while (i < n) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') break;

            int digit = c - '0';

            // Integer.MAX_VALUE ends in 7 (2147483647), Integer.MIN_VALUE ends in 8 (-2147483648)
            if (total > threshold || (total == threshold && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = total * 10 + digit;
            i++;
        }

        return total * sign;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String test1 = "42";
        String test2 = " -042";
        String test3 = "1337c0d3";
        String test4 = "0-1";
        String test5 = "words and 987";
        String test6 = "-91283472332"; // Underflow test

        System.out.println("Input: \"" + test1 + "\" | Output: " + sol.myAtoi(test1) + " | Expected: 42");
        System.out.println("Input: \"" + test2 + "\" | Output: " + sol.myAtoi(test2) + " | Expected: -42");
        System.out.println("Input: \"" + test3 + "\" | Output: " + sol.myAtoi(test3) + " | Expected: 1337");
        System.out.println("Input: \"" + test4 + "\" | Output: " + sol.myAtoi(test4) + " | Expected: 0");
        System.out.println("Input: \"" + test5 + "\" | Output: " + sol.myAtoi(test5) + " | Expected: 0");
        System.out.println("Input: \"" + test6 + "\" | Output: " + sol.myAtoi(test6) + " | Expected: " + Integer.MIN_VALUE);
    }
}