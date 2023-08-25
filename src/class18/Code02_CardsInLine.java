package class18;

//lc486
public class Code02_CardsInLine {

//	public boolean predictTheWinner(int[] arr) {
//		int first = f(arr, 0, arr.length - 1);
//		int second = s(arr, 0, arr.length - 1);
//		return first >= second;
//	}
//
//	public int f(int[] arr, int L, int R) {// 先手
//		if (L == R) {
//			return arr[L];
//		}
//		int left = arr[L] + s(arr, L + 1, R);// 自己先手选左 则后续等价于自己在L+1到R后手
//		int right = arr[R] + s(arr, L, R - 1);
//		return Math.max(left, right);
//	}
//
//	public int s(int[] arr, int L, int R) {// 后手
//		if (L == R) {
//			return 0;
//		}
//		int left = f(arr, L + 1, R);// 对手先手选左 等价于自己在L+1到R先手
//		int right = f(arr, L, R - 1);// 对手先手选右 等价于自己在L到R-1后手
//		return Math.min(left, right);
//	}

	public boolean predictTheWinner(int[] arr) {
		int n = arr.length;
		int[][] fMap = new int[n][n];// L和R的范围均为0到n-1
		int[][] sMap = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				fMap[i][j] = -1;
				sMap[i][j] = -1;
			}
		}
		int first = f(arr, 0, arr.length - 1, fMap, sMap);
		int second = s(arr, 0, arr.length - 1, fMap, sMap);
		return first >= second;
	}

	public int f(int[] arr, int L, int R, int[][] fMap, int[][] sMap) {
		if (fMap[L][R] != -1) {
			return fMap[L][R];
		}
		int ans = 0;
		if (L == R) {
			ans = arr[L];
		} else {
			int left = arr[L] + s(arr, L + 1, R, fMap, sMap);
			int right = arr[R] + s(arr, L, R - 1, fMap, sMap);
			ans = Math.max(left, right);
		}
		fMap[L][R] = ans;
		return ans;
	}

	public int s(int[] arr, int L, int R, int[][] fMap, int[][] sMap) {
		if (sMap[L][R] != -1) {
			return sMap[L][R];
		}
		int ans = 0;
		if (L != R) {
			int left = f(arr, L + 1, R, fMap, sMap);
			int right = f(arr, L, R - 1, fMap, sMap);
			ans = Math.min(left, right);
		}
		sMap[L][R] = ans;
		return ans;
	}

	// 根据规则，返回获胜者的分数
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int first = f1(arr, 0, arr.length - 1);
		int second = g1(arr, 0, arr.length - 1);
		return Math.max(first, second);
	}

	// arr[L..R]，先手获得的最好分数返回
	public static int f1(int[] arr, int L, int R) {
		if (L == R) {
			return arr[L];
		}
		int p1 = arr[L] + g1(arr, L + 1, R);
		int p2 = arr[R] + g1(arr, L, R - 1);
		return Math.max(p1, p2);
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g1(int[] arr, int L, int R) {
		if (L == R) {
			return 0;
		}
		int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
		int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
		return Math.min(p1, p2);
	}

	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				fmap[i][j] = -1;
				gmap[i][j] = -1;
			}
		}
		int first = f2(arr, 0, arr.length - 1, fmap, gmap);
		int second = g2(arr, 0, arr.length - 1, fmap, gmap);
		return Math.max(first, second);
	}

	// arr[L..R]，先手获得的最好分数返回
	public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		if (fmap[L][R] != -1) {
			return fmap[L][R];
		}
		int ans = 0;
		if (L == R) {
			ans = arr[L];
		} else {
			int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
			int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
			ans = Math.max(p1, p2);
		}
		fmap[L][R] = ans;
		return ans;
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		if (gmap[L][R] != -1) {
			return gmap[L][R];
		}
		int ans = 0;
		if (L != R) {
			int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
			int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
			ans = Math.min(p1, p2);
		}
		gmap[L][R] = ans;
		return ans;
	}

	public static int win3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			fmap[i][i] = arr[i];
		}
		for (int startCol = 1; startCol < N; startCol++) {
			int L = 0;
			int R = startCol;
			while (R < N) {
				fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
				gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
				L++;
				R++;
			}
		}
		return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
	}

	public static void main(String[] args) {
		int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
		System.out.println(win1(arr));
		System.out.println(win2(arr));
		System.out.println(win3(arr));

	}

}