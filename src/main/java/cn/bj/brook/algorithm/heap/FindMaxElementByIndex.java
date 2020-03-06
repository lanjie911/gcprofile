package cn.bj.brook.algorithm.heap;

import java.util.Arrays;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMaxElementByIndex {
    public int findKthLargest(int[] nums, int k) {
        int result = -1;
        // 循环k次
        for(int i=0;i<k;i++){
            // 每次都找到一个新的大堆的根
            buildMaxRootHeap(nums);
            // 这个根就是第k个最大的
            result = nums[0];
            // 缩短数组
            nums = Arrays.copyOfRange(nums,1,nums.length);
        }
        return result;
    }

    // 需要用到大根堆排序
    private void buildMaxRootHeap(int[] arr){
        HeapUtil.buildMaxRootHeap(arr);
    }

    public static void main(String[] args) {
        FindMaxElementByIndex i = new FindMaxElementByIndex();
        int m = i.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6},4);
        System.out.println(m);
    }
}
