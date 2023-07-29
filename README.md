# algorithm-main

Leetcode problems in data stucture & algorithm course by [Chengyun Zuo](https://github.com/algorithmzuo/algorithmbasic2020)

- [class01](#class01)
  - [lc704 确定目标值在有序数组中的位置](#lc704)
- [class02](#class02)

## class01

### lc704

@确定目标值在有序数组中的位置

```java
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
```
