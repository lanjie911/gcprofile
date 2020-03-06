package cn.bj.brook.algorithm.bitoper;

/**
 * 按照顺序颠倒给定的 32 位无符号整数的二进制位。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: 00000010100101000001111010011100
 * 输出: 00111001011110000010100101000000
 * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *       因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * 示例 2：
 *
 * 输入：11111111111111111111111111111101
 * 输出：10111111111111111111111111111111
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 *       因此返回 3221225471 其二进制表示形式为 10101111110010110010011101101001。
 *  
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class ReverseBinaryBitSequence {

    private String complement(String t){
        StringBuilder sb = new StringBuilder(t);
        if(sb.length() < 32){
            int left = 32 - sb.length();
            while(left > 0){
                sb.insert(0,"0");
                left--;
            }
        }
        return sb.toString();
    }

    // 具体过程如打印所示
    // 注意处理负数
    public int reverseBits(int n) {
        int result = 0;
        int b = 1;
        int left_value = 0;
        int i = 0;

        boolean negative = n < 0;

        do{
            // 按位求 &
            left_value = n & b;
//            out.println("1:         n="+complement(Integer.toBinaryString(n)));
//            out.println("2:         b="+complement(Integer.toBinaryString(b)));
//            out.println("3:left_value="+complement(Integer.toBinaryString(left_value)));
            // 将得到的值移动到最后1位
            // 即left_value不是0就是1，不可能出现其他的值
            left_value >>= i;
//            out.println("4:left_value="+complement(Integer.toBinaryString(left_value)));
            result = result | left_value;
//            out.println("5:    result="+complement(Integer.toBinaryString(result)));
            b <<= 1;
//            out.println("6:         b="+complement(Integer.toBinaryString(b)));
            result <<= 1;
//            out.println("7:    result="+complement(Integer.toBinaryString(result)));
            i++;
//            out.println("---------------------------------------------------");
        }while (i<31);

        // 处理符号位
        // 负数最后一位要补上
        // 计算机是补码表示
        if(negative){
            result |= 0b00000000_00000000_00000000_00000001;
//            out.println("8:    result="+complement(Integer.toBinaryString(result)));
        }

        return result;
    }
}
