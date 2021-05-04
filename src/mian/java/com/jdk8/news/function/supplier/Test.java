package mian.java.com.jdk8.news.function.supplier;

import mian.java.com.jdk8.demo.bean.Car;

import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) {

        // Car的无参构造函数，满足无参返回一个结果的Supplier的要求
        Supplier<Car> carFactory = Car::new;
        System.out.println(carFactory.get().getName());
    }
}
