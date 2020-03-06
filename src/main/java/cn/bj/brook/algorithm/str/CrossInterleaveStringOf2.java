package cn.bj.brook.algorithm.str;

import java.util.Arrays;

/**
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CrossInterleaveStringOf2 {

    // 思路交叉迭代
    // s1 = a b c d e f
    // s2 = 1 2 3 4 5 6
    // t  = a b c d e f 1 2 3 4 5 6
    // 看底下示意图

    // 初始状态
    //        overlap_right
    //           |
    // a b c d e f 1 2 3 4 5 6
    //             |         |
    //       overlap_left   end

    // 第1步： - 初始交换
    //      overlap_left    end
    //           |           |
    // a b c d e 1 f 2 3 4 5 6
    //             |
    //         overlap_right

    // 第2步： - 持续交换 overlap_left -1 和 overlap-right+1的内容，每次交换都是两两交换，注意不要越界
    //     overlap_left    end
    //         |             |
    // a b c d 1 e 2 f 3 4 5 6
    //               |
    //              overlap_right

    // 交换完了和目标到s3对比一下，是否是相同到字符串，如果是就停止，不是继续，直到

    // 第k步：- 交换方向，当overlao_left打头时，overlap_left要向内收，当overlap_right结尾时，也要向内收
    //    overlap_left            end
    //         |                   |
    //         1 a 2 b 3 c 4 d 5 e 6 f
    //                               |
    //                              overlap_right

    // 第n步:当left和right再次重逢时，循环结束
    //                      left right
    //                        |  |
    //         1  2  3  4  5  a  6  b  c  d  e  f
    //

    // 所以需要每次在after_start 和 front_end的数组元素两两交换
    // 当front_end 大于 after_end的位置时，如果after_end定位到了数组2到长度+1到位置就停下
    public boolean isInterleave(String s1, String s2, String s3) {

        // 对于长度小于等于1的处理
        // 互相交换即可
        if(s1.length() <= 1 && s2.length() <= 1){
            return s3.equals(s1+s2) || s3.equals(s2+s1);
        }

        // 让大的在前面
        if(s1.length()<s2.length()){
            String temp = s2;
            s2 = s1;
            s1 = temp;
        }

        String t = s1+s2;
        char[] total = t.toCharArray();
        int overlap_right = s1.length()-1;
        int overlap_left = overlap_right+1;
        int endPointer = total.length - 1;
        // 初始方向向两边扩展
        String left_direction = "expand";
        String right_direction = "expand";
        while(endPointer>s2.length()){
            // 计算坐标
            if(overlap_right < overlap_left){
                // 初始第一次交换
                changeRange(total, overlap_right, overlap_left);
                // 对换坐标值
                int temp = overlap_left;
                overlap_left = overlap_right;
                overlap_right = temp;
            }else{
                // 交叠区域达到左边界，位置0，掉头
                if(overlap_left == 0 && "expand".equals(left_direction)){
                    left_direction = "shrink";
                }
                // 交叠区域达到右边界，位置结尾，掉头
                if(overlap_right == total.length-1 && "expand".equals(right_direction)){
                    right_direction = "shrink";
                }
                // 决策交叠区域的方向
                if(left_direction.equals("expand")){
                    overlap_left--;
                }else{
                    overlap_left++;
                }
                if(right_direction.equals("expand")){
                    overlap_right++;
                }else{
                    overlap_right--;
                    endPointer--;
                }

                changeRange(total, overlap_left, overlap_right);
            }
            System.out.println(Arrays.toString(total));
            String my = new String(total);
            if(my.equals(s3)){
                return true;
            }
        }
        return false;
    }

    private void changeRange(char[] arr,int begin, int end) {
        for(int i = begin;i<end;i+=2){
            char temp = arr[i+1];
            arr[i+1] = arr[i];
            arr[i] = temp;
        }
    }
}
