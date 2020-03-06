package cn.bj.brook.basegramma;

import java.io.File;
import java.io.IOException;

/**
 * 资源和文件加载
 * 测试class加载和File加载的不同
 */
public class ResourceAndFileLoad {
    public static void main(String[] args) {
        // 如果采用getResourceAsStream的方式
        var is = ResourceAndFileLoad.class.getResourceAsStream("/cn/bj/brook/Logg.class");
        System.out.println(is);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 这个和类实例的方法相同
        is = new ResourceAndFileLoad().getClass().getResourceAsStream("/cn/bj/brook/Logg.class");
        System.out.println(is);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        is = new ResourceAndFileLoad().getClass().getResourceAsStream("/data.txt");
        System.out.println(is);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 如果使用File
        var me = new File(".");
        System.out.println(me.getAbsolutePath());

        me = new File("");
        System.out.println(me.getAbsolutePath());
    }
}
