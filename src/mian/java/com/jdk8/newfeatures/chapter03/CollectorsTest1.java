package mian.java.com.jdk8.newfeatures.chapter03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*; //static静态导入,导入这个类里的静态成员（静态方法、静态变量）

/**
 * 规约和汇总
 *  总数、最大值、最小值  counting、minBy、maxBy
 *  汇总 summing、averagingInt、IntSummaryStatistics
 * 分组
 * 分区
 */
public class CollectorsTest1 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. counting 收集器在和其他收集器联合使用的时候特别有用，后面会谈到这一点。
        Long count = transactions.stream().collect(counting());
        Long count2 = transactions.stream().count();
        System.out.println("************1************");
        System.out.println("1. counting::"+count);

        // 2. minBy 找到最小的交易 stream.min()来实现
        Optional<Transaction> transactionMin = transactions.stream().collect(minBy(Comparator.comparingInt(Transaction::getValue)));
        System.out.println("************2************");
        transactionMin.ifPresent(System.out::println);

        // 3. maxBy 找到最大的交易 stream.max()来实现
        Optional<Transaction> transactionMax = transactions.stream().collect(maxBy(Comparator.comparingInt(Transaction::getValue)));
        System.out.println("************3************");
        transactionMax.ifPresent(System.out::println);

        // 4. summing累加 IntStream.sum()来实现
        int summing = transactions.stream().collect(summingInt(Transaction::getValue));
        System.out.println("************4************");
        System.out.println(summing);

        // 5. averagingInt平均值
        Double averaging = transactions.stream().collect(averagingInt(Transaction::getValue));
        System.out.println("************5************");
        System.out.println(averaging);

        // 6. 获取以上五个值的概况对象 IntSummaryStatistics
        IntSummaryStatistics summary = transactions.stream().collect(summarizingInt(Transaction::getValue));
        System.out.println("************6************");
        System.out.println(summary);

        // 7. 连接字符串
        String names = transactions.stream().map(item->item.getTrader().getName()).distinct().collect(Collectors.joining("-"));
        System.out.println("************7************");
        System.out.println(names);

        // 8.分组 （把交易列表按交易者城市来分组）
        Map<String,List<Transaction>> cityMap = transactions.stream().collect(groupingBy(item->item.getTrader().getCity()));
        System.out.println(cityMap);

        // 9.1 多级分组（交易列表按交易者名称，交易者所在城市分组）
        Function<Transaction, String> classifierCity = item->item.getTrader().getCity();
        Function<Transaction, String> classifierName = item->item.getTrader().getName();
        Collector<Transaction, ?, Map<String, List<Transaction>>> downstream = groupingBy(classifierName);
        Collector<Transaction, ?, Map<String,Map<String,List<Transaction>>>> collector = Collectors.groupingBy(classifierCity, downstream);
        Map<String,Map<String,List<Transaction>>> cityNameMap =  transactions.stream().collect(collector);
        System.out.println(cityNameMap);
        // 9.2 多级分组（交易列表按交易者名称，交易者所在城市分组）
        Map<String,Map<String,List<Transaction>>> byCityNameMap =
                transactions.stream().collect(
                        groupingBy(transaction -> transaction.getTrader().getCity(), // 一级分类
                        groupingBy(transaction -> transaction.getTrader().getName()))); // 二级分类，也可为其他Collector

        // 10.按子组收集收据（城市和交易数量的映射关系）
        Map<String,Long> countingByCity =
                transactions.stream().collect(groupingBy(transaction -> transaction.getTrader().getCity(),counting())); // 子类收集器，默认是toList，则返回为List<Transaction>；现传入counting()则返回counting的返回值long，自动装箱为Long类型
        System.out.println(countingByCity);

        //10.1 与collectingAndThen配合使用 ,找到每个城市交易额最大的那一笔
        Map<String,Optional<Transaction>> maxValueByCity =
                transactions.stream().collect(groupingBy(
                        transaction -> transaction.getTrader().getCity(),
                        maxBy(Comparator.comparingInt(transaction -> transaction.getValue())))
                );
        System.out.println(maxValueByCity);
        //针对以上在Map中的value用Optional包装没什么用，如果不存在的value（Optional.empty()）,Map中的key值也不存在的，所以该如何去掉
        // 收集器返回的结果转换为另一种类型: maxBy收集器的R: Optional<T>，应用Optional.get转成另一种类型
        Map<String,Transaction> maxValueByCity2 =
                transactions.stream().collect(groupingBy(
                        transaction -> transaction.getTrader().getCity(),
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(transaction -> transaction.getValue())),
                                Optional::get))
                );
        System.out.println(maxValueByCity2);

        //10.2 与mapping配合使用，城市中交易额的档次（by value来分为高中低三档）
        // 对流中的元素transaction进行mapping成String类型的Level，tioList则将变换的结果对象收集起来
        Map<String,List<String>> levelByCity = transactions.stream().collect(groupingBy(
                transaction -> transaction.getTrader().getCity(),
                mapping(
                        transaction -> {
                            if (transaction.getValue() < 100) {
                                return "LOW";
                            } else if (transaction.getValue() < 500 && transaction.getValue() >= 100) {
                                return "MIDDLE";
                            } else {
                                return "HIGH";
                            }
                        },
                        toList()
                )
                )
        );
        System.out.println(levelByCity);
    }

    /**
     * Trader 交易者
     */
    @Setter
    @Getter
    @AllArgsConstructor
    public static class Trader{
        private final String name;
        private final String city;
        public String toString(){return "Trader:"+this.name + " in " + this.city;}
    }

    /**
     * Transaction 交易
     */
    @Setter
    @Getter
    @AllArgsConstructor
    public static class Transaction{
        private final Trader trader;
        private final int year;
        private final int value;
        public String toString(){
            return "{" + this.trader + ", " +
                    "year: "+this.year+", " +
                    "value:" + this.value +"}";
        }
    }
}
