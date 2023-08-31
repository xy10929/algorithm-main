package class21;
//lc576

//public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
//    int[][][] dp = new int[m][n][maxMove + 1];
//    for(int i = 0; i < m; i++){
//        for(int j = 0; j < n; j++){
//            for(int k = 0; k <= maxMove; k++){
//                dp[i][j][k] = -1;
//            }
//        }
//    }
//    return f(m, n, maxMove, startRow, startColumn, dp);
//}
//
//public int f(int m, int n, int step, int row, int col, int[][][] dp){
//    if(row < 0 || row == m || col < 0 || col == n){
//        return 1;
//    }
//    if(dp[row][col][step] != -1){
//        return dp[row][col][step];
//    }
//    long ans = 0;
//    int mod = 1000000007;
//    if(step == 0){
//        
//    }else{
//        ans = (ans + f(m, n, step - 1, row + 1, col, dp)) % mod;
//        ans = (ans + f(m, n, step - 1, row - 1, col, dp)) % mod;
//        ans = (ans + f(m, n, step - 1, row, col + 1, dp)) % mod;
//        ans = (ans + f(m, n, step - 1, row, col - 1, dp)) % mod;
//    }
//    dp[row][col][step] = (int)ans;
//    return (int)ans;
//}

public class Code05_BobDie {

	public int findPaths(int n, int m, int maxMove, int startRow, int startColumn) {
		long[][][] dp = new long[n][m][maxMove + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k <= maxMove; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		return (int) process(n, m, startRow, startColumn, maxMove, dp);
	}

	public long process(int n, int m, int row, int col, int k, long[][][] dp) {
		if (row < 0 || row == n || col < 0 || col == m) {
			return 1;
		}

		if (dp[row][col][k] != -1) {
			return dp[row][col][k];
		}
		long ans = 0;
		if (k != 0) {
			int mod = 1000000007;
			long up = process(n, m, row + 1, col, k - 1, dp);
			long down = process(n, m, row - 1, col, k - 1, dp);
			long left = process(n, m, row, col - 1, k - 1, dp);
			long right = process(n, m, row, col + 1, k - 1, dp);
			ans = (up + down + left + right) % mod;
		}
		dp[row][col][k] = ans;
		return ans;
	}

	public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
		return (int) process(m, n, startRow, startColumn, maxMove);
	}

	public long process(int n, int m, int row, int col, int k) {
		if (row < 0 || row == n || col < 0 || col == m) {// 越界 形成有效path
			return 1;
		}
		if (k == 0) {// 未越界
			return 0;
		}
		int mod = 1000000007;
		long up = process(n, m, row + 1, col, k - 1);
		long down = process(n, m, row - 1, col, k - 1);
		long left = process(n, m, row, col - 1, k - 1);
		long right = process(n, m, row, col + 1, k - 1);
		return (up + down + left + right) % mod;
	}

//	public static double livePosibility1(int row, int col, int k, int N, int M) {
//		return (double) process(row, col, k, N, M) / Math.pow(4, k);
//	}
//
//	// 目前在row，col位置，还有rest步要走，走完了如果还在棋盘中就获得1个生存点，返回总的生存点数
//	public static long process(int row, int col, int rest, int N, int M) {
//		if (row < 0 || row == N || col < 0 || col == M) {
//			return 0;
//		}
//		// 还在棋盘中！
//		if (rest == 0) {
//			return 1;
//		}
//		// 还在棋盘中！还有步数要走
//		long up = process(row - 1, col, rest - 1, N, M);
//		long down = process(row + 1, col, rest - 1, N, M);
//		long left = process(row, col - 1, rest - 1, N, M);
//		long right = process(row, col + 1, rest - 1, N, M);
//		return up + down + left + right;
//	}
//
//	public static double livePosibility2(int row, int col, int k, int N, int M) {
//		long[][][] dp = new long[N][M][k + 1];
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				dp[i][j][0] = 1;
//			}
//		}
//		for (int rest = 1; rest <= k; rest++) {
//			for (int r = 0; r < N; r++) {
//				for (int c = 0; c < M; c++) {
//					dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
//					dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
//					dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
//					dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
//				}
//			}
//		}
//		return (double) dp[row][col][k] / Math.pow(4, k);
//	}
//
//	public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
//		if (r < 0 || r == N || c < 0 || c == M) {
//			return 0;
//		}
//		return dp[r][c][rest];
//	}
//
//	public static void main(String[] args) {
//		System.out.println(livePosibility1(6, 6, 10, 50, 50));
//		System.out.println(livePosibility2(6, 6, 10, 50, 50));
//	}

}