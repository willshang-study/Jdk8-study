package mian.java.com.jdk8.newfeatures.chapter05;

import java.util.concurrent.RecursiveTask;

/**
 * 使用ForkJoin框架实现分解任务求和
 * 有返回值，需要继承抽象类 RecursiveTask<Long>
 *     并实现唯一抽象方法compute方法
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    // 一. 准备工作：数据源
    private final long[] numbers;
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    // 二. 准备工作：拆解任务逻辑(根据start和end把数组划分为子数组来构造 子ForkJoinSumCalculator对象)
    private final long thresholdLength = 1000;
    private final int start;
    private final int end;
    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        // 1.获取当前Task的大小
        int taskLength = end -start;

        // 2.判断没有达到分解阈值，则直接使用串行方法
        if(taskLength < thresholdLength){
            return this.computeSequential();
        }else{
            // 1.需要分解任务,使用ForkJoinTask.fork方法 由当前ForkJoinSumCalculator对象分解为两个ForkJoinSumCalculator子对象
            int newLength = taskLength/2;
            // 2.分解成子任务leftTask、rightTask
            ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start+newLength);
            ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+newLength,end);
            // 3.将leftTask推进线程池内的另外一个线程（会异步调用leftTask的compute方法）
            leftTask.fork();
            // 4.将rightTask留在当前线程递归调用compute得到结果 right
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
