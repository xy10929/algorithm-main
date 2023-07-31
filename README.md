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
    push = new Stack<Integer>();
    pop = new Stack<Integer>();
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
