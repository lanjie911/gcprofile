package cn.bj.brook.algorithm.sort;

/**
 * 02-选择排序
 * 复杂度O(n^2)
 *
 * 顾名思义，选择排序是"不停的选择最大或者最小"
 * 选择排序是不停的将剩余的元素中选出一个最小的，然后逐步放到左侧
 * 第一个选出的最大的来自于整个数组，然后放到第0位（和数组第0位交换）
 * 第二个选出的是除掉第0位后，右半边剩下的元素，然后放到第1位（和数组第1位交换）
 * 以此类推
 *
 * 比如： 18 23 12 8 62 91 35
 *
 * 第一轮：
 * 选出了 min=8，然后和第0位18交换
 * | 18 23 12 8  62 91 35
 * 8 |  18 23 12 62 91 35
 *
 * 第二轮及以后：
 * 8 |  12 18 23 62 91 35
 * 8 12 |  18 23 62 91 35
 * 8 12 18 |  23 62 91 35
 * 8 12 18 23 |  62 91 35
 * 8 12 18 23 35 |  62 91
 * 8 12 18 23 35 62 |  91
 * 8 12 18 23 35 62 91 | -> 结束
 *
 * @see cn.bj.brook.algorithm.sort.reverse.ReverseSelectionSort#sort(int[])
 */
public class SelectionSort implements SortFunction{

    @Override
    public void sort(int[] arr) {
        // 确定游标的位置
        // 游标是不断向后游走的
        // 游标是分隔着已经排好序和未排序的界限
        // 游标左侧是已经排好序的
        // 游标右侧是未排序仍然可以选择最（小）值的池子
        // 游标从0开始，意味着一开始整个数组都是被选择的池子
        // 游标达到数组的length的时候，计算结束
        int cursor = 0;

        // 游标没有达到数组的末尾
        while(cursor < arr.length){
            // 记录一个最小值
            int min = Integer.MAX_VALUE;
            // 记录当前最小值的脚标
            int j = cursor;
            // 从游标的右侧(剩余的池子)
            for(int i = cursor;i<arr.length;i++){
                // 如果找到了最小值
                if(arr[i]<min){
                    min = arr[i];
                    j = i;
                }
            }
            // 把max和当前的游标指向的元素交换位置
            int temp = arr[cursor];
            arr[cursor] = min;
            arr[j] = temp;
            // 游标向右拨动一格，游标左侧就是排好序的
            cursor++;
        }
    }

    public static SortFunction newInstance(){
        return new SelectionSort();
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100, 8);
        frame.sort(SelectionSort.newInstance());
    }
}
