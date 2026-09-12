/**
 * Day 007: Reverse Integer
 * LeetCode #7: https://leetcode.com/problems/reverse-integer/
 */
public class Solution {
    /**
     * Reverses the digits of a 32-bit signed integer.
     * Prevents 32-bit signed integer overflow without allocating 64-bit storage.
     *
     * @param x The 32-bit signed integer
     * @return Reversed integer or 0 if reversing exceeds 32-bit bounds
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // Overflow checks against [Integer.MIN_VALUE, Integer.MAX_VALUE]
            // Integer.MAX_VALUE is  2147483647 (ends with 7)
            // Integer.MIN_VALUE is -2147483648 (ends with -8)
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int test1 = 123;
        int test2 = -123;
        int test3 = 120;
        int test4 = 1534236469; // Causes 32-bit integer overflow

        System.out.println("Input: " + test1 + " | Output: " + sol.reverse(test1) + " | Expected: 321");
        System.out.println("Input: " + test2 + " | Output: " + sol.reverse(test2) + " | Expected: -321");
        System.out.println("Input: " + test3 + " | Output: " + sol.reverse(test3) + " | Expected: 21");
        System.out.println("Input: " + test4 + " | Output: " + sol.reverse(test4) + " | Expected: 0");
    }
}