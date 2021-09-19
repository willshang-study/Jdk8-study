package mian.java.com.jdk8.newfeatures.chapter05;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinSumTest {
    public static void main(String[] args) {

        // 试着改变数字的大小可获得不同的结果
        long[] numbers = LongStream.rangeClosed(0,10000000).toArray();
        long start = System.currentTimeMillis();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long sum = new ForkJoinPool().invoke(task);
        long period = System.currentTimeMillis() - start;
        System.out.println("总和："+sum+"，耗时："+period);

        long start2 = System.currentTimeMillis();
        long sum2 = Arrays.stream(numbers).reduce(0,Long::sum);
        long period2 = System.currentTimeMillis() - start2;
        System.out.println("总和："+sum2+"，耗时："+period2);
    }
}
