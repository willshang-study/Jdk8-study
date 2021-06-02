package mian.java.com.jdk8.news.collector;

import mian.java.com.jdk8.demo.bean.Car;
import mian.java.com.jdk8.demo.bean.CarFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

    }

    /**
     *  1. 将Car实例按照汽车品牌进行分组，key为汽车品牌，value为Car实例
     *  2. 统计每个品牌下汽车的数量，key为汽车品牌，value为Car的数量
     *  3. 30w万元以上一个分区，30w元以下一个分区
     */
    static void groupingBy(){
        List<Car> carList = CarFactory.buildCars();

        // 1
        Map<String,List<Car>> carMap = carList.stream().collect(Collectors.groupingBy(Car::getBrand));
        carMap.entrySet().forEach(System.out::println);

        // 2
        Map<String,Long> carCountMap = carList.stream().collect(Collectors.groupingBy(Car::getBrand,Collectors.counting()));
        carCountMap.entrySet().forEach(System.out::println);

        // 3
        Map<Boolean,List<Car>> carPartition = carList.stream().collect(Collectors.partitioningBy(item -> item.getPrice() < 300000));
        carPartition.entrySet().forEach(System.out::println);
    }
}
