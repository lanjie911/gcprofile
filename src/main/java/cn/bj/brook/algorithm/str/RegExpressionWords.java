package cn.bj.brook.algorithm.str;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RegExpressionWords {
    public boolean isMatch(String text, String pattern) {
        // 如果正则表达式是空的
        if (pattern.isEmpty()){
            // 返回字符串是不是空的，如果不空就是假的
            return text.isEmpty();
        }
        // 判断该句子的第一个字母是否相同
        // 如果字符串不为空，那么要么是第一个字符相等，要么是第一个字符是.任意匹配
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        // 如果正则表达式的长度大于等于2，并且第2个正则的字符是*，可以匹配0个或者多个前边的字符
        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            // 那么去掉这个*打头的正则表达式，看看后边是否匹配（相当于匹配0次）
            // 或者把字符串的第1个字符去掉，看是否匹配(相当于匹配多次)
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            // 不是上面这种情况，那就是看后面的精准匹配了
            // 后面是递归调用
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    public static void main(String[] args) {
        RegExpressionWords exp = new RegExpressionWords();
        boolean im = exp.isMatch("so good man","so g.*d m.n");
        System.out.println(im);
    }
}
