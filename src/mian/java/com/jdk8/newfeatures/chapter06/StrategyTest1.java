package mian.java.com.jdk8.newfeatures.chapter06;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 用【Lambda表达式】优化策略模式
 */
public class StrategyTest1 {

    public static void main(String[] args) {

        Map policy = new HashMap<String,Object>();
        policy.put("EffectiveDate",new Date());
        policy.put("InsuredAddress",null);
        policy.put("HolderAddress",null);
        policy.put("Premium",new BigDecimal(9999));

        FieldPrint.print(date-> {
            System.out.println("日期转换成列印格式中...");
            return "民国：100/01/01";
            },policy.get("EffectiveDate"));

        FieldPrint.print(address->{
            System.out.println("地址信息转换成列印格式中...");
            return "地址：台北市信义区";
        },policy.get("InsuredAddress"));

        FieldPrint.print(address->{
            System.out.println("地址信息转换成列印格式中...");
            return "地址：台北市信义区";
        },policy.get("HolderAddress"));

        FieldPrint.print(premium->{
            System.out.println("保费转换成列印格式中...");
            return "保费：9880.00";
        },policy.get("Premium"));
    }

    static class FieldPrint{
        static void print(Function<Object,String> strategy, Object field){
            System.out.println(strategy.apply(field));
        }
    }
}
