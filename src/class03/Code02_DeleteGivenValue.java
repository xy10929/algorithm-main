package class03;
//lc203
public class Code02_DeleteGivenValue {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value =   data;
		}
	}

	// head = removeValue(head, 2);
	public static Node removeValue(Node head, int num) {
		// head来到第一个不需要删的位置
		while (head != null) {
			if (head.value != num) {
				break;
			}
			head = head.next;
		}
		// 1 ) head == null
		// 2 ) head != null
		if (head == null) {
			return null;
		}
		Node pre = head;
		Node cur = head.next;
		while (cur != null) {
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				pre = pre.next;
			}
			cur = cur.next;
		}
		return head;
	}

}