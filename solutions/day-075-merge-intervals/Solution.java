import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Day 075: Merge Intervals
 * LeetCode #56: https://leetcode.com/problems/merge-intervals/
 */
public class Solution {

    /**
     * Merges all overlapping intervals and returns an array of the non-overlapping intervals.
     * Sorts the array by start time and iteratively merges overlapping ranges by updating endpoints.
     *
     * Time Complexity: O(N log N) where N is intervals.length due to sorting
     * Space Complexity: O(N) auxiliary space for the output list and sorting call stack
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort intervals based on starting values
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int i = 1; i < intervals.length; i++) {
            int[] nextInterval = intervals[i];

            // If overlapping, extend the current interval's upper bound
            if (nextInterval[0] <= currentInterval[1]) {
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
            } else {
                // Disjoint interval found: advance pointer and add to merged collection
                currentInterval = nextInterval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: [[1, 3], [2, 6], [8, 10], [15, 18]] -> Expected [[1, 6], [8, 10], [15, 18]]
        int[][] test1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Test 1: " + Arrays.deepToString(solver.merge(test1)));

        // Smoke Test 2: [[1, 4], [4, 5]] -> Expected [[1, 5]]
        int[][] test2 = {{1, 4}, {4, 5}};
        System.out.println("Test 2: " + Arrays.deepToString(solver.merge(test2)));

        // Smoke Test 3: Unsorted intervals [[4, 7], [1, 4]] -> Expected [[1, 7]]
        int[][] test3 = {{4, 7}, {1, 4}};
        System.out.println("Test 3: " + Arrays.deepToString(solver.merge(test3)));
    }
}