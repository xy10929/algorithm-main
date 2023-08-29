package class21;

//lc64
public class Code01_MinPathSum {

	public int minPathSum(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int[][] dp = new int[n][m];
		dp[0][0] = grid[0][0];
		for (int j = 1; j < m; j++) {
			dp[0][j] = grid[0][j] + dp[0][j - 1];
		}
		for (int i = 1; i < n; i++) {
			dp[i][0] = grid[i][0] + dp[i - 1][0];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				dp[i][j] = grid[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
			}
		}
		return dp[n - 1][m - 1];
	}

	public int minPathSum11(int[][] grid) {
		return process(grid, grid.length - 1, grid[0].length - 1);
	}

	public int process(int[][] grid, int i, int j) {
		if (i == 0 && j == 0) {// base case
			return grid[0][0];
		}
		if (i == 0) {
			return grid[i][j] + process(grid, i, j - 1);
		}
		if (j == 0) {
			return grid[i][j] + process(grid, i - 1, j);
		}
		return grid[i][j] + Math.min(process(grid, i, j - 1), process(grid, i - 1, j));
	}

	public static int minPathSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[0][0] = m[0][0];
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + m[0][j];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		return dp[row - 1][col - 1];
	}

	public static int minPathSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[] dp = new int[col];
		dp[0] = m[0][0];
		for (int j = 1; j < col; j++) {
			dp[j] = dp[j - 1] + m[0][j];
		}
		for (int i = 1; i < row; i++) {
			dp[0] += m[i][0];
			for (int j = 1; j < col; j++) {
				dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
			}
		}
		return dp[col - 1];
	}

	// for test
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		if (rowSize < 0 || colSize < 0) {
			return null;
		}
		int[][] result = new int[rowSize][colSize];
		for (int i = 0; i != result.length; i++) {
			for (int j = 0; j != result[0].length; j++) {
				result[i][j] = (int) (Math.random() * 100);
			}
		}
		return result;
	}

	// for test
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int rowSize = 10;
		int colSize = 10;
		int[][] m = generateRandomMatrix(rowSize, colSize);
		System.out.println(minPathSum1(m));
		System.out.println(minPathSum2(m));

	}
}