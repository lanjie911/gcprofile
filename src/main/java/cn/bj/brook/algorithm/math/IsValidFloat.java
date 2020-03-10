package cn.bj.brook.algorithm.math;

/**
 * 验证给定的字符串是否可以解释为十进制数字。
 *
 * 例如:
 *
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 *
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 *
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsValidFloat {

    boolean isNumber(String str) {
        char[] s = str.toCharArray();
        boolean decimal = false;  // flag of .
        boolean neg = false;      // flag of +/-
        boolean num = false;      // flag of numbers
        boolean esign = false;    // flag of e
        boolean finish = false;   // finish: there are spaces after a valid number
        for (int i = 0; i < s.length; ++i) {
            if (s[i] == ' ') {
                if (num || decimal || neg || esign)
                    finish = true;
                continue;
            } else if (finish)  // non-space character after "finish", return false
                return false;
            if (s[i] >= '0' && s[i] <= '9') {
                num = true;
            } else if (s[i] == '-' || s[i] == '+') {
                if ((decimal || num || neg || esign) && s[i - 1] != 'e') {  // before e: no double -/+, after e: right after e sign
                    return false;
                } else {
                    neg = true;
                }
            } else if (s[i] == '.') {
                if (esign || decimal) {  // before e: no double decimal points, after e：forbidden
                    return false;
                } else {
                    decimal = true;
                }
            } else if (s[i] == 'e') {  // e must after a valid number
                if (!num || esign) {
                    return false;
                } else {
                    esign = true;
                    num = false;
                }
            } else {
                return false;
            }
        }
        return num;
    }
}
