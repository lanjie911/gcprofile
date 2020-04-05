package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;

public class ReverseQuickSort implements SortFunction {
    public void sort(int[] arr) {
        partition(arr, 0, arr.length - 1);
    }

    private void partition(int[] arr, int left, int right) {
        if ((right - left) <= 0) {
            return;
        }
        int pivotPos = (left + right) / 2;
        pivotPos = moveElement(arr, pivotPos, left, right);
        partition(arr, left, pivotPos);
        partition(arr, pivotPos + 1, right);
    }

    private int moveElement(int[] ts, int pivot, int head, int tail) {
        int pos = pivot;
        for (int i = head; i <= tail; ) {
            if ((i < pos && ts[i] < ts[pos])) {
                int temp = ts[i];
                ts[i] = ts[pos];
                ts[pos] = temp;
                pos = i;
                i++;
                continue;
            }
            if ((i > pos && ts[i] > ts[pos])) {
                int temp = ts[i];
                int old_pos = pos;
                ts[i] = ts[pos];
                ts[pos] = temp;
                pos = i;
                i = old_pos + 1;
                continue;
            }
            i++;
        }
        return pos;
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(1000, 20);
        frame.sort(new ReverseQuickSort());
    }

}
