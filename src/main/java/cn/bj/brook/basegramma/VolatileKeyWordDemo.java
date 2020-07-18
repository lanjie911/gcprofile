package cn.bj.brook.basegramma;

import static java.lang.System.out;

/**
 * 反编译volatile关键字
 */
public class VolatileKeyWordDemo {

    private volatile int messageID;
    private volatile int salary;

    public int getMessageID(){
        int i = 0;
        int j = 0;
        i = messageID;
        j = salary;
        return  i+j;
    }

    public void setMessageID(int m){
        this.messageID += m;
    }

    public static void main(String[] args) {
        String a = "hello";
        String b = "hello";
        String c = new String("hello");
        String d = new String("hello");
        String e = new String(a);
        String f = new String(a);
        String g = new String(b);
        out.println(a==b); //true
        out.println(a==c); //false
        out.println(c==d); //false
        out.println(e==f); //false
        out.println(c==e); //false
        out.println(f==g); //false
    }

}
