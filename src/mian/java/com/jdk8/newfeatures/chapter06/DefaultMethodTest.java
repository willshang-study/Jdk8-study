package mian.java.com.jdk8.newfeatures.chapter06;

/**
 * 抽象类 和 接口的区别
 *      一个类只能继承一个抽象类，但是可以继承多个接口
 *      抽象类可以定义【实例变量】，保存一个通用状态，而接口是不能有实例变量的(接口中的变量是final的，不可更改值)
 */
public class DefaultMethodTest {

    interface PolicyInterface{
        int policyStatus = 0;
        default void updateStatus(int status){
//            policyStatus = status;
        }
    }

    abstract class AbstractPolicy{
        int policyStatus;
        void updateStatus(int status){
            policyStatus = status;
        }
    }

}
