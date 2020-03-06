package cn.bj.brook.algorithm.sort.reverse;

import cn.bj.brook.algorithm.sort.SortAlgorithmFrame;
import cn.bj.brook.algorithm.sort.SortFunction;

/**
 * 插入式排序的反向排序
 * @see cn.bj.brook.algorithm.sort.InsertSort#sort(int[])
 */
public class ReverseInsertSort implements SortFunction {

    @Override
    public void sort(int[] arr) {
        // 插入式排序和选择式排序有异曲同工之妙
        // 相同之处在于都需要定义游标来分隔左右两部分
        // 游标右边都是没排好序的，即剩下的元素构成了一个池子
        // 游标左边都是已经排好序的
        // 不同之处在于
        // 插入式排序是从右边剩下的池子中依次选择一个元素
        // 然后向游标左边移动的时候找到合适的位置插入进去
        // 选择式排序是先选定一个确定大/小的值，然后无脑向前安置
        // 插入式排序是无脑选择一个元素，向前放置的时候按照顺序插入
        int cursor = 1;

        // 插入式排序从第二个元素开始计算
        // 第1个元素（脚标0）默认是排好序的
        while(cursor < arr.length){
            // 和前面的元素依次比较
            // 举个例子，演变如下
            // [43, 85, 0, 33, 5]
            // [43| 85, 0, 33, 5]
            // [43 <-> 85| 0, 33, 5]
            // [85  42| 0, 33, 5]
            // [85  42, 0| 33, 5]
            // [85  42, 0, 33| 5]
            // [85  42, 0 <-> 33| 5]
            // [85  42, 33, 0| 5]...
            for(int i=cursor-1,j=cursor;i>=0;i--){
                // 如果这个元素比前面的元素大
                if(arr[j] > arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    j--;
                }else{
                    // 因为前面都是排好序的
                    // 所以如果不如前面的元素大就停止就好了
                    break;
                }
            }
            cursor++;
        }
    }

    public static SortFunction newInstance(){
        return new ReverseInsertSort();
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = new SortAlgorithmFrame(100,5);
        frame.sort(ReverseInsertSort.newInstance());
    }
}
