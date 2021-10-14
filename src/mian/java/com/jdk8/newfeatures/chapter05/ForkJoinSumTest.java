package mian.java.com.jdk8.newfeatures.chapter05;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 对比下面
 *      1. 串行方式求和
 *      2. 使用ForkJoin框架来提升求和计算的效能
 * 的计算效能，可使用ForkJoin框架来实现并发
 *
 * 变化因子有
 *      1. numbers的大小（集合的总量）
 *      2. ForkJoinSumCalculator.fence(分割栅栏)
 * 决定了任务在什么点拆分，会拆分成多少个任务
 */
public class ForkJoinSumTest {
    public static void main(String[] args) {

        long[] numbers = LongStream.rangeClosed(0,90000000).toArray();

        // 1. 串行方式求和
        long start2 = System.currentTimeMillis();
        long sum2 = Arrays.stream(numbers).reduce(0,Long::sum);
        long period2 = System.currentTimeMillis() - start2;
        System.out.println("串行总和："+sum2+"，耗时："+period2);

        // 2. 使用ForkJoin框架来提升求和计算的效能
        long start = System.currentTimeMillis();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long sum = new ForkJoinPool().invoke(task);
        long period = System.currentTimeMillis() - start;
        System.out.println("ForkJoinTask并行框架，总和："+sum+"，耗时："+period);
    }

    /**
     * 使用ForkJoin框架实现分解任务求和
     * 有返回值，需要继承抽象类 RecursiveTask<Long>
     *     并实现唯一抽象方法compute方法
     */
    public static class ForkJoinSumCalculator extends RecursiveTask<Long> {
        // 一. 准备工作：数据源
        private final long[] numbers;
        // 二. 准备工作：拆解任务逻辑(根据start和end把数组划分为子数组来构造 子ForkJoinSumCalculator对象)
        private final long fence = 100000;
        private final int start;
        private final int end;

        // 提供给外部生产Original的Task的构造器
        public ForkJoinSumCalculator(long[] numbers) {
            this(numbers,0,numbers.length);
        }
        // 内部拆分产生子Task的构造器
        private ForkJoinSumCalculator(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }
        @Override
        protected Long compute() {
            // 1.获取当前Task的大小
            int taskLength = end -start;
            // 2.判断没有达到分解阈值，则直接使用串行方法
            if(taskLength < fence){
                return this.computeSequential();
            }else{
                // 1.需要分解任务,使用ForkJoinTask.fork方法 由当前ForkJoinSumCalculator对象分解为两个ForkJoinSumCalculator子对象
                int newLength = taskLength/2;
                // 2.分解成子任务leftTask、rightTask
                ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start+newLength);
                ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+newLength,end);
                // 3.将leftTask推进线程池内的另外一个线程（会异步调用leftTask的compute方法）
                leftTask.fork();
                // 4.将rightTask留在当前线程“递归”调用compute得到结果 right
                Long rightResult = rightTask.compute();
                // 5.join获取另外一个线程执行的结果，将阻塞当前线程，直到获得result
                Long leftResult = leftTask.join();
                return rightResult + leftResult;
            }
        }
        long computeSequential(){
            long sum = 0;
            for(int i=this.start;i<this.end;i++){
                sum += numbers[i];
            }
            return sum;
        }
    }
}
