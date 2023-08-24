package class17;

//lc78
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code03_PrintAllSubsquences {

	public List<List<Integer>> subsets(int[] arr) {
		List<List<Integer>> ans = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		process(arr, 0, path, ans);
		return ans;
	}

	public void process(int[] arr, int i, List<Integer> path, List<List<Integer>> ans) {
		if (i == arr.length) {// 已对所有位置要、不要做出了决定 收集该种情况的结果
			ans.add(new ArrayList<>(path));// 生成相同的path加入结果
			return;
		}
		process(arr, i + 1, path, ans);// 不要当前位置
		// 按照深度优先 最先返回全不要的结果 然后从后向前地对每个位置做要/不要的决定
		path.add(arr[i]);
		process(arr, i + 1, path, ans);// 要当前位置
		path.remove(path.size() - 1);// 即将返回上游(前面某个位置)做(要的)决定 作为该位置后面的位置 应恢复成最初不要的状态
	}

	// s -> "abc" ->
	public static List<String> subs(String s) {
		char[] str = s.toCharArray();
		String path = "";
		List<String> ans = new ArrayList<>();
		process1(str, 0, ans, path);
		return ans;
	}

	// str 固定参数
	// 来到了str[index]字符，index是位置
	// str[0..index-1]已经走过了！之前的决定，都在path上
	// 之前的决定已经不能改变了，就是path
	// str[index....]还能决定，之前已经确定，而后面还能自由选择的话，
	// 把所有生成的子序列，放入到ans里去
	public static void process1(char[] str, int index, List<String> ans, String path) {
		if (index == str.length) {
			ans.add(path);
			return;
		}
		// 没有要index位置的字符
		process1(str, index + 1, ans, path);
		// 要了index位置的字符
		process1(str, index + 1, ans, path + String.valueOf(str[index]));
	}

	public static List<String> subsNoRepeat(String s) {
		char[] str = s.toCharArray();
		String path = "";
		HashSet<String> set = new HashSet<>();
		process2(str, 0, set, path);
		List<String> ans = new ArrayList<>();
		for (String cur : set) {
			ans.add(cur);
		}
		return ans;
	}

	public static void process2(char[] str, int index, HashSet<String> set, String path) {
		if (index == str.length) {
			set.add(path);
			return;
		}
		String no = path;
		process2(str, index + 1, set, no);
		String yes = path + String.valueOf(str[index]);
		process2(str, index + 1, set, yes);
	}

	public static void main(String[] args) {
		String test = "acccc";
		List<String> ans1 = subs(test);
		List<String> ans2 = subsNoRepeat(test);

		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=================");
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=================");

	}

}