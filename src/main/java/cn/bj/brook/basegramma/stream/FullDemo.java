package cn.bj.brook.basegramma.stream;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.lang.System.in;
import static java.lang.System.out;

public class FullDemo {

    static class Score {
        // 平均分
        int average;
        // 学生数量
        int number;
    }

    static void a1() {
        Score s1 = new Score();
        s1.average = 98;
        s1.number = 50;
        Score s2 = new Score();
        s2.average = 82;
        s2.number = 55;
        List<Score> ls = new LinkedList<>();
        ls.add(s1);
        ls.add(s2);
        // 这是真正求平均分的做法，看起来很高大上
        OptionalDouble d = ls.stream().flatMap(s -> {
            int[] all = new int[s.number];
            Arrays.fill(all, s.average);
            return Arrays.stream(all).mapToObj(e -> Integer.valueOf(e));
        }).mapToInt(x -> x.intValue()).average();
        out.println(d.getAsDouble());
    }

    public static void main(String[] args) {
        Score s1 = new Score();
        s1.average = 1;
        s1.number = 10;
        Score s2 = new Score();
        s2.average = 2;
        s2.number = 20;
        Score s3 = new Score();
        s3.average = 3;
        s3.number = 30;
        List<Score> ls = new LinkedList<>();
        ls.add(s1);
        ls.add(s2);
        ls.add(s3);


        int m = ls.stream().parallel().reduce(0, (value, score) -> {
                    System.out.println("value=" + value);
                    System.out.println("right=" + score.average);
                    return score.average * score.number + value;
                }, (value1, value2) -> {
                    System.out.printf("v1=%d,v2=%d %n", value1, value2);
                    return value1.intValue() + value2.intValue();
                }
        );
        System.out.println("m=" + m);


        Map<String, Integer> mapx = ls.stream().parallel().collect(HashMap<String, Integer>::new, (map, ele) -> {
            System.out.printf("e.average is %d%n", ele.average);
            map.put(ele.average + "", ele.number);
        }, (prevMap, postMap) -> {
            // 如果不是并行流，那么本函数压根不会得到被调用的机会
            // 只有流是并行的，才会调用本函数
            Set<String> keySets = postMap.keySet();
            keySets.forEach(
                    (key) -> {
                        Integer v = postMap.get(key);
                        prevMap.put(key, v);
                    }
            );
        });
        mapx.keySet().forEach(key -> {
            System.out.println("key=" + key + ",value=" + mapx.get(key));
        });

    }

    public <T> List<T> getTOfPara(T input) {
        List<T> list = new LinkedList<>();
        list.add(input);
        return list;
    }
}