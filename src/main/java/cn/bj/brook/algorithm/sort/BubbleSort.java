package cn.bj.brook.algorithm.sort;

/**
 * 01-冒泡排序
 * 复杂度O(n^2)
 * 这是一个把元素不停向右推的过程
 * 4 3 2 1
 * -> 3 4 2 1
 * -> 3 2 4 1
 * -> 3 2 1 4
 * ----------------第一轮
 * -> 2 3 1 4
 * -> 2 1 3 4
 * ----------------第二轮
 * -> 1 2 3 4
 * ----------------第三轮
 * 所以总循环次数最差就是n-1轮，每次内部循环都是从0 开始
 * <p>
 * 其反向算法请参考
 *
 * @see cn.bj.brook.algorithm.sort.reverse.ReverseBubbleSort#sort(int[])
 */
public class BubbleSort implements SortFunction {

    public static SortFunction newInstance() {
        return new BubbleSort();
    }

    public void sort(int[] arr) {
        // 外循环只需要n-1次
        // 比如 321最多只需要遍历2轮就可以顺位
        // 再比如4321最多只需要遍历3轮就可以顺位
        for (int i = 0; i < arr.length-1; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            // 内循环是比较前后的一对儿，所以最多只能到长度-1
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100, 8);
        frame.sort(BubbleSort.newInstance());
    }
}
