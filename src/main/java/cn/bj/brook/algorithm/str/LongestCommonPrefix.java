package cn.bj.brook.algorithm.str;

import cn.bj.brook.Logg;
import cn.bj.brook.algorithm.tree.trie.TrieTreeUtil;

/**
 * 最长字符串数组的公共前缀
 * 解题方式仍然是依次取值，知道找到不一样的一个char为止
 * 正常解题即可
 */
public class LongestCommonPrefix {

    // 暴力法
    // 遍历所有字符串的打头
    public static String execute(String[] args){
      String rs = "";
      int i=0;
      char prev = 0;
      while(true){
          for(String ele:args){
              if(i>=ele.length()){
                  return rs;
              }
              if(prev == 0){
                  prev = ele.charAt(i);
              } else {
                  if((prev != ele.charAt(i)))
                  return rs;
              }
          }
          rs += prev;
          prev = 0;
          i++;
      }
    }

    /**
     * 使用trie tree 字典树方式
     * 先构建一个字典树，然后找到最大num出现的字符即可
     * 时间复杂度 o(n)构建一个字典树 + o(n)遍历
     * @param args
     * @return
     */
    public static String execute2(String[] args){
        return TrieTreeUtil.queryMaxCommonString(args);
    }

    public static void main(String[] args) {
        String[] ss = new String[]{"flower","flow","flight"};
        Logg.println(execute(ss));
        Logg.println(execute2(ss));
    }
}
