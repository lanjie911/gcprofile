package cn.bj.brook.basegramma;

/**
 * 测试final和return，谁先执行
 */
public class FinalSyntax {
    static int test(){
        int s;
        try{
            s = 1;
            return s;
        }finally {
            s = 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
