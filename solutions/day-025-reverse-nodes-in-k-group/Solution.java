/**
 * Day 025: Reverse Nodes in k-Group
 * LeetCode #25: https://leetcode.com/problems/reverse-nodes-in-k-group/
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
     * Reverses nodes of a linked list in groups of size k.
     * Leaves remaining tail nodes intact if fewer than k nodes remain.
     *
     * @param head Head node of the linked list
     * @param k    Positive integer group size (1 <= k <= length)
     * @return Modified linked list head
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) {
                break;
            }

            ListNode groupNext = kth.next;
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;

            // Reverse current k-group
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }

            // Splice reversed segment back to groupPrev and advance
            ListNode tmp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = tmp;
        }

        return dummy.next;
    }

    /**
     * Locates the k-th node ahead of the given pointer.
     *
     * @param curr Starting pointer
     * @param k    Step count ahead
     * @return The k-th node, or null if fewer than k nodes remain
     */
    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
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
        ListNode t1 = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Input: [1,2,3,4,5], k = 2 | Output: " + printList(sol.reverseKGroup(t1, 2)) + " | Expected: [2,1,4,3,5]");

        ListNode t2 = buildList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Input: [1,2,3,4,5], k = 3 | Output: " + printList(sol.reverseKGroup(t2, 3)) + " | Expected: [3,2,1,4,5]");

        ListNode t3 = buildList(new int[]{1});
        System.out.println("Input: [1], k = 1         | Output: " + printList(sol.reverseKGroup(t3, 1)) + " | Expected: [1]");
    }
}