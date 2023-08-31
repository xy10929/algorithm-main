package class22;

//lc322
public class Code02_MinCoinsNoLimit {

	public int coinChange(int[] arr, int sum) {
		int n = arr.length;
		int[][] dp = new int[n + 1][sum + 1];
		for (int j = 1; j <= sum; j++) {// base case
			dp[n][j] = Integer.MAX_VALUE;
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j <= sum; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j >= arr[i] && dp[i][j - arr[i]] != Integer.MAX_VALUE) {// 防止1+左arr[i]溢出
					dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j - arr[i]]);
				}
			}
		}
		return dp[0][sum] == Integer.MAX_VALUE ? -1 : dp[0][sum];
	}

	public int coinChange1(int[] coins, int amount) {
		int ans = process(coins, 0, amount);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public int process(int[] arr, int i, int sum) {
		if (i == arr.length) {// 到达末尾时 只有sum为0才有效
			return sum == 0 ? 0 : Integer.MAX_VALUE;// 无效结果也设为Integer.MAX_VALUE
		}
		int ans = Integer.MAX_VALUE;// 确保在与有效结果求min时落败
		for (int num = 0; num * arr[i] <= sum; num++) {// 当前位置可能要也可能不要 所以从num从0开始尝试
			int n = process(arr, i + 1, sum - num * arr[i]);
			if (n != Integer.MAX_VALUE) {// 当前num有效
				ans = Math.min(ans, num + n);
			}
		}
		return ans;
	}

//	public static int minCoins(int[] arr, int aim) {
//		return process(arr, 0, aim);
//	}
//
//	// arr[index...]面值，每种面值张数自由选择，
//	// 搞出rest正好这么多钱，返回最小张数
//	// 拿Integer.MAX_VALUE标记怎么都搞定不了
//	public static int process(int[] arr, int index, int rest) {
//		if (index == arr.length) {
//			return rest == 0 ? 0 : Integer.MAX_VALUE;
//		} else {
//			int ans = Integer.MAX_VALUE;
//			for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
//				int next = process(arr, index + 1, rest - zhang * arr[index]);
//				if (next != Integer.MAX_VALUE) {
//					ans = Math.min(ans, zhang + next);
//				}
//			}
//			return ans;
//		}
//	}
//
//	public static int dp1(int[] arr, int aim) {
//		if (aim == 0) {
//			return 0;
//		}
//		int N = arr.length;
//		int[][] dp = new int[N + 1][aim + 1];
//		dp[N][0] = 0;
//		for (int j = 1; j <= aim; j++) {
//			dp[N][j] = Integer.MAX_VALUE;
//		}
//		for (int index = N - 1; index >= 0; index--) {
//			for (int rest = 0; rest <= aim; rest++) {
//				int ans = Integer.MAX_VALUE;
//				for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
//					int next = dp[index + 1][rest - zhang * arr[index]];
//					if (next != Integer.MAX_VALUE) {
//						ans = Math.min(ans, zhang + next);
//					}
//				}
//				dp[index][rest] = ans;
//			}
//		}
//		return dp[0][aim];
//	}
//
//	public static int dp2(int[] arr, int aim) {
//		if (aim == 0) {
//			return 0;
//		}
//		int N = arr.length;
//		int[][] dp = new int[N + 1][aim + 1];
//		dp[N][0] = 0;
//		for (int j = 1; j <= aim; j++) {
//			dp[N][j] = Integer.MAX_VALUE;
//		}
//		for (int index = N - 1; index >= 0; index--) {
//			for (int rest = 0; rest <= aim; rest++) {
//				dp[index][rest] = dp[index + 1][rest];
//				if (rest - arr[index] >= 0 
//						&& dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
//					dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
//				}
//			}
//		}
//		return dp[0][aim];
//	}
//
//	// 为了测试
//	public static int[] randomArray(int maxLen, int maxValue) {
//		int N = (int) (Math.random() * maxLen);
//		int[] arr = new int[N];
//		boolean[] has = new boolean[maxValue + 1];
//		for (int i = 0; i < N; i++) {
//			do {
//				arr[i] = (int) (Math.random() * maxValue) + 1;
//			} while (has[arr[i]]);
//			has[arr[i]] = true;
//		}
//		return arr;
//	}
//
//	// 为了测试
//	public static void printArray(int[] arr) {
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}
//		System.out.println();
//	}
//
//	// 为了测试
//	public static void main(String[] args) {
//		int maxLen = 20;
//		int maxValue = 30;
//		int testTime = 300000;
//		System.out.println("功能测试开始");
//		for (int i = 0; i < testTime; i++) {
//			int N = (int) (Math.random() * maxLen);
//			int[] arr = randomArray(N, maxValue);
//			int aim = (int) (Math.random() * maxValue);
//			int ans1 = minCoins(arr, aim);
//			int ans2 = dp1(arr, aim);
//			int ans3 = dp2(arr, aim);
//			if (ans1 != ans2 || ans1 != ans3) {
//				System.out.println("Oops!");
//				printArray(arr);
//				System.out.println(aim);
//				System.out.println(ans1);
//				System.out.println(ans2);
//				break;
//			}
//		}
//		System.out.println("功能测试结束");
//	}

}