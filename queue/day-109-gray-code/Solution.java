import java.util.*;

/**
 * Day 109: Gray Code
 * LeetCode #89: https://leetcode.com/problems/gray-code/
 */
public class Solution {
    public List<Integer> grayCode(int n) {
        int total = 1 << n;
        List<Integer> res = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }
}
