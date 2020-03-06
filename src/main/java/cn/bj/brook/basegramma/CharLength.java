package cn.bj.brook.basegramma;

import java.nio.charset.Charset;

/**
 * 测试字符char的字节长度
 */
public class CharLength {
    public static void main(String[] args) {
        // 字节数组的长度是3 6
        System.out.println("测".getBytes().length);
        System.out.println("测是".getBytes().length);
        // 3+1 = 4
        System.out.println("测a".getBytes().length);
        // 这是一个utf-16字符，所以是4
        System.out.println("😯".getBytes().length);

        // 显示6 9
        String u16s = new String("测".getBytes(), Charset.forName("UTF-16"));
        System.out.println(u16s.getBytes().length);
        u16s = new String("测是".getBytes(), Charset.forName("UTF-16"));
        System.out.println(u16s.getBytes().length);
        u16s = new String("测是一".getBytes(), Charset.forName("UTF-16"));
        System.out.println(u16s.getBytes().length);
        u16s = new String("测是一下".getBytes(), Charset.forName("UTF-16"));
        System.out.println(u16s.getBytes().length);
    }
}
