package mian.java.com.jdk8.demo.bean;

import java.util.Arrays;
import java.util.List;

public class CarFactory {
    public static List<Car> buildCars(){
        return Arrays.asList(
                new Car("Lexus","LS600H",1000000),
                new Car("Tesla","ModelY",300000),
                new Car("Honda","Fit",100000),
                new Car("Toyota","Avalon",200000),
                new Car("Lexus","ES200",350000));
    }
}
