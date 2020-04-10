package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;

public class ReverseQuickSortRemedy implements SortFunction {
    @Override
    public void sort(int[] arr) {
        sort0(arr, 0, arr.length - 1);
    }

    private void sort0(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            sort0(arr, left, partitionIndex - 1);
            sort0(arr, partitionIndex + 1, right);
        }
        return;
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] > arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        SortAlgorithmFrame a = new SortAlgorithmFrame(100,30);
        a.sort(new ReverseQuickSortRemedy());
    }
}
