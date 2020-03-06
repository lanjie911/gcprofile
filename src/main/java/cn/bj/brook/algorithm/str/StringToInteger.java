package cn.bj.brook.algorithm.str;

/**
 * 字符串转整型
 * 1-去掉开头的空字符串
 * 2-开头只能是+或者-打头
 * 3-如果+-连在一起是无效的
 * 4-遇到第一个不是数字的字符就停止
 * 5-溢出的就返回Int的最大值或者是最小值
 */
public class StringToInteger {
    public static int myAtoi(String str) {
        boolean isPositive = true;
        long tmp = 0;
        int result = 0;
        int lastChar = -1;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            // 如果是空格打头
            if(c == 32 && lastChar ==-1){
                continue;
            }
            // 如果处理过程中是空格
            if((c < '0' || c>'9') && lastChar >0){
                break;
            }
            if(c == '-'){
                isPositive = false;
                lastChar = '-';
                continue;
            }
            if(c == '+'){
                isPositive = true;
                lastChar = '+';
                continue;
            }
            if(c >= '0' && c <= '9') {
                lastChar = c;
                tmp = tmp * 10 + (c - 48);
                if(isPositive && tmp > Integer.MAX_VALUE){
                    return Integer.MAX_VALUE;
                }
                if(!isPositive && tmp > 2147483648l){
                    return Integer.MIN_VALUE;
                }
            }else{
                break;
            }
        }
        tmp = isPositive?tmp:-tmp;
        if(tmp>Integer.MAX_VALUE){
            result = Integer.MAX_VALUE;
        }else if(tmp<Integer.MIN_VALUE){
            result = Integer.MIN_VALUE;
        }else{
            result = (int)tmp;
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
