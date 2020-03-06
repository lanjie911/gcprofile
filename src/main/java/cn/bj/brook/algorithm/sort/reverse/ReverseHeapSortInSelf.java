package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortFunction;

/**
 * 大根堆排序
 * 空间复杂度为0
 * 使用数组本身
 * 关键在于记住数组的偏移量
 */
public class ReverseHeapSortInSelf implements SortFunction {


    @Override
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            sort0(arr, i);
        }
    }

    public void sort0(int[] arr, int offset) {
        // 从offset开始计算为0
        // 使用相同的空间
        for (int i = offset; i < arr.length; i++) {
            int j = i-offset;
            while (j > 0) {
                int parent;
                int parentIndex;
                boolean even = j % 2 == 0;
                if (even) {
                    parentIndex = (j - 2) / 2;
                } else {
                    parentIndex = (j - 1) / 2;
                }
                if (arr[j+offset] > arr[parentIndex+offset]) {
                    parent = arr[parentIndex+offset];
                    arr[parentIndex+offset] = arr[j+offset];
                    arr[j+offset] = parent;
                    j = parentIndex;
                    continue;
                } else {
                    break;
                }
            }
        }
    }
}
