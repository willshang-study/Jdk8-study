package mian.java.com.jdk8.news.function.biFunction;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Test {

    public static void main(String[] args) {

        // abstract apply
        System.out.println("加法："+calculate("4","6",(a,b) -> Integer.parseInt(a) + Integer.parseInt(b) ));
        System.out.println("减法："+calculate("4","6",(a,b) -> Integer.parseInt(a) - Integer.parseInt(b) ));

        // default andThen
        BiFunction<String,String,StringBuffer> biFunction = (a,b) -> new StringBuffer(a).append(b);
        Function<StringBuffer,String[]> afterFunction = item -> item.toString().split("");
        // 先执行currentBiFunction的将两个字符串合并，再执行afterFunction的分割字符串
        Arrays.stream(biFunction.andThen(afterFunction).apply("Hello", "World")).forEach(item -> System.out.println(item));

    }

    // 定义一个更高层次的计算方法，该方法入参为 a,b，还有一个计算action来传递具体的计算逻辑
    static Integer calculate(String a,String b, BiFunction<String,String,Integer> calculateAction){
        return calculateAction.apply(a,b);
    }
}


