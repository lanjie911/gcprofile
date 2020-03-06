package cn.bj.brook.algorithm.math;

import java.util.Stack;

/**
 * 面试题：求表达式的值，实现简单计算器，加法和乘法
 * 前置条件如下：
 * 1. 字符串是完备的，不会出现符号连续出现的情况，和除了+ * 之外的其他字符
 * 2. 字符串不会出现空串，但是可能会出现没有符号的单独表达式
 * 3. 0不会出现在每个数字开头，除非这个数字是0本身
 * 4. 字符串包含必要的空格用来间隔语义
 * 5. 字符串所代表的整型计算不会出现溢出，即在合理范围内足够小
 * 6. 不包含括号
 */
public class Calculator {
    // 解题思路，利用栈
    // 第一步，构建表达式连续整数栈
    public int evaluate(String expression) {
        // replace all the whitespaces
        expression = expression.replaceAll(" ", "");
        Stack<String> stack = new Stack<>();

        // first for recycle construct a stack
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '*') {
                stack.push("" + c);
            } else {
                // find a sequential string of number
                StringBuilder numBuilder = new StringBuilder();
                numBuilder.append(c);
                char next = 0;
                while ((i + 1) < expression.length()) {
                    next = expression.charAt(i + 1);
                    if (next == '+' || next == '*') {
                        break;
                    }
                    numBuilder.append(next);
                    i++;
                }
                stack.push(numBuilder.toString());
            }
        }

        // second recycle calc
        // last result
        // first multiply
        Stack<String> result = new Stack<>();
        while (stack.size() > 0) {
            String ele = stack.pop();
            if ("*".equals(ele)) {
                int previous = Integer.parseInt(result.pop());
                int next = Integer.parseInt(stack.pop());
                result.push(previous * next + "");
            } else {
                result.push(ele);
            }
        }

        // third, calc sum
        int lastIntResult = 0;
        while (result.size() > 0) {
            String value = result.pop();
            if("+".equals(value)){
                continue;
            }
            lastIntResult += Integer.parseInt(value);
        }
        return lastIntResult;
    }
}
