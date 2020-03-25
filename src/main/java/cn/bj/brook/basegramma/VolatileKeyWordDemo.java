package cn.bj.brook.basegramma;

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

}
