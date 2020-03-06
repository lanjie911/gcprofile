package cn.bj.brook.algorithm;

/**
 * 反转短整型比如 123 -> 321, -229 -> -922
 * 需要注意处理正负号和溢出
 */
public class RevertIntNumber {

    public static  int reverse(int x) {
        int t = 0;
        int max = 2147483647;
        boolean sign = x>0;
        while(x!=0){
            // 这里用long来判断是否max溢出
            // 不溢出的话就继续赋值给t（整型）
            long tmp = t;
            tmp = tmp*10+ (Math.abs(x % 10));
            if(tmp > max){
                return 0;
            }
            t = t*10 + (Math.abs(x % 10));
            x = x/ 10;
        }
        return sign?t:-t;
    }



    public static void main(String[] args) {

    }
}
