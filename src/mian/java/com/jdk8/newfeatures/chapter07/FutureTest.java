package mian.java.com.jdk8.newfeatures.chapter07;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.*;

public class FutureTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("当前线程："+Thread.currentThread().getName());

        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        LocalDateTime start = LocalDateTime.now();

        // 2. 向线程池提交任务
        Future<Integer> futureResult = executorService.submit(()->{
            System.out.println("线程池线程："+Thread.currentThread().getName());
            Thread.sleep(2000);
            return 1;
        });

        Future<Integer> futureResult2 = executorService.submit(()->{
            System.out.println("线程池线程："+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

//        doOtherSomething(....);
        LocalDateTime end1 = LocalDateTime.now();
        System.out.println("执行阶段1耗时：：："+ Duration.between(start,end1).toMillis());
        try {
            // 3. 获得异步结果
//            System.out.println("异步结果1："+futureResult.get(3,TimeUnit.SECONDS));
//            System.out.println("异步结果2："+futureResult2.get(3,TimeUnit.SECONDS));
            LocalDateTime end2 = LocalDateTime.now();
            System.out.println("执行阶段2耗时：：："+ Duration.between(start,end2).toMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }
}
