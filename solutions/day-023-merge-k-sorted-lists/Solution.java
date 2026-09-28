/**
 * Day 023: Merge k Sorted Lists
 * LeetCode #23: https://leetcode.com/problems/merge-k-sorted-lists/
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
     * Merges k sorted linked-lists into one sorted list using bottom-up divide-and-conquer.
     * Merges pairs iteratively in place to achieve O(1) auxiliary space.
     *
     * @param lists Array of heads of k sorted linked lists
     * @return Head of the final merged sorted list
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int interval = 1;
        while (interval < lists.length) {
            for (int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }

        return lists[0];
    }

    /**
     * Merges two sorted linked lists using two pointers and a sentinel node.
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        current.next = (l1 != null) ? l1 : l2;
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
        if (head == null) return "[]";
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
        ListNode[] test1 = new ListNode[] {
            buildList(new int[]{1, 4, 5}),
            buildList(new int[]{1, 3, 4}),
            buildList(new int[]{2, 6})
        };
        System.out.println("Input: [[1,4,5],[1,3,4],[2,6]] | Output: " + printList(sol.mergeKLists(test1)) + " | Expected: [1,1,2,3,4,4,5,6]");

        ListNode[] test2 = new ListNode[]{};
        System.out.println("Input: []                      | Output: " + printList(sol.mergeKLists(test2)) + " | Expected: []");

        ListNode[] test3 = new ListNode[]{ buildList(new int[]{}) };
        System.out.println("Input: [[]]                    | Output: " + printList(sol.mergeKLists(test3)) + " | Expected: []");
    }
}