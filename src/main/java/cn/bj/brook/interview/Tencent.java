package cn.bj.brook.interview;

import java.util.Stack;

/**
 * 腾讯面试题
 */
public class Tencent {

    // 定义一个对象来保存内部输出多少次
    class CString {
        int repeat = 0;
        StringBuilder content;

        CString() {
            content = new StringBuilder();
        }
    }

    public String decompressString(String compressedString) {
        // 用栈来保存嵌套字符串
        Stack<CString> stack = new Stack<>();
        // 最终的答案
        StringBuilder answer = new StringBuilder();
        // 当前的CString对象
        CString current = null;
        // 是否正在计算｜左边的数值
        boolean isCalculated = false;
        // 是否正在计算｜右边的字符串
        boolean isContent = false;
        // 要重复多少次的数值
        int num = 0;

        // O(n)一次遍历
        for (int i = 0; i < compressedString.length(); i++) {
            char c = compressedString.charAt(i);
            switch (c) {
                case '[':
                    // 遇到[，入栈
                    if (current != null) {
                        stack.push(current);
                    }
                    current = new CString();
                    isCalculated = true;
                    num = 0;
                    break;
                case ']':
                    // 遇到]，出栈
                    StringBuilder temp = new StringBuilder();
                    for (int m = 0; m < current.repeat; m++) {
                        temp.append(current.content);
                    }
                    if (stack.size() > 0) {
                        CString previous = stack.pop();
                        previous.content.append(temp);
                        current = previous;
                    } else {
                        answer.append(temp);
                        isContent = false;
                        current = null;
                    }
                    break;
                case '|':
                    // 计数结束，准备开始统计当前CString对象
                    isCalculated = false;
                    current.repeat = num;
                    num = 0;
                    isContent = true;
                    break;
                default:
                    if (isCalculated) {
                        num = num * 10 + Integer.parseInt(c + "");
                    } else if (isContent) {
                        current.content.append(c);
                    } else {
                        answer.append(c);
                    }
                    break;
            }
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        Tencent tencent = new Tencent();
        String a = tencent.decompressString("[3|A]");
        System.out.println(a);
        a = tencent.decompressString("[3|AB]");
        System.out.println(a);
        a = tencent.decompressString("[1|A]");
        System.out.println(a);
        a = tencent.decompressString("[0|A]");
        System.out.println(a);
        a = tencent.decompressString("[10|AB]");
        System.out.println(a);
        a = tencent.decompressString("X[3|A]");
        System.out.println(a);
        a = tencent.decompressString("1[3|A]0");
        System.out.println(a);
        a = tencent.decompressString("1[2|ABC]9[4|X]5");
        System.out.println(a);
        a = tencent.decompressString("[2|A][3|JL]");
        System.out.println(a);
        a = tencent.decompressString("[2|A[3|C[4|0]]8]");
        System.out.println(a);
        a = tencent.decompressString("HG[3|B[2|CA]]F");
        System.out.println(a);
    }
}
