package mian.java.com.jdk8.news.function.binaryOperator;

import java.util.Comparator;
import java.util.function.BinaryOperator;

/**
 * 两个入参和返回参数类型都是一样的，它是一种特殊的 BiFunction，继承自BiFunction
 */
public class Test {
    public static void main(String[] args) {

        // 继承自BiFunction的 apply
        System.out.println("4加5："+calculate("4","5",(a,b) -> String.valueOf(Integer.parseInt(a)+Integer.parseInt(b))));
        System.out.println("10减4："+calculate("10","4",(a,b) -> String.valueOf(Integer.parseInt(a)-Integer.parseInt(b))));

        // static minBy ：以字符串的长度做对比返回一个Comparator类型对象
        BinaryOperator<String> minBinaryOperator = BinaryOperator.minBy(Comparator.comparingInt(String::length));
        System.out.println(calculate("audi","lexus",minBinaryOperator));

        // static maxBy
        System.out.println(calculate("audi","lexus",BinaryOperator.maxBy(Comparator.comparingInt(String::length))));
    }

    //入参和返回参数都是String
    static String calculate(String a,String b,BinaryOperator<String> calculateAction){
        return calculateAction.apply(a,b);
    }
}
