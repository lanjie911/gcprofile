package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.math.Calculator;
import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ReverseBucketSort implements SortFunction {

    private int[] copyArray(int[] org,int m){
        int[] tmp = Arrays.copyOf(org,org.length+1);
        tmp[org.length] = m;
        return tmp;
    }

    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void sort(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        //找到最大值和最小值
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                continue;
            }
            if (arr[i] < min) {
                min = arr[i];
                continue;
            }
        }
        //定义桶的数量
        int bucketCount = 10;
        // 桶的容量需要计算，桶少就必须装的多
        int bucketCapacity = (max-min) / bucketCount + 1;

        // 依次把数组的元素放入到不同的桶内
        int[][] buckets = new int[bucketCount][];
        for (int i = 0; i < arr.length; i++) {
            int bucketNum = (arr[i]-min) / bucketCapacity;
            int[] bucket = buckets[bucketNum];
            if(bucket == null){
                bucket = new int[0];
            }
            bucket = copyArray(bucket,arr[i]);
            buckets[bucketNum] = bucket;
        }

        // 每个桶内的元素做插入式排序
        for(int i=0;i<buckets.length;i++){
            CalculatorX x = new CalculatorX(buckets, i);
            Thread t = new Thread(x);
            counter.incrementAndGet();
            t.start();

        }

        // 拼接桶之间的元素
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<buckets.length;j++){
                int[] m = buckets[j];
                for(int k=0;k<m.length;k++){
                    arr[i] = m[k];
                    i++;
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(1000, 30);
        frame.sort(new ReverseBucketSort());
    }
}

class CalculatorX implements Runnable {

    private int[][] buckets;
    private int pos;

    public CalculatorX(int[][] arr, int i){
        this.buckets = arr;
        pos = i;
    }

    @Override
    public void run() {
        int[] bucket = buckets[pos];
        Arrays.sort(bucket);
        buckets[pos] = bucket;
    }
}
