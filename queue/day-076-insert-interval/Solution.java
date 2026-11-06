import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Day 076: Insert Interval
 * LeetCode #57: https://leetcode.com/problems/insert-interval/
 */
public class Solution {

    /**
     * Inserts a new interval into an array of non-overlapping sorted intervals,
     * merging overlapping intervals as necessary.
     *
     * Processes intervals in three phases:
     * 1. Add all intervals ending strictly before newInterval starts.
     * 2. Merge all overlapping intervals with newInterval into a single combined interval.
     * 3. Add all remaining intervals starting strictly after newInterval ends.
     *
     * Time Complexity: O(N) where N is intervals.length
     * Space Complexity: O(N) auxiliary space to construct the result list
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Phase 1: Add intervals occurring before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // Phase 3: Add remaining intervals occurring after newInterval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: intervals = [[1,3],[6,9]], newInterval = [2,5] -> Expected: [[1,5],[6,9]]
        int[][] test1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        System.out.println("Test 1: " + Arrays.deepToString(solver.insert(test1, newInterval1)));

        // Smoke Test 2: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8] -> Expected: [[1,2],[3,10],[12,16]]
        int[][] test2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        System.out.println("Test 2: " + Arrays.deepToString(solver.insert(test2, newInterval2)));

        // Smoke Test 3: empty intervals, newInterval = [5,7] -> Expected: [[5,7]]
        int[][] test3 = {};
        int[] newInterval3 = {5, 7};
        System.out.println("Test 3: " + Arrays.deepToString(solver.insert(test3, newInterval3)));
    }
}