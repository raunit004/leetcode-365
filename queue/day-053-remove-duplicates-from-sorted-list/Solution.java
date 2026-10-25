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
 * Day 053: Remove Duplicates from Sorted List
 * LeetCode #83: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 */
public class Solution {

    /**
     * Deletes all duplicates such that each element appears only once in a sorted linked list.
     * Rewires pointers in-place by comparing adjacent node values.
     *
     * Time Complexity: O(N) where N is the number of nodes in the linked list
     * Space Complexity: O(1) auxiliary space
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                // Bypass the duplicate node
                curr.next = curr.next.next;
            } else {
                // Advance only when the next node has a distinct value
                curr = curr.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Smoke Test 1: [1, 1, 2] -> [1, 2]
        ListNode test1 = new ListNode(1, new ListNode(1, new ListNode(2)));
        ListNode res1 = solver.deleteDuplicates(test1);

        System.out.print("Test 1 Result (Expected 1 -> 2): ");
        printList(res1);

        // Smoke Test 2: [1, 1, 2, 3, 3] -> [1, 2, 3]
        ListNode test2 = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        ListNode res2 = solver.deleteDuplicates(test2);

        System.out.print("Test 2 Result (Expected 1 -> 2 -> 3): ");
        printList(res2);
    }

    private static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + (node.next != null ? " -> " : "\n"));
            node = node.next;
        }
    }
}