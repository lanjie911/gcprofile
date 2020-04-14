package cn.bj.brook.algorithm.array;

import cn.bj.brook.algorithm.array.binarysearch.BasicBinarySearch;
import cn.bj.brook.algorithm.sort.SortUtil;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindStartAndEndOfNumber {
    BasicBinarySearch util;

    public FindStartAndEndOfNumber() {
        util = new BasicBinarySearch();
    }

    public int[] searchRange(int[] array, int target) {
        if (array.length == 0) {
            return new int[]{-1, -1};
        }
        int pos = util.search(array, target);
        if (pos == -1) {
            return new int[]{-1, -1};
        }
        int head = pos, tail = pos;
        while (head >= 1 && array[--head] == target) {
        }
        if (array[head] != target) head++;
        while (tail < array.length - 1 && array[++tail] == target) {
        }
        if (array[tail] != target) tail--;
        return new int[]{head, tail};
    }

    public static void main(String[] args) {
        int[] data = new int[]{5, 7, 7, 8, 8, 10};
        FindStartAndEndOfNumber func = new FindStartAndEndOfNumber();
        int[] rs = func.searchRange(data, 8);
        SortUtil.print(rs);

        data = new int[]{5, 7, 7, 8, 8, 10};
        func = new FindStartAndEndOfNumber();
        rs = func.searchRange(data, 6);
        SortUtil.print(rs);
    }
}
