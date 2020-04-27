package cn.bj.brook.algorithm.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordFitToPattern {
    public boolean wordPattern(String pattern, String str) {
        // 每一个字符，对应的都是空格分隔的str中的单词
        // 一次遍历搞定
        String[] words = str.split("\\s");
        char[] chars = pattern.toCharArray();
        if (words.length != chars.length) {
            return false;
        }
        // 双哈希表保存彼此对应关系
        Map<String, Character> word2Char = new HashMap<>();
        Map<Character, String> char2Word = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            Character thisChar = Character.valueOf(chars[i]);
            String thisWord = words[i];

            Character currentPattern = word2Char.get(thisWord);
            String currentWord = char2Word.get(thisChar);
            if (currentPattern == null && currentWord == null) {
                word2Char.put(thisWord, thisChar);
                char2Word.put(thisChar, thisWord);
            } else {
                if(!thisChar.equals(currentPattern) || !thisWord.equals(currentWord)){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordFitToPattern p = new WordFitToPattern();
        String pattern = "abba", str = "dog cat cat dog";
        boolean rs = p.wordPattern(pattern, str);
        System.out.println(rs);

        pattern = "abba";
        str = "dog cat cat fish";
        rs = p.wordPattern(pattern, str);
        System.out.println(rs);

        pattern = "aaaa";
        str = "dog cat cat dog";
        rs = p.wordPattern(pattern, str);
        System.out.println(rs);

        pattern = "abba";
        str = "dog dog dog dog";
        rs = p.wordPattern(pattern, str);
        System.out.println(rs);

        pattern = "abbac";
        str = "dog boy boy dog girl";
        rs = p.wordPattern(pattern, str);
        System.out.println(rs);
    }
}
