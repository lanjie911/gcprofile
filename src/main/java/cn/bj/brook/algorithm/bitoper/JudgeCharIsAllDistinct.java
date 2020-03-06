package cn.bj.brook.algorithm.bitoper;

import java.util.Arrays;

/**
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode"
 * 输出: false
 * 示例 2：
 *
 * 输入: s = "abc"
 * 输出: true
 * 限制：
 *
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-unique-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class JudgeCharIsAllDistinct {
    // 解法1
    // 使用bit运算也可以有相同的效果
    // 只要该位变为1，那么就证明有数字了
    public boolean isUniqueUsingBit(String astr) {
        // 放26个英文字母的
        // 从a开始计算，倒着，a在最后1位，z在第一位
        int bits = 0b00_00000000_00000000_00000000;
        char[] sub = astr.toCharArray();
        for(int i=0;i<sub.length;i++){
            char c = sub[i];
            // 这是当前字符的偏移量
            // 比如a就是0，b就是1
            int offset = c - 97;

            // 要检查该位置的变量在bits中是0还是1
            int valueOfBits = (bits >> offset) & 1;
            if(valueOfBits == 1){
                return false;
            }
            bits = bits | (1 << offset);
        }
        return true;
    }

    // 解法2
    // 思路就是用数组
    // 如果脚标出现过，直接返回
    // 否则就把脚标赋值
    public boolean isUnique(String astr) {
        // 放26个英文字母的
        int[] seq = new int[26];
        Arrays.fill(seq,-1);
        char[] sub = astr.toCharArray();
        for(int i=0;i<sub.length;i++){
            char c = sub[i];
            if(seq[c-97] == c){
                return false;
            }else{
                seq[c-97] = c;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println((int)'a');
        System.out.println((int)'z');
        System.out.println((int)'A');
        System.out.println((int)'Z');
    }
}
