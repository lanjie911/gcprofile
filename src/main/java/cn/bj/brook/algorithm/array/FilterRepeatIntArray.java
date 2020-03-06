package cn.bj.brook.algorithm.array;

/**
 * 去重整形数组
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FilterRepeatIntArray {
    // 本题难度主要在于空间复杂度
    // 如果允许数组拷贝这肯定是比较简单的
    // 问题是在原数组上实现O(n)
    // 本算法是o(n^2)
    public int removeDuplicatesInOrgArray(int[] nums) {

        // 定义双指针，this指针指向当前元素
        // next指针指向下一个，只要二者相等，就一直移动next，直到不想等，然后
        // 把next赋值给this
        int pointer_this = 0;
        int pointer_next = pointer_this;

        while (pointer_next < nums.length) {
            int this_val = nums[pointer_this];
            if (pointer_next + 1 >= nums.length) {
                return pointer_this + 1;
            }
            pointer_next++;
            int next_val = nums[pointer_next];

            // 后面的值和前面的相等
            if (next_val <= this_val) {
                // 继续向下找一个不相同的
                while (++pointer_next < nums.length) {
                    if (nums[pointer_next] <= this_val) {
                        // 如果还相等，继续减
                        continue;
                    } else {
                        // 找到一个不想等的，把这个挪到当前位置的下一个档位
                        nums[pointer_this + 1] = nums[pointer_next];
                        // 当前游标往下挪动
                        pointer_this++;
                        pointer_next = pointer_this;
                        break;
                    }
                }
            } else {
                pointer_this++;
                pointer_next = pointer_this;
            }
        }

        return pointer_this + 1;
    }

    // 快慢指针1次遍历
    public int removeDuplicatesInOrgArrayFastIterator(int[] nums) {

        if(nums.length == 0){
            return 0;
        }

        // 定义快慢指针，慢指针一个一个往前跳，快指针往下遍历更大的元素
        int pointer_slow = 1;
        int pointer_fast = 1;
        int prev_element = nums[0];
        for (; pointer_fast < nums.length; ) {
            // 如果慢指针指向的元素和前一个相同
            if (nums[pointer_slow] <= prev_element) {
                // 找到快指针指向的一个比自己大的元素，替换掉自己的位置
                // 快指针不要动，慢指针继续往下走一步
                if (nums[pointer_fast] > prev_element) {
                    // 直接赋值
                    nums[pointer_slow] = nums[pointer_fast];
                    pointer_fast++;
                } else {
                    while (true) {
                        if (++pointer_fast >= nums.length) {
                            return pointer_slow;
                        }
                        if (nums[pointer_fast] <= prev_element) {
                            continue;
                        }
                        if (nums[pointer_fast] > prev_element) {
                            nums[pointer_slow] = nums[pointer_fast];
                            break;
                        }
                    }
                }
            }
            // 前一个元素被替换掉
            prev_element = nums[pointer_slow];
            // 慢指针向下移动一格
            pointer_slow++;
            // 如果快指针被慢指针追上了，则快指针向下走一步
            if (pointer_fast <= pointer_slow) {
                pointer_fast++;
            }
        }
        return pointer_slow;
    }


    // 使用一个新数组来去重
    public int removeDuplicatesInNewArray(int[] nums) {
        int[] newArray = new int[nums.length];
        int curr = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            curr = nums[i];
            if (j == 0) {
                newArray[j] = curr;
                j++;
            } else {
                // 只有比前一个数大才能放进去
                if (curr > newArray[j - 1]) {
                    newArray[j] = curr;
                    j++;
                }
            }
        }
        for (int i = 0; i < j; i++) {
            nums[i] = newArray[i];
        }
        return j;
    }
}
