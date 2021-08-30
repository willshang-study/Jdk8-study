package mian.java.com.jdk8.newfeatures.chapter03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*; //static静态导入,导入这个类里的静态成员（静态方法、静态变量）

/**
 * 规约和汇总 counting,minBy,maxBy
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

        // counting收集器在和其他收集器联合使用的时候特别有用，后面会谈到这一点。
        transactions.stream().map(item->item.getTrader().getCity()).distinct().collect(counting());
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
