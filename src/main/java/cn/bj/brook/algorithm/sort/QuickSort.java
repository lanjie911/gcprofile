package cn.bj.brook.algorithm.sort;

/**
 * 06-快速排序 QuickSort O(N*LogN)  数组外部排序 空间复杂度O(N*LogN)
 * 找到一个pivot，可以选择两边
 * 也可以选择中间位置，然后，让这个数组内的pivot左边都比其小，右边都比其大
 * 然后再以pivot为界，分为两部分，继续递归
 */
public class QuickSort implements SortFunction {

    private int moveElement(int[] ts, int pivot, int head, int tail) {
        // 定义枢轴量
        int pos = pivot;
        //System.out.println("分区内待移动的数组=" + SortUtil.toString(ts) + ":pivot["+pivot+"]="+ts[pivot]+";head=" + head + ";tail=" + tail);
        for (int i = head; i <= tail;) {
            if ((i < pos && ts[i] > ts[pos])) {
                // 在pivot左边，值却比pivot大
                // 需要让pivot向左跳，和该元素互换位置
                // 然后循环指针从新的pivot位置开始向右+
                // 比如 2 8 12 6 3 7, i=1, pivot=6
                // 发现 8 > 6，交换 arr[1] 和 arr[3]
                // 枢轴量新的位置是 1
                // 从下一位开始执行继续比较
                int temp = ts[i];
                ts[i] = ts[pos];
                ts[pos] = temp;

                // 新的枢轴量的位置
                pos = i;
                i++;
                continue;
            }
            // 在枢轴量pivot右面的元素却比枢轴量小
            // 让该元素和枢轴量互换位置
            // 换完位置之后从该元素新的位置下一个开始循环
            // 比如 2 6 12 8 3 7, i=2, pivot=6
            // 发现 3 < 6，交换 arr[4] 和 arr[2]
            // 枢轴量新的位置是 4
            // i指针回退到2继续重新从老位置2的下一位开始执行继续比较
            if((i > pos && ts[i] < ts[pos])){
                int temp = ts[i];
                int old_pos = pos;
                ts[i] = ts[pos];
                ts[pos] = temp;

                // 新的枢轴量的位置
                pos = i;

                // 新的计数器的位置
                i = old_pos+1;
                continue;
            }
            i++;
        }
        return pos;
    }

    // 递归调用
    // 以二分法的中间一个点的数字作为分区种子
    // 小于这个数的全都放在左边
    // 大于的放到右边
    // 不停的二分直到最后一个
    private void partition(int[] arr, int left, int right) {
        if ((right-left)<=0) {
            return;
        }
        int pivotPos = (left + right) / 2;
        //System.out.println("选中对pivot是arr[" + pivotPos + "]=" + arr[pivotPos]);
        // 这个枢轴量最开始找到是数组中间的一个数
        // 先把整个数组分为两部分，左边的都比第一个枢轴量小，右边的都比枢轴量大
        pivotPos = moveElement(arr, pivotPos, left, right);
        // 以枢轴量为界，分成两部分
        partition(arr, left, pivotPos);
        partition(arr, pivotPos + 1, right);
    }

    @Override
    public void sort(int[] arr) {
        partition(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        SortAlgorithmFrame frame = SortAlgorithmFrame.build(100, 10);
        frame.sort(new QuickSort());
    }
}
