package mian.java.com.jdk8.news.function.comparator;

import mian.java.com.jdk8.demo.bean.Car;
import mian.java.com.jdk8.demo.bean.CarFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Car> carList = Arrays.asList(
                new Car("Lexus","LS600H",1000000),
                new Car("Benz","S600",1000000),
                new Car("Tesla","ModelY",300000),
                new Car("Honda","Fit",100000),
                new Car("Toyota","Avalon",200000),
                new Car("Lexus","ES200",350000));

        Comparator<Car> priceComparator = Comparator.comparingInt(Car::getPrice);

        Comparator<Car> brandComparator = Comparator.comparing(Car::getBrand);

        // 对Car按品牌字母进行升序排序 (利用Collections.sort方法)
        System.out.println("**************对Car按品牌字母进行升序排序************");

        carList.sort(priceComparator);
        carList.forEach(System.out::println);

        carList.sort(brandComparator.reversed());
        carList.forEach(System.out::println);

        System.out.println("**************对Car按品牌字母进行倒序排序，再按照价格倒序******** ****");
        carList.sort(brandComparator.reversed().thenComparing(priceComparator));
        carList.forEach(System.out::println);
    }
}
