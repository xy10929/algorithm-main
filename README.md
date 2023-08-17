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
