import java.util.*;

/**
 * Day 113: Restore IP Addresses
 * LeetCode #93: https://leetcode.com/problems/restore-ip-addresses/
 */
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        if (n < 4 || n > 12) return res;
        backtrack(s, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void backtrack(String s, int idx, int parts, StringBuilder sb, List<String> res) {
        int n = s.length();
        if (parts == 4) {
            if (idx == n) res.add(sb.toString());
            return;
        }
        if ((4 - parts) * 3 < n - idx) return;
        int val = 0, prevLen = sb.length();
        for (int len = 1; len <= 3 && idx + len <= n; len++) {
            if (len > 1 && s.charAt(idx) == '0') break;
            val = val * 10 + (s.charAt(idx + len - 1) - '0');
            if (val > 255) break;
            if (parts > 0) sb.append('.');
            sb.append(val);
            backtrack(s, idx + len, parts + 1, sb, res);
            sb.setLength(prevLen);
        }
    }
}
