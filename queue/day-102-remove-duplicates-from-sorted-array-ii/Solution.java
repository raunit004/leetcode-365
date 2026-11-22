/**
 * Day 102: Remove Duplicates from Sorted Array II
 * LeetCode #80: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0;
        for (int x : nums) {
            if (k < 2 || x != nums[k - 2]) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
