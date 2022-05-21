package mian.java.com.jdk8.examination.model;

public class Risk {
    private String Name;
    private Integer Age;

    public Risk(String name, Integer age) {
        Name = name;
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }
}
