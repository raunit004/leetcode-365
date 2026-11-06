/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

/**
 * Day 078: Rotate List
 * LeetCode #61: https://leetcode.com/problems/rotate-list/
 */
public class Solution {

    /**
     * Rotates a singly-linked list to the right by k places.
     * Computes the list length, connects tail to head to form a circular ring,
     * and cuts the ring at (length - k % length) to establish the new head.
     *
     * Time Complexity: O(N) where N is the number of nodes in the linked list
     * Space Complexity: O(1) auxiliary space
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        k = k % length;
        if (k == 0) {
            return head;
        }

        // Form a circular ring
        tail.next = head;

        int stepsToNewTail = length - k;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Helper to construct list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode test1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode res1 = solver.rotateRight(test1, 2);
        System.out.print("Test 1 (k = 2, Expected [4, 5, 1, 2, 3]): ");
        printList(res1);

        // Helper to construct list: 0 -> 1 -> 2
        ListNode test2 = new ListNode(0, new ListNode(1, new ListNode(2)));
        ListNode res2 = solver.rotateRight(test2, 4);
        System.out.print("Test 2 (k = 4, Expected [2, 0, 1]): ");
        printList(res2);

        // Single-node list: 1
        ListNode test3 = new ListNode(1);
        ListNode res3 = solver.rotateRight(test3, 99);
        System.out.print("Test 3 (Single node, Expected [1]): ");
        printList(res3);
    }

    private static void printList(ListNode node) {
        System.out.print("[");
        while (node != null) {
            System.out.print(node.val + (node.next != null ? ", " : ""));
            node = node.next;
        }
        System.out.println("]");
    }
}