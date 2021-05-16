package mian.java.com.jdk8.news.stream.senior;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 *  1.并发实验
 *  2.短路实验
 */
public class SeniorTest {

    public static void main(String[] args) {

//        parallelTest();
//        shortCircuit();
//        removeDuplicate();
        cross();
    }

    /**
     * 并发的耗时
     */
    static void parallelTest(){
        //创建list
        List<String> list = new ArrayList<>(5000000);
        for(int i=0;i<5000000;i++){
            list.add(UUID.randomUUID().toString());
        }

        System.out.println("开始");
        long startTime = System.nanoTime();
        // sequential Stream(串行流)
        list.stream().sorted().count();
        // parallel Stream(并行流)
//        list.parallelStream().sorted().count();
        long endTime = System.nanoTime();
        System.out.println("共耗时：：："+TimeUnit.MILLISECONDS.convert((endTime-startTime),TimeUnit.NANOSECONDS)+"毫秒");
    }

    /**
     * mapToInt内循环了几次？ 答：1次
     */
    static void shortCircuit(){
        List<String> list = Arrays.asList("Hello","World","Hello World");
        // 找到长度为5的第一个单词
        list.stream().mapToInt(String::length).filter(length -> length == 5).findFirst().ifPresent(System.out::println);
        list.stream().mapToInt(item -> {
            System.out.println(item); // 只打印了一次Hello，短路操作
            return item.length();
        }).findFirst().ifPresent(System.out::println);
    }

    /**
     * 问题：将句子数组中的单词去重并输出
     */
    static void removeDuplicate(){
        List<String> list = Arrays.asList("Welcome to Wuxi","Wuxi is a good place","I love Wuxi");
        // 1. 将句子Stream flat map成 单词Stream
        // 2. 再集合成Set类型（天然去重）
        list.stream().flatMap(item -> Arrays.stream(item.split(" "))).collect(Collectors.toSet()).forEach(System.out::println);

        // 可使用 stream distinct方式
        System.out.println("*************************");
        list.stream().flatMap(item -> Arrays.stream(item.split(" "))).distinct().forEach(System.out::println);

        // 方式3：
        // 1. 先将List<String(句子)> map 成List<String[](单词数组)>
        // 2. 再将元素String[](单词数组)  flat map 为 string  方法为Arrays.stream
        System.out.println("*************************");
        list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
    }

    /**
     *  二维流操作 flat map
     *  如：list1 {Hi,Hello,你好}
     *      list2 {zhangsan,lisi,wangwu,zhaoliu}
     *  输出：Hi zhangsan Hi lisi Hi wangwu Hello zhangsan Hello lisi ...
     */
    static void cross(){
        List<String> list1 = Arrays.asList("Hi","Hello","你好");
        List<String> list2 = Arrays.asList("Zhangsan","Lisi","Wangwu","Zhaoliu");
        list1.stream().flatMap(item1 -> list2.stream().map(item2 -> new StringBuilder(item1).append(" ").append(item2).toString())).forEach(System.out::println);
    }
}
