package cn.bj.brook.spring.bean.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zoo {
    @Autowired
    Cat cat;

    @Autowired
    Dog dog;

    public void whoAmI(){
        System.out.println("---");
        cat.sayMew();
        dog.sayHound();
        System.out.println("---");
    }
}
