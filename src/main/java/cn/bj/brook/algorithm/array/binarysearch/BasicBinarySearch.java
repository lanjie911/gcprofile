package cn.bj.brook.algorithm.array.binarysearch;

/**
 * 基本二分法查找元素
 */
public class BasicBinarySearch {
    /**
     * 思路是二分法
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {

        if(nums.length == 1){
            return nums[0] == target?0:-1;
        }

        int left_pointer = 0;
        int right_pointer = nums.length-1;
        int middle = -1;

        while(left_pointer < right_pointer){

            // 处理相邻两个元素无限循环
            // 找不到返回-1
            if(left_pointer+1 == right_pointer){
                if(nums[left_pointer] == target){
                    return left_pointer;
                }
                if(nums[right_pointer] == target){
                    return right_pointer;
                }
                return -1;
            }

            middle = (left_pointer+right_pointer) / 2;
            int middle_value = nums[middle];
            // 目标值比中间值还大
            if(target > middle_value){
                left_pointer = middle;
                continue;
            }
            if(target < middle_value){
                right_pointer = middle;
                continue;
            }

            break;

        }
        return middle;
    }
}
