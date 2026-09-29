/**
 * Day 024: Swap Nodes in Pairs
 * LeetCode #24: https://leetcode.com/problems/swap-nodes-in-pairs/
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
     * Swaps every two adjacent nodes in a linked list by modifying node pointers.
     * Uses a sentinel dummy node to maintain the list head and manage pair connections.
     *
     * @param head Head node of the singly linked list
     * @return Head of the modified list with adjacent nodes swapped
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // Re-point links to perform pairwise swap
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Advance prev pointer forward by two nodes (to 'first')
            prev = first;
        }

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
        ListNode t1 = buildList(new int[]{1, 2, 3, 4});
        System.out.println("Input: [1,2,3,4] | Output: " + printList(sol.swapPairs(t1)) + " | Expected: [2,1,4,3]");

        ListNode t2 = buildList(new int[]{});
        System.out.println("Input: []        | Output: " + printList(sol.swapPairs(t2)) + " | Expected: []");

        ListNode t3 = buildList(new int[]{1});
        System.out.println("Input: [1]       | Output: " + printList(sol.swapPairs(t3)) + " | Expected: [1]");

        ListNode t4 = buildList(new int[]{1, 2, 3});
        System.out.println("Input: [1,2,3]   | Output: " + printList(sol.swapPairs(t4)) + " | Expected: [2,1,3]");
    }
}