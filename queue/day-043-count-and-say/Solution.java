/**
 * Day 043: Count and Say
 * LeetCode #38: https://leetcode.com/problems/count-and-say/
 */
public class Solution {

    /**
     * Computes the nth element of the Count and Say sequence iteratively.
     * Uses two pointers to perform run-length encoding on the previous sequence string.
     *
     * Time Complexity: O(L) where L is the total length of all intermediate strings generated
     * Space Complexity: O(M) auxiliary space where M is the length of the nth string
     */
    public String countAndSay(int n) {
        String current = "1";

        for (int step = 2; step <= n; step++) {
            StringBuilder next = new StringBuilder();
            int len = current.length();
            int i = 0;

            while (i < len) {
                int j = i;
                while (j < len && current.charAt(j) == current.charAt(i)) {
                    j++;
                }

                int count = j - i;
                next.append(count).append(current.charAt(i));
                i = j;
            }

            current = next.toString();
        }

        return current;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        System.out.println("Test 1 (n = 1, Expected '1'): " + solver.countAndSay(1));
        System.out.println("Test 2 (n = 4, Expected '1211'): " + solver.countAndSay(4));
        System.out.println("Test 3 (n = 5, Expected '111221'): " + solver.countAndSay(5));
    }
}