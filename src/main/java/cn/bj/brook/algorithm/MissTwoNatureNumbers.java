package cn.bj.brook.algorithm;

import java.util.Arrays;

/**
 * 消失的两个数字
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 * <p>
 * 以任意顺序返回这两个数字均可。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1]
 * 输出: [2,3]
 * 示例 2:
 * <p>
 * 输入: [2,3]
 * 输出: [1,4]
 * 提示：
 * <p>
 * nums.length <= 30000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-two-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MissTwoNatureNumbers {
    public int[] missingTwo(int[] nums) {
        Arrays.sort(nums);
        int a = -1;
        int b = -1;
        int carry = 0;
        int i = 1;
        for (; i <= nums.length;) {
            if (nums[i - 1] > i + carry) {
                if (a == -1) {
                    a = i+carry;
                } else {
                    b = i+carry;
                }
                carry++;
                continue;
            }else{
                i++;
            }
        }
        if (a == -1 && b == -1) {
            a = i;
            b = i + 1;
        } else if (a > 0 && b == -1) {
            int last = nums[nums.length - 1];
            if (i < last) {
                b = i;
            } else {
                b = last + 1;
            }
        }
        return new int[]{a, b};
    }
}
