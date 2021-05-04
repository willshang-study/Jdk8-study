package mian.java.com.jdk8.news.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 需要实现将list内的数据字母变为大写输出
 *
 *
 *
 */
public class Test {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("audi","benz","bmw");

        // 1. 传统循环
        List<String> newList = new ArrayList<>();
        for(String item : list){
            newList.add(item.toUpperCase());
        }
        System.out.println(newList);

        // 2. 加入Lambda表达式
        list.forEach(item -> newList.add(item.toUpperCase()));
        System.out.println(newList);

        // 3. stream
        System.out.println(list.stream().map(item -> item.toUpperCase()).collect(Collectors.toList()));
        System.out.println(list.stream().map(String::toUpperCase).collect(Collectors.toList()));
    }
}
