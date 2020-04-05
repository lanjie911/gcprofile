package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;

public class ReverseShellSort implements SortFunction {
    @Override
    public void sort(int[] arr) {
        int N = arr.length;
        // 每次都折半排序
        for (int gap = N / 2; gap > 0; gap /= 2) {
            // 对每个逻辑上对分组（其实就是以gap作为距离，数组中两个元素对换）进行插入式排序
            for (int i = gap; i < N; i++) {
                int inserted = arr[i];
                int j;
                // 组内两个元素对调，比如第6和第0，第7和第1，第8和第2...
                for (j = i - gap; j >= 0 && inserted > arr[j]; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = inserted;
            }
        }
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100, 17);
        frame.sort(new ReverseShellSort());
    }
}
