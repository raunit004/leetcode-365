/**
 * Day 012: Integer to Roman
 * LeetCode #12: https://leetcode.com/problems/integer-to-roman/
 */
public class Solution {
    /**
     * Converts an integer to a Roman numeral using a greedy subtraction strategy
     * across pre-sorted value-to-symbol pairings.
     *
     * @param num The integer to convert (1 <= num <= 3999)
     * @return Roman numeral representation
     */
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int test1 = 3749;
        int test2 = 58;
        int test3 = 1994;

        System.out.println("Input: " + test1 + " | Output: \"" + sol.intToRoman(test1) + "\" | Expected: \"MMMDCCXLIX\"");
        System.out.println("Input: " + test2 + "   | Output: \"" + sol.intToRoman(test2) + "\" | Expected: \"LVIII\"");
        System.out.println("Input: " + test3 + " | Output: \"" + sol.intToRoman(test3) + "\" | Expected: \"MCMXCIV\"");
    }
}