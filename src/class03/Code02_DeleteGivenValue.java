package class03;

//lc203
public class Code02_DeleteGivenValue {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

	public ListNode removeElements(ListNode head, int num) {
		while (head != null) {
			if (head.val == num) {
				head = head.next;
			} else {
				break;//找到要返回的头结点
			}
		}//head可能为空 即链表所有节点都要删去
		ListNode pre = head;//已处理完成的部分的结尾
		ListNode cur = head;//当前到达的节点
		while (cur != null) {
			if (cur.val == num) {
				pre.next = cur.next;
			} else {
				pre = cur;
			}
			cur = cur.next;
		}
		return head;
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