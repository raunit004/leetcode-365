import java.util.*;

/**
 * Day 092: Text Justification
 * LeetCode #68: https://leetcode.com/problems/text-justification/
 */
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0, n = words.length;
        while (i < n) {
            int j = i + 1, lineLen = words[i].length();
            while (j < n && lineLen + 1 + words[j].length() <= maxWidth) {
                lineLen += 1 + words[j].length();
                j++;
            }
            int gaps = j - i - 1;
            StringBuilder sb = new StringBuilder();
            if (j == n || gaps == 0) {
                for (int k = i; k < j; k++) {
                    if (k > i) sb.append(' ');
                    sb.append(words[k]);
                }
                while (sb.length() < maxWidth) sb.append(' ');
            } else {
                int totalChars = 0;
                for (int k = i; k < j; k++) totalChars += words[k].length();
                int spaces = (maxWidth - totalChars) / gaps;
                int extra = (maxWidth - totalChars) % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        int pad = spaces + (k - i < extra ? 1 : 0);
                        for (int s = 0; s < pad; s++) sb.append(' ');
                    }
                }
            }
            res.add(sb.toString());
            i = j;
        }
        return res;
    }
}
