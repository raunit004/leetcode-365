/**
 * Day 019: Remove Nth Node From End of List
 * LeetCode #19: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    /**
     * Removes the nth node from the end of the list in a single pass.
     * Uses a sentinel (dummy) head and two pointers spaced (n + 1) nodes apart.
     *
     * @param head Head node of the singly linked list
     * @param n    1-based index from the end of the list to remove
     * @return Head of the modified list
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // Advance fast pointer so that the gap between fast and slow is n + 1 nodes
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move fast to the end, maintaining the gap
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Skip the target node
        slow.next = slow.next.next;

        return dummy.next;
    }

    // Helper method to convert an array into a linked list
    private static ListNode buildList(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : vals) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper method to serialize a linked list to a string
    private static String printList(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) sb.append(",");
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Smoke Tests
        ListNode t1 = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Input: [1,2,3,4,5], n = 2 | Output: " + printList(sol.removeNthFromEnd(t1, 2)) + " | Expected: [1,2,3,5]");

        ListNode t2 = buildList(new int[]{1});
        System.out.println("Input: [1], n = 1         | Output: " + printList(sol.removeNthFromEnd(t2, 1)) + " | Expected: []");

        ListNode t3 = buildList(new int[]{1, 2});
        System.out.println("Input: [1,2], n = 1       | Output: " + printList(sol.removeNthFromEnd(t3, 1)) + " | Expected: [1]");
    }
}