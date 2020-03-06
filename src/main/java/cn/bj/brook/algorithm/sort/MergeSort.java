package cn.bj.brook.algorithm.sort;

/**
 * 04 - 归并排序，递归，排序两个有序数组 O(N*LogN)
 */

import java.util.Arrays;

public class MergeSort implements SortFunction {

    public void sort(int[] arr) {
        int[] rs = sort0(arr);
        // 把数组拷贝到原数组
        System.arraycopy(rs,0,arr,0,rs.length);
    }

    private int[] sort0(int[] arr){
        if(arr.length <= 1){
            return arr;
        }
        int mid = arr.length / 2;
        return merge(sort0(Arrays.copyOfRange(arr,0,mid)),sort0(Arrays.copyOfRange(arr,mid,arr.length)));
    }

    private int[] merge(int[]a, int[] b){
        int sz = a.length+b.length;
        int t[] = new int[sz];
        int leftPointer = 0;
        int rightPointer = 0;
        int i = 0;
        while(i<sz){
            if(a[leftPointer]<b[rightPointer]){
                t[i] = a[leftPointer];
                leftPointer++;
                i++;
                if(leftPointer==a.length){
                    for(;rightPointer<b.length;rightPointer++){
                        t[i] = b[rightPointer];
                        i++;
                    }
                    break;
                }
            }else{
                t[i]=b[rightPointer];
                rightPointer++;
                i++;
                if(rightPointer==b.length){
                    for(;leftPointer<a.length;leftPointer++){
                        t[i] = a[leftPointer];
                        i++;
                    }
                    break;
                }
            }
        }
        return t;
    }
}
