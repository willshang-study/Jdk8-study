package mian.java.com.jdk8.news.function.function;

import java.util.function.Function;

/**
 * Function : Represents a function that accepts one argument and produces a result.
 *  abstract:   apply
 *  default:    compose
 *  default:    andThen
 *  default:    identity
 */
public class FunctionTest {

    public static void main(String[] args) {

        //  abstract:   apply
        // 将字符串转大写的操作传递进去，而不用事先定义好
        System.out.println(operationStrAction("will", item -> item.toUpperCase()));
        // 将字符串转小写的操作传递进去，而不用事先定义好
        System.out.println(operationStrAction("HELLO WORLD", item -> item.toLowerCase()));


    }

    // 操作字符串的一个行为方法，具体怎么操作，看传入的 Function 是怎么操作的
    // Function为一个入参一个返回参数的函数接口
    public static String operationStrAction (String keyWords, Function<String, String> function) {
        System.out.println("开始操作字符串了。。。");
        return function.apply(keyWords);
    }
}
