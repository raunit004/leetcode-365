import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Day 030: Substring with Concatenation of All Words
 * LeetCode #30: https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 */
public class Solution {
    /**
     * Finds starting indices of substrings in s that are concatenations of each word in words.
     * Uses a multi-offset sliding window partitioned by word length with frequency tracking.
     *
     * @param s     Target text string
     * @param words Array of uniform-length query words
     * @return List of starting 0-based indices
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        int sLen = s.length();

        if (sLen < totalLen) {
            return result;
        }

        Map<String, Integer> targetCounts = new HashMap<>();
        for (String word : words) {
            targetCounts.put(word, targetCounts.getOrDefault(word, 0) + 1);
        }

        for (int offset = 0; offset < wordLen; offset++) {
            int left = offset;
            int right = offset;
            Map<String, Integer> windowCounts = new HashMap<>();
            int wordsMatched = 0;

            while (right + wordLen <= sLen) {
                String sub = s.substring(right, right + wordLen);
                right += wordLen;

                if (targetCounts.containsKey(sub)) {
                    windowCounts.put(sub, windowCounts.getOrDefault(sub, 0) + 1);
                    wordsMatched++;

                    // Shrink window if frequency of current word exceeds expectation
                    while (windowCounts.get(sub) > targetCounts.get(sub)) {
                        String leftSub = s.substring(left, left + wordLen);
                        windowCounts.put(leftSub, windowCounts.getOrDefault(leftSub, 0) - 1);
                        wordsMatched--;
                        left += wordLen;
                    }

                    if (wordsMatched == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Invalid word encountered: flush window and reset boundary
                    windowCounts.clear();
                    wordsMatched = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        String s1 = "barfoothefoobarman";
        String[] w1 = {"foo", "bar"};
        System.out.println("Input: s = \"barfoothefoobarman\", words = [\"foo\",\"bar\"] | Output: " 
                + sol.findSubstring(s1, w1) + " | Expected: [0, 9]");

        String s2 = "wordgoodgoodgoodbestword";
        String[] w2 = {"word", "good", "best", "word"};
        System.out.println("Input: s = \"wordgoodgoodgoodbestword\", words = [...]        | Output: " 
                + sol.findSubstring(s2, w2) + " | Expected: []");

        String s3 = "barfoofoobarthefoobarman";
        String[] w3 = {"bar", "foo", "the"};
        System.out.println("Input: s = \"barfoofoobarthefoobarman\", words = [...]       | Output: " 
                + sol.findSubstring(s3, w3) + " | Expected: [6, 9, 12]");
    }
}