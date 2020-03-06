package cn.bj.brook.basegramma;

import org.openjdk.jol.info.ClassLayout;

/**
 * 打印对象的长度
 */
public class ClassSizePrinter {
    public static void main(String[] args) {
        char[] t = new char[]{'a','b'};
        String emptyString = ClassLayout.parseInstance(t).toPrintable();
        System.out.println(emptyString);

    }
}
