package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;
import cn.bj.brook.algorithm.sort.SortUtil;

import java.util.Arrays;

public class CompareThem {
    public static void main(String[] args) {
        int[] arr = SortUtil.generate(10000000, 40000);
        int[] copy = new int[arr.length];

        long begin = 0l;
        long end = 0l;

        //冒泡排序
        SortFunction bubble = new ReverseBubbleSort();
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        bubble.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("冒泡排序:" + (end - begin) + "毫秒");

        // 选择排序
        SortFunction select = new ReverseSelectionSort();
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        select.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("选择排序:" + (end - begin) + "毫秒");

        // 插入排序
        SortFunction insert = new ReverseInsertSort();
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        insert.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("插入排序:" + (end - begin) + "毫秒");

        //堆排序
        SortFunction heap = new ReverseHeapSort();
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        heap.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("堆排序:" + (end - begin) + "毫秒");

        //计数排序
        SortFunction count = new ReverseCountSort();
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        count.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("计数排序:" + (end - begin) + "毫秒");

        // 基数排序
        SortFunction radix = new ReverseRadixSort();
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        radix.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("基数排序:" + (end - begin) + "毫秒");

        // 归并排序
        SortFunction merge = new ReverseMergeSort();
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        merge.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("归并排序:" + (end - begin) + "毫秒");

        // 希尔排序
        SortFunction shell = new ReverseShellSort();
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        shell.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("希尔排序:" + (end - begin) + "毫秒");

        // 快速排序
        SortFunction quick = new ReverseQuickSort();
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        quick.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("快速排序:" + (end - begin) + "毫秒");

        // JDK默认双枢轴量快排
        System.arraycopy(arr, 0, copy, 0, arr.length);
        begin = System.currentTimeMillis();
        Arrays.sort(copy);
        end = System.currentTimeMillis();
        System.out.println("JDK双枢轴量快速排序:" + (end - begin) + "毫秒");
    }
}
