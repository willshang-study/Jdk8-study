package mian.java.com.jdk8.demo.bean;

import java.util.Arrays;
import java.util.List;

public class CarUtil {
    public static List<Car> initCars(){
        return Arrays.asList(
                new Car("Lexus","LS600H",400000),
                new Car("Tesla","ModelY",300000),
                new Car("Honda","Fit",100000),
                new Car("Toyota","Avalon",200000),
                new Car("Lexus","ES200",350000));
    }
}
