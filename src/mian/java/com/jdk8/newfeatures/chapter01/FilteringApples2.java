package mian.java.com.jdk8.newfeatures.chapter01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples2 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));


        Predicate<Apple> p = FilteringApples2::isGreenApples;

        // 2.1 定义一个公共的filterApples方法，将具体的过滤条件作为参数传递到循环体内；
        // 2.2 定义两个判断条件的方法，isGreenApples、isHeavyApples
        List<Apple> greenApples = filterApples(inventory, FilteringApples2::isGreenApples);
        greenApples.forEach(apple -> System.out.println(apple.getColor()));

        List<Apple> heavyApples = filterApples(inventory, FilteringApples2::isHeavyApples);
        heavyApples.forEach(apple -> System.out.println(apple.getWeight()));

        // 3.1 从方法传递到Lambda
        List<Apple> greenApples2 = filterApples(inventory, (Apple a)->"green".equals(a.getColor()));
        greenApples2.forEach(apple -> System.out.println(apple.getColor()));

        List<Apple> heavyApples2 = filterApples(inventory, (Apple a)->a.getWeight() > 150);
        heavyApples2.forEach(apple -> System.out.println(apple.getWeight()));
    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List result = new ArrayList<Apple>();
        for(Apple apple : inventory){
            // 判断该Apple是否符合条件
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
    static boolean isGreenApples(Apple a){ return "green".equals(a.getColor());}
    static boolean isHeavyApples(Apple a){ return a.getWeight() > 150;}

    @Getter
    @Setter
    @AllArgsConstructor
    static class Apple{
        private int weight;
        private String color;
    }
}
