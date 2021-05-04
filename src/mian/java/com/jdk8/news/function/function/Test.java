package mian.java.com.jdk8.news.function.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Function : Represents a function that accepts one argument and produces a result.
 *  abstract:   apply
 *  default:    compose
 *  default:    andThen
 *  default:    identity
 */
public class Test {

    public static void main(String[] args) {

        //  abstract:   apply
        // 将字符串转大写的操作传递进去，而不用事先定义好
        System.out.println(operationStrAction("will", item -> item.toUpperCase()));
        // 将字符串转小写的操作传递进去，而不用事先定义好
        System.out.println(operationStrAction("HELLO WORLD", item -> item.toLowerCase()));

        // default compose
        Function<String,String> f1 = item -> item.toUpperCase();
        Function<String,String[]> f2 = item -> item.split("");
        // f1 为beforeFunction 先执行转大写；f2为currentFunction执行分割字符串
        Arrays.stream(f2.compose(f1).apply("Hello World")).forEach(item -> System.out.println(item));

        // default andThen
        // f1 为currentFunction先执行转大写；f2为afterFunction再执行分割字符串
        Arrays.stream(f1.andThen(f2).apply("Hello World")).forEach(item -> System.out.println(item));

        // default identity
        // 返回一个输入和输出是一样的值(当前表达式的类型就是需要返回的类型时，可省略return 关键词)
        // 应用场景：将list转为map
        List<String> cars = Arrays.asList("audi","bmw","lexus","peugeot");
        System.out.println(cars.stream().collect(Collectors.toMap(Function.identity(), String::length)).entrySet());
    }

    // 操作字符串的一个行为方法，具体怎么操作，看传入的 Function 是怎么操作的
    // Function为一个入参一个返回参数的函数接口
    public static String operationStrAction (String keyWords, Function<String, String> function) {
        System.out.println("开始操作字符串了。。。");
        return function.apply(keyWords);
    }
}
