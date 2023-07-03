package class40;
//lc48
public class Code06_RotateMatrix {
//	public void rotate(int[][] matrix) {
//	int a = 0;
//	int b = matrix.length - 1;//4顶点为(a,a) (a,b) (b,b) (b,a)
//	while (a < b) {//还有内层可以收缩
//		process(matrix, a++, b--);//4顶点确定的一层旋转
//	}
//}
//
//public void process(int[][] m, int a, int b) {
//	int tmp = 0;
//	for (int i = 0; i < b - a; i++) {//每次4个点一组进行旋转 以4条边最上面一边的每个位置(除最后)作为分组的第一个位置
//		tmp = m[a][a + i];
//		m[a][a + i] = m[b - i][a];
//		m[b - i][a] = m[b][b - i];
//		m[b][b - i] = m[a + i][b];
//		m[a + i][b] = tmp;
//	}
//}
	public static void rotate(int[][] matrix) {
		int a = 0;
		int b = 0;
		int c = matrix.length - 1;
		int d = matrix[0].length - 1;
		while (a < c) {
			rotateEdge(matrix, a++, b++, c--, d--);
		}
	}

	public static void rotateEdge(int[][] m, int a, int b, int c, int d) {
		int tmp = 0;
		for (int i = 0; i < d - b; i++) {
			tmp = m[a][b + i];
			m[a][b + i] = m[c - i][b];
			m[c - i][b] = m[c][d - i];
			m[c][d - i] = m[a + i][d];
			m[a + i][d] = tmp;
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		printMatrix(matrix);
		rotate(matrix);
		System.out.println("=========");
		printMatrix(matrix);

	}

}