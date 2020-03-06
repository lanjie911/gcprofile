package cn.bj.brook.algorithm.slidingwindow;

import cn.bj.brook.algorithm.sort.SortUtil;

import java.util.Arrays;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *  
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxValueFromSlidingWindowInArray {

    // 思路 - 比较好的解法是线性解法
    // 即在每个窗口中都是找到最大值
    // 找到值的顺序是排序大根堆
    // 一个优化的方案是如果这个值比之前的大根堆都大，那就是新值
    // 如果不如新的值那么就是之前的值（在有效期内）
    // 由于最大值的有效期可能会因为窗口滑动而失效，所以要处理
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 定义最终输出的数组
        // m个元素的n滑动窗口最大值是m-n+1
        int[] rs = new int[nums.length - k + 1];
        int i = 0;

        // 定义窗口左边和右边
        // 题目说左右都是合法的值
        // 所以我们就不再做这个过滤了
        int window_left_edge = 0;
        int window_right_edge = window_left_edge + k;

        int previousMax = Integer.MIN_VALUE;
        // 前一个窗口的最大元素的脚标
        // 用来判定前一个窗口的最大元素是否还处于本窗口中
        int previousMaxIndex = -1;

        while (window_right_edge <= nums.length) {
            // 证明是初始滑动窗口
            // 需要初始化
            int[] sub_arr = null;
            // 第一次初始化
            if (previousMaxIndex < 0) {
                sub_arr = Arrays.copyOfRange(nums, window_left_edge, window_right_edge);
                int[] maxRootHeaps = this.searchValueByMaxRootHeap(sub_arr);
                // 把最大值和最大值的脚标记录下来
                previousMaxIndex = maxRootHeaps[0];
                // 这个脚标是相对于窗口左边边框的，所以要加上左边边框
                previousMax = window_left_edge + maxRootHeaps[1];
                rs[i] = previousMax;
                i++;
            } else {
                // 如果不是
                // 证明滑动过了
                // 那么可以有节省的地方
                // 如果上一个最大值，还在当前滑动窗口内
                if (window_left_edge <= previousMaxIndex) {
                    // 除非新滑动到的末尾元素比这个值大，就更新
                    if (nums[window_right_edge - 1] > previousMax) {
                        previousMax = nums[window_right_edge-1];
                        previousMaxIndex = window_right_edge-1;
                        rs[i] = previousMax;
                        i++;
                    }else{
                        // 还是前边的最大值
                        rs[i] = previousMax;
                        i++;
                    }
                } else {
                    // 离滑动窗口之外了
                    sub_arr = Arrays.copyOfRange(nums, window_left_edge, window_right_edge);
                    int[] maxRootHeaps = this.searchValueByMaxRootHeap(sub_arr);
                    // 把最大值和最大值的脚标记录下来
                    previousMaxIndex = window_left_edge + maxRootHeaps[0];
                    // 这个脚标是相对于窗口左边边框的，所以要加上左边边框
                    previousMax =  maxRootHeaps[1];
                    rs[i] = previousMax;
                    i++;
                }
            }
            window_left_edge++;
            window_right_edge++;
        }
        return rs;
    }

    // 返回大根堆的最大值
    // 返回数组，元素0是元素在这个数组中的脚标
    // 元素1是最大值本身
    private int[] searchValueByMaxRootHeap(int[] nums) {
        int maxIndex = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > max){
                maxIndex = i;
                max = nums[i];
            }
        }
        return new int[]{maxIndex,max};
    }

    public static void main(String[] args) {
        MaxValueFromSlidingWindowInArray max = new MaxValueFromSlidingWindowInArray();
        int[] s = max.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        SortUtil.print(s);
    }
}
