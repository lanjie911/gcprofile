package cn.bj.brook.algorithm.array.binarysearch;

import cn.bj.brook.algorithm.sort.SortUtil;

/**
 * 基本二分法查找元素
 */
public class BasicBinarySearch {
    /**
     * 思路是二分法
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left_pointer = 0;
        int right_pointer = nums.length - 1;
        int middle = -1;
        // 两个指针至少相隔2以上的距离
        while (left_pointer + 1 < right_pointer) {
            middle = (left_pointer + right_pointer) / 2;
            int middle_value = nums[middle];
            if (target > middle_value) {
                left_pointer = middle;
            } else if (target < middle_value) {
                right_pointer = middle;
            } else {
                return middle;
            }
        }
        // 最后处理数组中指针相邻的情况
        if (nums[left_pointer] == target) {
            return left_pointer;
        }
        if (nums[right_pointer] == target) {
            return right_pointer;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,7,9};
        BasicBinarySearch search = new BasicBinarySearch();
        int t = search.search(arr, 5);
        System.out.println("t="+t);
    }
}
