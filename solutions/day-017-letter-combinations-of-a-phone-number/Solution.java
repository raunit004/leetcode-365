import java.util.ArrayList;
import java.util.List;

/**
 * Day 017: Letter Combinations of a Phone Number
 * LeetCode #17: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class Solution {
    private static final String[] KEYPAD = {
        "",    "",    "abc", "def", // 0, 1, 2, 3
        "ghi", "jkl", "mno",        // 4, 5, 6
        "pqrs", "tuv", "wxyz"       // 7, 8, 9
    };

    /**
     * Generates all possible letter combinations that the phone number could represent.
     * Uses depth-first backtracking with a mutable StringBuilder buffer.
     *
     * @param digits String containing digits from 2-9 inclusive
     * @return List of all valid letter combinations
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = KEYPAD[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i));
            backtrack(digits, index + 1, current, result);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String test1 = "23";
        String test2 = "2";
        String test3 = "";

        System.out.println("Input: \"23\" | Output: " + sol.letterCombinations(test1) + " | Expected: [ad, ae, af, bd, be, bf, cd, ce, cf]");
        System.out.println("Input: \"2\"  | Output: " + sol.letterCombinations(test2) + " | Expected: [a, b, c]");
        System.out.println("Input: \"\"   | Output: " + sol.letterCombinations(test3) + " | Expected: []");
    }
}