package cn.bj.brook.interview;

/**
 * 蚂蚁金服面试题
 * 找到数组中连续子数组乘积的最大值
 * 数组可以包含负数和0
 * 比如
 * [-2,0,-1]最大乘积是0
 * [2,4,-2,4]最大乘积是8
 * 面试时注意和面试官约定好，非空，1个数组元素和相乘的结果不要溢出
 */
public class MaxMultipleValueInArray {
    /**
     * 解题思路
     * 显然是DP动态规划
     * 遍历数组时计算当前最大值，不断更新
     * 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
     * 当负数出现时则imax与imin进行交换再进行下一步计算
     * 时间复杂度：O(n)
     */
    public int max(int[] nums){
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }
}
