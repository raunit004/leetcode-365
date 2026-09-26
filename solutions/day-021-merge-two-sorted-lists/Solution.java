/**
 * Day 021: Merge Two Sorted Lists
 * LeetCode #21: https://leetcode.com/problems/merge-two-sorted-lists/
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
     * Merges two sorted linked lists by splicing nodes together in place.
     * Uses a sentinel dummy node to maintain head reference.
     *
     * @param list1 Head of the first sorted linked list
     * @param list2 Head of the second sorted linked list
     * @return Head of the merged sorted list
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // Attach whatever remaining sublist is non-null
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }

    // Helper method to convert an array to a linked list
    private static ListNode buildList(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : vals) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper method to serialize a linked list to string
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
        ListNode l1 = buildList(new int[]{1, 2, 4});
        ListNode l2 = buildList(new int[]{1, 3, 4});
        System.out.println("Input: list1 = [1,2,4], list2 = [1,3,4] | Output: " + printList(sol.mergeTwoLists(l1, l2)) + " | Expected: [1,1,2,3,4,4]");

        ListNode l3 = buildList(new int[]{});
        ListNode l4 = buildList(new int[]{});
        System.out.println("Input: list1 = [], list2 = []           | Output: " + printList(sol.mergeTwoLists(l3, l4)) + " | Expected: []");

        ListNode l5 = buildList(new int[]{});
        ListNode l6 = buildList(new int[]{0});
        System.out.println("Input: list1 = [], list2 = [0]          | Output: " + printList(sol.mergeTwoLists(l5, l6)) + " | Expected: [0]");
    }
}