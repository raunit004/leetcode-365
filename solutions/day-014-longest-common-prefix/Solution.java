/**
 * Day 014: Longest Common Prefix
 * LeetCode #14: https://leetcode.com/problems/longest-common-prefix/
 */
public class Solution {
    /**
     * Finds the longest common prefix among an array of strings using vertical scanning.
     *
     * @param strs Array of input strings
     * @return Longest common prefix, or "" if none exists
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String[] test1 = {"flower", "flow", "flight"};
        String[] test2 = {"dog", "racecar", "car"};
        String[] test3 = {"interspecies", "interstellar", "interstate"};
        String[] test4 = {"a"};

        System.out.println("Input: [\"flower\", \"flow\", \"flight\"]          | Output: \"" + sol.longestCommonPrefix(test1) + "\" | Expected: \"fl\"");
        System.out.println("Input: [\"dog\", \"racecar\", \"car\"]              | Output: \"" + sol.longestCommonPrefix(test2) + "\" | Expected: \"\"");
        System.out.println("Input: [\"interspecies\", \"interstellar\", ...] | Output: \"" + sol.longestCommonPrefix(test3) + "\" | Expected: \"inters\"");
        System.out.println("Input: [\"a\"]                                    | Output: \"" + sol.longestCommonPrefix(test4) + "\" | Expected: \"a\"");
    }
}