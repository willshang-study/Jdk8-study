package mian.java.com.jdk8.news.function.consumer;

import mian.java.com.jdk8.demo.bean.Car;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Test {
    public static void main(String[] args) {
        List<Car> carList = Arrays.asList(
                new Car("Lexus","LS600H",1000000),
                new Car("Benz","S600",1000000),
                new Car("Tesla","ModelY",300000),
                new Car("Honda","Fit",100000),
                new Car("Toyota","Avalon",200000),
                new Car("Lexus","ES200",350000));

        Consumer<Car> addPrice = a -> a.setPrice(a.getPrice()+1000);
        Consumer<Car> addLength = a -> a.setName(a.getName()+"-L");
//
//        consumerNum(carList,addPrice);
//        consumerNum(carList,addLength);
        consumerNum(carList,addPrice.andThen(addLength));
    }

    static void consumerNum(List<Car> list, Consumer<Car> consumer){
        list.forEach(car -> {
            consumer.accept(car);
            System.out.println(car);
        });
        System.out.println("*******************");
    }
}
