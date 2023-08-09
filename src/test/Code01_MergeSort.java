package test;

public class Code01_MergeSort {
	
    public void quickSort(int[] arr, int start, int end){
        if(start >= end){
            return;
        }
        int pivot_pos = partition(arr, start, end);
        quickSort(arr, start, pivot_pos - 1);
        quickSort(arr, pivot_pos + 1, end);
    }

    public int partition(int[] arr, int start, int end){
        swap(arr, start + (int)(Math.random() * (end - start + 1)), end);
        int pivot = arr[end];
        //小于等于区最后位置
        int i = start - 1;
        //小于等于区 start到i
        //大于区 i + 1 到 cur- 1
        for(int cur = start; cur <= end - 1; cur++){
            if(arr[cur] <= pivot){
                swap(arr, cur, i + 1);
                i++;
            }
        }
        swap(arr, end, i + 1);
        return i + 1;
    }

    public void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}