package cn.bj.brook.interview;

public class PinDuoDuo {

    public int magicBoxWithBalls(int boxNumber){
        int i = 1;
        while(boxNumber != 1){
            boxNumber /= 2;
            i++;
        }
        System.out.printf("i=%d\r\n",i);
        return i;
    }

    public int magicBoxWithBalls2(int n){
        int t = Integer.toBinaryString(n).length();
        System.out.printf("t=%d\r\n",t);
        return t;
    }

    public static void main(String[] args) {
        PinDuoDuo pdd = new PinDuoDuo();
        pdd.magicBoxWithBalls(5);
        pdd.magicBoxWithBalls2(5);
        pdd.magicBoxWithBalls(6);
        pdd.magicBoxWithBalls2(6);
        pdd.magicBoxWithBalls(3);
        pdd.magicBoxWithBalls2(3);
    }
}
