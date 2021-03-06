package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import challenges.AbstractCustomTestRunner;

/**
 * 206. Reverse Linked List
 * 
 * Reverse a singly linked list.
 * 
 * @author Hxkandwal
 */
public class ReverseLinkedList extends AbstractCustomTestRunner {
	
	private static ReverseLinkedList _instance = new ReverseLinkedList();
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ListNode reverseList(ListNode head) {
		ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
	
    public ListNode _reverseList(ListNode head) {
    	return reverse (null, head);
    }
    
    private ListNode reverse (ListNode parent, ListNode node) {
        if (node == null) return parent;
        ListNode ret = reverse (node, node.next);
        node.next = parent;
        return ret;
    }

	// driver method
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		
		assertThat(_instance._reverseList(head).val).isEqualTo(3);
		System.out.println("ok!");
	}
}
