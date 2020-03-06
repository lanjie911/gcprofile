package cn.bj.brook.algorithm.heap;

import java.util.Arrays;

/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解决思路有三种：
 * 1. 暴力法
 * 2. 插入排序法
 * 3. 维护一个K个元素的小根堆
 *
 */
public class KthLargest {

    private int[] nums;

    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.nums = nums;
        // 配合方法add3使用
        // this.sortWithSelection(this.nums, this.nums.length);

        // 仅初始化最大的K个元素
        // 把游标左侧的作为最大K个元素的池子
        // 每当有元素进来的时候，如果比这个池子里的元素要大，那么就换掉这个元素
        // 把被换掉的元素放入到数组结尾
        // 比如
        // [4, 10, 2, 8, 0, 3, 9]，k=3
        // 初始化后
        // [10, 9, 8, | 4, 2, 0, 3]
        // 放入一个元素 11
        // [11, 10, 9, | 4, 2, 0, 3, 8]
        this.sortWithSelection(this.nums, this.k);

    }

    // 效率较高的选择排序
    // 选到合适的就停止
    private void sortWithSelection(int[] arr, int len) {
        // 确定游标的位置
        // 游标是不断向后游走的
        // 游标是分隔着已经排好序和未排序的界限
        // 游标左侧是已经排好序的
        // 游标右侧是未排序仍然可以选择最（大）值的池子
        // 游标从0开始，意味着一开始整个数组都是被选择的池子
        // 游标达到数组的length的时候，计算结束
        int cursor = 0;

        // 游标只要跨过了合适的长度就停止
        if(len > arr.length){
            len = arr.length;
        }
        while (cursor < len) {
            // 记录一个最大值
            int max = Integer.MIN_VALUE;
            // 记录当前最大值的脚标
            int j = cursor;
            // 从游标的右侧(剩余的池子)
            for (int i = cursor; i < arr.length; i++) {
                // 如果找到了最大值
                if (arr[i] > max) {
                    max = arr[i];
                    j = i;
                }
            }
            // 把max和当前的游标指向的元素交换位置
            // 这个地方可以优化
            // 如果cursor的值和j的值相等
            // 就可以避免一次交换
            // 即cursor == j 就不交换
            int temp = arr[cursor];
            arr[cursor] = max;
            arr[j] = temp;
            // 游标向右拨动一格，游标左侧就是排好序的
            cursor++;
        }
    }

    // 此算法效率太低
    // 暴力法
    public int add2(int val) {
        this.nums = Arrays.copyOf(this.nums, this.nums.length + 1);
        this.nums[this.nums.length - 1] = val;
        Arrays.sort(this.nums);
        if (k >= this.nums.length) {
            return this.nums[0];
        }
        int tail = this.nums.length - 1;
        for (int i = 0; i < this.k; i++) {
            tail--;
        }
        return this.nums[tail + 1];
    }

    // 此方法是保存一个初始化（排好序）到数组
    // 然后每次add的时候，实际上是动态插入一个元素
    // 效率略有提升（从700ms提升到350ms）
    // 但是仍然难以满足
    // 同时要求在进行构造函数时就必须初始化顺序
    // 在最差复杂度的情况下，该方法可能会遍历所有的数组元素
    public int add3(int val) {
        int size = this.nums.length;
        int i = 0;
        // 需要把新的val值插入到合适的位置
        // 这个遍历就是找到插入的位置i
        for (; i < size; i++) {
            if (val > this.nums[i]) {
                break;
            }
        }

        // 将数组扩大1个栏位
        this.nums = Arrays.copyOf(this.nums, size + 1);
        // 从原数组最后1个数开始往前倒
        int j = this.nums.length - 1;
        // 把原数组最后一个数移动到新数组的最后1位
        // 前面的依次往后挪
        // 直接移动到上文中被break掉的第i位
        while (j > i) {
            this.nums[j] = this.nums[j - 1];
            j--;
        }
        // 将新插入的这个值放到第i个位置
        this.nums[j] = val;

        if (k >= this.nums.length) {
            return this.nums[size];
        } else {
            return this.nums[k - 1];
        }
    }

    // 最佳方法是维持一个额外的小数组，长度为K(实际这个小数组空间复杂度完全可以是o(1)，就是使用原来的数据插入排序的左侧部分）
    // 这个小数组保持着数组中最大的K个元素
    // 当有新元素进来的时候，如果新加入的元素比这个小数组的任意一个元素大
    // 则把这个小数组的最小值踢出去，重建这个小数组
    // 如果新加入的元素比这个小数组的任意一个元素都小，那就只复制到数组+1，其他操作不做
    public int add(int val){
        int size = this.nums.length;
        this.nums = Arrays.copyOf(this.nums,size+1);

        // 对于零数组的处理
        if(size == 0){
            this.nums = new int[1];
            this.nums[0] = val;
            return val;
        }

        // 不是零数组
        boolean evictedMin = false;
        for(int i=0;i<k;i++){
            if(val > this.nums[i]){
                // 把最小的那个驱逐出去
                this.nums[size] = this.nums[k-1];
                for(int j=(k-1);j>i;j--){
                    this.nums[j] = this.nums[j-1];
                }
                this.nums[i] = val;
                evictedMin = true;
                break;
            }
        }
        if(!evictedMin){
            // 没有驱逐过最小元素
            this.nums[size] = val;
        }

        if(k >= this.nums.length){
            return this.nums[this.nums.length-1];
        }

        return this.nums[k-1];
    }


    public static void main(String[] args) {
        KthLargest k = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(k.add(3));
        System.out.println(k.add(5));
        System.out.println(k.add(10));
        System.out.println(k.add(9));
        System.out.println(k.add(4));
    }
}
