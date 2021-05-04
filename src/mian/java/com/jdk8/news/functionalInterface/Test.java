package mian.java.com.jdk8.news.functionalInterface;

public class Test {

    public static void main(String[] args) {
        //实现 函数式接口 的三种方式
        /***************************************/
        //1. Constructor (构造器)
        Person person1 = new Person() {
            @Override
            public void say(String words) {
                System.out.println("构造器 Person Say:::"+words);
            }
        };
        person1.say("Hello World!");
        System.out.println(person1.getClass());

        Car car1 = new Car() {
            @Override
            public void start() {
                System.out.println("构造器 Car Start!");
            }

            @Override
            public void stop() {
                System.out.println("构造器 Car Stop!");
            }
        };
        car1.start();
        car1.stop();

        /***************************************/

        //2. Lambda表达式
        Person person2 = item -> System.out.println("Lambda Person Say:::"+item);
        person2.say("哈哈哈");
        System.out.println(person2.getClass());
//        Car car2 = () -> {};  //编译报错：Multiple non-overriding abstract methods found in interface

        /***************************************/
        //3. 方法引用 静态类::方法名
        Person person3 = System.out::println;
        person3.say("方法引用 哈哈哈");
        System.out.println(person3.getClass());
//        Car car3 = System.out::println; // 编译报错：Car is not a functional interface
    }
}

@FunctionalInterface
interface Person{
    void say(String words);
//    void work();
    // 该方法为Object类的方法
    // any implementation of the interface will have an implementation from java.lang.Object or elsewhere
    String toString();
    static void otherMethod(){
        System.out.println("我是一个静态方法");
    };
    default void otherMethod2(){
        System.out.println("我是一个default方法实现");
    }
}

interface Car{
    void start();
    void stop();
}
