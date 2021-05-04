package mian.java.com.jdk8.news.methodReference;

import java.util.function.Function;

/**
 * 1. 对象实例::方法名
 * 2. 静态类::方法名
 * 3. 类实体::方法名
 * 4. ClassName::new (构造器引用)
 */
public class Test {

    public static void main(String[] args) {
        // 1. 对象实例::方法名
        PrintServiceImpl service = new PrintServiceImpl();
        Staff staff1 = service::print;
        staff1.work("1.对象实例::方法名");

        // 2. 静态类::方法名
        Staff staff2 = PrintUtil::print;
        staff2.work("2.静态类::方法名");

        // 3. 类实体::方法名
        Staff staff3 = PrintServiceImpl::print2;
        staff3.work("3.类实体::方法名");

        // 4. ClassName::new (构造器引用)
        Staff staff4 = Company::new;  //要求入参一致
        staff4.work("4.ClassName::new (构造器引用)");

        // 5. 当引用方法（toUpperCase）为 函数接口抽象方法（apply）的入参类型（String）中的方法时，即toUpperCase为入参String的一个method时，
        // （因为一定会有一个String的实例来调用toUpperCase,该实例就作为了 抽象方法(apply)的入参）
        Function<String,String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0]);

        // 此时 Function
        Function<MyString,String> f = MyTest::test;
        Function<MyString,String> f1 = MyString::toUpperCase;
    }

    // 静态内部类
    static class PrintUtil{
        static void print(String words){
            System.out.println("*********静态内部类***********");
            System.out.println(words);
        }
    }
}

@FunctionalInterface
interface Staff{
    void work(String content);
}

class PrintServiceImpl{
    void print(String words){
        System.out.println("********公共方法************");
        System.out.println(words);
    }
    public static void print2(String words) {
        System.out.println("*********静态方法***********");
        System.out.println(words);
    }
}

class Company{
    String name;
    Integer age;

    public Company(String name) {
        this.name = name;
        System.out.println("********构造器方法************");
        System.out.println(name);
    }

    public Company(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

class MyString{
    public String toUpperCase(){
        return null;
    }
}

class MyTest{
    static String test(MyString s){
        return s.toUpperCase();
    }
}

