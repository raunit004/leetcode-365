/**
 * Day 013: Roman to Integer
 * LeetCode #13: https://leetcode.com/problems/roman-to-integer/
 */
public class Solution {
    /**
     * Converts a Roman numeral string to an integer using look-ahead subtraction logic.
     *
     * @param s Roman numeral string (length 1 to 15, valid symbols only)
     * @return Converted integer value
     */
    public int romanToInt(String s) {
        int total = 0;
        int n = s.length();

        for (int i = 0; i < n - 1; i++) {
            int current = getValue(s.charAt(i));
            int next = getValue(s.charAt(i + 1));

            if (current < next) {
                total -= current;
            } else {
                total += current;
            }
        }

        total += getValue(s.charAt(n - 1));
        return total;
    }

    /**
     * Maps each Roman numeral character to its numeric value via direct branch dispatch.
     *
     * @param c Character representing a Roman numeral symbol
     * @return Numeric integer equivalent
     */
    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String test1 = "III";
        String test2 = "LVIII";
        String test3 = "MCMXCIV";

        System.out.println("Input: \"" + test1 + "\"     | Output: " + sol.romanToInt(test1) + "  | Expected: 3");
        System.out.println("Input: \"" + test2 + "\"   | Output: " + sol.romanToInt(test2) + " | Expected: 58");
        System.out.println("Input: \"" + test3 + "\" | Output: " + sol.romanToInt(test3) + " | Expected: 1994");
    }
}