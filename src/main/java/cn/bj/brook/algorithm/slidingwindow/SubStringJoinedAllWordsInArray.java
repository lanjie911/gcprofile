package cn.bj.brook.algorithm.slidingwindow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 串联数组中所有单词的子串
 *
 * 给定一个字符串 s 和一些长度相同的单词数组 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 */
public class SubStringJoinedAllWordsInArray {

    // 思路：所有的子串长度相同 - 很重要
    // 使用滑动窗口
    // 以每个子串作为key，放入list
    // 从0开始，按照子串总长度切割一个长段，然后把这个长段按照每个子串段长度继续分割
    // 每个被切割下来的子串list中找，如果找到，就删除这个key，继续切割下一个
    // 直到list被清空，这就是一个合法的位置
    // 重新初始化list，继续，比如：
    // barfoothefoobarman
    // "bar","foo"
    // 步骤一：barfoo -> bar, foo -> 0
    // 步骤二：arfoot -> arf, oot -> 没有
    // 步骤三：rfooth -> rfo, oth -> 没有
    // ...
    // foobar -> foo, bar -> 9
    // ...
    // barman -> bar, man -> 没有
    public List<Integer> execute(String s, String[] words) {

        // 定义返回结果的列表
        List<Integer> rs = new LinkedList<>();

        // 处理边界值
        if(s.length() == 0 || words.length == 0){
            return rs;
        }

        // 每个单位子串的长度
        // 也就是每次步进的长度
        // 每个数组中的元素都相等
        int step = words[0].length();

        int allLen = words.length * step;

        for(int i=0;i<s.length();i++){
            // 从第i个位置开始，加上整个words的长度会超过s的长度
            if(i+allLen>s.length()){
                return rs;
            }
            String sub = s.substring(i,i+allLen);
            // 使用列表的原因是题目中没有明确说明单词是不是可以重复的
            // 如果列表中单词是独一无二的，那么可以用效率更高的哈希表
            List<String> tags = Arrays.asList(words);

            for(int j=0;j<sub.length();j+=step){
               String tmp = sub.substring(j,j+step);
               boolean isDel = tags.remove(tmp);
               //没删除掉，证明列表中不存在此元素，不用继续了，直接下一个
               if(!isDel){
                   break;
               }
            }

            // 如果列表都清空了
            // 证明这个字串恰好是符合要求的
            if(tags.size() == 0){
                rs.add(i);
            }
        }
        return rs;
    }
}
