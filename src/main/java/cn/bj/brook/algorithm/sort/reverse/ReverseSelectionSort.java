package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;

/**
 * 选择排序的反向排序
 * @see cn.bj.brook.algorithm.sort.SelectionSort#sort(int[])
 */
public class ReverseSelectionSort implements SortFunction {

    @Override
    public void sort(int[] arr) {
        // 确定游标的位置
        // 游标是不断向后游走的
        // 游标是分隔着已经排好序和未排序的界限
        // 游标左侧是已经排好序的
        // 游标右侧是未排序仍然可以选择最（大）值的池子
        // 游标从0开始，意味着一开始整个数组都是被选择的池子
        // 游标达到数组的length的时候，计算结束
        int cursor = 0;

        // 游标没有达到数组的末尾
        while(cursor < arr.length-1){
            // 记录一个最大值
            int max = arr[cursor];
            // 记录当前最大值的脚标
            int j = cursor;
            // 从游标的右侧(剩余的池子)
            for(int i = cursor;i<arr.length;i++){
                // 如果找到了最大值
                if(arr[i]>max){
                    max = arr[i];
                    j = i;
                }
            }
            // 把max和当前的游标指向的元素交换位置
            // 这个地方可以优化
            // 如果cursor的值和j的值相等
            // 就可以避免一次交换
            // 即cursor == j 就不交换
            if(cursor != j) {
                int temp = arr[cursor];
                arr[cursor] = max;
                arr[j] = temp;
            }
            // 游标向右拨动一格，游标左侧就是排好序的
            cursor++;
        }
    }

    public static SortFunction newInstance() {
        return new ReverseSelectionSort();
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100,18);
        frame.sort(ReverseSelectionSort.newInstance());
    }
}
