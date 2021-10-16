package mian.java.com.jdk8.newfeatures.chapter06;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 用【Lambda表达式】优化模板方法模式
 */
public class TemplateMethodTest1 {

    public static void main(String[] args) {

        // 调用核保
        PolicyService policyService = new PolicyService();

        policyService.underwritingPolicy(policyInfo -> {
            System.out.println("... 【寿险保单】初始化和默认值的实现逻辑");// 简单不易复用的逻辑,若需复用，则用TemplateMethodTest2方式重构
        });

        policyService.underwritingPolicy(policyInfo -> {
            System.out.println("... 【健康险保单】初始化和默认值的实现逻辑");// 简单不易复用的逻辑,若需复用，则用TemplateMethodTest2方式重构
        });
    }

    static class PolicyService {
        /**
         * Template Method
         */
        public void underwritingPolicy(Consumer<Map> initAction){
            System.out.println("模板方法...");
            initAction.accept(new HashMap());// 将原来的抽象方法删除，以函数是接口的调用代替
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
}
