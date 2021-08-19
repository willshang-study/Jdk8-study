package mian.java.com.jdk8.newfeatures.chapter01;

public class DefaultInterface {
    public static void main(String[] args) {

    }

    // C inherits unrelated defaults for hello() from types A and B
//    static class Programmer implements Person,Animal{
//
//    }

    interface Animal{
        default void say(){
            System.out.println("我是Animal！");
        }
    }

    interface Person{
        default void say(){
            System.out.println("我是Person！");
        }
    }
}
