package class05;

//lc327
public class Code01_CountOfRangeSum {

	public int countRangeSum(int[] nums, int lower, int upper) {
		int n = nums.length;
		long[] sum = new long[n];// 前缀和数组
		sum[0] = nums[0];
		for (int i = 1; i < n; i++) {
			sum[i] = sum[i - 1] + nums[i];
		}
		return f(sum, 0, n - 1, lower, upper);
	}

	public int f(long[] sum, int start, int end, int lower, int upper) {
		if (start == end) {// base case 只传进来sum[]中的某一个数sum[x](数组0到x位置的累加和) 即判断0到x这个子数组是否达标
			return sum[start] >= lower && sum[start] <= upper ? 1 : 0;
		}
		int mid = (start + end) / 2;
		return f(sum, start, mid, lower, upper) + f(sum, mid + 1, end, lower, upper)
				+ merge(sum, start, mid, end, lower, upper);
	}
	
	//左组右组已经f计算出组内的结果 且组内已有序
	//对于右组每个数k 求左组有多少数在[k-upper,k-lower] 累加得到返回值
	public int merge(long[] arr, int start, int mid, int end, int lower, int upper) {
		int ans = 0;
		int L = start;
		int R = start;
		// 窗口为[L,R) 边界只会不回退地向右
		// 依次计算对于右组的每个数 左组有多少数在对应的范围
		for (int i = mid + 1; i <= end; i++) {
			long min = arr[i] - upper;
			long max = arr[i] - lower;
			while (L <= mid && arr[L] < min) {
				L++;
			}
			while (R <= mid && arr[R] <= max) {
				R++;
			}
			ans += R - L;
		}
		// merge sort
		long[] help = new long[end - start + 1];
		int i = 0;
		int p1 = start;
		int p2 = mid + 1;
		while (p1 <= mid && p2 <= end) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= mid) {
			help[i++] = arr[p1++];
		}
		while (p2 <= end) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[start + i] = help[i];
		}
		return ans;
	}
//
//	public static int countRangeSum(int[] nums, int lower, int upper) {
//		if (nums == null || nums.length == 0) {
//			return 0;
//		}
//		long[] sum = new long[nums.length];
//		sum[0] = nums[0];
//		for (int i = 1; i < nums.length; i++) {
//			sum[i] = sum[i - 1] + nums[i];
//		}
//		return process(sum, 0, sum.length - 1, lower, upper);
//	}
//
//	public static int process(long[] sum, int L, int R, int lower, int upper) {
//		if (L == R) {
//			return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
//		}
//		int M = L + ((R - L) >> 1);
//		return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
//				+ merge(sum, L, M, R, lower, upper);
//	}
//
//	public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
//		int ans = 0;
//		int windowL = L;
//		int windowR = L;
//		// [windowL, windowR)
//		for (int i = M + 1; i <= R; i++) {
//			long min = arr[i] - upper;
//			long max = arr[i] - lower;
//			while (windowR <= M && arr[windowR] <= max) {
//				windowR++;
//			}
//			while (windowL <= M && arr[windowL] < min) {
//				windowL++;
//			}
//			ans += windowR - windowL;
//		}
//		long[] help = new long[R - L + 1];
//		int i = 0;
//		int p1 = L;
//		int p2 = M + 1;
//		while (p1 <= M && p2 <= R) {
//			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
//		}
//		while (p1 <= M) {
//			help[i++] = arr[p1++];
//		}
//		while (p2 <= R) {
//			help[i++] = arr[p2++];
//		}
//		for (i = 0; i < help.length; i++) {
//			arr[L + i] = help[i];
//		}
//		return ans;
//	}

}