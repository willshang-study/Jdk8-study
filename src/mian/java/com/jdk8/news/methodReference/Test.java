package mian.java.com.jdk8.news.methodReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 1. 对象实例::实例方法名
 * 2. 类名::静态方法名
 * 3. 类名::实例方法名
 * 4. ClassName::new (构造器引用)
 */
public class Test {

    static Map<String,Supplier<Fruit>> map = new HashMap<>();
    static{
        map.put("orange",Orange::new);
        map.put("apple",Apple::new);
    }
    public static void main(String[] args) {

        // 1. 对象实例::实例方法名
        Car carBenz = new Car();
        Supplier<String> supplier1 = carBenz::toString;

        // 2. 类名::静态方法名
        Supplier<List<Car>> supplier2 = CarFactory::buildCars;

        // 3. 类名::实例方法名
        // 该方法一定是某个实例“A”来调用，实例A作为 函数式接口的第一个参数
        Comparator<Car> carComparator = Car::compareByPrice; //Comparator<Car> carComparator1 = (a,b) -> a.compareByPrice(b);
        Comparator<String> stringComparator = String::compareToIgnoreCase; //Comparator<String> stringComparator = (a,b) -> a.compareToIgnoreCase(b);
        // case2: toUpperCase无参，Function的apply需要一个入参，调用toUpperCase的String实例作为apply的入参
        Function<String,String> function = String::toUpperCase;

        // 4. ClassName::new (构造器引用)
        Supplier<Car> carSupplier = Car::new;  //构造函数与Supplier的get入参（无），返回值（Car）一致
        BiFunction<String,Integer,Car> carSupplier2 = Car::new;

        // 5.关于构造器方法引用的有趣实验
        giveMeFruit("apple");

    }

    static Fruit giveMeFruit(String fruitName){
        return map.get(fruitName).get();
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Car{
        private String name;
        private int price;
        public int compareByPrice(Car car){
            return this.price - car.getPrice();
        }
    }

    static class CarFactory{
        static List<Car> buildCars(){
            return Arrays.asList(
                    new Car("ModelY",300000),
                    new Car("Fit",100000),
                    new Car("Avalon",200000),
                    new Car("ES200",350000));
        }
    }
}
interface Fruit{}
class Apple implements Fruit{}
class Orange implements Fruit{}