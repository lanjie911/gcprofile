package cn.bj.brook.spring.bean.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalConfiguration {

    @Bean(value = "dog",initMethod = "init")
    public Dog getDog(){
        return new Dog();
    }
}
