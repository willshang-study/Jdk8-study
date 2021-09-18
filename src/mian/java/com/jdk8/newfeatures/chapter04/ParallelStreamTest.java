package mian.java.com.jdk8.newfeatures.chapter04;

import java.util.OptionalLong;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class ParallelStreamTest {

    public static void main(String[] args) {

        OptionalLong timeBoxed = LongStream.range(0,10).map(item->{
            long startTime = System.nanoTime();
            IntStream.rangeClosed(0,10000000).reduce(Integer::sum);
            long period = System.nanoTime() - startTime;
            return period;
        }).min();

        System.out.println("串行流(带有拆箱损耗)："+timeBoxed.getAsLong());

        OptionalLong time = LongStream.range(0,10).map(item->{
            long startTime = System.nanoTime();
            IntStream.rangeClosed(0,10000000).sum();
            long period = System.nanoTime() - startTime;
            return period;
        }).min();

        System.out.println("串行流："+time.getAsLong());

        OptionalLong timeParallel = LongStream.range(0,10).map(item->{
            long startTime = System.nanoTime();
            IntStream.rangeClosed(0,10000000).parallel().sum();
            long period = System.nanoTime() - startTime;
            return period;
        }).min();

        System.out.println("并行流："+timeParallel.getAsLong());
    }
}
