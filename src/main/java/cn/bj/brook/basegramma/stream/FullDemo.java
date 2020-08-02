package cn.bj.brook.basegramma.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class FullDemo {
    public static void main(String[] args) {
        Stream<String> s1 = Stream.of("hello");
        Stream<String> s2 = Stream.of(new String[]{"world", "Brook"});
        Stream.concat(s1, s2).forEach(System.out::println);
    }

    public <T> List<T> getTOfPara(T input) {
        List<T> list = new LinkedList<>();
        list.add(input);
        return list;
    }
}