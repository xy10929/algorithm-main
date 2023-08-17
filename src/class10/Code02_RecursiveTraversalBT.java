package class10;

import java.util.ArrayList;
import java.util.List;

//lc 144 94 145
public class Code02_RecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static void f(Node head) {
		if (head == null) {
			return;
		}
		// 1
		f(head.left);
		// 2
		f(head.right);
		// 3
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	List<Integer> ans = new ArrayList<>();

	public List<Integer> preorderTraversal(TreeNode root) {
		if (root == null) {
			return ans;
		}
		ans.add(root.val);
		preorderTraversal(root.left);
		preorderTraversal(root.right);
		return ans;
	}

	public List<Integer> inorderTraversal(TreeNode root) {
		if (root == null) {
			return ans;
		}
		inorderTraversal(root.left);
		ans.add(root.val);
		inorderTraversal(root.right);
		return ans;
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		if (root == null) {
			return ans;
		}
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		ans.add(root.val);
		return ans;
	}

	// 先序打印所有节点
	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		System.out.println(head.value);
		pre(head.left);
		pre(head.right);
	}

	public static void in(Node head) {
		if (head == null) {
			return;
		}
		in(head.left);
		System.out.println(head.value);
		in(head.right);
	}

	public static void pos(Node head) {
		if (head == null) {
			return;
		}
		pos(head.left);
		pos(head.right);
		System.out.println(head.value);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos(head);
		System.out.println("========");

	}

}