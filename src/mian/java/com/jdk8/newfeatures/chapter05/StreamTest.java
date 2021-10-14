package mian.java.com.jdk8.newfeatures.chapter05;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        List list = Arrays.asList("apple","huawei","xiaomi");
        list.stream().forEach(System.out::println);
        list.stream().map(item->item).forEach(System.out::println);

    }
}
