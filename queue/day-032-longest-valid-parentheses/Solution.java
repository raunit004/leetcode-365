import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Day 032: Longest Valid Parentheses
 * LeetCode #32: https://leetcode.com/problems/longest-valid-parentheses/
 */
public class Solution {
    /**
     * Computes the length of the longest valid (well-formed) parentheses substring.
     * Tracks candidate substring boundaries using an index stack initialized with a sentinel base.
     *
     * @param s String containing only '(' and ')'
     * @return Length of the longest valid substring
     */
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        System.out.println("Input: s = \"(()\"     | Output: " + sol.longestValidParentheses("(()") + " | Expected: 2");
        System.out.println("Input: s = \")()())\" | Output: " + sol.longestValidParentheses(")()())") + " | Expected: 4");
        System.out.println("Input: s = \"\"       | Output: " + sol.longestValidParentheses("") + " | Expected: 0");
        System.out.println("Input: s = \"()(()\"   | Output: " + sol.longestValidParentheses("()(()") + " | Expected: 2");
    }
}