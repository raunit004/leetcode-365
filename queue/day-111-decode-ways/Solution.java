/**
 * Day 111: Decode Ways
 * LeetCode #91: https://leetcode.com/problems/decode-ways/
 */
public class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        int n = s.length(), prev2 = 1, prev1 = 1;
        for (int i = 2; i <= n; i++) {
            int curr = 0;
            if (s.charAt(i - 1) != '0') curr += prev1;
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (twoDigit >= 10 && twoDigit <= 26) curr += prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
