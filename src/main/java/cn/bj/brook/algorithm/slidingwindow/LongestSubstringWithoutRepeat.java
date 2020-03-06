package cn.bj.brook.algorithm.slidingwindow;

/**
 * 最长无重复子字符串
 * 使用滑动窗口来解决，比如abcabcbb
 * 过程为：
 * a
 * ab
 * abc
 * abc｜a，此时最大字符串为abc
 * 删除掉a，继续从b开始滑动，如下，就像个框线条在横着移动一样
 *   -------------
 * a | b | c | a | b c b b
 *   _____________
 *
 * b
 * bc
 * bca
 * bca | b，然后最大无重复字符串为bca
 * 删除掉b，继续....
 *
 */
public class LongestSubstringWithoutRepeat {

    int execute(String s){
        if(s == null || s.length()==0){
            return 0;
        }
        char[] arr = s.toCharArray();
        // 滑动窗口的左边缘
        int windowLeftEdge = 0;
        // 滑动窗口的右边缘
        int windowRightEdge = 0;
        String maxString = "";
        String tmp = "";
        // 保持窗口滑动
        while(windowRightEdge < arr.length && windowLeftEdge < arr.length){
            char c = arr[windowRightEdge];
            int firstRepeatPosition = tmp.indexOf(c);
            // 发现了重复的元素
            if(firstRepeatPosition!=-1){
                if(tmp.length()>maxString.length()){
                    maxString = tmp;
                }
                // System.out.println("last tmp="+tmp+" & maxString="+maxString);
                tmp = "";
                // 新的窗口左边缘位置
                // 是在tmp内第一个重复的元素的下一位
                windowLeftEdge += firstRepeatPosition+1;
                // 左右边缘归位重合
                windowRightEdge = windowLeftEdge;
                continue;
            }
            tmp += c;
            windowRightEdge++;
        }
        // 这是要处理如果一个重复的都没有的情况
        if(tmp.length()>maxString.length()){
            maxString = tmp;
        }
        return maxString.length();
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeat l = new LongestSubstringWithoutRepeat();
        int m = l.execute("abcbdefmkip");
        System.out.println(m);
    }
}
