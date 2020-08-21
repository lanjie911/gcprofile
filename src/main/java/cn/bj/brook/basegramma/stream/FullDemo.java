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
        s1.average = 98;
        s1.number = 50;
        Score s2 = new Score();
        s2.average = 82;
        s2.number = 55;
        List<Score> ls = new LinkedList<>();
        ls.add(s1);
        ls.add(s2);
        Long total = 0l;
        total = ls.stream().reduce( 3l, (a, b) -> {
                    System.out.println("a1="+a);
                    System.out.println("b1="+(b.average*b.number));
                    return b.average*b.number+a;
                }, (m, n)->{
            return 0l;
                }
        );
        System.out.println("t=" + total);

    }

    public <T> List<T> getTOfPara(T input) {
        List<T> list = new LinkedList<>();
        list.add(input);
        return list;
    }
}