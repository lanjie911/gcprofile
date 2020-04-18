package cn.bj.brook.algorithm.parentheses;

import java.util.*;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateParenthesis {

    /**
     * 解决思路是新的括号可以插入n-1括号的任何位置
     * 包括左边，右边，甚至包含住原来的括号
     * @param n
     * @return
     */
    public List<String> generate(int n) {
        Set<String> res = this.innerGenerate(n);
        List<String> list = new LinkedList<>(res);
        return list;
    }

    private Set<String> innerGenerate(int n){
        Set<String> resultSet = new LinkedHashSet<>();
        // 1对括号只能生成这种
        if(n==1){
            String t = "()";
            resultSet.add(t);
            return resultSet;
        }
        // 返回n-1括号匹配方式
        Set<String> tempSet = innerGenerate(n-1);
        for(String ele:tempSet){
            // 新的括号可以放在原来的左边
            resultSet.add("()"+ele);
            // 可以包住原来的括号
            resultSet.add("("+ele+")");
            // 可以放在原来括号的右边
            resultSet.add(ele+"()");
        }
        return resultSet;
    }

    public static void main(String[] args) {
        GenerateParenthesis gp = new GenerateParenthesis();
        List<String> ls = gp.generate(4);
        for(String ele:ls){
            System.out.println(ele);
        }
        System.out.println(ls.size());
    }
}
