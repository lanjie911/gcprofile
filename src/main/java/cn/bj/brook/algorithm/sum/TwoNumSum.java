package cn.bj.brook.algorithm.sum;

import cn.bj.brook.algorithm.sort.SortUtil;

import java.util.HashMap;
import java.util.Map;

public class TwoNumSum {

    public int[] twoSum(int[] arr, int target){
        Map<Integer,Integer> map = new HashMap<>();
        for(int i: arr){
            map.put(i,1);
        }
        for(int i: arr){
            int me = target - i;
            if(map.containsKey(me)){
                return new int[]{i, me};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TwoNumSum s2 = new TwoNumSum();
        int[] rs = s2.twoSum(new int[]{2,3,6,6,8}, 12);
        SortUtil.print(rs);
    }
}
