package cn.bj.brook.algorithm.sort;

import java.util.Arrays;

/**
 * 09-分桶排序 CountingSort O(n+k)  数组外部排序 空间复杂度O(k)
 * 这种排序算法是根据数组里的最大元素的值，随便定义一个桶的数量
 * 然后计算出桶的容量（均匀分布），把元素按照其值的归属区间依次放入每个桶，在每个桶内进行排序
 * 然后把所有的桶的值联合起来
 * 比如：21 35 9 8 98 41，我们分5个桶
 * 桶1 0-19
 * 桶2 20-39
 * 桶3 40-59
 * 桶4 60-79
 * 桶5 80-99
 *
 * 那么
 *
 * 桶1 放 9，8
 * 桶2 放 21 ， 35
 * 桶3 放 41
 * 桶4 放 98
 *
 * 每个桶内进行插入式排序，然后联合
 * 桶排序非常好理解，可以方便的使用多线程进行处理，可以加速并行排序的过程（其他算法是不可以用多线程的）
 * 但是也有几个缺点：
 * 1 - 需要O(n)的复杂度计算出最大值，然后根据桶的数量计算出区间
 * 2 - 需要O(n)的复杂度把数组的值一个一个放进去
 * 3 - 需要O(每个桶内的排序复杂度)*桶的数量去计算内部排序 - 这个步骤是可以利用多线程的
 * 4 - 还需要一个O(n)的复杂度进行桶的联合
 */
public class BucketSort {


    private static class DefaultImpl implements SortFunction{

        private SortFunction is = InsertSort.newInstance();

        private int[] copyArray(int[] org,int m){
            int[] tmp = Arrays.copyOf(org,org.length+1);
            tmp[org.length] = m;
            return tmp;
        }

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
            int bucketCount = 5;
            // 桶的容量需要计算，桶少就必须装的多
            // 0...19, 20-39..., 40...59, 60...79, 80...99
            int bucketCapacity = (max-min) / bucketCount + 1;

            // 依次把数组的元素放入到不同的桶内
            System.out.println("桶的容量是:" + bucketCapacity);
            int[][] buckets = new int[bucketCount][];
            for (int i = 0; i < arr.length; i++) {
                int bucketNum = (arr[i]-min) / bucketCapacity;
                System.out.println("值：" + arr[i] + "应该放在第" + bucketNum + "个桶");
                int[] bucket = buckets[bucketNum];
                if(bucket == null){
                    bucket = new int[0];
                }
                bucket = copyArray(bucket,arr[i]);
                buckets[bucketNum] = bucket;
            }

            // 每个桶内的元素做插入式排序
            // TODO some bugs here
            for(int i=0;i<buckets.length;i++){
                // SortAlgorithmFrame frame = new SortAlgorithmFrame();
                // 桶内做插入式排序
                // frame.sort(buckets[i],is);
                //SortUtil.print(buckets[i]);
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
    }

    public static void main(String[] args) {

        int[] arr = SortUtil.generateWithMinus(1000,12);
        SortUtil.print(arr);

        SortAlgorithmFrame frame = new SortAlgorithmFrame(100, 17);
        frame.sort(arr,new BucketSort.DefaultImpl());
    }
}
