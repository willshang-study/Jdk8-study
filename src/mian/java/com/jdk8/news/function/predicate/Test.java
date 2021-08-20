package mian.java.com.jdk8.news.function.predicate;

import mian.java.com.jdk8.demo.bean.Car;
import mian.java.com.jdk8.demo.bean.CarFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Test {

    public static void main(String[] args) {
        List<Car> carList = Arrays.asList(
                new Car("Lexus","LS600H",1000000),
                new Car("Benz","S600L",1000000),
                new Car("Tesla","ModelY",300000),
                new Car("Honda","Fit",100000),
                new Car("Toyota","Avalon",200000),
                new Car("Lexus","ES200",350000));

        Predicate<Car> isLexusCar = car -> "Lexus".equals(car.getBrand());
        Predicate<Car> isExpensiveCar = car -> car.getPrice() > 500000;

        // 1 雷克萨斯车
        filter(carList, isLexusCar);

        // 2 雷克萨斯且大于50w的车
        filter(carList, isLexusCar.and(isExpensiveCar));

        // 2 雷克萨斯且或50w的车
        filter(carList, isLexusCar.or(isExpensiveCar));

        // 4 不是雷克萨斯的车
        filter(carList, isLexusCar.negate());

        // isEqual
        System.out.println(Predicate.isEqual("aaa").test("aaa"));
    }

    static void filter(List<Car> cars,Predicate<Car> filterAction){
        cars.stream().forEach(item -> {
            if(filterAction.test(item)){
                System.out.println(item.getName());
            }
        });
        System.out.println("************************************");
    }
}
