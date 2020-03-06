package cn.bj.brook.algorithm.heap;

import cn.bj.brook.algorithm.sort.SortUtil;

public class HeapUtil {
    /**
     * 把传入的无序数组改造为大根堆
     * 改造的过程就是根据完全二叉树的遍历顺序在数组中做交换
     * 扫描数组一次就够了，数组的脚标决定了在二叉树的中的顺序
     *
     *
     * 如果脚标是单数，就和前面紧挨着的一个数比较
     * 如果脚标是双数，就和前面隔1个数，也就是往前数2个的数比较
     * 比如：[34, 23, 65]
     * 23 vs 34 不用交换
     * 65 vs 34 需要交换
     *
     * 奇数脚标的父节点是 -1 再 /2 比如 3的父节点是1 = (3-1)/2  ； 7的父节点是3 即 (7-1)/2 = 3
     * 偶数脚标的父节点是 -2 再 /2 比如 4的父节点是1 = (4-2)/2  ； 8的父节点是3 即 (8-2)/2 = 3
     * @param arr
     * @return
     */
    public static int[] buildMaxRootHeap(int[] arr){
        // 默认arr[0]是大根堆第一个节点
       return buildMaxRootHeap(arr,arr.length);
    }

    public static int[] buildMinRootHeap(int[] arr){
        // 默认arr[0]是小根堆第一个节点
        return buildMinRootHeap(arr,arr.length);
    }

    public static int[] buildMinRootHeap(int[] arr, int endExclusive){
        // 默认arr[0]是大堆根
        for(int i=1;i<endExclusive;i++){
            for(int j=i;j>0;){
                // 在奇数的情况下找到父节点
                int parentIndex = -1;
                if(j%2==1){
                    parentIndex = (j-1)/2;

                }else if(j%2==0){
                    parentIndex = (j-2)/2;
                }
                int parent = arr[parentIndex];
                // 只要父节点大就交换
                if(arr[j]<parent){
                    arr[parentIndex] = arr[j];
                    arr[j] = parent;
                    j = parentIndex;
                    continue;
                }
                // 如果没有父节点小，证明合理，继续测试下一个数
                break;
            }
        }
        return  arr;
    }

    /**
     *
     * @param arr
     * @param endExclusive 这个是遍历的次数，遍历几次就取得第几个大根
     * @return
     */
    public static int[] buildMaxRootHeap(int[] arr,int endExclusive){
        // 默认arr[0]是大堆根
        for(int i=1;i<endExclusive;i++){
            for(int j=i;j>0;){
                // 在奇数的情况下找到父节点
                int parentIndex = -1;
                if(j%2==1){
                    parentIndex = (j-1)/2;
                }else if(j%2==0){
                    parentIndex = (j-2)/2;
                }
                int parent = arr[parentIndex];
                // 只要父节点小就交换
                if(arr[j]>parent){
                    arr[parentIndex] = arr[j];
                    arr[j] = parent;
                    // j的层级不停的向上换，直到换到0为止
                    j = parentIndex;
                    continue;
                }
                // 如果没有父节点大，证明合理，继续测试下一个数
                break;
            }
        }
        return  arr;
    }

    public static void main(String[] args) {
        int[] arr = SortUtil.generateWithMinus(100,12);
        SortUtil.print(arr);
        HeapUtil.buildMaxRootHeap(arr);
        SortUtil.print(arr);
    }
}
