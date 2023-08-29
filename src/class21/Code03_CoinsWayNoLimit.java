package class21;

//lc518
public class Code03_CoinsWayNoLimit {

	public int change(int amount, int[] coins) {
		int n = coins.length;
		int[][] dp = new int[n + 1][amount + 1];
		dp[n][0] = 1;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j <= amount; j++) {
				dp[i][j] = dp[i + 1][j];
				if (j >= coins[i]) {
					dp[i][j] += dp[i][j - coins[i]];
				}
			}
		}
		return dp[0][amount];
	}

	public int change1(int amount, int[] coins) {
		return process(coins, 0, amount);
	}

	public int process(int[] arr, int i, int sum) {
		if (i == arr.length) {// base case 没有硬币可以选择了 判断剩下要组成的总数是否为0
			return sum == 0 ? 1 : 0;
		}
		int ans = 0;
		for (int num = 0; arr[i] * num <= sum; num++) {// 当前面额arr[i]用num个
			ans += process(arr, i + 1, sum - arr[i] * num);
		}
		return ans;
	}

//	public static int coinsWay(int[] arr, int aim) {
//		if (arr == null || arr.length == 0 || aim < 0) {
//			return 0;
//		}
//		return process(arr, 0, aim);
//	}
//
//	// arr[index....] 所有的面值，每一个面值都可以任意选择张数，组成正好rest这么多钱，方法数多少？
//	public static int process(int[] arr, int index, int rest) {
//		if (index == arr.length) { // 没钱了
//			return rest == 0 ? 1 : 0;
//		}
//		int ways = 0;
//		for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
//			ways += process(arr, index + 1, rest - (zhang * arr[index]));
//		}
//		return ways;
//	}

	public static int dp1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				int ways = 0;
				for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
					ways += dp[index + 1][rest - (zhang * arr[index])];
				}
				dp[index][rest] = ways;
			}
		}
		return dp[0][aim];
	}

	public static int dp2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest - arr[index] >= 0) {
					dp[index][rest] += dp[index][rest - arr[index]];
				}
			}
		}
		return dp[0][aim];
	}

	// 为了测试
	public static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		boolean[] has = new boolean[maxValue + 1];
		for (int i = 0; i < N; i++) {
			do {
				arr[i] = (int) (Math.random() * maxValue) + 1;
			} while (has[arr[i]]);
			has[arr[i]] = true;
		}
		return arr;
	}

	// 为了测试
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 为了测试
	public static void main(String[] args) {
		int maxLen = 10;
		int maxValue = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = coinsWay(arr, aim);
			int ans2 = dp1(arr, aim);
			int ans3 = dp2(arr, aim);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(ans3);
				break;
			}
		}
		System.out.println("测试结束");
	}

}