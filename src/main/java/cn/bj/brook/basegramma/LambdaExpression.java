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

    public int sum(int a, int b, IntAddSum cal) {
        int k = 0;
        k = cal.sum(a, b);
        return k;
    }

    public static void main(String[] args) {
        LambdaExpression e = new LambdaExpression();
        int m = e.sum(3,5, IntAddSumImpl::sum);
        System.out.println(m);
    }


}

interface MyFunc {
    String getStringValue(int a);
}

interface MyPOJOFactory {
    LambdaExpression newInstance();
}

interface IntAddSum {
    int sum(int a, int b);
}

class IntAddSumImpl {
    public static int sum(int a, int b){
        return a+b;
    }
}
