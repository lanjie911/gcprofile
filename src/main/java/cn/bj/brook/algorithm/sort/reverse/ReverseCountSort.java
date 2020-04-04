package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;

public class ReverseCountSort implements SortFunction {
    @Override
    public void sort(int[] arr) {
        // 找到最大和最小值
        int min = 0;
        int max = 0;
        for(int i=0;i<arr.length;i++){
            if(i==0){
                min = arr[i];
                max = arr[i];
                continue;
            }
            if(arr[i]<min){
                min = arr[i];
                continue;
            }
            if(arr[i]>max){
                max = arr[i];
                continue;
            }
        }
        // 计算区间，用来初始化差异数组
        int gap = max-min+1;
        int[] counts = new int[gap];
        // 把差异数组算出来，这个数组保存的元素是每个元素和最小值的差
        for(int i=0;i<arr.length;i++){
            counts[arr[i]-min] += 1;
        }

        // 然后重新排序，原数组从0开始加差异，最小的就是min自己
        int k = 0;
        for(int i=counts.length-1;i>=0;i--){
            // 差异为0的证明不存在元素
            int number = counts[i];
            while(number>0){
                arr[k] = i+min;
                number--;
                k++;
            }
        }
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100,8);
        frame.sort(new ReverseCountSort());
    }
}
