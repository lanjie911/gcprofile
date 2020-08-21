package cn.bj.brook.algorithm.bitoper;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 使用最少的兔子来试出毒药
 */
public class DrugWithLeastRabbits {

    static class Bottle {
        // 是否是毒药
        boolean isPoison;

        // 编码
        int code;
    }

    // 生成随机毒药
    static Bottle[] generateRandomPoisons(){
        Bottle[] bottles = new Bottle[1001];
        boolean hasGeneratePoison = false;
        for(int i=1;i<bottles.length;i++){
            Bottle b = new Bottle();
            bottles[i] = b;
            b.code = i;
            if(!hasGeneratePoison){
                long a = System.currentTimeMillis();
                if((a % 13) %2 == 0){
                    b.isPoison = true;
                    hasGeneratePoison = true;
                    System.out.println("第"+i+"瓶是毒药");
                }
            }
        }
        if(!hasGeneratePoison){
            bottles[1000].isPoison = true;
            System.out.println("第1000瓶是毒药");
        }
        return bottles;
    }

    static void feedRabbits(){
        Bottle[] bottles = generateRandomPoisons();
        List<List<Bottle>> groups = new LinkedList<>();
        // 将瓶子分为10组
        for(int i=1;i<1001;i++){
            Bottle b = bottles[i];
            for(int m=10;m>0;m--){
                int bit = (b.code & (2^m)) >> (m);
            }
        }
    }

    public static void main(String[] args) {
        feedRabbits();
    }
}
