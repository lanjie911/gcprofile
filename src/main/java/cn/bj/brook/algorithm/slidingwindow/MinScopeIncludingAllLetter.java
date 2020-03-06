package cn.bj.brook.algorithm.slidingwindow;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinScopeIncludingAllLetter {
    // 解题思路
    // 滑动窗口
    // 从包含该字符串的长度开始搜索，每次加1，直接找到最长
    // 如果找不到就返回空
    public String minWindow(String source, String target) {
        // 最小距离可能就是目标字符串的长度
        int scope = target.length();
        while (scope <= source.length()) {
            for (int i = 0; i + scope <= source.length(); i++) {
                String temp_source = source.substring(i, i + scope);
                // 这种每次探测的方法实际上重复运算了很多次
                // 在大量字符串的情况下会超时
                // 所以需要引入一种优化算法
                // 就是移动实际上是左边去掉了一个字符
                // 右边增加了一个字符
                // 那么如果使用备忘录
                // 移动和增加的结果就不需要重新计算
                // TODO
                if (testify(temp_source, target)) {
                    return temp_source;
                }
            }
            // 增加scope的长度
            scope++;
        }
        return "";
    }

    // 检测字符串是否包含目标的每一个字符
    private boolean testify(String source, String target) {
        StringBuilder sourceBuilder = new StringBuilder(source);
        for(int i=0;i<target.length();i++){
            String s = target.substring(i,i+1);
            int pos = sourceBuilder.indexOf(s);
            if(pos>=0){
                sourceBuilder.deleteCharAt(pos);
            }else{
                return false;
            }
        }
        return true;
    }
}
