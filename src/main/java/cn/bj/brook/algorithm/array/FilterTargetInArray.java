package cn.bj.brook.algorithm.array;

import cn.bj.brook.algorithm.sort.SortUtil;

/**
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [3,2,2,3], val = 3,
 * <p>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * <p>
 * 注意这五个元素可为任意顺序。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FilterTargetInArray {
    public int removeTargetElementInArray(int[] nums, int target) {
        int cursor = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                nums[cursor] = nums[i];
                cursor++;
                continue;
            }
        }
        return cursor++;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 2, 3};
        FilterTargetInArray ftia = new FilterTargetInArray();
        int t = ftia.removeTargetElementInArray(arr, 3);
        SortUtil.print(arr);
        System.out.println("t=" + t);
        arr = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        t = ftia.removeTargetElementInArray(arr, 2);
        SortUtil.print(arr);
        System.out.println("t=" + t);
    }
}
