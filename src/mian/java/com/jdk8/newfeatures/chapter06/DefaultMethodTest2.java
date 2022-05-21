package mian.java.com.jdk8.newfeatures.chapter06;

/**
 * 菱形继承问题
 */
public class DefaultMethodTest2 {

    public static void main(String[] args) {
        D d = new D();
        d.hello();
    }

    interface A{
        default void hello(){
            System.out.println("hello from A");
        }
    }

    interface B extends A{
        default void hello(){
            System.out.println("hello from B");
        }
    }

    interface C extends A{
        default void hello(){
            System.out.println("hello from B");
        }
    }

    static class D implements B,A{
        void sayHello(){
            hello();
        }
    }
}
