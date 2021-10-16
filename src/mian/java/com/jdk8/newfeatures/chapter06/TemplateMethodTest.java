package mian.java.com.jdk8.newfeatures.chapter06;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义一个操作中的算法的骨架(稳定)，而将一些步骤延迟(变化)到子类中。Template Method使得子类可以不改变(复用)一个算法的结构即可重定义(override 重写)该算法的某些特定步骤。
 */
public class TemplateMethodTest {

    public static void main(String[] args) {

        // 调用核保
        AbstractPolicyService lifePolicyService = new LifePolicyService();
        lifePolicyService.underwritingPolicy();

        AbstractPolicyService healthPolicyService = new HealthPolicyService();
        healthPolicyService.underwritingPolicy();
    }

    /**
     * 1. AbstractClass
     */
    static abstract class AbstractPolicyService {
        /**
         * Template Method
         */
        public void underwritingPolicy(){
            System.out.println("模板方法...");
            initPolicy(new HashMap());
            savePolicy();
            underwriting();
        }
        private void savePolicy(){
            System.out.println("... 调用平台持久化保单");
        }
        private void underwriting(){
            System.out.println("... 调用平台核保规则");
        }
        /**
         * Abstract Method: 依据不同的商品，初始化的方法各不相同，需要子类去实现
         */
        abstract void initPolicy(Map policy);
    }

    /**
     * 2.1 Concrete Class 寿险保单
     */
    static class LifePolicyService extends AbstractPolicyService {

        @Override
        void initPolicy(Map policy) {
            System.out.println("... 【寿险保单】初始化和默认值的实现逻辑");
        }
    }

    /**
     * 2.2 Concrete Class 健康险保单
     */
    static class HealthPolicyService extends AbstractPolicyService {

        @Override
        void initPolicy(Map policy) {
            System.out.println("... 【健康险保单】初始化和默认值的实现逻辑");
        }
    }
}
