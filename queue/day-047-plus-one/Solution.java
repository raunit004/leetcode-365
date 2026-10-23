import java.util.Arrays;

/**
 * Day 047: Plus One
 * LeetCode #66: https://leetcode.com/problems/plus-one/
 */
public class Solution {

    /**
     * Increments the large integer represented by an array of digits by one.
     * Iterates backwards from the least significant digit, propagating carry as needed.
     *
     * Time Complexity: O(N) where N is digits.length
     * Space Complexity: O(1) auxiliary space (O(N) only when expanding for an all-9 overflow)
     */
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        // Triggered only if all digits were 9 (e.g., 999 -> 1000)
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        int[] test1 = {1, 2, 3};
        System.out.println("Test 1 (Expected [1, 2, 4]): " + Arrays.toString(solver.plusOne(test1)));

        int[] test2 = {4, 3, 2, 1};
        System.out.println("Test 2 (Expected [4, 3, 2, 2]): " + Arrays.toString(solver.plusOne(test2)));

        int[] test3 = {9};
        System.out.println("Test 3 (Expected [1, 0]): " + Arrays.toString(solver.plusOne(test3)));

        int[] test4 = {9, 9, 9};
        System.out.println("Test 4 (Expected [1, 0, 0, 0]): " + Arrays.toString(solver.plusOne(test4)));
    }
}