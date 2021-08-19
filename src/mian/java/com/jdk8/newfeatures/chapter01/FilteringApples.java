package mian.java.com.jdk8.newfeatures.chapter01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 假设你有一个Apple类，它
 * 有一个getColor方法，还有一个变量inventory保存着一个Apples的列表。
 *  1. 你可能想要选出所有的绿苹果，并返回一个列表。通常我们用筛选（filter）一词来表达这个概念。
 *  2. 有人可能想要选出重的苹果，比如超过150克
 */
public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // 1.1.绿苹果
        List<Apple> greenApples = filterGreenApples(inventory);
        greenApples.forEach(apple -> System.out.println(apple.getColor()));

        // 1.2.超过150g的苹果
        List<Apple> heavyApples = filterHeavyApples(inventory);
        heavyApples.forEach(apple -> System.out.println(apple.getWeight()));
        // 1.3 ... ...

        // 以上代码没有任何代码设计
        // 那么怎么改造？
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List result = new ArrayList<Apple>();
        for(Apple apple : inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
}
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List result = new ArrayList<Apple>();
        for(Apple apple : inventory){
            if(apple.getWeight() > 150){
                result.add(apple);
            }
        }
        return result;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Apple{
        private int weight;
        private String color;
    }
}
