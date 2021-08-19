package mian.java.com.jdk8.news.comparator;

import mian.java.com.jdk8.demo.bean.Car;
import mian.java.com.jdk8.demo.bean.CarFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

        System.out.println("**************原数组未排序输出************");
        List<Car> carList = CarFactory.buildCars();
        carList.stream().forEach(System.out::println);

        // 对Car按品牌字母进行升序排序 (利用Collections.sort方法)
        System.out.println("**************对Car按品牌字母进行升序排序************");
        carList.sort((o1,o2) -> o1.getBrand().compareTo(o2.getBrand()));
        carList.sort(Comparator.comparing(Car::getBrand).reversed());
        carList.stream().peek(System.out::println);

        System.out.println("**************对Car按品牌字母进行倒序排序，再按照价格倒序******** ****");
        carList.sort(Comparator.comparing(Car::getBrand).reversed().thenComparing(Comparator.comparing(Car::getPrice).reversed()));
        carList.stream().forEach(System.out::println);
    }
}
