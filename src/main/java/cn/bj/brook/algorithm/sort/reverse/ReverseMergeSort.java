package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.BucketSort;
import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;

import java.util.Arrays;

public class ReverseMergeSort implements SortFunction {
    @Override
    public void sort(int[] arr) {
        int[] rs = sort0(arr);
        System.arraycopy(rs, 0, arr, 0, rs.length);
    }

    private int[] sort0(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left_array = sort0(Arrays.copyOfRange(arr, 0, mid));
        int[] right_array = sort0(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left_array, right_array);
    }

    private int[] merge(int[] a, int[] b) {
        int sz = a.length + b.length;
        int t[] = new int[sz];
        int pointerOf_A_Array = 0;
        int pointerOf_B_Array = 0;
        int i = 0;
        while (i < sz) {
            if (a[pointerOf_A_Array] > b[pointerOf_B_Array]) {
                t[i] = a[pointerOf_A_Array];
                pointerOf_A_Array++;
                i++;
                if (pointerOf_A_Array == a.length) {
                    for (; pointerOf_B_Array < b.length; pointerOf_B_Array++) {
                        t[i] = b[pointerOf_B_Array];
                        i++;
                    }
                    break;
                }
            } else {
                t[i] = b[pointerOf_B_Array];
                pointerOf_B_Array++;
                i++;
                if (pointerOf_B_Array == b.length) {
                    for (; pointerOf_A_Array < a.length; pointerOf_A_Array++) {
                        t[i] = a[pointerOf_A_Array];
                        i++;
                    }
                    break;
                }
            }
        }
        return t;
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100, 17);
        frame.sort(new ReverseMergeSort());
    }
}
