package cn.bj.brook.basegramma.stream;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * java stream api demo
 */
public class JSADemo {
    public static void main(String[] args) {
        Drug[] drugs = new Drug[3];

        Drug d1 = new Drug();
        d1.index = 100;
        drugs[0] = d1;

        Drug d2 = new Drug();
        d2.index = 255;
        drugs[1] = d2;

        Drug d3 = new Drug();
        d3.index = 192;
        drugs[2] = d3;

        Object[] ss = Arrays.stream(drugs).map(d-> d.index).sorted().toArray();
        System.out.println(Arrays.toString(ss));
    }
}
