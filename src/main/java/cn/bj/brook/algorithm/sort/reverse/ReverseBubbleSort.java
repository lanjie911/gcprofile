package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;
import cn.bj.brook.algorithm.sort.SortUtil;

/**
 * 按照降序排序的冒泡排序法
 *
 * @see cn.bj.brook.algorithm.sort.BubbleSort#sort(int[])
 */
public class ReverseBubbleSort implements SortFunction {

    @Override
    public void sort(int[] arr) {
        // 一共需要数组长度n-1轮排序
        // 比如10个元素只需要9轮就可以了
        // 所以冒泡排序最外层就是循环的次数
        for (int i = 0; i < arr.length - 1; i++) {
            // 该标志位是测量是否在一次循环中出现过冒泡行为
            boolean bubbled = false;
            // 每次排序具体交换算法
            // 比较到结尾元素
            for (int j = 0; j < arr.length - 1; j++) {
                // 如果当前元素比后一个元素要小
                // 那么把当前元素挪动到后面
                // 把后面元素挪动到前面
                // 这种交换能保证下一个元素仍然是比较小的元素
                // 举个例子
                // [1, 2, 3, 4]
                // 第一次循环, i=0, j=0 开始
                // arr[0] = 1 , < 2
                // 交换
                // [2, 1, 3, 4]
                // 然后j++，这个时候arr[j] => arr[1]
                // arr[1] 仍然是1 < 3
                // 继续交换
                // [2, 3, 1, 4]
                // 然后j++，这个时候arr[j] => arr[2]
                // arr[2] 仍然是1 < 4，依次类推
                if (arr[j] < arr[j + 1]) {
                    int next = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = next;
                    bubbled = true;
                }
                SortUtil.print(arr);
            }
            // 如果在一次循环中没有出现过冒泡的行为那么证明排好序了
            if (!bubbled) {
                break;
            }
        }
    }

    public static SortFunction newInstance() {
        return new ReverseBubbleSort();
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100, 5);
        frame.sort(ReverseBubbleSort.newInstance());
    }
}
