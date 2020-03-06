package cn.bj.brook.algorithm.parentheses;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * 输入:
 * "()(())"
 * 预期结果 6 - 括号嵌套也是可以的
 */
public class LongestValidParentheses {

    /**
     * 暴力法：时间复杂度O(n^2)
     * 在这种方法中，我们考虑给定字符串中每种可能的非空偶数长度子字符串，
     * 检查它是否是一个有效括号字符串序列。为了检查有效性，我们使用栈的方法。
     * <p>
     * 每当我们遇到一个 （ ，我们把它放在栈顶。对于遇到的每个 ），
     * 我们从栈中弹出一个 （ ，如果栈顶没有 ），或者遍历完整个子字符串后栈中仍然有元素，
     * 那么该子字符串是无效的。这种方法中，我们对每个偶数长度的子字符串都进行判断，并保存目前为止找到的最长的有效子字符串的长度。
     * <p>
     * 例子:
     * "((())"
     * <p>
     * (( --> 无效
     * (( --> 无效
     * () --> 有效，长度为 2
     * )) --> 无效
     * ((()--> 无效
     * (())--> 有效，长度为 4
     * 最长长度为 4
     *
     * @param
     * @return
     */
    public int explosiveExecute(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                String t = s.substring(i, j);
                if (isValid(t)) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }

    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }

    /**
     * 动态规划法
     * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode/
     * <p>
     * 定义一个数组，数组名字为dp，该数组的长度和整个字符串一样长
     * 数组中的元素的意义是，以当前字符作为结尾，一直回溯到头，有效的字符串长度是几
     * 比如dp[i]=4，意味着以当前的字符结尾，回溯到头，有效字符串长度为4
     * 有效长度一定都是偶数的，奇数不可能，因为()要前后匹配
     * 默认dp全都初始化为0
     * <p>
     * 我们再定义s[i]是指字符串中的某个元素，对于java来讲就是s.charAt(i)，分为两种情况：
     * <p>
     * 1.    s[i] = ) 并且 s[i-1]=(，就是形如 ......()的格式，那么
     * dp[i]=dp[i-1]+2，
     * 一定要记住dp[x]表示的是长度,dp[i-1]可能是0,2,4....任意的值
     * <p>
     * 2.    s[i] = ) 并且 s[i-1]=)，就是形如 ......))的格式，那么
     * 如果s[i-dp[i-1]-1] = '('
     * 有 dp[i]=dp[i-1]+dp[i-dp[i-1]-2]+2
     * 上式理解如下：如果倒数第二个)是一个有效子串sub的一部分，对于最后一个),要构成更长的有效子串，要求有一个(在子串sub的更前面
     * 于是 有dp[i-1]+2 ， 这是   ...(sub)  的长度
     * 还需要计算 ...的长度，(sub)前面的长度是dp[i-2-dp[i-1]], i-2是去掉()的长度，再减去dp[i-1]是去掉sub的长度
     * <p>
     * 除了以上两种情况，只要在位置i开头的是(当前位置都要填写0
     * <p>
     * 举例如下：
     * ( ) ) ( ( ( ) )
     * 0 1 2 3 4 5 6 7
     * <p>
     * dp[0] = 0
     * dp[1] = 2 命中情况1 - 有效子串长度是2
     * dp[2] = 0 命中情况2 - 但是没有命中最前方是(，所以是0
     * dp[3] = 0
     * dp[4] = 0
     * dp[5] = 0
     * dp[6] = 2 命中情况1 - 有效子串长度是2
     * dp[7] = 4 命中情况2，也命中了2的如果条件，dp[7] = dp[6]+2 + dp[7-2-dp[6]]
     * <p>
     * //     -sub长度-         -减去sub+()的长度2- : 下方找到了(sub)前的节点dp[3]
     * dp[7] = dp[6]+2     +         dp[7-2-dp[6]]
     *
     * @param s
     * @return
     */
    public int dpExecute(String s) {
        int maxans = 0;
        // 所有的位置都初始化为0，如果不改动就是0
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            // 只有碰到了结尾字符串)才是0
            if (s.charAt(i) == ')') {
                // 情况1
                if (s.charAt(i - 1) == '(') {
                    // 如果i>2就找前边的，否则就是0
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                // 情况2 并且命中情况2的分之条件
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * 使用栈的方法 需要类库支持
     *
     * 方法说明如下：栈中放的是字符串的位置脚标，遇到(入栈，遇到)弹出，脚标相减，需要初始化栈底为-1
     *
     * ( ) ) ( ( ( ) )
     * 0 1 2 3 4 5 6 7
     *
     * stack:
     * 第一步：-1, 0(,
     * 第二步：遇到 1)，弹出0(，使用当前脚标1 - 留在栈顶的元素-1 = 2，记录maxlen = 2
     * 第三步：遇到 2)，弹出-1，栈为空，把2压入栈
     * 此时栈 2
     * 第四步：遇到3(，继续压入栈
     * 此时栈：2 3
     * 第五步：遇到4(，继续压入栈
     * 此时栈：2 3 4
     * 第六步：遇到5(，继续压入栈
     * 此时栈：2 3 4 5
     * 第七步：遇到6)，弹出5，用6-4=2，记录maxlen=2
     * 此时栈：2 3 4
     * 第八步：遇到7)，弹出4，用7-3=4，记录maxlen=4
     *
     * @param s
     * @return
     */
    public int stackExecute(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // 遇到) 就弹出
                stack.pop();
                // 如果空栈了把当前元素压入
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public static void main(String[] args) {
        LongestValidParentheses obj = new LongestValidParentheses();
        int k = obj.explosiveExecute("()(())");
        System.out.println(k);

        k = obj.dpExecute("()(())");
        System.out.println(k);

        k = obj.stackExecute("()(())");
        System.out.println(k);
    }

}
