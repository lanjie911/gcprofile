package cn.bj.brook.algorithm.array.binarysearch;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchInsertPosition {
    // 解题思路是二分法查找
    // 复杂度可以到O(logN)
    // 使用顺序查找是O(n)到复杂度，不是最优化方案
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int head = 0;
        int tail = nums.length - 1;
        while (head + 1 < tail) {
            int pos = (head + tail) / 2;
            if (target > nums[pos]) {
                head = pos;
            } else if (target < nums[pos]) {
                tail = pos;
            } else {
                return pos;
            }
        }
        if (target <= nums[head]) {
            return head;
        } else if (target > nums[head] && target < nums[tail]) {
            return head + 1;
        } else if (target == nums[tail]) {
            return tail;
        } else {
            return tail + 1;
        }
    }

    public static void main(String[] args) {
        SearchInsertPosition sip = new SearchInsertPosition();
        int[] nums = new int[]{1, 3, 5, 6};
        int k = 5;
        int t = sip.searchInsert(nums, k);
        System.out.println("t=" + t);
    }
}
