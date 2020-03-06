package cn.bj.brook.algorithm.greedy;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 *
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeapLeastStepsGame {
    /**
     * 解题思路，在自己的范围允许内，找到一个能跳到范围的最大值，即
     * 我每走一步，看这范围内的一步，谁离终点最近，谁就是我要跳到的地方
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int i=0;
        int jump_times = 0;
        while(i<nums.length-1){
            // 当前元素的"范围"，即够得着的地方
            int scope = nums[i];
            // 这是一个[(结尾脚标 - 该元素脚标) - 元素值]的最小值
            int local_min_distance = Integer.MAX_VALUE;
            int currentIndex = 0;
            // 最后一步，距离已经超过终点就是他了
            if(nums[i]>=(nums.length-1-i)){
                jump_times++;
                break;
            }
            // 从当前节点够得着的范围内每一步
            // 每一步，谁离终点最近，谁就是要找的那个节点
            for(int j=1;j<=scope;j++){
                // 如果跳，跳到数据的第i+j个元素
                int step = nums[i+j];
                // 第i+j个元素距离结尾元素的距离
                int distance = (nums.length-1)-(i+j);
                // 那么就找到一个距离结尾最小的元素，即[(结尾脚标 - 该元素脚标) - 元素值]的最小值
                int leap_length = Math.abs(step-distance);
                System.out.println("元素脚标i="+i+",值为num["+i+"]="+nums[i]+",范围scope="+scope+",当前范围j="+j+",num["+i+"+"+j+"]="+step+",距离distance="+distance+",飞跃差leap_len="+leap_length);
                if(step >= distance){
                    // 从第(i+j)个元素，一步到位，到达结尾
                    // 为什么要+2是因为跳到这个(i+j)节点还要再跳一次
                    return jump_times+=2;
                }else{
                    // 如果第(i+j)个元素仍然够不到结尾
                    // 那么就找到一个距离结尾最小的元素，即[(结尾脚标 - 该元素脚标) - 元素值]的最小值
                    if(leap_length<=local_min_distance){
                        local_min_distance = leap_length;
                        currentIndex = i+j;
                    }
                }
            }
            i = currentIndex;
            jump_times++;
        }
        return jump_times;
    }

    public static void main(String[] args) {
        LeapLeastStepsGame g = new LeapLeastStepsGame();
        int k = g.jump(new int[]{10,9,8,7,6,5,4,3,2,1,1,0});
        System.out.println(k);
    }
}
