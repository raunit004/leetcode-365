import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list node.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * Day 044: Linked List Components
 * LeetCode #817: https://leetcode.com/problems/linked-list-components/
 */
public class Solution {

    /**
     * Counts the number of connected components in nums within a linked list.
     * Identifies components by detecting the "tail" or boundary transition of each segment.
     *
     * Time Complexity: O(N + M) where N is list length and M is nums length
     * Space Complexity: O(M) auxiliary space for the hash set
     */
    public int numComponents(ListNode head, int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }

        int count = 0;
        ListNode curr = head;

        while (curr != null) {
            // A component ends when curr is in nums and the next node is either null or not in nums
            if (set.contains(curr.val) && (curr.next == null || !set.contains(curr.next.val))) {
                count++;
            }
            curr = curr.next;
        }

        return count;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test: head = [0, 1, 2, 3], nums = [0, 1, 3] -> Expected: 2
        ListNode head = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3))));
        int[] nums = {0, 1, 3};

        System.out.println("Test 1 (Expected 2): " + solver.numComponents(head, nums));
    }
}