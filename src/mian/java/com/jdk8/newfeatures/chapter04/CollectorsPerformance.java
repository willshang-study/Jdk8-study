package mian.java.com.jdk8.newfeatures.chapter04;

import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 将前n个自然数按质数和非质数分区
 * { true:质数列表，false:非质数列表 }
 */
public class CollectorsPerformance {
    public static void main(String[] args) {

        // 1. 串行流
        OptionalLong minTime = LongStream.rangeClosed(1,10).map(i->{
            long start = System.currentTimeMillis();
            partitionPrimes(100000,false);
            long period = System.currentTimeMillis() - start;
            return period;
        }).min();
        System.out.println("串行流::"+minTime.getAsLong());
    }

    static Map<Boolean, List<Integer>> partitionPrimes(int n,boolean isParallel){
        Collector<Integer,?,Map<Boolean, List<Integer>>> collector = Collectors.partitioningBy(CollectorsPerformance::isPrimes);

        // IntStream 的 collect 方法仅有一个<R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R, R> combiner)
        // List<Integer> list = IntStream.rangeClosed(0,n).collect(ArrayList::new,ArrayList::add,List::addAll);
//        IntStream.rangeClosed(0,n).collect(collector); // 入参不对，并不能像Stream.collect接收Collector对象

        if(isParallel){
            return IntStream.rangeClosed(0, n).boxed().parallel().collect(collector);
        }else {
            return IntStream.rangeClosed(0,n).boxed().collect(collector);
        }
    }


    /**
     * 判断入参i是否是质数 (从2到i，没有任何一个数可以被i整除)
     * @param i
     * @return
     */
    static boolean isPrimes(int i){
//        return IntStream.range(2,i).noneMatch(item-> i%item == 0);
        // 优化1：到i的平方根即可
        int a = (int)Math.sqrt(i);
        return IntStream.rangeClosed(2,a).noneMatch(item -> i/item == 0);
    }
}
