package mian.java.com.jdk8.newfeatures.chapter06;

/**
 * 涉及重载的上下文里，将匿名类转换为Lambda表达式可能导致最终的代码更加晦涩
 * Task接口：      execute（无参无返回值）
 * Runnable接口：  run（无参无返回值）
 * Test拥有两个重载方法
 *  doSomething(Task)
 *  doSomething(Runnable)
 *
 * 当一个Lambda表达式作为doSomething方法的参数时，该Lambda表达式无法根据上下文来确定类型到底为Task还是为Runnable
 * 编译报错：ambiguous method call.both
 */
public class Test1 {

    public static void main(String[] args) {
        Test1 test1 = new Test1();
//        test.doSomething(()-> System.out.println()); // 编译报错：ambiguous method call.both
        test1.doSomething((Task)()-> System.out.println()); // 解决办法：将Lambda表达式显示的类型转换 如转换成 Task 或者Runnable
    }

    // overload
    void doSomething(Task t){
        t.execute();
    }

    // overload
    void doSomething(Runnable runnable){
        runnable.run();
    }

    // 和 Runnable.run 一致的方法签名(无参无返回值)
    interface Task{
        void execute();
    }
}
