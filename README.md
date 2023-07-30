# algorithm-main

Leetcode problems in data stucture & algorithm course by [Chengyun Zuo](https://github.com/algorithmzuo/algorithmbasic2020)

- [class01](#class01)
  - [lc704 确定目标值在有序数组中的位置](#lc704)
  - [lc34 确定目标值在有序数组中的起始位置](#lc34)
  - [lc162 找到数组中一个局部最大值](#lc34)
- [class02](#class02)

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
