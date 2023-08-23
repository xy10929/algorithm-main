package class14;

//lc502
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_IPO {

	public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
		PriorityQueue<Program> minCost = new PriorityQueue<>(new minCostComparator());// cost小跟堆
		PriorityQueue<Program> maxProfit = new PriorityQueue<>(new maxProfitComparator());// profit大根堆
		for (int i = 0; i < profits.length; i++) {// 所有项目先放进小跟堆
			minCost.add(new Program(capital[i], profits[i]));
		}
		for (int i = 0; i < k; i++) {// 最多做k个项目
			while (!minCost.isEmpty() && minCost.peek().cost <= w) {// 把当前能做的所有项目放进profit大根堆
				maxProfit.add(minCost.poll());
			}
			if (maxProfit.isEmpty()) {// 还未做够k个项目就已经不能做了
				return w;
			}
			w += maxProfit.poll().profit;// 更新资金w
		}
		return w;
	}

	public class Program {
		public int cost;
		public int profit;

		public Program(int cost, int profit) {
			this.cost = cost;
			this.profit = profit;
		}
	}

	public class minCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.cost - o2.cost;
		}
	}

	public class maxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.profit - o1.profit;
		}
	}
}

	// 最多K个项目
	// W是初始资金
	// Profits[] Capital[] 一定等长
	// 返回最终最大的资金
//	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
//		PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
//		PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
//		for (int i = 0; i < Profits.length; i++) {
//			minCostQ.add(new Program(Profits[i], Capital[i]));
//		}
//		for (int i = 0; i < K; i++) {
//			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
//				maxProfitQ.add(minCostQ.poll());
//			}
//			if (maxProfitQ.isEmpty()) {
//				return W;
//			}
//			W += maxProfitQ.poll().p;
//		}
//		return W;
//	}
//
//	public static class Program {
//		public int p;
//		public int c;
//
//		public Program(int p, int c) {
//			this.p = p;
//			this.c = c;
//		}
//	}
//
//	public static class MinCostComparator implements Comparator<Program> {
//
//		@Override
//		public int compare(Program o1, Program o2) {
//			return o1.c - o2.c;
//		}
//
//	}
//
//	public static class MaxProfitComparator implements Comparator<Program> {
//
//		@Override
//		public int compare(Program o1, Program o2) {
//			return o2.p - o1.p;
//		}
//
//	}

