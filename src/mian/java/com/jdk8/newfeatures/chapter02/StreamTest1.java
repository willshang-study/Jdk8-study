package mian.java.com.jdk8.newfeatures.chapter02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest1 {
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
        new Dish("salmon", false, 1450, Dish.Type.FISH) );

        // 1.1谓词筛选
        menu.stream().filter(item -> item.getCalories()>1000).map(Dish::getCalories).forEach(System.out::println);
        System.out.println("*************************");
        // 1.2 distinct
        menu.stream().map(Dish::getCalories).distinct().forEach(System.out::println);
        System.out.println("*************************");
        // 1.3 limit
        menu.stream().limit(2).forEach(System.out::println);
        System.out.println("*************************");
        // 1.4 skip
        menu.stream().skip(5).map(Dish::getName).forEach(System.out::println);
        System.out.println("*************************");
        // 1.5 map
        menu.stream().map(item -> item.getCalories() +1000).forEach(System.out::println);
        System.out.println("*************************");
        // 1.6 flatmap
        menu.stream().flatMap(item -> Arrays.stream(item.getOtherNameList())).distinct().forEach(System.out::println);
        System.out.println("*************************");

        // 2.1 匹配 anyMatch
        boolean hasHighHeat = menu.stream().anyMatch(item->item.getCalories()>500);
        System.out.println("************2.1 是否有高热量："+hasHighHeat+"*************");
        // 2.2 匹配 allMatch
        boolean allHighHeat = menu.stream().allMatch(item->item.getCalories()>500);
        System.out.println("************2.2 是否都是高热量："+allHighHeat+"*************");
        // 2.3 查找 findAny 并发流每次返回的数据可能不一致
        Optional<Dish> dish0 = menu.stream().filter(item->item.getCalories()>500).findAny();
        Optional<Dish> dish1 = menu.parallelStream().filter(item->item.getCalories()>500).findAny();
        dish0.ifPresent(item->System.out.println("************2.3 stream任意一个高热量的："+item.getName()+"*************"));
        dish1.ifPresent(item->System.out.println("************2.3 parallelStream任意一个高热量的："+item.getName()+"*************"));
        // 2.4 查找 findFirst
        Optional<Dish> dish2 = menu.stream().filter(item->item.getCalories()>500).findFirst();
        Optional<Dish> dish3 = menu.parallelStream().filter(item->item.getCalories()>500).findFirst();
        dish2.ifPresent(item->System.out.println("************2.4 stream第一个高热量的："+item.getName()+"*************"));
        dish3.ifPresent(item->System.out.println("************2.4 parallelStream第一个高热量的："+item.getName()+"*************"));

        // 3.1 规约 求和
        int sum = menu.stream().map(item -> item.getCalories()).reduce(0,(a,b)->a+b); //Integer方法引用 Integer::sum
        System.out.println("************3.1 求和："+sum+"*************");
        // 3.2 规约 最大值
        Optional<Integer> max = menu.stream().map(item -> item.getCalories()).reduce((a,b)-> a>b? a:b); //Integer方法引用 Integer::max
        System.out.println("************3.2 最大值："+max+"*************");
        // 3.3 规约 最小值
        Optional<Integer> min = menu.stream().map(item -> item.getCalories()).reduce((a,b)-> a<b? a:b); //Integer方法引用 Integer::min
        System.out.println("************3.3 最小值："+min+"*************");

        // 4.1 数值流
        menu.stream().mapToInt(item->item.getCalories()).sum();
        menu.stream().mapToInt(item->item.getCalories()).max();
        menu.stream().mapToInt(item->item.getCalories()).min();
        // 4.2 数值范围
        IntStream.range(0,5).forEach(System.out::println);
        IntStream.rangeClosed(0,5).forEach(System.out::println);// 闭区间，包含5

        // 5.1 构建流 Stream.of(...)
        Stream.of("a","b","c");
        // 5.2 构建流 Arrays.Stream(数组)
        String str[] = new String[]{"a","b","c"};
        Arrays.stream(str);
        // 5.3 构建流 Collection.stream()
        // 5.4 构建流 Stream.iterate(seed,UnaryOperator<T>)
        Stream.iterate(0, a -> ++a).limit(5).forEach(System.out::println);
        // 5.5 构建流 Stream.generate(Supplier<T>)
        Stream.generate(Math::random).limit(5).forEach(System.out::println);
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
    }
}
