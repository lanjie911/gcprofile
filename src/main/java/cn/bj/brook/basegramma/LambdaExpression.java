package cn.bj.brook.basegramma;

/**
 * 测试拉姆达表达式
 */
public class LambdaExpression {

    public MyFunc methodA() {
        MyFunc my = String::valueOf;
        return my;
    }

    public MyPOJOFactory methodB() {
        MyPOJOFactory factory = LambdaExpression::new;
        return factory;
    }

}

interface MyFunc {
    String getStringValue(int a);
}

interface MyPOJOFactory {
    LambdaExpression newInstance();
}
