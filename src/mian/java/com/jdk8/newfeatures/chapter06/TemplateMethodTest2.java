package mian.java.com.jdk8.newfeatures.chapter06;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 用【方法引用】优化模板方法模式
 */
public class TemplateMethodTest2 {

    public static void main(String[] args) {

        PolicyService policyService = new PolicyService();
        // 调用核保
        policyService.underwritingPolicy(LifePolicy::initPolicy); //LifePolicy::initPolicy 可复用
        policyService.underwritingPolicy(HealthPolicy::initPolicy);//HealthPolicy::initPolicy 可复用
    }

    static class PolicyService {
        /**
         * Template Method
         */
        public void underwritingPolicy(Consumer<Map> initAction){
            System.out.println("模板方法...");
            initAction.accept(new HashMap<>()); // 将原来的抽象方法删除，以函数是接口的调用代替
            savePolicy();
            underwriting();
        }
        private void savePolicy(){
            System.out.println("... 调用平台持久化保单");
        }
        private void underwriting(){
            System.out.println("... 调用平台核保规则");
        }
    }

    /**
     * 2.1 Concrete Class 寿险保单
     */
    static class LifePolicy{
        public static void initPolicy(Map policy) {
            System.out.println("... 【寿险保单】初始化和默认值的实现逻辑");
        }
    }

    /**
     * 2.2 Concrete Class 健康险保单
     */
    static class HealthPolicy{
        public static void initPolicy(Map policy) {
            System.out.println("... 【健康险保单】初始化和默认值的实现逻辑");
        }
    }
}
