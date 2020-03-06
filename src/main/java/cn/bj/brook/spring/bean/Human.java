package cn.bj.brook.spring.bean;

public class Human {
    private int age;
    private String name;

    public Human(){
        this(20,"brook");
    }

    public Human(int age, String name){
        this.age = age;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public  void sayHi(){
        System.out.println("hi, world.");
    }
}
