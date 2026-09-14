/**
 * Day 009: Palindrome Number
 * LeetCode #9: https://leetcode.com/problems/palindrome-number/
 */
public class Solution {
    /**
     * Determines whether an integer is a palindrome by reversing only its second half.
     * Prevents 32-bit integer overflow without string conversion.
     *
     * @param x The integer to check
     * @return true if x is a palindrome, false otherwise
     */
    public boolean isPalindrome(int x) {
        // Negative numbers cannot be palindromes (e.g., -121 != 121-)
        // Numbers ending in 0 (except 0 itself) cannot be palindromes (e.g., 10 != 01)
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is odd, we can get rid of the middle digit via revertedNumber / 10
        // (e.g., for 121: at loop end, x = 1, revertedNumber = 12 -> 1 == 12 / 10)
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int test1 = 121;
        int test2 = -121;
        int test3 = 10;
        int test4 = 0;
        int test5 = 1221;

        System.out.println("Input: " + test1 + "  | Output: " + sol.isPalindrome(test1) + "  | Expected: true");
        System.out.println("Input: " + test2 + " | Output: " + sol.isPalindrome(test2) + " | Expected: false");
        System.out.println("Input: " + test3 + "   | Output: " + sol.isPalindrome(test3) + " | Expected: false");
        System.out.println("Input: " + test4 + "    | Output: " + sol.isPalindrome(test4) + "  | Expected: true");
        System.out.println("Input: " + test5 + " | Output: " + sol.isPalindrome(test5) + "  | Expected: true");
    }
}