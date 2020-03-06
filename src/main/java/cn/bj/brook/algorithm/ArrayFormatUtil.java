package cn.bj.brook.algorithm;

import java.util.LinkedList;
import java.util.List;

public class ArrayFormatUtil {
    public static int[][] transform(String inputPara){
        String a = inputPara.substring(1,inputPara.lastIndexOf("]"));
        String[] colors = a.split("],");
        List<List<Integer>> dx = new LinkedList<>();
        for(String e:colors){
            e = e.trim();
            if(e.startsWith("[")){
                e = e.substring(1,e.length());
            }
            if(e.endsWith("]")){
                e = e.substring(0,e.length()-1);
            }
            String[] parts = e.split(",");

            List<Integer> p = new LinkedList<>();
            for(int j=0;j<parts.length;j++){
                String ele = parts[j].trim();
                int m = Integer.parseInt(ele);
                p.add(m);
            }
            dx.add(p);
        }

        int[][] result = new int[dx.size()][dx.get(0).size()];
        int i=0;
        for(List<Integer> p : dx){
            int j=0;
            for(Integer e:p){
                result[i][j] = p.get(j);
                j++;
            }
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] l = ArrayFormatUtil.transform("[[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]");
        System.out.println(l.length);

        l=ArrayFormatUtil.transform("[" +
                "  [1,   4,  7, 11, 15]," +
                "  [2,   5,  8, 12, 19]," +
                "  [3,   6,  9, 16, 22]," +
                "  [10, 13, 14, 17, 24]," +
                "  [18, 21, 23, 26, 30]" +
                "]");
        System.out.println(l.length);
    }
}
