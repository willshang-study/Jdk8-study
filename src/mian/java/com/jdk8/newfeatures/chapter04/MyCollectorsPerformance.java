package mian.java.com.jdk8.newfeatures.chapter04;

import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * 将前n个自然数按质数和非质数分区
 * { true:质数列表，false:非质数列表 }
 */
public class MyCollectorsPerformance {
    public static void main(String[] args) {

        // 1. 串行流
        OptionalLong minTime = LongStream.rangeClosed(1,10).map(i->{
            long start = System.currentTimeMillis();
            partitionPrimes(100000,false);
            long period = System.currentTimeMillis() - start;
            return period;
        }).min();
        System.out.println("串行流::"+minTime.getAsLong());

        // 1. 并行流
        OptionalLong minTimeParallel = LongStream.rangeClosed(1,10).map(i->{
            long start = System.currentTimeMillis();
            partitionPrimes(100000,true);
            long period = System.currentTimeMillis() - start;
            return period;
        }).min();
        System.out.println("并行流::"+minTimeParallel.getAsLong());
    }

    /**
     * 使用自定义的【MyCollector】
     */
    static Map<Boolean, List<Integer>> partitionPrimes(int n, boolean isParallel){
        Collector<Integer,?,Map<Boolean, List<Integer>>> collector = new MyCollector();
        if(isParallel){
            return IntStream.rangeClosed(2,n).boxed().parallel().collect(collector);
        }else{
            return IntStream.rangeClosed(2,n).boxed().collect(collector);
        }
    }
}
