package mian.java.com.jdk8.newfeatures.chapter03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*; //static静态导入,导入这个类里的静态成员（静态方法、静态变量）

/**
 * 规约和汇总
 *  总数、最大值、最小值  counting、minBy、maxBy
 *  汇总 summing、averagingInt、IntSummaryStatistics
 * 分组
 * 分区
 */
public class CollectorsTest {
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
        String names = transactions.stream().map(item->item.getTrader().getName()).distinct().collect(joining("-"));
        System.out.println("************7************");
        System.out.println(names);
    }

    /**
     * 交易员
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
     * 交易额
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
