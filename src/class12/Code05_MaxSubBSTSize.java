package class12;

//lc333
import java.util.ArrayList;

// 在线测试链接 : https://leetcode.com/problems/largest-bst-subtree
public class Code05_MaxSubBSTSize {

	// 提交时不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int value) {
			val = value;
		}
	}

	public int largestBSTSubtree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return process(root).BSTSub;
	}

	public class info {
		public int BSTSub;// 最大搜索子树的size
		public int min;
		public int max;
		public int size;

		public info(int BSTSub, int min, int max, int size) {
			this.BSTSub = BSTSub;
			this.min = min;
			this.max = max;
			this.size = size;
		}
	}

	public info process(TreeNode root) {
		if (root == null) {
			return null;
		}
		info leftInfo = process(root.left);
		info rightInfo = process(root.right);
		int n1 = -1;
		int n2 = -1;// 左树和右树的最大搜索子树的 size
		int n3 = 1;// root为头的树是BST,则BSTSub为整棵树的size
		int min = root.val;
		int max = root.val;
		int size = 1;
		if (leftInfo != null) {
			n1 = leftInfo.BSTSub;
			max = Math.max(max, leftInfo.max);
			min = Math.min(min, leftInfo.min);
			size += leftInfo.size;
		}
		if (rightInfo != null) {
			n2 = rightInfo.BSTSub;
			max = Math.max(max, rightInfo.max);
			min = Math.min(min, rightInfo.min);
			size += rightInfo.size;
		}
		//整棵树为BST的条件: 左右树均为BST且左max < root.val < 右min
		if ((leftInfo == null || (leftInfo.BSTSub == leftInfo.size && leftInfo.max < root.val))
				&& (rightInfo == null || (rightInfo.BSTSub == rightInfo.size && rightInfo.min > root.val))) {
			n3 = size;
		}
		int BSTSub = Math.max(n3, Math.max(n1, n2));
		return new info(BSTSub, min, max, size);
	}

//	// 提交如下的largestBSTSubtree方法，可以直接通过
//	public static int largestBSTSubtree(TreeNode head) {
//		if (head == null) {
//			return 0;
//		}
//		return process(head).maxBSTSubtreeSize;
//	}
//
//	public static class Info {
//		public int maxBSTSubtreeSize;
//		public int allSize;
//		public int max;
//		public int min;
//
//		public Info(int m, int a, int ma, int mi) {
//			maxBSTSubtreeSize = m;
//			allSize = a;
//			max = ma;
//			min = mi;
//		}
//	}
//
//	public static Info process(TreeNode x) {
//		if (x == null) {
//			return null;
//		}
//		Info leftInfo = process(x.left);
//		Info rightInfo = process(x.right);
//		int max = x.val;
//		int min = x.val;
//		int allSize = 1;
//		if (leftInfo != null) {
//			max = Math.max(leftInfo.max, max);
//			min = Math.min(leftInfo.min, min);
//			allSize += leftInfo.allSize;
//		}
//		if (rightInfo != null) {
//			max = Math.max(rightInfo.max, max);
//			min = Math.min(rightInfo.min, min);
//			allSize += rightInfo.allSize;
//		}
//		int p1 = -1;
//		if (leftInfo != null) {
//			p1 = leftInfo.maxBSTSubtreeSize;
//		}
//		int p2 = -1;
//		if (rightInfo != null) {
//			p2 = rightInfo.maxBSTSubtreeSize;
//		}
//		int p3 = -1;
//		boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
//		boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
//		if (leftBST && rightBST) {
//			boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
//			boolean rightMinMoreX = rightInfo == null ? true : (x.val < rightInfo.min);
//			if (leftMaxLessX && rightMinMoreX) {
//				int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
//				int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
//				p3 = leftSize + rightSize + 1;
//			}
//		}
//		return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
//	}

	// 为了验证
	// 对数器方法
	public static int right(TreeNode head) {
		if (head == null) {
			return 0;
		}
		int h = getBSTSize(head);
		if (h != 0) {
			return h;
		}
		return Math.max(right(head.left), right(head.right));
	}

	// 为了验证
	// 对数器方法
	public static int getBSTSize(TreeNode head) {
		if (head == null) {
			return 0;
		}
		ArrayList<TreeNode> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).val <= arr.get(i - 1).val) {
				return 0;
			}
		}
		return arr.size();
	}

	// 为了验证
	// 对数器方法
	public static void in(TreeNode head, ArrayList<TreeNode> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	// 为了验证
	// 对数器方法
	public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// 为了验证
	// 对数器方法
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		TreeNode head = new TreeNode((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	// 为了验证
	// 对数器方法
//	public static void main(String[] args) {
//		int maxLevel = 4;
//		int maxValue = 100;
//		int testTimes = 1000000;
//		System.out.println("测试开始");
//		for (int i = 0; i < testTimes; i++) {
//			TreeNode head = generateRandomBST(maxLevel, maxValue);
//			if (largestBSTSubtree(head) != right(head)) {
//				System.out.println("出错了！");
//			}
//		}
//		System.out.println("测试结束");
//	}

}