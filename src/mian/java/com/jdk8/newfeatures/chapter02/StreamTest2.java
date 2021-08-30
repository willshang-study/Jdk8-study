package mian.java.com.jdk8.newfeatures.chapter02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest2 {
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

        //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        System.out.println("(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）");
        transactions.stream().filter(item-> item.getYear() == 2011).sorted(Comparator.comparingInt(Transaction::getValue)).forEach(System.out::println);
        //(2) 交易员都在哪些不同的城市工作过？
        System.out.println("(2) 交易员都在哪些不同的城市工作过？");
        transactions.stream().map(item-> item.getTrader().getCity()).distinct().forEach(System.out::println);
        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        System.out.println("(3) 查找所有来自于剑桥的交易员，并按姓名排序。");
        transactions.stream().filter(item->"Cambridge".equals(item.getTrader().getCity())).distinct().sorted(Comparator.comparing(item->item.getTrader().getName())).forEach(System.out::println);
        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        System.out.println("(4) 返回所有交易员的姓名字符串，按字母顺序排序。");
        String name = transactions.stream().map(item->item.getTrader().getName()).distinct().sorted().reduce("",String::concat);// 效率不高，每次迭代都创建了新的string
        String name2 = transactions.stream().map(item->item.getTrader().getName()).distinct().sorted().collect(Collectors.joining());// 每次迭代都是用的StringBuilder::append
        System.out.println(name);
        //(5) 有没有交易员是在米兰工作的？
        System.out.println("(5) 有没有交易员是在米兰工作的？");
        boolean result1 = transactions.stream().anyMatch(item->"Milan".equals(item.getTrader().getCity()));
        System.out.println(result1);
        //(6) 打印生活在剑桥的交易员的所有交易额。
        System.out.println("(6) 打印生活在剑桥的交易员的所有交易额。");
        Integer sum = transactions.stream().filter(item->"Cambridge".equals(item.getTrader().getCity())).map(Transaction::getValue).reduce(0,Integer::sum);// 利用原始类型特化流: IntStream
        int sum2 = transactions.stream().filter(item->"Cambridge".equals(item.getTrader().getCity())).mapToInt(Transaction::getValue).sum();
        System.out.println(sum+"-----------------"+sum2);
        //(7) 所有交易中，最高的交易额是多少？
        System.out.println("(7) 所有交易中，最高的交易额是多少？");
        Optional<Integer> max = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        OptionalInt max2 = transactions.stream().mapToInt(Transaction::getValue).max();
        System.out.println(max+"-----------------"+max2);
        //(8) 找到交易额最小的交易。
        System.out.println("(8) 找到交易额最小的交易。");
        Optional<Transaction> min = transactions.stream().reduce((a,b)->a.getValue()>b.getValue()?a:b);
        Optional<Transaction> min2 = transactions.stream().min(Comparator.comparingInt(Transaction::getValue));
        System.out.println(min2.get());
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
