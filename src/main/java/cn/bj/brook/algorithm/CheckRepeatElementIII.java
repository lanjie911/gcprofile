package cn.bj.brook.algorithm;

import java.util.TreeSet;

/**
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大
 * 为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckRepeatElementIII {
    // 思路使用一个内部有序的集合+滑动窗口

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 定义一个内部排序的集合
        // TreeSet内部实现是红黑树（自平衡二叉树）
        // 滑动窗口的大小就是k
        // 比如数组 1  2  3  1 k=3, t=0
        // 1    2    3    1
        // <-  窗口  ->
        // 遍历步骤
        // 找到1 集合是空
        // 把1放到集合中
        // 找到2，看集合中离自己最近的元素（分别是大于自己和小于自己的）和自己的差是否小于k，如果小于肯定是符合要求的
        // 这里集合中有1，1比2小，又在窗口内，所以符合要求
        // 这个算法的本质就是利用滑动窗口内部排序
        // 并且窗口移动的时候要保持窗口内的元素有序
        TreeSet<Integer> set = new TreeSet<>();
        // 遍历数组
        for (int i = 0; i < nums.length; ++i) {
            // 找到集合中比当前元素大的最小的一个
            Integer s = set.ceiling(nums[i]);
            // 如果这个数比自己大，要找到离自己最近的（最小的），即找最小最大数
            // TODO 加法会溢出 - 这里不严谨
            if (s != null && s <= nums[i] + t) return true;

            // 如果这个数比自己小，那么要找到离自己最近的（最大的），即找最大最小数
            Integer g = set.floor(nums[i]);
            // TODO 加法会溢出 - 这里不严谨
            if (g != null && nums[i] <= g + t) return true;

            // 能够执行到这个步骤，就把这个元素加入到窗口中
            // TODO 不严谨，集合如果遇到重复元素就瞎了
            set.add(nums[i]);

            // 如果窗口超过k了，移除掉最左侧的那个元素
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
}
