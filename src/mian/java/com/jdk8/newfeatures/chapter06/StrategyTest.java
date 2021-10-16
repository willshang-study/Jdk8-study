package mian.java.com.jdk8.newfeatures.chapter06;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义一系列算法，把他们一个个封装起来，并且可以使他们可以相互替换（变化）。该模式可以使算法【独立于使用他们的客户程序】（稳定）而变化（扩展，子类化）
 * 即定义的这些策略谁在用，何时用与具体的策略定义无关
 */
public class StrategyTest {

    public static void main(String[] args) {

        Map policy = new HashMap<String,Object>();
        policy.put("EffectiveDate",new Date());
        policy.put("InsuredAddress",new Address());
        policy.put("HolderAddress",new Address());
        policy.put("Premium",new BigDecimal(9999));

        FieldPrint.print(new DateStrategy(),policy.get("EffectiveDate"));
        FieldPrint.print(new AddressStrategy(),policy.get("InsuredAddress"));
        FieldPrint.print(new AddressStrategy(),policy.get("HolderAddress"));
        FieldPrint.print(new PremiumStrategy(),policy.get("Premium"));
    }

    static class FieldPrint{
        static void print(Strategy strategy, Object field){
            System.out.println(strategy.convert(field));
        }
    }

    /**
     * Strategy Interface
     * @param <T>
     */
    interface Strategy<T>{
        String convert(T field);
    }

    static class DateStrategy implements Strategy<Date> {

        @Override
        public String convert(Date field) {
            System.out.println("日期转换成列印格式中...");
            return "民国：100/01/01";
        }
    }

    static class PremiumStrategy implements Strategy<BigDecimal> {

        @Override
        public String convert(BigDecimal field) {
            System.out.println("保费转换成列印格式中...");
            return "保费：9880.00";
        }
    }

    static class AddressStrategy implements Strategy<Address> {

        @Override
        public String convert(Address field) {
            System.out.println("地址信息转换成列印格式中...");
            return "地址：台北市信义区";
        }
    }

    static class Address{}
}
