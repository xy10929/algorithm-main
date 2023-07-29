package class01;
//leetcode704

public class Code04_BSExist {

	public int search(int[] arr, int target) {
		int start = 0;
		int end = arr.length - 1;
		while (start <= end) {// 范围上有数
			int mid = (start + end) / 2;
			if (arr[mid] == target) {
				return mid;
			}
			if (target < arr[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

//	// for test
//	public static boolean test(int[] sortedArr, int num) {
//		for(int cur : sortedArr) {
//			if(cur == num) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	
//	// for test
//	public static int[] generateRandomArray(int maxSize, int maxValue) {
//		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
//		}
//		return arr;
//	}
//	
//	public static void main(String[] args) {
//		int testTime = 500000;
//		int maxSize = 10;
//		int maxValue = 100;
//		boolean succeed = true;
//		for (int i = 0; i < testTime; i++) {
//			int[] arr = generateRandomArray(maxSize, maxValue);
//			Arrays.sort(arr);
//			int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
//			if (test(arr, value) != exist(arr, value)) {
//				succeed = false;
//				break;
//			}
//		}
//		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
//	}

}