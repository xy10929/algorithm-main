package class40;
//lc54
public class Code05_PrintMatrixSpiralOrder {
//	public List<Integer> spiralOrder(int[][] matrix) {
//	List<Integer> ans = new ArrayList<>();
//	int n = matrix.length - 1;
//	int m = matrix[0].length - 1;
//	int a = 0;//每层(a,a)->(a,m)->(n,m)->(a+1,a)
//	while (a <= n && a <= m) {//相等时 只剩一行/一列
//		ans.addAll(process(matrix, a++, n--, m--));//下一层顶点位置为上一层各-1
//	}
//	return ans;
//}
//
//public List<Integer> process(int[][] matrix, int a, int n, int m) {
//	List<Integer> ans = new ArrayList<>();
//	if (a == n && a == m) {
//		ans.add(matrix[a][a]);
//	} else if (a == n) {//剩一行
//		while (a <= m) {//需要走完剩下所有位置
//			ans.add(matrix[n][a++]);
//		}
//	} else if (a == m) {//剩一列
//		while (a <= n) {//需要走完剩下所有位置
//			ans.add(matrix[a++][m]);
//		}
//	} else {
//		int i = a;
//		int j = a;
//		while (j < m) {//每行/列留一个位置给下一次转向后走
//			ans.add(matrix[a][j++]);
//		}
//		while (i < n) {
//			ans.add(matrix[i++][m]);
//		}
//		while (j > a) {
//			ans.add(matrix[n][j--]);
//		}
//		while (i > a) {
//			ans.add(matrix[i--][a]);
//		}
//	}
//	return ans;
//}
	public static void spiralOrderPrint(int[][] matrix) {
		int tR = 0;
		int tC = 0;
		int dR = matrix.length - 1;
		int dC = matrix[0].length - 1;
		while (tR <= dR && tC <= dC) {
			printEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}

	public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
		if (tR == dR) {
			for (int i = tC; i <= dC; i++) {
				System.out.print(m[tR][i] + " ");
			}
		} else if (tC == dC) {
			for (int i = tR; i <= dR; i++) {
				System.out.print(m[i][tC] + " ");
			}
		} else {
			int curC = tC;
			int curR = tR;
			while (curC != dC) {
				System.out.print(m[tR][curC] + " ");
				curC++;
			}
			while (curR != dR) {
				System.out.print(m[curR][dC] + " ");
				curR++;
			}
			while (curC != tC) {
				System.out.print(m[dR][curC] + " ");
				curC--;
			}
			while (curR != tR) {
				System.out.print(m[curR][tC] + " ");
				curR--;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}

}