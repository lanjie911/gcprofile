package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortFunction;

import java.util.Arrays;

public class ReverseHeapSort implements SortFunction {

    @Override
    public void sort(int[] arr) {
        // 数组拷贝 from包含 to不包含
        int[] tempArr = Arrays.copyOf(arr,arr.length);
        for(int i=0;i<arr.length;i++){
            // 从指定位置开始构建大根堆
            int max = this.buildMaxRootHeapFromIndex(tempArr);
            arr[i] = max;
            // 去掉头
            tempArr = Arrays.copyOfRange(tempArr,1,tempArr.length);
        }
    }

    private int buildMaxRootHeapFromIndex(int[] arr) {
        for(int i=0;i<arr.length;i++){
            int j = i;
            while(j>0){
                boolean even = j % 2 == 0;
                int parent;
                int parentIndex;
                if(even){
                    parentIndex = (j-2)/2;
                }else{
                    parentIndex = (j-1)/2;
                }
                parent = arr[parentIndex];
                if(arr[j] > parent){
                    arr[parentIndex] = arr[j];
                    arr[j] = parent;
                    j = parentIndex;
                    continue;
                }
                break;
            }
        }
        return arr[0];
    }
}
