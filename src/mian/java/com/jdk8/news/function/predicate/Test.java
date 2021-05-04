package mian.java.com.jdk8.news.function.predicate;

import mian.java.com.jdk8.demo.bean.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Test {

    public static void main(String[] args) {
        List<Car> list = Arrays.asList(new Car("audi",100000),new Car("benz",200000),new Car("bmw",300000),new Car("lexus",380000));
        // test
        filter(list, item -> item.getPrice() > 200000).forEach(item -> System.out.println("大于20w的车："+item));
        filter(list, item -> item.getPrice() < 200000).forEach(item -> System.out.println("小于20w的车："+item));

        // 使用stream的filter方法，也是接收一个Predicate
        list.stream().filter(item -> Objects.equals(item.getName(),"lexus")).forEach(item -> System.out.println("名字为lexus的车："+item));

        // and
        andFilter(list, item -> item.getPrice() > 200000, item -> Objects.equals(item.getName(),"lexus")).forEach(item -> System.out.println("大于20w的车 and 名字为lexus的车："+item));

        // negate
        negateFilter(list, item -> Objects.equals(item.getName(),"lexus")).forEach(item -> System.out.println("名字不是lexus的车："+item));

        // or
        orFilter(list, item -> item.getPrice() < 200000, item -> Objects.equals(item.getName(),"lexus")).forEach(item -> System.out.println("小于于20w的车 or 名字为lexus的车："+item));

        // isEqual
        System.out.println(Predicate.isEqual("aaa").test("aaa"));
    }

    static List<Car> filter(List<Car> cars,Predicate<Car> filterAction){
        List<Car> newList = new ArrayList<>();
        cars.stream().forEach(item -> {
            if(filterAction.test(item)){
                newList.add(item);
            }
        });
        return newList;
    }

    static List<Car> andFilter(List<Car> cars,Predicate<Car> filter1,Predicate<Car> filter2){
        List<Car> newList = new ArrayList<>();
        cars.stream().forEach(item -> {
            if(filter1.and(filter2).test(item)){
                newList.add(item);
            }
        });
        return newList;
    }

    static List<Car> negateFilter(List<Car> cars,Predicate<Car> filterAction){
        List<Car> newList = new ArrayList<>();
        cars.stream().forEach(item -> {
            if(filterAction.negate().test(item)){
                newList.add(item);
            }
        });
        return newList;
    }

    static List<Car> orFilter(List<Car> cars,Predicate<Car> filter1,Predicate<Car> filter2){
        List<Car> newList = new ArrayList<>();
        cars.stream().forEach(item -> {
            if(filter1.or(filter2).test(item)){
                newList.add(item);
            }
        });
        return newList;
    }
}
