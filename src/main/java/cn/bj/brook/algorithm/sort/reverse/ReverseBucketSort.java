package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ReverseBucketSort implements SortFunction {

    private int bucketNumber = 10;

    public ReverseBucketSort setBucketNumber(int n){
        bucketNumber = n;
        return this;
    }

    private int[] copyArray(int[] org, int m) {
        int[] tmp = Arrays.copyOf(org, org.length + 1);
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
        int bucketCount = bucketNumber;
        // 桶的容量需要计算，桶少就必须装的多
        int bucketCapacity = (max - min) / bucketCount + 1;

        // 依次把数组的元素放入到不同的桶内
        int[][] buckets = new int[bucketCount][];
        for (int i = 0; i < arr.length; i++) {
            int bucketNum = (arr[i] - min) / bucketCapacity;
            int[] bucket = buckets[bucketNum];
            if (bucket == null) {
                bucket = new int[0];
            }
            bucket = copyArray(bucket, arr[i]);
            buckets[bucketNum] = bucket;
        }

        // 每个桶内的元素做插入式排序
        for (int i = 0; i < buckets.length; i++) {
            final int[] bucket = buckets[i];
            if (bucket == null || bucket.length == 0) {
                continue;
            }
            Thread thread = new Thread(() -> {
                Arrays.sort(bucket);
                counter.decrementAndGet();
            });
            counter.incrementAndGet();
            thread.start();
        }

        while (counter.getAcquire() > 0) {
            continue;
        }

        // 拼接桶之间的元素
        for (int i = 0; i < arr.length; i++) {
            for (int j = buckets.length - 1; j >= 0; j--) {
                int[] m = buckets[j];
                if (m == null || m.length == 0) {
                    continue;
                }
                for (int k = m.length - 1; k >= 0; k--) {
                    arr[i] = m[k];
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(1000, 30);
        frame.sort(new ReverseBucketSort());
    }
}
