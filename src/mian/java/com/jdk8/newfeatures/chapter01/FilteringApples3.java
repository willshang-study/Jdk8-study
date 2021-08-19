package mian.java.com.jdk8.newfeatures.chapter01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 以下完成了 将 值传递（ApplePredicate对象） 变为  Lambda表达式传递
 * 还可进一步优化，将Apple类型抽象出来
 */
public class FilteringApples3 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        Predicate<Apple> p = a -> "green".equals(a.getColor());
        Consumer<Apple> c =  a -> "green".equals(a.getColor());
        List<Apple> greenApples = filterApples(inventory, p);

    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List result = new ArrayList<Apple>();
        for(Apple apple : inventory){
            if(p.test(apple)){
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
