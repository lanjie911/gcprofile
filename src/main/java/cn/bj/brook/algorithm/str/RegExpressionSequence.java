package cn.bj.brook.algorithm.str;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RegExpressionSequence {
    public boolean isMatch(String text, String pattern) {
        // 如果正则表达式是空的
        if (pattern.isEmpty()){
            // 返回字符串是不是空的，如果不空就是假的
            return text.isEmpty();
        }
        // 判断该句子的第一个字母是否相同
        // 如果字符串不为空，那么要么是第一个字符相等，要么是第一个字符是.任意匹配
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '?' || pattern.charAt(0) == '*'));

        if(pattern.charAt(0)  == '*'){
            return first_match && ( isMatch(text.substring(1), pattern)
                    || isMatch(text.substring(1), pattern.substring(1)) );
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }

    }

    public static void main(String[] args) {
        RegExpressionSequence seq = new RegExpressionSequence();
        boolean b = seq.isMatch("adceb","*a*b");
        System.out.println(b);
    }
}
