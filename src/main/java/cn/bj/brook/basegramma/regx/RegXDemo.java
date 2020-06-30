package cn.bj.brook.basegramma.regx;

/**
 * 正则表达式测试
 */
public class RegXDemo {
    public static void main(String[] args) {
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
