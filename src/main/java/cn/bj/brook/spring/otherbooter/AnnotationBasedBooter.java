package cn.bj.brook.spring.otherbooter;

import cn.bj.brook.spring.bean.annotation.AnimalConfiguration;
import cn.bj.brook.spring.bean.annotation.Cat;
import cn.bj.brook.spring.bean.annotation.Dog;
import cn.bj.brook.spring.bean.annotation.Zoo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 演示如何用注释初始化的例子
 */
public class AnnotationBasedBooter {
    public static void main(String[] args) {
        // 直接扫描使用 @Component作为Bean类型的类
        ApplicationContext context = new AnnotationConfigApplicationContext(Cat.class);
        Cat cat = context.getBean("cat",Cat.class);
        cat.sayMew();

        // 扫描一个@configuration标记的类
        ApplicationContext context2 = new AnnotationConfigApplicationContext(AnimalConfiguration.class);
        Dog dog = context2.getBean("dog",Dog.class);
        dog.sayHound();

        // 还可以接受一个基础的包名作为参数
        ApplicationContext context3 = new AnnotationConfigApplicationContext("cn.bj.brook.spring.bean.annotation");
        Cat cat2 = context3.getBean("cat",Cat.class);
        cat2.sayMew();
        Dog dog2 = context3.getBean("dog",Dog.class);
        dog2.sayHound();
        Zoo zoo = context3.getBean("zoo",Zoo.class);
        zoo.whoAmI();
    }
}
