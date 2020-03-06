package cn.bj.brook.algorithm.greedy;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeapGame {
    /**
     * 解决思路是倒着找，找到0元素继续往前找，每一个元素到0元素到距离如果小于自己的值，只要有1个，就可以成功飞跃
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        // 数组为空不可以
        if(nums.length==0){
            return false;
        }

        // 起始位置为0，长度==1可以
        if(nums[0] == 0 && nums.length == 1){
            return true;
        }
        // 起始位置为0，长度大于1不可以
        if(nums[0] == 0 && nums.length > 1){
            return false;
        }

        // 任意长度为1的都可以
        if(nums.length == 1){
            return true;
        }

        int j = nums.length - 2;
        while(j>=0){
            // 处理0元素
            // 主要是0元素不能移动
            int current_num = nums[j];
            if(current_num == 0){
                // 看是否该0元素与前边所有元素的差都正好是元素的值本身
                int current = j;
                boolean isOverZeroDistance = false;
                while(--j>=0){

                    // 如果距离比元素值小，那么就可以完成跳跃，越过这个0
                    // 只要有一个元素能越过当前这个0，就是可以完成跳跃的
                    if(current - j < nums[j]){
                        isOverZeroDistance = true;
                        break;
                    }
                }
                if(isOverZeroDistance == false){
                    return false;
                }
            }else{
              j--;
            }
        }

        return true;
    }



    public static void main(String[] args) {
        LeapGame l = new LeapGame();
        boolean b = l.canJump(new int[]{3,1,2,0,4});
        System.out.println(b);
    }
}
