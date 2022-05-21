package mian.java.com.jdk8.newfeatures.chapter07;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * CompletableFuture和Future的关系就如 Stream和Collection一样
 *
 * 案例： 价格查询
 *      它会查询多个在线商店的商品价格
 */
public class CompletableFutureTest {

    public static void main(String[] args){
//        System.out.println(String.format("CPU的数量:::%s",Runtime.getRuntime().availableProcessors()));
        LocalDateTime start = LocalDateTime.now();
//        bestPrice();
//        bestPriceParallel();
//        bestPriceAsyncV1();
        bestPriceAsyncV2();
        LocalDateTime end = LocalDateTime.now();
        System.out.println("查询总耗时：：："+ Duration.between(start,end).toMillis());
    }

    /**
     * 1. 同步查询
     */
    static void bestPrice() {
        Map<String,Double> priceMap =
                Arrays.asList("iphone","macbook","apple watch")
                .stream().collect(Collectors.toMap(
                        Function.identity(),
                        productName -> Shop.getPrice(productName))
                );
        priceMap.entrySet().forEach(entry->System.out.println(String.format("%s商品价格：%s",entry.getKey(),entry.getValue())));
    }

    /**
     * 2.并发查询
     */
    static void bestPriceParallel(){
        System.out.println(String.format("CPU的数量:::%s",Runtime.getRuntime().availableProcessors()));
        Map<String,Double> priceMap =
                Arrays.asList("iphone","macbook","apple1","apple2","apple3")
//                Arrays.asList("iphone","macbook","apple watch","iphone2","macbook2","apple watch2","iphone3","macbook3","apple watch3","iphone4","macbook4","apple watch4","apple watch5")
                        .parallelStream().collect(Collectors.toMap(
                        Function.identity(),
                        productName -> Shop.getPrice(productName))
                );
        priceMap.entrySet().forEach(entry->System.out.println(String.format("%s商品价格：%s",entry.getKey(),entry.getValue())));
    }

    /**
     * 2.异步查询
     * 调用：
     *      相比于Future要去向ExcutorService来提交Callable实例实现异步
     *      CompletableFuture只需要调用supplyAsync或 runAsync方法来向 ForkJoinPool线程池来提交任务
     *
     * 获取结果：
     *      不需要使用Future.get，需要捕获异常
     *      使用CompletableFuture.join来获取结果
     *
     * 但是效能上却和并发执行的效能几乎一致？？？？
     * 那么 CompletableFuture的价值体现在哪呢
     *
     * 其原因：
     *      CompletableFuture#supplyAsync 和 Collection#parallelStream 内部使用的是同一个线程池ForkJoinPool
     *      默认的线程数量是一致的，为Runtime.getRuntime().availableProcessors(),即CPU的数量
     *
     */
    static void bestPriceAsyncV1(){
        // 1. 获得未来结果的引用CompletableFuture<Double>
        Map<String,CompletableFuture<Double>> priceFutureMap =
                Arrays.asList("iphone","macbook","apple watch")
                .stream().collect(Collectors.toMap(
                        Function.identity(),
                        productName -> CompletableFuture.supplyAsync(()->{
                            System.out.println(String.format("线程%s 执行商品：%s 的价格计算",Thread.currentThread().getName(),productName));
                            return Shop.getPrice(productName);
                        }))
                );

        // 2. 对未来结果进行join来获得最后执行计算的结果
        priceFutureMap.entrySet().stream().forEach(entry->{
            System.out.println(String.format("%s商品价格：%s",entry.getKey(),entry.getValue().join()));
        });
    }

    /**
     * 使用自定义线程池，线程池的数量使用公式：Nthreads = NCPU * UCPU * (1 + W/C)
     *
     * 并行还是使用CompletableFuture？
     *  1. 进行的计算是密集型的操作，并且没有I/O，换句话说就是CPU几乎没有需要等待的情况，其实就没有必要创建比CPU核数更多的线程，推荐使用Stream的并行
     *  2. 如果任务（工作单元）涉及到I/O等待且任务数是超过CPU核数的，则推荐使用CompletableFuture，灵活性更好，且性能更好
     */
    static void bestPriceAsyncV2(){
        // 自定义线程池，
        ExecutorService executor = Executors.newFixedThreadPool(20);

        // 1. 获得未来结果的引用CompletableFuture<Double>
        Map<String,CompletableFuture<Double>> priceFutureMap =
                Arrays.asList("iphone","macbook","apple watch","iphone2","macbook2","apple watch2","iphone3"
                        ,"macbook3","apple watch3","iphone4","macbook4","apple watch4","apple watch5")
                        .stream().collect(Collectors.toMap(
                            Function.identity(),
                            productName -> CompletableFuture.supplyAsync(
                                    ()->{
                                        System.out.println(String.format("线程%s 执行商品：%s 的价格计算",Thread.currentThread().getName(),productName));
                                        return Shop.getPrice(productName);
                                    },
                                    executor
                            )
                        )
                );
        // 2. 对未来结果进行join来获得最后执行计算的结果
        priceFutureMap.entrySet().stream().forEach(entry->{
            System.out.println(String.format("%s商品价格：%s",entry.getKey(),entry.getValue().join()));
        });
    }

//    static void bestPriceAsyncV3(){
//        // 自定义线程池，
//        ExecutorService executor = Executors.newFixedThreadPool(20);
//
//        // 1. 获得未来结果的引用CompletableFuture<Double>
//        List<CompletableFuture<Double>> priceFutureList =
//                Arrays.asList("iphone","macbook","apple watch","iphone2","macbook2","apple watch2","iphone3"
//                        ,"macbook3","apple watch3","iphone4","macbook4","apple watch4","apple watch5")
//                        .stream()
//                        .map(productName ->
//                                CompletableFuture.supplyAsync(()->{
//                                        System.out.println(String.format("线程%s 执行商品：%s 的价格计算",Thread.currentThread().getName(),productName));
//                                        return Shop.getPrice(productName);
//                                    },executor).thenAccept())
//                        .collect(Collectors.toList()
//                );
//        // 2. 对未来结果进行join来获得最后执行计算的结果
//        CompletableFuture.allOf((CompletableFuture[])priceFutureList.toArray()).join();
//    }

}
