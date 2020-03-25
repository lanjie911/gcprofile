package cn.bj.brook.spring.otherbooter;


import cn.bj.brook.spring.bean.Human;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 从XML文件初始化bean的经典启动场景
 */
public class ClassicXMLBooter {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:classic-spring.xml");
        Human human = context.getBean("humanInstance", Human.class);
        human.sayHi();
    }
}
