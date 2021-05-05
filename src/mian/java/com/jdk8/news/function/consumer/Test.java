package mian.java.com.jdk8.news.function.consumer;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("audi","benz","bmw");

        // forEach接收一个Consumer类型的参数，对list内的元素进行处理，且没有值返回。
        list.forEach(item -> System.out.println(item.toUpperCase()));
    }
}
