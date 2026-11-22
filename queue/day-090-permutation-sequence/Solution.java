import java.util.*;

/**
 * Day 090: Permutation Sequence
 * LeetCode #60: https://leetcode.com/problems/permutation-sequence/
 */
public class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            fact *= i;
        }
        k--;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            fact /= i;
            int idx = k / fact;
            sb.append(nums.remove(idx));
            k %= fact;
        }
        return sb.toString();
    }
}
