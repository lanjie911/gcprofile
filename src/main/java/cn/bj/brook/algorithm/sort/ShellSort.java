package cn.bj.brook.algorithm.sort;

/**
 * 04-希尔排序 O(N*logN)
 * 希尔排序是插入排序(InsertSort)都改进版
 * 它把较大的数据集合分割成若干个小组（逻辑上分组），
 * 然后对每一个小组分别进行插入排序，此时，
 * 插入排序所作用的数据量比较小（每一个小组），插入的效率比较高
 *
 * 例子如下：
 * [77,61,0] => gap = 3/2 =1 (数组的长度/2)
 * 以gap=1为距离交换元素
 * [61,77,0]
 * [61,77,77]
 * [61,61,77]
 * [0,61,77]
 *
 */
public class ShellSort {
    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100,3);
        frame.sort(new int[]{77,61,0},(arr)->{
            int N = arr.length;
            // 每次都折半排序
            for(int gap=N/2; gap>0; gap /=2){
                // 对每个逻辑上对分组（其实就是以gap作为距离，数组中两个元素对换）进行插入式排序
                for(int i=gap; i< N; i++){
                    int inserted = arr[i];
                    int j;
                    // 组内两个元素对调，比如第6和第0，第7和第1，第8和第2...
                    for(j=i-gap;j>=0 && inserted< arr[j];j-=gap){
                        arr[j+gap] = arr[j];
                    }
                    arr[j+gap] = inserted;
                }
            }
        });
    }
}
