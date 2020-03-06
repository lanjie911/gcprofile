package cn.bj.brook.spring;

import cn.bj.brook.spring.bean.annotation.Dog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AppLauncher {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppLauncher.class,args);
        Dog dog = context.getBean("dog", Dog.class);
        dog.sayHound();
    }
}
