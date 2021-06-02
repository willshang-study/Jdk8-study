package mian.java.com.jdk8.news.methodReference;

import mian.java.com.jdk8.demo.bean.Car;
import mian.java.com.jdk8.demo.bean.CarFactory;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 1. 对象实例::实例方法名
 * 2. 类名::静态方法名
 * 3. 类名::实例方法名
 * 4. ClassName::new (构造器引用)
 */
public class Test {

    public static void main(String[] args) {
        // 1. 对象实例::实例方法名
        Car carBenz = new Car();
        Supplier<String> supplier1 = carBenz::toString;

        // 2. 类名::静态方法名
        Supplier<List<Car>> supplier2 = CarFactory::buildCars;

        // 3. 类名::实例方法名
        // 分析：Comparator 的 int compare(T o1, T o2)
        // 两个T类型入参，一个int返回值，但是 Car的compareByPrice只有一个入参，是如何匹配上?
        // 类名::实例方法名   该实例方法一定需要某个实例才调用，则该实例将作为函数式接口唯一的Abstract Method（compare）的第一个参数
        // 即：o1 -> 实例；o2 -> compareByPrice接收的参数
        List<Car> carList = CarFactory.buildCars();
        carList.sort(Car::compareByPrice);
        // case2: toUpperCase无参，Function的apply需要一个入参，调用toUpperCase的String实例作为apply的入参
        Function<String,String> function = String::toUpperCase;

        // 4. ClassName::new (构造器引用)
        Supplier<Car> carSupplier = Car::new;  //构造函数与Supplier的get入参（无），返回值（Car）一致
    }
}