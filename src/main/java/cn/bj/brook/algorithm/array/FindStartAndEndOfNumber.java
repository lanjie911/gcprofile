package cn.bj.brook.algorithm.array;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindStartAndEndOfNumber {

    // 此种解法是O(n)级别的复杂度
    // 题目要求是O(log n)级别的复杂度
    public int[] searchRange(int[] nums, int target) {

        if(nums.length == 0){
            return new int[]{-1,-1};
        }

        // 思路
        // 使用双指针向内收敛
        // 如果头指针和尾指针碰到一起就结束循环
        int head = 0;
        int tail = nums.length-1;
        boolean foundHead = false;
        boolean foundTail = false;
        while(head < tail && (!foundHead || !foundTail)){
            if(nums[head] != target){
                head++;
            }else{
                foundHead = true;
            }
            if(nums[tail] != target){
                tail--;
            }else{
                foundTail = true;
            }
        }
        if(head==tail && nums[head] == target){
            foundHead = true;
            foundTail = true;
        }
        // 尾巴比头大
        if(foundHead && !foundTail){
            tail = head;
        }
        if(!foundHead && foundTail){
            head = tail;
        }
        if(!foundTail && !foundHead){
            head = -1;
            tail = -1;
        }
        return new int[]{head,tail};
    }

    // 采用lgN的复杂度 - 因为是升序数组
    // 思路就是头跳，尾跳
    // 有以下几种情况
    // 跳过去一下子跳过了比target大，那么就把tail置为这一跳
    // 跳过去还没够得着target，那么就把head置为这一跳
    // 跳过去恰好踩到了，然后以这个为中心向两边扩散，直到发现小于和大于的为止
    public int[] searchRangeInLgNTime(int[] nums, int target) {
        if(nums.length == 0){
            return new int[]{-1,-1};
        }
        // 跳跃指针
        int jumper = 0;
        int head = 0;
        int tail = nums.length-1;
        boolean found = false;
        while (head <= tail){
            if(target > nums[tail] || target < nums[head]){
                head = -1;
                tail = -1;
                break;
            }
            // 存在一个问题就是head + tail除以2始终不会变化
            // 比如0+1 /2 4+5 /2 这些数字会把jumper指针钉住
            // 要解决这个问题，头尾指针相邻时单独处理
            if(head+1 == tail){
                if(nums[head] == target && nums[tail] == target){
                    return new int[]{head, tail};
                }
                if(nums[head] != target && nums[tail] == target){
                    return new int[]{tail, tail};
                }
                if(nums[head] == target && nums[tail] != target){
                    return new int[]{head, head};
                }
                if(nums[head] != target && nums[tail] != target){
                    return new int[]{-1, -1};
                }
            }
            // 如果间距仍然很远
            jumper = (head+tail) / 2;
            if(nums[jumper] == target){
                // 找到了，开始向两边扩散
                found = true;
                head = jumper;
                boolean changed = false;
                while(head >=0 && nums[head] == target){
                    head--;
                    changed = true;
                }
                if(changed){
                    head++;
                }
                tail = jumper;
                changed = false;
                while(tail <nums.length && nums[tail] == target){
                    tail++;
                    changed = true;
                }
                if(changed){
                    tail--;
                }
                break;
            }else if(nums[jumper] > target){
                tail = jumper;
            }else if(nums[jumper] < target){
                head = jumper;
            }
        }
        if(!found){
            return new int[]{-1,-1};
        }
        return new int[]{head,tail};
    }
}
