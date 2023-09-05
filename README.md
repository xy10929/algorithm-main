# algorithm-main

Leetcode problems in data stucture & algorithm course by [Chengyun Zuo](https://github.com/algorithmzuo/algorithmbasic2020)

- [class01](#class01)
  - [lc704 确定目标值在有序数组中的位置](#lc704)
  - [lc34 确定目标值在有序数组中的起始位置](#lc34)
  - [lc162 找到数组中一个局部最大值](#lc34)
- [class02](#class02)
  - [lc136 找到数组中唯一出现奇数次的数](#lc136)
  - [lc260 找到数组中唯二出现奇数次的数](#lc260)
  - [lc137 找到数组中唯一出现 k 次的数 其他数均出现 m 次(m>k)](#lc137)
- [class03](#class03)
  - [lc203 删除链表中的某个值](#lc203)
  - [lc622 实现循环队列](#lc622)
  - [lc155 实现可返回最小值的栈](#lc155)
  - [lc232 用两个栈实现队列](#lc232)
  - [lc225 用两个队列实现栈](#lc225)
- [class05](#class05)
  - [lc327 求数组中有多少子数组累加和在目标范围内](#lc327)
- [class09](#class09)
  - [lc876 求链表(下)中点](#lc876)
  - [lc234 判断链表是否回文](#lc234)
  - [lc138 复制有 random 指针的链表](#lc138)
- [class10](#class10)
  - [lc141 判断链表是否有环](#lc141)
  - [lc142 判断链表入环节点的位置](#lc142)
  - [lc160 判断两个无环链表是否相交 是则返回相交节点](#lc160)
  - [lc144 二叉树的先序遍历](#lc144)
  - [lc94 二叉树的中序遍历](#lc94)
  - [lc145 二叉树的后序遍历](#lc145)
- [class11](#class11)
  - [lc102 二叉树层序遍历](#lc102)
- [class12](#class12)
  - [lc958 判断是否为完全二叉树](#lc958)
  - [lc333 求二叉树的最大搜索二叉树子树的节点个数](#lc333)
  - [lc543 求二叉树节点间的最大距离](#lc543)
- [class13](#class13)
  - [lc236 寻找二叉树中两节点的最低公共祖先](#lc236)
- [class14](#class14)
  - [lc502 一些项目有各自的成本和利润 求 w 本金做最多 k 个项目后的最大资本](#lc502)
- [class15 并查集](#class15)
- [class17](#class17)
  - [lc78 返回一个数字数组的全部子序列(可以不连续)](#lc78)
  - [lc46 返回一个数字数组的所有全排列结果](#lc46)
- [class18](#class18)
  - [lc486 数组表示一些牌 两人依次从最左或最右拿并获取对应分数 预测胜者](#lc486)
- [class19](#class19)
  - [lc91 字符串中 1 至 26 可解码为 a 到 z, 求可能的解码结果数](#lc91)
- [class20](#class20)
  - [lc516 求字符串最长回文子序列长度](#lc516)
- [class21](#class21)
  - [lc64 求从矩阵左上开始 朝右或下走到右下的最小路径和](#lc64)
  - [lc518 给定硬币面值数组 每种硬币数量无限 求组成 aim 的方法数](#lc518)
  - [lc576 给定矩阵规模和初始位置 求最多 k 步后出界的 path 数](#lc576)
  - [lc322 给定硬币面值数组 每种硬币数量无限 求组成 aim 的最少硬币数](#lc322)
- [class23](#class23)
  - [lc52 N 皇后问题](#lc52)

## class01

### lc704

@确定目标值在有序数组中的位置

```java
public int search(int[] arr, int target) {
  int start = 0;
  int end = arr.length - 1;
  //while中包含了对首尾的判断
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
```

### lc34

@确定目标值在有序数组中的起始位置

即 找到某个数的最左位置&最右位置  
使用二分法时 如果某一步找到了目标位置 不直接返回 而是先记录该位置 然后向左/右方向继续二分 直至范围内没有数

```java
public int[] searchRange(int[] arr, int target) {
  if (arr.length == 0) {
    return new int[] { -1, -1 };
  }
  return new int[] { findLeft(arr, target), findRight(arr, target) };
}

public int findLeft(int[] arr, int target) {
  int start = 0;
  int end = arr.length - 1;
  int ans = -1;
  while (start <= end) {//二分 直至范围内没有数
    int mid = (start + end) / 2;
    if (arr[mid] == target) {
      ans = mid;//先记录位置
      end = mid - 1;//继续向左寻找
    } else if (target < arr[mid]) {
      end = mid - 1;
    } else {
      start = mid + 1;
    }
  }
  return ans;
}

public int findRight(int[] arr, int target) {
  int start = 0;
  int end = arr.length - 1;
  int ans = -1;
  while (start <= end) {
    int mid = (start + end) / 2;
    if (arr[mid] == target) {
      ans = mid;
      start = mid + 1;
    } else if (target < arr[mid]) {
      end = mid - 1;
    } else {
      start = mid + 1;
    }
  }
  return ans;
}
```

### lc162

@找到数组中一个局部最大值的 index

先检查首末情况  
如果未找到 则根据单调性 中间必存在局部最大值 利用二分法查找剩余的范围

```java
public int findPeakElement(int[] arr) {
  if (arr.length == 1) {
    return 0;
  }
  if (arr[0] > arr[1]) {//首
    return 0;
  }
  if (arr[arr.length - 1] > arr[arr.length - 2]) {//末
    return arr.length - 1;
  }
  int start = 1;
  int end = arr.length - 2;//对剩下的部分进行二分
  while (start <= end) {
    int mid = (start + end) / 2;
    if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
      return mid;
    } else if (arr[mid] < arr[mid - 1]) {
      end = mid - 1;
    } else {
      start = mid + 1;
    }
  }
  return -1;
}
```

## class02

### lc136

@找到数组中唯一出现奇数次的数

异或运算即无进位相加  
0 ^ N = N  
N ^ N = 0  
将 0 依次与所有数异或 则出现偶数次的数运算得到 0, 剩下唯一的出现奇数次的数

```java
public int singleNumber(int[] arr) {
  int ans = 0;
  for (int i = 0; i < arr.length; i++) {
    ans ^= arr[i];
  }
  return ans;
}
```

### lc1260

@找到数组中唯一出现奇数次的数

把所有数进行异或得到 eor, 即 a^b(不为 0)  
eor 不为 0 的位代表 a 和 b 在这个位上不同  
找到一个不为 0 的位--最右的 1

提取一个数最右侧的 1  
方法: a&(-a)

把数组中的数分为这个位上为 0 和 1 两类  
把任意一类数异或得到 a 或 b

```java
public int[] singleNumber2(int[] arr) {
  int eor = 0;
  for (int i = 0; i < arr.length; i++) {
    eor ^= arr[i];// 得到a^b
  }
  int rightOne = eor & (-eor);// 提取最右的1, a和b在这个位上不同
  int num1 = 0;
  int num2 = 0;
  for (int i = 0; i < arr.length; i++) {
    if ((arr[i] & rightOne) == 0) {// 根据这个位上的数为0或1, 把arr分为两类 在其中分别求a和b
      num1 ^= arr[i];
    } else {
      num2 ^= arr[i];
    }
  }
  return new int[] { num1, num2 };
}
```

### lc137

@找到数组中唯一出现 k 次的数 其他数均出现 m 次(m>k)

用辅助数组收集每一位状态的累加  
对于目标数的某一位 如果其为 1, 则辅助数组对应位的结果%m 一定不为 0, 以此还原目标数的每一位

```java
public int singleNumber(int[] arr) {
  int[] help = new int[32];
  for (int i = 0; i < 32; i++) {
    for (int num : arr) {
      help[i] += (num >> i) & 1;// 把对应位上的数累加
    }
  }
  int ans = 0;
  for (int i = 0; i < 32; i++) {
    if (help[i] % 3 != 0) {// 根据能否整除 确定目标数在对应位上是0还是1
      ans |= (1 << i);
    }
  }
  return ans;
}
```

## class03

### lc203

@删除链表中的某个值  
头结点有可能是目标值 可能需要返回新头  
所以先找到要返回的头结点  
用 pre 表示已经完成处理的部分的结尾  
用 cur 表示当前到达的节点

```java
public ListNode removeElements(ListNode head, int num) {
  while (head != null) {
    if (head.val == num) {
      head = head.next;
    } else {
      break;//找到要返回的头结点
    }
  }//head可能为空 即链表所有节点都要删去
  ListNode pre = head;//已处理完成的部分的结尾
  ListNode cur = head;//当前到达的节点
  while (cur != null) {
    if (cur.val == num) {
      pre.next = cur.next;
    } else {
      pre = cur;
    }
    cur = cur.next;
  }
  return head;
}
```

### lc622

@实现循环队列

用数组实现  
用 begin 记录队列的首位置 即从队列弹出元素时的 index  
用 end 记录队列需要加元素时要放的位置  
(begin 和 end 的值可用于判断是否到达数组结构的末端 需要跳转)  
用 size 记录队列中的数量 用于判断能否增删

```java
class MyCircularQueue {

  private int[] arr;
  private int begin;
  private int end;
  private int size;
  private final int limit;

  public MyCircularQueue(int limit) {
    arr = new int[limit];
    begin = 0;// 队列首位置
    end = 0;// 加元素时要放入的位置
    size = 0;// 队列元素数量
    this.limit = limit;
  }

  public boolean enQueue(int value) {
    if (size == limit) {
      return false;
    }
    size++;
    arr[end] = value;
    end = end == limit - 1 ? 0 : end + 1;
    return true;
  }

  public boolean deQueue() {
    if (size == 0) {
      return false;
    }
    size--;
    begin = begin == limit - 1 ? 0 : begin + 1;
    return true;
  }

  public int Front() {
    if (size == 0) {
      return -1;
    }
    return arr[begin];
  }

  public int Rear() {
    if (size == 0) {
      return -1;
    }
    return end == 0 ? arr[limit - 1] : arr[end - 1];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == limit;
  }
}
```

### lc155

@实现可返回最小值的栈, pop push getMin 操作的时间复杂度都是 O(1)

准备同步增删的数据栈和最小栈  
压入数据时  
如果 min 栈为空 则和数据栈压入相同的数据  
如果 min 栈不为空 则比较该数据和 min 栈栈顶 将较小值压入  
弹出时也同步弹出

```java
class MinStack {

  private Stack<Integer> min;
  private Stack<Integer> data;

  public MinStack() {
    min = new Stack<Integer>();
    data = new Stack<Integer>();
  }

  public void push(int val) {
    if (min.isEmpty()) {
      min.push(val);
    } else {
      min.push(Math.min(val, min.peek()));
    }
    data.push(val);
  }

  public void pop() {
    data.pop();
    min.pop();
  }

  public int top() {
    return data.peek();
  }

  public int getMin() {
    return min.peek();
  }
}
```

### lc232

@用两个栈实现队列

把获得的数据压入 push 栈 再依次弹出 压入 pop 栈  
需要数据时 从 pop 栈弹出 即可实现数据的先进先出

push 栈向 pop 栈倒数据的过程需把数据全部倒完 且只有在 pop 栈为空时才能进行

```java
class MyQueue {

  private Stack<Integer> push;
  private Stack<Integer> pop;

  public MyQueue() {
    push = new Stack<>();
    pop = new Stack<>();
  }

  private void pushData() {// push栈向pop栈倒倒数据
    if (pop.isEmpty()) {// pop栈为空时才能倒数据
      while (!push.isEmpty()) {// 需要把push栈的数据全部倒出
        pop.push(push.pop());
      }
    }
  }

  // 进行任何操作前先倒数据

  public void push(int x) {
    pushData();
    push.push(x);
  }

  public int pop() {
    pushData();
    return pop.pop();
  }

  public int peek() {
    pushData();
    return pop.peek();
  }

  public boolean empty() {
    pushData();
    return pop.isEmpty();
  }
}
```

### lc225

@用两个队列实现栈

两个队列分别为 queue 和 help  
当需要返回数据时 把 queue 的数据留下一个 剩下的弹出到 help, 返回留下的数据  
最后交换 queue 和 help 的引用

```java
class MyStack {

  private Queue<Integer> queue;
  private Queue<Integer> help;

  public MyStack() {
    queue = new LinkedList<>();
    help = new LinkedList<>();
  }

  public void push(int x) {
    queue.add(x);
  }

  public int pop() {
    while (queue.size() > 1) {// queue留下一个用于返回 剩下的数进入help
      help.add(queue.poll());
    }
    int ans = queue.poll();
    Queue<Integer> tmp = queue;// 交换queue和help
    queue = help;
    help = tmp;
    return ans;
  }

  public int top() {
    while (queue.size() > 1) {
      help.add(queue.poll());
    }
    int ans = queue.peek();
    help.add(queue.poll());
    Queue<Integer> tmp = queue;
    queue = help;
    help = tmp;
    return ans;
  }

  public boolean empty() {
    return queue.isEmpty();
  }
}
```

## class05

### lc327

@求数组中有多少子数组累加和在目标范围内

分别求出以每个位置结尾的子数组中符合要求的子数组个数 再把它们相加

生成前缀和数组 用于直接获得前缀和  
对于某个位置 已知目标范围[upper,lower]和其前缀和 k, 以它结尾的子数组中符合要求的子数组个数 即 求该位置之前有多少前缀和落在[k-upper,k-lower]

即对一个数的左边的数的情况进行统计  
对前缀和数组使用 merge sort 并改写 对于右组的每个数 k 求左组(已有序)有多少数在[k-upper,k-lower]

用递归函数计算总个数 返回值为 递归地计算出的左组范围和右组范围内的结果 + 两组 merge 过程中的结果  
merge 函数中 先求对于右组每个数 k, 左组(已经过 merge)有多少数在[k-upper,k-lower],再把它们排有序

因为左右组都已有序 所以可以用不回退的滑动窗口求对于右组某个数 左组中符合[k-upper,k-lower]的数的范围 进而知道数的个数

```java
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
  if (start == end) {// base case 只传进来sum[]中的某一个数sum[x](数组0到x位置的累加和) 即判断0到x位置这个子数组是否达标
    return sum[start] >= lower && sum[start] <= upper ? 1 : 0;
  }
  int mid = (start + end) / 2;
  return f(sum, start, mid, lower, upper) + f(sum, mid + 1, end, lower, upper)
      + merge(sum, start, mid, end, lower, upper);
}

// 左组右组已经f计算出组内的结果 且组内已有序
// 现将左右组合并为一个组 根据前缀和的性质 计算跨组的子数组中符合条件的结果数
// 对于右组每个数k 求左组有多少数在[k-upper,k-lower] 累加得到返回值
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
```

@快速排序

```java
public void quickSort(int[] arr, int start, int end) {
  if (start >= end) {
    return;
  }
  int pivot_pos = partition(arr, start, end);
  quickSort(arr, start, pivot_pos - 1);
  quickSort(arr, pivot_pos + 1, end);
}

public int partition(int[] arr, int start, int end) {
  swap(arr, start + (int) (Math.random() * (end - start + 1)), end);
  int pivot = arr[end];
  // 小于等于区最后位置
  int i = start - 1;
  // 小于等于区 start 到 i
  // 大于区 i + 1 到 cur - 1
  for (int cur = start; cur <= end - 1; cur++) {
    if (arr[cur] <= pivot) {
      swap(arr, cur, i + 1);
      i++;
    }
  }
  swap(arr, end, i + 1);
  return i + 1;
}

public void swap(int[] arr, int i, int j) {
  int tmp = arr[i];
  arr[i] = arr[j];
  arr[j] = tmp;
}
```

## class09

### lc876

@求链表(下)中点  
快慢指针

```java
public ListNode middleNode(ListNode head) {
  if (head.next == null) {
    return head;
  }
  ListNode slow = head.next;
  ListNode fast = head.next;
  while (fast.next != null && fast.next.next != null) {
    slow = slow.next;
    fast = fast.next.next;
  }
  return slow;
}
```

### lc234

@判断链表是否回文(O(1)空间)  
找到链表上中点 反转其后面的部分 将两部分的节点一一进行比较

```java
public boolean isPalindrome(ListNode head) {
  if (head.next == null) {
    return true;
  }
  ListNode slow = head;
  ListNode fast = head;
  while (fast.next != null && fast.next.next != null) {
    slow = slow.next;
    fast = fast.next.next;
  } // slow为上中点
  fast = slow.next;// 从对称位置开始反转后半部分链表 前半部分节点个数不少于后半部分
  slow = head;// slow指向前半部分的比较起点
  ListNode pre = null;
  ListNode next = null;
  while (fast != null) {// 反转后半部分
    next = fast.next;
    fast.next = pre;
    pre = fast;
    fast = next;
  }
  fast = pre;// fast也指向比较的起点
  while (fast != null) {
    if (slow.val == fast.val) {
      slow = slow.next;
      fast = fast.next;
    } else {
      return false;
    }
  }
  return true;
}
```

### lc138

@复制有 random 指针的链表

先按着原链表的 next 方向复制出 val 相同的拷贝节点 并把一一对应的节点放入 hashmap  
然后再次沿 next 遍历 根据原链表的结构和 hashmap 记录的节点对应关系 连接复制节点的指针

```java
public Node copyRandomList(Node head) {
  HashMap<Node, Node> map = new HashMap<>();
  Node cur = head;
  while (cur != null) {
    map.put(cur, new Node(cur.val));// 拷贝节点 并把对应的节点记录进hashmap
    cur = cur.next;
  }
  cur = head;
  while (cur != null) {
    Node copyN = map.get(cur);
    copyN.next = map.get(cur.next);
    copyN.random = map.get(cur.random);
    cur = cur.next;
  }
  return map.get(head);
}
```

### lc141

@判断链表是否有环(O(1)空间)

快慢指针最终相遇则有环 快指针到 null 则无环

```java
public boolean hasCycle(ListNode head) {
  if (head == null || head.next == null || head.next.next == null) {// 快慢指针需要先各走一步 以确定接下来是否能相遇
    return false;
  }
  ListNode slow = head.next;
  ListNode fast = head.next.next;// 快慢指针先各走一步
  while (slow != fast) {// 快慢指针相遇则有环
    if (fast.next == null || fast.next.next == null) {// 快指针可达null则无环
      return false;
    }
    slow = slow.next;
    fast = fast.next.next;
  }
  return true;
}
```

### lc142

@判断链表入环节点的位置(O(1)空间)

如果有环(快慢指针相遇) 将快指针回到原点并降速 两指针再次相遇的节点即入环节点

```java
public ListNode detectCycle(ListNode head) {
  if (head == null || head.next == null || head.next.next == null) {
    return null;
  }
  ListNode slow = head.next;
  ListNode fast = head.next.next;
  while (slow != fast) {
    if (fast.next == null || fast.next.next == null) {
      return null;
    }
    slow = slow.next;
    fast = fast.next.next;
  }
  fast = head;// 快指针回原点
  while (slow != fast) {// 再次相遇的节点即入环节点
    slow = slow.next;
    fast = fast.next;// 快指针降速
  }
  return slow;
}
```

### lc160

@判断两个无环链表是否相交 是则返回相交节点

遍历两链表到各自结尾并记录长度 若两节点不同则不想交  
把长链表先前进相差的长度 再令两链表同时直至节点相同

```java
public ListNode getIntersectionNode(ListNode head1, ListNode head2) {
  ListNode cur1 = head1;
  ListNode cur2 = head2;
  int n = 0;
  while (cur1.next != null) {// 找到末尾节点
    cur1 = cur1.next;
    n++;
  }
  while (cur2.next != null) {
    cur2 = cur2.next;
    n--;
  }
  if (cur1 != cur2) {// 末尾不同则必不相交
    return null;
  }
  cur1 = n < 0 ? head1 : head2;
  cur2 = cur1 == head1 ? head2 : head1;
  n = Math.abs(n);
  while (n != 0) {// 长链表前进相差的长度
    cur2 = cur2.next;
    n--;
  }
  while (cur1 != cur2) {// 同时前进直至相交
    cur1 = cur1.next;
    cur2 = cur2.next;
  }
  return cur1;
}
```

### lc144

@二叉树的先序遍历

```java
List<Integer> ans = new ArrayList<>();

public List<Integer> preorderTraversal(TreeNode root) {
  if (root == null) {
    return ans;
  }
  ans.add(root.val);
  preorderTraversal(root.left);
  preorderTraversal(root.right);
  return ans;
}
```

### lc94

@二叉树的中序遍历

```java
List<Integer> ans = new ArrayList<>();

public List<Integer> inorderTraversal(TreeNode root) {
  if (root == null) {
    return ans;
  }
  inorderTraversal(root.left);
  ans.add(root.val);
  inorderTraversal(root.right);
  return ans;
}
```

### lc145

@二叉树的后序遍历

```java
List<Integer> ans = new ArrayList<>();

public List<Integer> postorderTraversal(TreeNode root) {
  if (root == null) {
    return ans;
  }
  postorderTraversal(root.left);
  postorderTraversal(root.right);
  ans.add(root.val);
  return ans;
}
```

## class11

### lc102

@二叉树层序遍历

准备一个队列 用于存放节点 先放入头结点  
while 队列不为空  
记录队列 size 生成存放当前层的 list  
进行 size 次如下操作:  
(节点出队列 放入其 val 放入当前层 list  
有左把左加入队列 有右把右加入队列)  
当前层 list 加入结果

```java
public List<List<Integer>> levelOrder(TreeNode root) {
  List<List<Integer>> ans = new ArrayList<>();
  if (root == null) {
    return ans;
  }
  Queue<TreeNode> q = new LinkedList<>();
  q.add(root);
  while (!q.isEmpty()) {
    int size = q.size();// 队列当前的size
    List<Integer> curLevel = new ArrayList<>();// 当前层
    for (int i = 0; i < size; i++) {// 重复size次
      TreeNode cur = q.poll();
      curLevel.add(cur.val);
      if (cur.left != null) {
        q.add(cur.left);
      }
      if (cur.right != null) {
        q.add(cur.right);
      }
    }
    ans.add(curLevel);
  }
  return ans;
}
```

## class12

### lc958

@判断是否为完全二叉树

根据完全二叉树的几种形态 确定 info 包含是否满 是否完全 高度  
先确认是否满 即左右都满且高度相同  
如果不是 先把是否完全设为 false, 满足以下任一形态则改为 true  
① 左满右完全 高度相同  
② 左满右满 左比右高 1  
③ 左完全右满 左比右高 1

```java
public boolean isCompleteTree(TreeNode root) {
  return process(root).isComplete;
}

public class info {
  public boolean isFull;
  public boolean isComplete;
  public int height;

  public info(boolean isFull, boolean isComplete, int height) {
    this.isFull = isFull;
    this.isComplete = isComplete;
    this.height = height;
  }
}

public info process(TreeNode root) {
  if (root == null) {
    return new info(true, true, 0);
  }
  info leftInfo = process(root.left);
  info rightInfo = process(root.right);
  int height = Math.max(leftInfo.height, rightInfo.height) + 1;
  boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
  if (isFull) {
    return new info(true, true, height);
  }
  boolean isComplete = false;
  if (leftInfo.isFull && rightInfo.isComplete && leftInfo.height == rightInfo.height) {// 左满右完全 高度相同
    isComplete = true;
  }
  if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {// 左满右满 左比右高1
    isComplete = true;
  }
  if (leftInfo.isComplete && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {// 左完全右满 左比右高1
    isComplete = true;
  }
  return new info(false, isComplete, height);
}
```

### lc333

@求二叉树的最大搜索二叉树子树的节点个数

① 入参节点不是子树的头结点 则需要分别求其左右树的最大搜索子树的 size  
② 入参节点为头的树即搜索二叉树 则需要左右树都是 bst, 左最大<val<右最小, 左右树的 size  
综上可知, info 需要包含最大搜索子树的 size,min 和 max,树的 size  
(比较 size 和 BSTSub 可知是否为 BST)

```java
public int largestBSTSubtree(TreeNode root) {
  if (root == null) {
    return 0;
  }
  return process(root).BSTSub;
}

public class info {
  public int BSTSub;// 最大搜索子树的size
  public int min;
  public int max;
  public int size;

  public info(int BSTSub, int min, int max, int size) {
    this.BSTSub = BSTSub;
    this.min = min;
    this.max = max;
    this.size = size;
  }
}

public info process(TreeNode root) {
  if (root == null) {
    return null;
  }
  info leftInfo = process(root.left);
  info rightInfo = process(root.right);
  int n1 = -1;
  int n2 = -1;// 左树和右树的最大搜索子树的 size
  int n3 = 1;// root为头的树是BST,则BSTSub为整棵树的size
  int min = root.val;
  int max = root.val;
  int size = 1;
  if (leftInfo != null) {
    n1 = leftInfo.BSTSub;
    max = Math.max(max, leftInfo.max);
    min = Math.min(min, leftInfo.min);
    size += leftInfo.size;
  }
  if (rightInfo != null) {
    n2 = rightInfo.BSTSub;
    max = Math.max(max, rightInfo.max);
    min = Math.min(min, rightInfo.min);
    size += rightInfo.size;
  }
  //整棵树为BST的条件: 左右树均为BST且左max < root.val < 右min
  if ((leftInfo == null || (leftInfo.BSTSub == leftInfo.size && leftInfo.max < root.val))
      && (rightInfo == null || (rightInfo.BSTSub == rightInfo.size && rightInfo.min > root.val))) {
    n3 = size;
  }
  int BSTSub = Math.max(n3, Math.max(n1, n2));
  return new info(BSTSub, min, max, size);
}
```

### lc543

@求二叉树节点间的最大距离  
① 最大距离和入参节点无关 则需要左右树的最大距离  
② 最大距离和入参节点有关 即需要左右树的高度相加  
因此 info 需要最大距离和高度

```java
public int diameterOfBinaryTree(TreeNode root) {
  return process(root).max;
}

public class info {
  int max;
  int height;

  public info(int max, int height) {
    this.max = max;
    this.height = height;
  }
}

public info process(TreeNode root) {
  if (root == null) {
    return new info(0, 0);
  }
  info leftInfo = process(root.left);
  info rightInfo = process(root.right);
  int height = Math.max(leftInfo.height, rightInfo.height) + 1;
  int max = leftInfo.height + rightInfo.height;// 经过root
  max = Math.max(max, Math.max(leftInfo.max, rightInfo.max));// 和root无关
  return new info(max, height);
}
```

## class13

### lc236

@寻找二叉树中两节点的最低公共祖先

入参节点是否为最低公共祖先 LCA

不是  
①LCA 在入参节点的子树上  
② 入参节点为头的子树中不能找全 a 和 b

是  
❶ 入参节点左右树各有一个 在入参节点汇聚  
❷ 入参节点即是其中一个节点 子树中有另一个节点

所以 info 包含  
LCA(在入参节点为头的树中不存在则为 null)  
节点 a 和 b 是否存在

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
  return process(root, p, q).ans;
}

public class info {
  public boolean findA;
  public boolean findB;
  TreeNode ans;

  public info(boolean findA, boolean findB, TreeNode ans) {
    this.findA = findA;
    this.findB = findB;
    this.ans = ans;
  }
}

public info process(TreeNode n, TreeNode a, TreeNode b) {
  if (n == null) {
    return new info(false, false, null);
  }
  info leftInfo = process(n.left, a, b);
  info rightInfo = process(n.right, a, b);
  boolean findA = (n == a) || leftInfo.findA || rightInfo.findA;
  boolean findB = (n == b) || leftInfo.findB || rightInfo.findB;
  TreeNode ans = null;
  if (leftInfo.ans != null) {// LCA在入参节点为头的左/右树中
    ans = leftInfo.ans;
  } else if (rightInfo.ans != null) {
    ans = rightInfo.ans;
  } else {// 入参节点即LCA
    if (findA && findB) {
      ans = n;
    }
  }
  return new info(findA, findB, ans);
}
```

## class14

### lc502

@两个数组记录一些项目各自的成本和利润 求 w 本金做最多 k 个项目后的最大资本

把项目全部放入按花费的小跟堆 再准备按利润的大根堆  
每次把当前能做的项目从小跟堆弹出 放入大根堆 做大根堆顶的项目 然后根据其利润更新资金

```java
public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
  PriorityQueue<Program> minCost = new PriorityQueue<>(new minCostComparator());// cost小跟堆
  PriorityQueue<Program> maxProfit = new PriorityQueue<>(new maxProfitComparator());// profit大根堆
  for (int i = 0; i < profits.length; i++) {// 所有项目先放进小跟堆
    minCost.add(new Program(capital[i], profits[i]));
  }
  for (int i = 0; i < k; i++) {// 最多做k个项目
    while (!minCost.isEmpty() && minCost.peek().cost <= w) {// 把当前能做的所有项目放进profit大根堆
      maxProfit.add(minCost.poll());
    }
    if (maxProfit.isEmpty()) {// 还未做够k个项目就已经不能做了
      return w;
    }
    w += maxProfit.poll().profit;// 更新资金w
  }
  return w;
}

public class Program {
  public int cost;
  public int profit;

  public Program(int cost, int profit) {
    this.cost = cost;
    this.profit = profit;
  }
}

public class minCostComparator implements Comparator<Program> {

  @Override
  public int compare(Program o1, Program o2) {
    return o1.cost - o2.cost;
  }
}

public class maxProfitComparator implements Comparator<Program> {

  @Override
  public int compare(Program o1, Program o2) {
    return o2.profit - o1.profit;
  }
}
```

## class15

### 并查集

## class17

### lc78

@返回一个数字数组的全部子序列(可以不连续)
即对于每个位置可以要/不要做出全部可能的决定

用递归函数求解 参数包括{  
表示字符串的数组(固定不变)  
做决定到达的位置 i  
path 记录之前的路径  
ans 收集所有路径  
}

```java
public List<List<Integer>> subsets(int[] arr) {
  List<List<Integer>> ans = new ArrayList<>();
  List<Integer> path = new ArrayList<>();
  process(arr, 0, path, ans);
  return ans;
}

public void process(int[] arr, int i, List<Integer> path, List<List<Integer>> ans) {
  if (i == arr.length) {// 已对所有位置要/不要做出了决定 收集该种情况的结果
    ans.add(new ArrayList<>(path));// path按引用传递 所以需要生成相同的path加入结果
    return;
  }
  process(arr, i + 1, path, ans);// 不要当前位置
  // 按照深度优先 最先返回全不要的结果 然后从后向前地对每个位置做要/不要的决定
  path.add(arr[i]);
  process(arr, i + 1, path, ans);// 要当前位置
  path.remove(path.size() - 1);// 即将返回上游(前面某个位置)做(要的)决定 作为该位置后面的位置 应恢复成最初不要的状态
}
```

### lc46

@返回一个数字数组的所有全排列结果

把所有数字收集进 rest  
可能的组合放进 path  
所有 path 收集进 ans

尝试从 rest 每个位置取数字放进 path 开头 剩余的部分递归调用函数  
rest 为空时形成 path, 将其存入 ans

```java
public List<List<Integer>> permute(int[] arr) {
  List<List<Integer>> ans = new ArrayList<>();
  List<Integer> path = new ArrayList<>();
  List<Integer> rest = new ArrayList<>();// 剩余可用的数字
  for (int i = 0; i < arr.length; i++) {
    rest.add(arr[i]);
  }
  process(ans, path, rest);
  return ans;
}

public void process(List<List<Integer>> ans, List<Integer> path, List<Integer> rest) {
  if (rest.isEmpty()) {// 所有数字都已加入path
    ans.add(new ArrayList<>(path));
    return;
  }
  int n = rest.size();
  for (int i = 0; i < n; i++) {// 所有数字都可能作为开头
    int cur = rest.get(i);
    path.add(cur);
    rest.remove(i);
    process(ans, path, rest);
    path.remove(path.size() - 1);// 恢复现场
    rest.add(i, cur);
  }
}
```

## class18

<font color=red>
暴力递归

尝试模型  
① 从左往右(每个位置要/不要)  
② 范围(开头和结尾)  
③ 样本对应(结尾)

</font>
找到影响返回值的全部参数(实参组合会重复调用)

<font color=orange>

记忆化搜索(无枚举行为做到此步即可)  
1.根据可变参数范围建立缓存表 先为每个位置设置不合理返回值  
2.递归函数先判断缓存表中的值是否合理 是则直接返回  
3.先用 ans 记录每一种情况的返回值 返回 ans 前先将其记录在缓存表中

</font>

<font color=yellow>

严格表结构  
依据递归过程  
1.根据可变参数范围建立缓存表  
2.确定返回值在表中的位置  
3.确定 base case 在表中的位置和值  
4.由递归子过程确定普遍位置的依赖关系并填表

</font>

### lc486

@数组表示一些牌 两人依次从最左或最右拿并获取对应分数 预测胜者

1.暴力递归

```java
public boolean predictTheWinner(int[] arr) {
  int first = f(arr, 0, arr.length - 1);
  int second = s(arr, 0, arr.length - 1);
  return first >= second;
}

public int f(int[] arr, int L, int R) {// 先手
  if (L == R) {
    return arr[L];
  }
  int left = arr[L] + s(arr, L + 1, R);// 自己先手选左 则后续等价于自己在L+1到R后手
  int right = arr[R] + s(arr, L, R - 1);
  return Math.max(left, right);
}

public int s(int[] arr, int L, int R) {// 后手
  if (L == R) {
    return 0;
  }
  int left = f(arr, L + 1, R);// 对手先手选左 等价于自己在L+1到R先手
  int right = f(arr, L, R - 1);// 对手先手选右 等价于自己在L到R-1后手
  return Math.min(left, right);// 零和博弈 对手先手选max的情况 自己后手剩min的情况
}
```

2.记忆化搜索

```java
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
```

## class19

### lc91

@字符串中 1 至 26 可解码为 a 到 z, 求可能的解码结果数

暴力递归

```java
public int numDecodings(String s) {
  char[] str = s.toCharArray();
  return process(str, 0);
}

public int process(char[] str, int i) {
  if (i == str.length) {// 到达字符串末尾 转换有效
    return 1;
  }
  if (str[i] == '0') {// 0开头的字符串无法转化 转换无效
    return 0;
  }
  int ans = process(str, i + 1);// 当前字符单独转换
  if (i + 1 < str.length && ((str[i] - '0') * 10 + str[i + 1] - '0' < 27)) {// 当前字符和下一个字符可以一起转换
    ans += process(str, i + 2);
  }
  return ans;
}
```

动态规划

```java
public int numDecodings(String s) {
  char[] str = s.toCharArray();
  int n = str.length;
  int[] dp = new int[n + 1];// i范围为0到n
  dp[n] = 1;// base case
  for (int i = n - 1; i >= 0; i--) {// 普遍位置依赖于右边两个位置
    // str[i]为0时dp[i]填0
    if (str[i] != '0') {
      dp[i] = dp[i + 1];
      if (i + 1 < n && ((str[i] - '0') * 10 + str[i + 1] - '0' < 27)) {
        dp[i] += dp[i + 2];
      }
    }
  }
  return dp[0];
}
```

## class20

### lc516

@求字符串最长回文子序列长度(可以不连续)

暴力递归

```java
public int longestPalindromeSubseq(String s) {
  char[] str = s.toCharArray();
  int n = str.length;
  return process(str, 0, n - 1);
}

public int process(char[] str, int start, int end) {
  if (start == end) {
    return 1;
  }
  if (start + 1 == end) {
    return str[start] == str[end] ? 2 : 1;
  }
  // 分类: start和end是否做结果的开头结尾
  int p1 = process(str, start + 1, end);
  int p2 = process(str, start, end - 1);
  int p3 = process(str, start + 1, end - 1);
  int p4 = 0;
  if (str[start] == str[end]) {
    p4 = 2 + process(str, start + 1, end - 1);
  }
  return Math.max(Math.max(p1, p2), Math.max(p3, p4));
}
```

动态规划

```java
public int longestPalindromeSubseq(String s) {
  char[] str = s.toCharArray();
  int n = str.length;
  int[][] dp = new int[n][n];// start和end的范围为0到n-1
  // base case: 两条对角线
  dp[n - 1][n - 1] = 1;
  for (int i = 0; i < n - 1; i++) {
    dp[i][i] = 1;
    dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
  }
  // 普遍位置依赖左 下 左下
  // 从下往上 从左往右填表
  for (int start = n - 3; start >= 0; start--) {
    for (int end = start + 2; end < n; end++) {
      // 每个位置取左 下 左下的最大值 即左/下一定比左下大 只需要取它们的较大值
      dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
      if (str[start] == str[end]) {
        dp[start][end] = Math.max(dp[start][end], 2 + dp[start + 1][end - 1]);
      }
    }
  }
  return dp[0][n - 1];
}
```

## class21

### lc64

@求从矩阵左上朝右或下走到右下的最小路径和

根据终点的位置建立递归函数

```java
public int minPathSum(int[][] grid) {
  return process(grid, grid.length - 1, grid[0].length - 1);
}

public int process(int[][] grid, int i, int j) {
  if (i == 0 && j == 0) {//base case
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
```

动态规划

```java
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
```

### lc518

@给定硬币面值数组 每种硬币数量无限 求组成 aim 的方法数

递归函数  
求从数组 i 位置开始 组成 sum 的方法数

```java
public int change(int amount, int[] coins) {
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
```

动态规划  
每个位置填表时都要经过一个 for 循环 即每个位置由其下和下每次向左 arr[i]的位置决定  
进一步转换为由下和向左 arr[i]位置决定(如果存在)

```java
public int change(int amount, int[] coins) {
  int n = coins.length;
  int[][] dp = new int[n + 1][amount + 1];
  dp[n][0] = 1;//base case
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
```

### lc576

@给定矩阵规模和初始位置 求最多 k 步后出界的 path 数

暴力递归  
递归函数参数: 当前位置和剩余的步数

```java
public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
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
```

记忆化搜索

```java
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
```

### lc322

@给定硬币面值数组 每种硬币数量无限 求组成 aim 的最少硬币数

递归函数
求从数组 i 位置开始 组成 sum 的最少硬币数

```java
public int coinChange(int[] coins, int amount) {
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
```

动态规划  
每个位置由其下, 下向左 arr[i]+ 1, 下再向左 arr[i]+2...的最小值决定  
所以除了下以外的其他项最小值可以转换为 1+左 arr[i]

```java
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
```

## class23

### lc52

@N 皇后问题

```java
public int totalNQueens(int n) {
  int[] record = new int[n];// record[i,j]表示第i行把皇后放在j列
  return process(0, n, record);
}

public int process(int i, int n, int[] record) {
  if (i == n) {// 有效策略
    return 1;
  }
  int ans = 0;
  for (int j = 0; j < n; j++) {// 尝试该行的每个位置是否可以放皇后
    if (isValid(record, i, j)) {
      record[i] = j;
      ans += process(i + 1, n, record);
    }
  }
  return ans;
}

public boolean isValid(int[] record, int i, int j) {// record[]已记录0至i-1行皇后位置 检查(i,j)位置是否有效
  for (int row = 0; row < i; row++) {// 0至i-1行各有一个位置放着皇后
    if (record[row] == j || Math.abs(row - i) == Math.abs(record[row] - j)) {// [row,record[row]]位置不能和(i,j)共列或共斜线(斜率为1)
      return false;
    }
  }
  return true;
}
```
