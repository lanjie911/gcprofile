package cn.bj.brook.algorithm.array.binarysearch;

/**
 * 搜索旋转排序升序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchRotateAscArray {

    /**
     * 思路是二分法
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left_pointer = 0;
        int right_pointer = nums.length-1;

        if(target == nums[left_pointer]){
            return left_pointer;
        }

        if(target == nums[right_pointer]){
            return right_pointer;
        }

        // 小于右侧（较小的部分）
        if(target < nums[right_pointer]){
            // 把左指针，向右移动到不能移动的部分
            left_pointer = moveLeftPointerToRight(left_pointer,right_pointer,target,nums);
            // 再双指针
            return left_pointer;
        }

        if(target > nums[left_pointer]){
            // 把右指针，向左移动到不能移动的部分
            right_pointer = moveRightPointerToLeft(left_pointer,right_pointer,target,nums);
            // 再双指针
            return right_pointer;
        }

        return -1;
    }

    private int moveLeftPointerToRight(int left_pointer, int right_pointer, int target, int[] nums) {
        int start = left_pointer;
        int end = right_pointer;
        while(left_pointer < right_pointer){
            // 处理相邻两个元素无限循环
            if(left_pointer+1 == right_pointer){
                if(nums[left_pointer] == target){
                    return left_pointer;
                }
                if(nums[right_pointer] == target){
                    return right_pointer;
                }
                return -1;
            }

            if(nums[left_pointer] > nums[right_pointer]){
                // 还在左半部分大数区域，不够，继续移动
                left_pointer = (left_pointer+right_pointer) / 2;
                continue;
            }
            // 判断target在什么区间
            if(target > nums[left_pointer]){
                // O了，已经落在一个小数 和 大数区间了，可以用二分法了
                return binarySearch(left_pointer,right_pointer,target,nums);
            }
            if(target < nums[left_pointer]){
                // 比这个数还小
                right_pointer = left_pointer;
                // 左指针继续向左移动，试图把区间变的更小
                left_pointer = (start + left_pointer) / 2;
                continue;
            }
            if(target == nums[left_pointer]){
                return left_pointer;
            }
        }
        return -1;
    }

    private int moveRightPointerToLeft(int left_pointer, int right_pointer, int target, int[] nums) {
        int start = left_pointer;
        int end = right_pointer;
        while(left_pointer < right_pointer){
            // 处理相邻两个元素无限循环
            if(left_pointer+1 == right_pointer){
                if(nums[left_pointer] == target){
                    return left_pointer;
                }
                if(nums[right_pointer] == target){
                    return right_pointer;
                }
                return -1;
            }
            if(nums[right_pointer] < nums[left_pointer]){
                // 还在右半部分小数区域，不够，继续移动
                right_pointer = (left_pointer+right_pointer) / 2;
                continue;
            }
            // 判断target在什么区间
            if(target < nums[right_pointer]){
                // O了，已经落在一个小数 和 大数区间了，可以用二分法了
                return binarySearch(left_pointer,right_pointer,target,nums);
            }
            if(target > nums[right_pointer]){
                // 比这个数还大
                left_pointer = right_pointer;
                // 右指针继续向右移动，试图把区间变的更大
                right_pointer = (right_pointer + end) / 2;
                continue;
            }
            if(target == nums[right_pointer]){
                return right_pointer;
            }
        }
        return -1;
    }

    public int binarySearch(int left_pointer, int right_pointer, int target, int[] nums) {

        int middle = -1;

        while(left_pointer < right_pointer){

            // 处理相邻两个元素无限循环
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

            return middle;

        }
        return middle;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 10, 11, 12, 13, 0, 1, 2, 3};
        SearchRotateAscArray s = new SearchRotateAscArray();
        int k = s.search(arr, 0);
        System.out.println(k);
    }
}
