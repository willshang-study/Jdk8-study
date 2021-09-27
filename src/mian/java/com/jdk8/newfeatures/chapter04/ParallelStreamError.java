package mian.java.com.jdk8.newfeatures.chapter04;

import java.util.OptionalLong;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreamError {

    public static void main(String[] args) {

        // 陷阱1：流不易迭代划分为多个子流
        // 1. 我们很难把iterate分成多个独立块来并行执行。(每次生成一个新的结果必须依赖上一次的结果)
        OptionalLong timeIterateError = LongStream.range(0,10).map(item->{
            long startTime = System.nanoTime();
            IntStream.iterate(0,a->++a).limit(10000000).parallel().sum();
            long period = System.nanoTime() - startTime;
            return period;
        }).min();

        // 2. Stream.iterate生成的是装箱的对象，必须拆箱成数字才能求和
        OptionalLong timeIterateBoxed = LongStream.range(0,10).map(item->{
            long startTime = System.nanoTime();
            Stream.iterate(0, a->++a).limit(10000000).parallel().reduce(Integer::sum);
            long period = System.nanoTime() - startTime;
            return period;
        }).min();

        OptionalLong timeRange = LongStream.range(0,10).map(item->{
            long startTime = System.nanoTime();
            IntStream.rangeClosed(0,10000000).sum();
            long period = System.nanoTime() - startTime;
            return period;
        }).min();
        System.out.println("iterate(陷阱：流不易分割)："+timeIterateError.getAsLong());
        System.out.println("iterate(陷阱：需要拆箱成原始类型计算求和)："+timeIterateBoxed.getAsLong());
        System.out.println("range："+timeRange.getAsLong());

        // 陷阱2：算法改变了共享状态
        Accumulator accumulator = new Accumulator();
        // 每一次迭代改变 accumulator 对象的 sum 变量
        LongStream.range(0,10000).forEach(accumulator::add);
        System.out.println("串行流::"+accumulator.sum);
        Accumulator accumulator2 = new Accumulator();
        // ForkJoinPool线程池多个线程竞争sum，且未加锁,此处将sum声明为volatile也无济于事
        LongStream.range(0,10000).parallel().forEach(accumulator2::add);
        System.out.println("并行流::"+accumulator2.sum);
    }

    public static class Accumulator{
        public volatile long sum = 0;
        public void add(long a){
            sum += a;
        }
    }
}
