package cn.bj.brook.algorithm.math;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongStringMultiply {
    // 解题思路
    // 乘法本质上是被乘数加法
    // 自己加上自己加多少次
    // 比如5*3，就是5加自己加三次
    // 这里面最大的问题是溢出
    // 即大数相加
    // 另外，乘数即便是做循环也会出现溢出
    // 题目的要求是110位，这意味着110位长的数字本身long也不可能转的下
    // 所以需要一个能够循环110位的数
    // 循环本身也是这个大数加法+1的特例
    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        // 快速优化方案
        // 999999999 * 999999999 是可以直接求的，不会溢出
        // 小于long的可以直接用乘法
//        if (num1.length() + num2.length() <= 18) {
//            return "" + Long.parseLong(num1) * Long.parseLong(num2);
//        }

        // 被乘数（左侧）
        String multiplicand = null;
        // 乘数（右侧），乘数就是倍数
        String multiplier = null;
        // 选长度大的作为被乘数，这样可以减少循环
        if (num1.length() > num2.length()) {
            multiplicand = num1;
            multiplier = num2;
        } else {
            multiplicand = num2;
            multiplier = num1;
        }
        String result = "0";
        // 这是10的幂
        // 从0开始计算
        int tenPower = 0;
        // 从被乘数最后一位倒着乘
        for (int i = multiplier.length(); i > 0; i--) {
            String t = this.longStringMultiplySingleNumber(multiplicand, multiplier.substring(i-1, i));
            result = this.longStringAdd(this.longStringMultiply10Times(t, tenPower), result);
            tenPower++;
        }
        return result;
    }


    // 一个巨大数乘以一个单数
    public String longStringMultiplySingleNumber(String huge, String a) {
        int overflow = 0;
        StringBuilder result = new StringBuilder();
        int ia = Integer.parseInt(a);
        for (int i = huge.length(); i > 0; i--) {
            int c = Integer.parseInt(huge.substring(i - 1, i));
            int t = c * ia + overflow;
            if (t >= 10) {
                overflow = t / 10;
                t = t % 10;
            } else {
                overflow = 0;
            }
            result.insert(0, t);
        }
        if (overflow > 0) {
            result.insert(0, overflow);
        }
        return result.toString();
    }

    // 一个巨大数乘以10的幂
    // 10^0 , 10^1,...
    public String longStringMultiply10Times(String huge, int times) {
        StringBuilder sb = new StringBuilder(huge);
        for (int i = 0; i < times; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    // 两个大长数字串相加
    public String longStringAdd(String num1, String num2) {
        // 补齐0，让两个字符串相等
        int diff = Math.abs(num1.length() - num2.length());
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < diff) {
            sb.append("0");
            i++;
        }
        if (num1.length() > num2.length()) {
            // 补齐num2
            num2 = sb.append(num2).toString();
        } else {
            num1 = sb.append(num1).toString();
        }
        // 开始逐位计算加法
        StringBuilder result = new StringBuilder();
        int overflow = 0;
        for (i = num1.length(); i > 0; i--) {
            int n1 = Integer.parseInt(num1.substring(i - 1, i));
            int n2 = Integer.parseInt(num2.substring(i - 1, i));
            int t = n1 + n2 + overflow;
            if (t >= 10) {
                t -= 10;
                overflow = 1;
            } else {
                overflow = 0;
            }
            result.insert(0, t);
        }
        if (overflow > 0) {
            result.insert(0, overflow);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        long a = 999999l;
        long b = 999999999999l;
        System.out.println(a * b);
    }
}
