package cn.bj.brook.thread;

public class ThreadSleep {




    public static void sleep(int mils){
        try {
            Thread.sleep(mils);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
