package cn.bj.brook.bonus;

import java.util.HashMap;
import java.util.Map;

public class BonusMain {
    public void printBonusPerson(){
        Map<Integer, String> pool = new HashMap<Integer, String>();
        pool.put(1,"张贵涛");
        pool.put(2,"李凤洪");
        pool.put(3,"张贺鹏");
        pool.put(4,"李钊");
        pool.put(5,"郭来中");
        pool.put(6,"张万松");
        pool.put(7,"张立军");
        pool.put(8,"谢海波");
        pool.put(9,"大李涛");
        pool.put(0,"车路彬");
        long t = System.currentTimeMillis();
        String s = ""+t;
        String tail = s.substring(s.length()-1,s.length());
        System.out.println(pool.get(Integer.parseInt(tail)));
    }

    public static void main(String[] args) {
        BonusMain main = new BonusMain();
        main.printBonusPerson();
    }
}
