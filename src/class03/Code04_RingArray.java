package class03;

//lc622
public class Code04_RingArray {

	class MyCircularQueue {

		private int[] arr;
		private int begin;
		private int end;
		private int size;
		private final int limit;

		public MyCircularQueue(int limit) {
			arr = new int[limit];
			begin = 0;// 队列首位置
			end = 0;// 加元素时要放入的位置
			size = 0;// 队列元素数量
			this.limit = limit;
		}

		public boolean enQueue(int value) {
			if (size == limit) {
				return false;
			}
			size++;
			arr[end] = value;
			end = end == limit - 1 ? 0 : end + 1;
			return true;
		}

		public boolean deQueue() {
			if (size == 0) {
				return false;
			}
			size--;
			begin = begin == limit - 1 ? 0 : begin + 1;
			return true;
		}

		public int Front() {
			if (size == 0) {
				return -1;
			}
			return arr[begin];
		}

		public int Rear() {
			if (size == 0) {
				return -1;
			}
			return end == 0 ? arr[limit - 1] : arr[end - 1];
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}
	}

	public static class MyQueue {
		private int[] arr;
		private int pushi;// end
		private int polli;// begin
		private int size;
		private final int limit;

		public MyQueue(int limit) {
			arr = new int[limit];
			pushi = 0;
			polli = 0;
			size = 0;
			this.limit = limit;
		}

		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("队列满了，不能再加了");
			}
			size++;
			arr[pushi] = value;
			pushi = nextIndex(pushi);
		}

		public int pop() {
			if (size == 0) {
				throw new RuntimeException("队列空了，不能再拿了");
			}
			size--;
			int ans = arr[polli];
			polli = nextIndex(polli);
			return ans;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		// 如果现在的下标是i，返回下一个位置
		private int nextIndex(int i) {
			return i < limit - 1 ? i + 1 : 0;
		}

	}

}