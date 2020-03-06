package cn.bj.brook.spring.bean.annotation;

import org.springframework.stereotype.Component;

@Component
public class Cat {
    public void sayMew(){
        System.out.println("mew");
    }
}
