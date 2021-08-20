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

        Function<Integer,Integer> f = a -> a+1;

        Function<Integer,Integer> g = a -> a*a;

        operationNumAction(10, f);

        operationNumAction(10, g);

        // f返回的结果作为g的入参
        operationNumAction(10, f.andThen(g));

        // g返回的结果作为f的入参
        operationNumAction(10, f.compose(g));
    }

    public static void operationNumAction (Integer keyWords, Function<Integer, Integer> function) {
        System.out.println(keyWords+"::::"+function.apply(keyWords));
    }
}
