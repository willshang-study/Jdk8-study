package mian.java.com.jdk8.newfeatures.chapter03;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 想知道每个城市的交易实例
 * 建模：返回值为 map<String,List<Transaction>>: key为城市名称，value为交易实例列表
 */
public class StreamTest3 {
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

        // jdk8之前的做法
        Map<String,List<Transaction>> result = new HashMap<>();
        for(Transaction transaction : transactions){
            // 1.取出key -> city
            String city = transaction.getTrader().getCity();
            // 2.拿到value-> transactionList
            List<Transaction> transactionList = result.get(city);
            if(transactionList == null){
                //首次需新建
                transactionList = new ArrayList<>();
                result.put(city,transactionList);
            }
            // 3.实例add进List
            transactionList.add(transaction);
        }
        result.entrySet().forEach(item-> System.out.println(item.getKey()+":::"+item.getValue()));
        System.out.println("**********************************");

//        // jdk8的做法
        Map<String,List<Transaction>> result2 = transactions.stream().collect(Collectors.groupingBy(item->item.getTrader().getCity()));
        result2.entrySet().forEach(item-> System.out.println(item.getKey()+":::"+item.getValue()));
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
