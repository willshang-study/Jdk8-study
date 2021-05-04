package mian.java.com.jdk8.news.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Note that instances of functional interfaces can be created with
 *  1. lambda expressions,
 *  2. method references,
 *  3. or constructor references.
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        // 1. “匿名内部类”
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        // 2. “Lambda表达式”
        //  1.  Consumer是一个FunctionalInterface
        //  2.  exactly one abstract method is "accept"
        list.forEach(item -> System.out.println(item));

        //3. method reference方法引用
        list.forEach(System.out::println);
    }
}
