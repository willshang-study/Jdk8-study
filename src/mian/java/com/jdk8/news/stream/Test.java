package mian.java.com.jdk8.news.stream;

import mian.java.com.jdk8.demo.bean.Car;
import mian.java.com.jdk8.demo.bean.CarUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {

        /***构造stream的几种方式****/
        // 1. of 传递一系列的参数(T... values)
        Stream<String> cars1 = Stream.of("lexus","benz","audi");
        // 2. Arrays的静态方法stream
        Stream<String> cars2 = Arrays.stream(new String[]{"lexus","benz","audi"});
        // 3. Collection的default stream方法 (最常用)
        List<Car> carsList = CarUtil.initCars();
        Stream<Car> cars3 = carsList.stream();

        /***构造stream的几种应用****/
        // 1. 打印 10到20 的数字的前五个
        IntStream intStream = IntStream.range(10,20);
        intStream.limit(5).forEach(System.out::println);

        // 2. 整型list集合内的数字*2，再求和
        Stream<Integer> numStream = Stream.of(new Integer[]{1,2,3,4,5,6});
        System.out.println(numStream.map(item -> item*2).reduce(0,Integer::sum));

        // 3. 流（Stream）转换成数组
        String[] cars = cars1.toArray(String[]::new);
        Arrays.stream(cars).forEach(System.out::println);

        // 4. 流（Stream）转成 List
        // 4.1 提供集合List函数
        Supplier<List<String>> supplier = () -> new ArrayList<>();
        // 4.2 将元素并到集合List中的函数
        BiConsumer<List<String>,String> accumulator = (theList, item) -> theList.add(item);
        // 4.3 组合两个List的函数
        BiConsumer<List<String>,List<String>> combiner = (a,b) -> a.addAll(b);
        List<String> list = cars2.collect(supplier,accumulator,combiner);
        list.forEach(System.out::println);
        // 对4进行改造后为：
        IntStream.rangeClosed(0,5).collect(ArrayList::new, List::add, List::addAll).forEach(System.out::println);
        System.out.println(Stream.of("lexus","benz","audi").collect(StringBuilder::new,StringBuilder::append,StringBuilder::append).toString());
        System.out.println(Stream.of("lexus","benz","audi").collect(Collectors.joining()));
        // 用Stream的另外一个collect重载的方法
        Stream.of("lexus","benz","audi").collect(Collectors.toList()).forEach(System.out::println);
        Stream.of("lexus","benz","audi").collect(Collectors.toCollection(ArrayList::new)).forEach(System.out::println);

        // 5. 给定一个集合，将所有的元素转大写，然后输出
        Stream.of("lexus","benz","audi").map(String::toUpperCase).forEach(System.out::println);

        // 6. flat map举例 flatMap : 将List中的每一个子list的stream打平，组成一个stream
        List<List<String>> carStore = Arrays.asList(Arrays.asList("lexus","benz"),Arrays.asList("audi","bmw"),Arrays.asList("honda","mazda"));
        carStore.stream().flatMap(item -> item.stream()).map(String::toUpperCase).forEach(System.out::println);

        // 7. Stream.iterate 会产生死循环
//        Stream.iterate(2,item  -> item*2).forEach(System.out::println);

        // 8. 将数组或list中前后两个元素用“-”相连接产生新的字符串
        String[] aaaa = new String[]{"11111111","2222222","333333"};
        Stream.of(aaaa).reduce((a,b) -> new StringBuilder(a).append("-").append(b).toString()).ifPresent(System.out::println);
    }
}
