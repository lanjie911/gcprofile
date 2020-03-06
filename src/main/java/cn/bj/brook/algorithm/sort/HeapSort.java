package cn.bj.brook.algorithm.sort;

/**
 * 07 - 堆排序
 * 使用堆这种完全二叉树结构，分为大堆和小堆
 * 堆排序的原理是数组中的一个元素经过遍历后，一定会找到合适的位置
 * 正序排序使用小根堆，即任意父节点的元素都比子节点的元素要小
 * 比如
 * [1,8,9,12,13,17,15]
 *
 *       1
 *    /     \
 *   8      9
 *  / \    / \
 * 12  13 17 15
 *
 * 根据上述结构，可以始终找到最小的元素
 * 那么循环找到最小的元素就可以把数组变的有序
 * 数组虽然是个横向结构，但是每个元素如果按照满二叉树的顺序来安排
 * 是可以在树上找到对应的位置的
 * 比如 数组脚标为奇数的元素，其父元素的脚标是子元素脚标-1再除以2
 * 数组脚标为偶数的元素，其父元素的脚标是子元素脚标-2再除以2
 *
 * 小根堆的本质是选择排序，选择排序是遍历整个右侧剩余池子
 * 而小根堆是右侧剩余池子的折半查找
 *
 */
public class HeapSort implements SortFunction {

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
