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
