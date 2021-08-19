package mian.java.com.jdk8.newfeatures.chapter01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples1 {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // 策略模式改造
        // 1. 定义一个接口来对择标准建模 ApplePredicate，有一个方法，入参是Apple，返回参数是boolean
        // 2. 定义选择绿苹果的策略： GreenApplePredicate，定义选择重的苹果策略：HeavyApplePredicate
        List<Apple> greenApples = filterApples(inventory, new GreenApplePredicate());
        greenApples.forEach(apple -> System.out.println(apple.getColor()));

        List<Apple> heavyApples = filterApples(inventory, new HeavyApplePredicate());
        heavyApples.forEach(apple -> System.out.println(apple.getWeight()));

        // 3. 如果是仅用一次的帅选策略，不会进行复用的，则用匿名类来实现
        List<Apple> greenHeavyApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple a) {
                return "green".equals(a.getColor()) && a.getWeight() > 150;
            }
        });
        // 目前已完成阶段性的成功，将 filter的“行为” 通过各个 ApplePredicate的对象来进行传递
        // 但是对于filter真正有用的部分其实是 ApplePredicate 各个对象内的  test方法实现
        // 即："green".equals(a.getColor()) 或者 a.getWeight() > 150
        // 但是不得借助于 ApplePredicate 对象来传递  或者匿名类
        // 能否将真正有用的部分进行传递，而并不是值（ApplePredicate对象引用）传递
    }

    /**
     * 将  “迭代集合的逻辑”  与  “应用到每个元素的行为”  分开
     * 固定不变的部分是 迭代集合
     * 变化的部分是 如何选择苹果
     * @param inventory 集合
     * @param p 选择行为（策略）
     * @return
     */
    static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List result = new ArrayList<Apple>();
        for(Apple apple : inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 选择苹果的标准
     */
    interface ApplePredicate{
        boolean test(Apple a);
    }

    /**
     * 选择绿苹果
     */
    static class GreenApplePredicate implements ApplePredicate{

        @Override
        public boolean test(Apple a) {
            return "green".equals(a.getColor());
        }
    }

    /**
     * 选择重量大于150g的
     */
    static class HeavyApplePredicate implements ApplePredicate{

        @Override
        public boolean test(Apple a) {
            return a.getWeight() > 150;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class Apple{
        private int weight;
        private String color;
    }
}
