package cn.bj.brook.algorithm.sort;

/**
 * 03-插入式排序 O(n^2)
 * 从第二个元素开始，依次向前遍历插入，比如：
 * 12 8 35 24 19 69 57
 * 12 | 8 35 24 19 69 57 i=1, 8 < 12 交换（原来的值往右移动）
 * 8 12 35 24 19 69 57
 * <p>
 * 8 12 | 35 24 19 69 57 i=2, 35 > 12 35 > 8 不动
 * <p>
 * 8 12 35 | 24 19 69 57 i=3, 24 < 35 交换过程如下
 * 8 12 35 | 35 19 69 57
 * 8 12 24 | 35 19 69 57
 * <p>
 * 继续
 * 8 12 24 35 | 19 69 57
 * 8 12 24 35 | 35 69 57
 * 8 12 24 24 | 35 69 57
 * 8 12 19 24 | 35 69 57
 * 8 12 19 24 35 | 69 57
 * 8 12 19 24 35 69 | 57
 * 8 12 19 24 35 69 | 69
 * 8 12 19 24 35 57 | 69
 * 8 12 19 24 35 57 69
 *
 * @see cn.bj.brook.algorithm.sort.reverse.ReverseInsertSort#sort(int[])
 */
public class InsertSort implements SortFunction {

    @Override
    public void sort(int[] arr) {
        // 从下标为1的元素开始选择合适的位置插入，因为数组首个元素（下标为0的）是默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据 - 从下标为1开始（第二个元素）
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            // 从右向左倒着找，依次往右挪元素
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }

            // 最后比较一下是否这个值做了插入操作，如果有，把当前第j个元素直接替换掉
            if (j != i) {
                arr[j] = tmp;
            }
        }
    }

    public static SortFunction newInstance() {
        return new InsertSort();
    }

    ;

    public static void main(String[] args) {
        SortAlgorithmFrame frame = SortAlgorithmFrame.build(100, 8);
        frame.sort(InsertSort.newInstance());
    }
}
