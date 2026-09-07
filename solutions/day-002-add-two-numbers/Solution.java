/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Day 002: Add Two Numbers
 * LeetCode #2: https://leetcode.com/problems/add-two-numbers/
 */
public class Solution {
    /**
     * Adds two numbers represented by linked lists in reverse order.
     *
     * @param l1 Head of first linked list
     * @param l2 Head of second linked list
     * @return Head of resultant linked list representing the sum
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test Case: l1 = [2, 4, 3] (represents 342), l2 = [5, 6, 4] (represents 465)
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode result = sol.addTwoNumbers(l1, l2);

        // Expected Output: [7, 0, 8] (represents 807)
        System.out.print("Result: ");
        printList(result);
    }

    private static void printList(ListNode node) {
        StringBuilder sb = new StringBuilder("[");
        while (node != null) {
            sb.append(node.val);
            if (node.next != null) sb.append(" -> ");
            node = node.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}