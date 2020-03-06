package cn.bj.brook.algorithm;

import java.util.Arrays;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class ZTransforming {
    public String convert(String s, int numRows) {
        String rs = "";
        if(s == null || s.length() == 0){
            return rs;
        }
        // 行数是个负值或者0行
        if(numRows <= 0){
            return "";
        }
        // 行数是1，直接返回字符串本身
        if(numRows == 1){
            return s;
        }
        // 行数比字符串的长度还高，那返回字符串本身
        if(numRows >= s.length()){
            return s;
        }

        char[][] buckets = new char[numRows][];
        char[] inputChars = s.toCharArray();
        // 定义行的扩展方向，一开始是正的
        // 达到行数时，变为负的，达到0时又变成正的来回折叠 Z 字型
        int rowExpandDirection = 1;
        for(int i=0,j=0;i<inputChars.length;i++){
            char[] bucket = buckets[j];
            if(bucket == null){
                // 弄一个新的数组
                bucket = new char[1];
            }else{
                // 拿到旧的数组
                int oldLen = bucket.length;
                bucket = Arrays.copyOf(bucket,oldLen+1);
            }
            bucket[bucket.length-1] = inputChars[i];
            buckets[j] = bucket;

            // 根据当前的方向和步伐，处理容器组中下一个应该选择哪个桶
            if((rowExpandDirection == 1)){
                // 向正方向(朝向行底)走一步仍然未达到行底
                if(j+1<numRows){
                    j++;
                    continue;
                }
                // 向正方向走一步摸到行底了
                if(j+1 == numRows){
                    // 该向反方向走了
                    rowExpandDirection = -1;
                    j--;
                    continue;
                }
            }

            if((rowExpandDirection == -1)){
                // 向反方向（朝向行顶）走一步仍然未达到行底
                if(j-1>=0){
                    j--;
                    continue;
                }
                // 向反方向走一步摸到行顶了
                if(j-1 < 0){
                    // 该向正方向走了
                    rowExpandDirection = 1;
                    j++;
                    continue;
                }
            }

        }

        // 处理结果集合
        for(int i=0;i<buckets.length;i++){
            char[] bucket = buckets[i];
            System.out.println(Arrays.toString(bucket));
            rs += new String(bucket);
        }

        return rs;
    }

    public static void main(String[] args) {
        ZTransforming forming = new ZTransforming();
        String rs = forming.convert("abcdef",2);
        System.out.println(rs);
        return;
    }
}
