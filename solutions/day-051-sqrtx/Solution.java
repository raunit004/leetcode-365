/**
 * Day 051: Sqrt(x)
 * LeetCode #69: https://leetcode.com/problems/sqrtx/
 */
public class Solution {

    /**
     * Computes the floor square root of a non-negative integer x using binary search.
     * Uses division (mid <= x / mid) instead of multiplication (mid * mid <= x)
     * to prevent 32-bit signed integer overflow.
     *
     * Time Complexity: O(log x)
     * Space Complexity: O(1) auxiliary space
     */
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }

        int left = 1;
        int right = x / 2;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid <= x / mid) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Tests
        System.out.println("Test 1 (x = 4, Expected 2): " + solver.mySqrt(4));
        System.out.println("Test 2 (x = 8, Expected 2): " + solver.mySqrt(8));
        System.out.println("Test 3 (x = 0, Expected 0): " + solver.mySqrt(0));
        System.out.println("Test 4 (x = 1, Expected 1): " + solver.mySqrt(1));
        System.out.println("Test 5 (x = 2147395599, Expected 46339): " + solver.mySqrt(2147395599));
    }
}