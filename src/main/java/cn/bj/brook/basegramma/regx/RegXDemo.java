package cn.bj.brook.basegramma.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试
 */
public class RegXDemo {



    private static Object[] abstractNameAndCount(String t){
        Pattern pattern = Pattern.compile("^[a-zA-Z]+\\((\\d+)\\)$");
        Matcher m = pattern.matcher(t);
        boolean b = m.matches();
        System.out.println(b);
        String g1 = m.group(1);
        System.out.println("g1="+g1);
        return null;
    }

    public static void main(String[] args) {

        abstractNameAndCount("andy(100)");



        // 测试分组和后项引用
        String expression = "(andy+brook)-(YC01+t45/(YC02+YC03))";
        expression = expression.replaceAll("(\\(|\\))", "$1,")
                .replaceAll("([\\+\\-\\*\\/])", "$1,")
                .replaceAll("([A-Za-z0-9]+)", "$1,");
        System.out.println(expression);


        String testStr = "ABCABCABC";
        testStr = testStr.replaceAll("(ABC)+", "$1");
        System.out.println(testStr);


    }
}
