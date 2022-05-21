package mian.java.com.jdk8.newfeatures.chapter06;

/**
 * 1. 一个类继承了两个接口，两个接口中都有同样的default方法，则会编译报错
 * 三条规则判断
 *  (1) 类中的方法优先级最高。类或父类中声明的方法优先级高于任何声明为默认方法的优先级
 *  (2) 如果第一条无法判断，那么子接口的优先级更高：函数签名相同时，优先选择拥有最具体实现的默认方法的接口。如果B继承了A，那么B就更具体。
 *  (3) 最后如果还是无法判断(则编译器报错，Error: class C inherits unrelated defaults for hello() from types B and A.)，继承了多个接口的类，必须通过显示覆盖和调用期望的方法，子类中重写default方法，显示的选择调用哪个接口的default方法
 */
public class DefaultMethodTest1 {

    public static void main(String[] args) {
        // C继承A,B接口. 第一条无法判断。
        // 第二条，因B继承A接口，所以B更为具体，则输出 "hello from B"
        C c = new C();
        c.hello();

        // 依据第一条类或父类的方法优先级最高，但是E和D都并未重写hello方法。但是D的继承的接口A拥有default hello方法
        // 所以D( A.hello) 和 B.hello方法选择，B更为具体，则输出 "hello from B"
        E e = new E();
        e.hello();
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

    interface B1{
        default void hello(){
            System.out.println("hello from B");
        }
    }

    static class C implements A,B{
        void sayHello(){
            hello();
        }
    }

    static class D implements A{
    }

    static class E extends D implements B{
        void sayHello(){
            hello();
        }
    }

    /**
     * 若不override hello方法，则编译器报错，此时重写hello方法，使其明确调用B1接口的hello方法
     */
    static class F implements A,B1{

        @Override
        public void hello() {
            B1.super.hello();
        }
    }

}
