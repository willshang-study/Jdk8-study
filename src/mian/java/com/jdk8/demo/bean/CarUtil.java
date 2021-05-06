package mian.java.com.jdk8.demo.bean;

import java.util.Arrays;
import java.util.List;

public class CarUtil {
    public static List<Car> initCars(){
        return Arrays.asList(new Car("Tesla-ModelY",300000),new Car("Honda-Fit",100000),new Car("Toyota-Avalon",200000),new Car("Lexus-GS",400000));
    }
}
