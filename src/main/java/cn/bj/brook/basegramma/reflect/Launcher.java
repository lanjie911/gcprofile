package cn.bj.brook.basegramma.reflect;

import java.lang.reflect.*;

/**
 * 测试反射机制
 */
public class Launcher {
    public static void main(String[] args) {

        try {
            Constructor cons = RefBean.class.getConstructor(null);
            RefBean bean = (RefBean)cons.newInstance(null);

            Field fd = RefBean.class.getDeclaredField("age");
            int mds = fd.getModifiers();
            System.out.println(Modifier.toString(mds));
            fd.setAccessible(true);
            // Integer age = (Integer) fd.get(bean);
            fd.set(bean,10);
            System.out.println(bean.getAge());

            Method a = RefBean.class.getDeclaredMethod("action1",null);
            mds = a.getModifiers();
            System.out.println(Modifier.toString(mds));
            a.setAccessible(true);
            a.invoke(bean);

        } catch (NoSuchMethodException| NoSuchFieldException | IllegalAccessException |InstantiationException |InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
