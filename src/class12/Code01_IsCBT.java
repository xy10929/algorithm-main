package class12;
//lc958
import java.util.LinkedList;

public class Code01_IsCBT {

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

	public boolean isCompleteTree(TreeNode root) {
		return process(root).isComplete;
	}

	public class info {
		public boolean isFull;
		public boolean isComplete;
		public int height;

		public info(boolean isFull, boolean isComplete, int height) {
			this.isFull = isFull;
			this.isComplete = isComplete;
			this.height = height;
		}
	}

	public info process(TreeNode root) {
		if (root == null) {
			return new info(true, true, 0);
		}
		info leftInfo = process(root.left);
		info rightInfo = process(root.right);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		if (isFull) {
			return new info(true, true, height);
		}
		boolean isComplete = false;
		if (leftInfo.isFull && rightInfo.isComplete && leftInfo.height == rightInfo.height) {// 左满右完全 高度相同
			isComplete = true;
		}
		if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {// 左满右满 左比右高1
			isComplete = true;
		}
		if (leftInfo.isComplete && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {// 左完全右满 左比右高1
			isComplete = true;
		}
		return new info(false, isComplete, height);
	}

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isCBT1(Node head) {
		if (head == null) {
			return true;
		}
		LinkedList<Node> queue = new LinkedList<>();
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if (
			// 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
			(leaf && (l != null || r != null)) || (l == null && r != null)

			) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			if (l == null || r == null) {
				leaf = true;
			}
		}
		return true;
	}

//	public static boolean isCBT2(Node head) {
//		if (head == null) {
//			return true;
//		}
//		return process(head).isCBT;
//	}

//	// 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
//	public static class Info {
//		public boolean isFull;
//		public boolean isCBT;
//		public int height;
//
//		public Info(boolean full, boolean cbt, int h) {
//			isFull = full;
//			isCBT = cbt;
//			height = h;
//		}
//	}
//
//	public static Info process(Node X) {
//		if (X == null) {
//			return new Info(true, true, 0);
//		}
//		Info leftInfo = process(X.left);
//		Info rightInfo = process(X.right);
//
//		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
//
//		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
//
//		boolean isCBT = false;
//		if (isFull) {
//			isCBT = true;
//		} else { // 以x为头整棵树，不满
//			if (leftInfo.isCBT && rightInfo.isCBT) {
//				if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
//					isCBT = true;
//				}
//				if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
//					isCBT = true;
//				}
//				if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
//					isCBT = true;
//				}
//
//			}
//		}
//		return new Info(isFull, isCBT, height);
//	}
//-978

}