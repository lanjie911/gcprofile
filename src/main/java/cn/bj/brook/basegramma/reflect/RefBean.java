package cn.bj.brook.basegramma.reflect;

public class RefBean {
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private final void action1(){
        System.out.println("action1 called");
    }

    private String action2(Long b){
        System.out.println("action2 called, b is "+b);
        return Long.toBinaryString(b);
    }
}
