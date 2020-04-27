package cn.bj.brook.algorithm.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 * <p>
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 * <p>
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-case-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AllStringsByConvertUpperLower {
    public List<String> letterCasePermutation(String para) {
        List<String> rs = new LinkedList<>();
        int sz = para.length();
        for(int i=0;i<sz;i++){
            rs = inner2(rs,para,i);
        }
        return rs;
    }

    private List<String> inner2(List<String> parts, String para, int i) {
        char c1 = para.charAt(i);
        char c2 = 1;
        if (c1 >= 97 && c1 <= 122) {
            c2 = (char) (c1 - 32);
        } else if (c1 >= 65 && c1 <= 90) {
            c2 = (char) (c1 + 32);
        }
        if(parts.size()==0){
            parts.add(""+c1);
            if(c2 != 1) {
                parts.add("" + c2);
            }
            return parts;
        }else{
            List<String> newParts = new LinkedList<>();
            for(int j=0;j<parts.size();j++){
                String tmp = parts.get(j)+c1;
                newParts.add(tmp);
            }
            if(c2 != 1){
                for(int j=0;j<parts.size();j++){
                    String tmp = parts.get(j)+c2;
                    newParts.add(tmp);
                }
            }
            return newParts;
        }
    }

    private List<String> backtrack(String para){
        if(para.length() <= 1){
            char c1 = para.charAt(0);
            char c2 = assumeC2(c1);
            List<String> ts = new LinkedList<>();
            ts.add(c1+"");
            if(c2 != 1){
                ts.add(c2+"");
            }
            return ts;
        }
        char c1 = para.charAt(para.length()-1);
        char c2 = assumeC2(c1);
        List<String> prevs = backtrack(para.substring(0,para.length()-1));
        List<String> current = new LinkedList<>();
        for(int j=0;j<prevs.size();j++){
            String tmp = prevs.get(j)+c1;
            current.add(tmp);
        }
        if(c2 != 1){
            for(int j=0;j<prevs.size();j++){
                String tmp = prevs.get(j)+c2;
                current.add(tmp);
            }
        }
        return current;
    }

    private char assumeC2(char c1){
        char c2 = 1;
        if (c1 >= 97 && c1 <= 122) {
            c2 = (char) (c1 - 32);
        } else if (c1 >= 65 && c1 <= 90) {
            c2 = (char) (c1 + 32);
        }
        return c2;
    }

    public static void main(String[] args) {
        AllStringsByConvertUpperLower al = new AllStringsByConvertUpperLower();
        List<String> rs = al.letterCasePermutation("a1b2");
        for(String e: rs){
            System.out.println(e);
        }

        rs = al.backtrack("a1b2");
        for(String e: rs){
            System.out.println(e);
        }
    }
}
