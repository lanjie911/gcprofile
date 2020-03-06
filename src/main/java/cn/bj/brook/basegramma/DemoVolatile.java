package cn.bj.brook.basegramma;

import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class DemoVolatile {
    public volatile int i;

    public void doModify() {
        i = i + 1;
    }

    AtomicInteger ai;

    FutureTask<String> ft ;
}

class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    public static SingletonDemo getInstance(){
        if(instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
