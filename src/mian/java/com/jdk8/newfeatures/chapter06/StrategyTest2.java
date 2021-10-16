package mian.java.com.jdk8.newfeatures.chapter06;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * 用【方法引用】优化策略模式
 */
public class StrategyTest2 {

    @SuppressWarnings("all")
    public static void main(String[] args) {

        Map policy = new HashMap<String,Object>();
        policy.put("EffectiveDate",new Date());
        policy.put("InsuredAddress",null);
        policy.put("HolderAddress",null);
        policy.put("Premium",new BigDecimal(9999));

        FieldPrint.print(DateStrategy::convert,policy.get("EffectiveDate"));
        FieldPrint.print(AddressStrategy::convert,policy.get("InsuredAddress"));
        FieldPrint.print(AddressStrategy::convert,policy.get("HolderAddress"));
        FieldPrint.print(PremiumStrategy::convert,policy.get("Premium"));
    }

    static class FieldPrint{
        static void print(Function<Object,String> strategy, Object field){
            System.out.println(strategy.apply(field));
        }
    }

    static class DateStrategy{
        public static String convert(Object field) {
            System.out.println("日期转换成列印格式中...");
            return "民国：100/01/01";
        }
    }

    static class PremiumStrategy{
        public static String convert(Object field) {
            System.out.println("保费转换成列印格式中...");
            return "保费：9880.00";
        }
    }

    static class AddressStrategy{
        public static String convert(Object field) {
            System.out.println("地址信息转换成列印格式中...");
            return "地址：台北市信义区";
        }
    }
}
