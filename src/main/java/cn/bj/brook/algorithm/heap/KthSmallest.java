package cn.bj.brook.algorithm.heap;

import java.util.Arrays;

public class KthSmallest {

    public int[] smallestK(int[] arr, int k) {
        for(int i=0;i<k;i++){
            sort0(arr, i);
        }
        return Arrays.copyOfRange(arr, 0 , k);
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
                if (arr[j+offset] < arr[parentIndex+offset]) {
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
