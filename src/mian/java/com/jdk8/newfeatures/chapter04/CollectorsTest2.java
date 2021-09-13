package mian.java.com.jdk8.newfeatures.chapter04;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectorsTest2 {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
        new Dish("pork", false, 800, Dish.Type.MEAT),
        new Dish("beef", false, 700, Dish.Type.MEAT),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER),
        new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("season fruit", true, 120, Dish.Type.OTHER),
        new Dish("pizza", true, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH),
        new Dish("salmon", false, 1450, Dish.Type.FISH)
        );

        // 1.分组：将菜单按照Type分类
        Map<Dish.Type,List<Dish>> menuByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(menuByType);

        // 2.将菜单中每种类型的最高热量找到（类型和最高热量值的Mapping关系）
        // 注：为什么这里的value是Optional<T>类型Collector<Dish,?,Optional<Dish>> collector = Collectors.maxBy(Comparator.comparingInt(Dish::getCalories));
        Map<Dish.Type,Optional<Dish>> maxCaloriesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.maxBy(Comparator.comparing(Dish::getCalories))));
        System.out.println(maxCaloriesByType);

        //针对以上在Map中的value用Optional包装没什么用，如果不存在的value（Optional.empty()）,Map中的key值也不存在的，所以该如何去掉
        // Collectors.collectingAndThen(),对finisher使用了andThen
        Map<Dish.Type,Dish> maxCaloriesByType2 = menu.stream().collect(Collectors.groupingBy(
                Dish::getType,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)),
                        Optional::get)// 加一个转换函数，在这里为什么是安全的？ 在这里是安全的，因为Optional::get是在collect方法最后一部finisher执行的时候才会调用，而因为分组这个reducing永远不会返回Optional.empty()
                )
        );
        System.out.println(maxCaloriesByType2);


    }
    /**
     * 盘子
     */
    @Setter
    @Getter
    public static class Dish {
        private String name;
        private boolean vegetarian;
        private int calories;
        private Type type;
        private String[] otherNameList = new String[]{"a","b","c","d"};
        public enum Type { MEAT,FRUIT,FISH,OTHER }

        public Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Dish{" +
                    "name='" + name + '\'' +
                    ", vegetarian=" + vegetarian +
                    ", calories=" + calories +
                    ", type=" + type +
                    '}';
        }
    }
}
