import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Day 020: Valid Parentheses
 * LeetCode #20: https://leetcode.com/problems/valid-parentheses/
 */
public class Solution {
    /**
     * Determines if an input string containing brackets is valid.
     * Pushes expected closing brackets onto a stack to match against subsequent closing symbols.
     *
     * @param s String containing '(', ')', '{', '}', '[', and ']'
     * @return true if all brackets are properly opened and closed in correct order, false otherwise
     */
    public boolean isValid(String s) {
        // Any string with an odd length cannot form matching pairs
        if (s.length() % 2 != 0) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String test1 = "()";
        String test2 = "()[]{}";
        String test3 = "(]";
        String test4 = "([)]";
        String test5 = "{[]}";
        String test6 = "]";

        System.out.println("Input: \"" + test1 + "\"     | Output: " + sol.isValid(test1) + "  | Expected: true");
        System.out.println("Input: \"" + test2 + "\" | Output: " + sol.isValid(test2) + "  | Expected: true");
        System.out.println("Input: \"" + test3 + "\"     | Output: " + sol.isValid(test3) + " | Expected: false");
        System.out.println("Input: \"" + test4 + "\"   | Output: " + sol.isValid(test4) + " | Expected: false");
        System.out.println("Input: \"" + test5 + "\"   | Output: " + sol.isValid(test5) + "  | Expected: true");
        System.out.println("Input: \"" + test6 + "\"      | Output: " + sol.isValid(test6) + " | Expected: false");
    }
}