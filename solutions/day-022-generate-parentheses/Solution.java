import java.util.ArrayList;
import java.util.List;

/**
 * Day 022: Generate Parentheses
 * LeetCode #22: https://leetcode.com/problems/generate-parentheses/
 */
public class Solution {
    /**
     * Generates all combinations of well-formed parentheses for n pairs.
     * Uses depth-first backtracking constrained by available open and close counts.
     *
     * @param n Number of pairs of parentheses
     * @return List of all valid parenthesis combinations
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        if (open < max) {
            current.append('(');
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(')');
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        int test1 = 3;
        int test2 = 1;
        int test3 = 2;

        System.out.println("Input: n = 3 | Output: " + sol.generateParenthesis(test1));
        System.out.println("Expected:    | [\"((()))\", \"(()())\", \"(())()\", \"()(())\", \"()()()\"]");

        System.out.println("Input: n = 1 | Output: " + sol.generateParenthesis(test2));
        System.out.println("Expected:    | [\"()\"]");

        System.out.println("Input: n = 2 | Output: " + sol.generateParenthesis(test3));
        System.out.println("Expected:    | [\"(())\", \"()()\"]");
    }
}