package class03;

//LC232
import java.util.Stack;

public class Code06_TwoStacksImplementQueue {

	class MyQueue {

		private Stack<Integer> push;
		private Stack<Integer> pop;

		public MyQueue() {
			push = new Stack<Integer>();
			pop = new Stack<Integer>();
		}

		public void pushData() {// push栈向pop栈倒倒数据
			if (pop.isEmpty()) {// pop栈为空时才能倒数据
				while (!push.isEmpty()) {// 需要把push栈的数据全部倒出
					pop.push(push.pop());
				}
			}
		}

		// 进行任何操作前先倒数据

		public void push(int x) {
			pushData();
			push.push(x);
		}

		public int pop() {
			pushData();
			return pop.pop();
		}

		public int peek() {
			pushData();
			return pop.peek();
		}

		public boolean empty() {
			pushData();
			return pop.isEmpty();
		}
	}

	public static class TwoStacksQueue {
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		// push栈向pop栈倒入数据
		private void pushToPop() {
			if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
		}

		public void add(int pushInt) {
			stackPush.push(pushInt);
			pushToPop();
		}

		public int poll() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			pushToPop();
			return stackPop.pop();
		}

		public int peek() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			pushToPop();
			return stackPop.peek();
		}
	}

	public static void main(String[] args) {
		TwoStacksQueue test = new TwoStacksQueue();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
	}

}