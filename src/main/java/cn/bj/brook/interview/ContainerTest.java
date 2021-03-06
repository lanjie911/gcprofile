package cn.bj.brook.interview;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContainerTest {
    public static void main(String[] args) {
        Map<Integer,Boolean> follower = new LinkedHashMap<>();
        for(int i=1;i<100000000;i++){
            follower.put(i,true);
        }
        while(true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
