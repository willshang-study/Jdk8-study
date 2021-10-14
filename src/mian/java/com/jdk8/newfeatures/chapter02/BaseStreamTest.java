package mian.java.com.jdk8.newfeatures.chapter02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Returns an equivalent stream with an additional close handler.  Close
 * close handler会返回一个相同的流
 * handlers are run when the {@link java.util.stream.BaseStream#close()} method
 * close handler会运行当流中close方法被调用时
 * is called on the stream, and are executed in the order they were
 * 他们会按照添加的顺序运行
 * added.  All close handlers are run, even if earlier close handlers throw
 * 所有的close handler会运行，甚至早期的close handlers抛出了一个异常
 * exceptions.  If any close handler throws an exception, the first
 * 如果close handlers抛出异常，第一个异常
 * exception thrown will be relayed to the caller of {@code close()}, with
 * 将会把剩余的异常添加进来作为抑制异常返回给调用者，
 * any remaining exceptions added to that exception as suppressed exceptions
 * (unless one of the remaining exceptions is the same exception as the
 * first exception, since an exception cannot suppress itself.)  May
 * return itself.
 * 除非其中一个异常跟第一个异常是同一个异常，因为一个异常不能抑制他自己，返回他自己
 */
public class BaseStreamTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("benz","audi","bwm","tesla");

        // 1.未调用close方法，onClose方法不会被触发
        list.stream().onClose(()->{
            System.out.println("first close");
        }).onClose(()->{
            System.out.println("second close");
        }).forEach(System.out::println);
        System.out.println("1.未调用close方法，onClose方法不会被触发");

        // 2.使用 try-with-resource 表达式来包装资源stream，最后会调用AutoCloseable#close方法
        try(Stream<String> stream = list.stream();){
            stream.onClose(()->{
                System.out.println("first close");
            }).onClose(()->{
                System.out.println("second close");
            }).forEach(System.out::println);
        }
        System.out.println("2.使用 try-with-resource 表达式来包装资源stream");

        // 3.异常
        //  1.第一个onClose抛出异常,并带着其余的异常作为抑制异常suppressed exceptions一并抛出
        //  2.当抛出的异常为同一个异常对象(不是同一类，而是同一个异常对象)，则只会抛出第一个，因为他异常自己不可能抑制他自己
        NullPointerException e = new NullPointerException("空指针异常对象");
        try(Stream<String> stream = list.stream();){
            stream.onClose(()->{
                System.out.println("first close");
//                throw new NullPointerException("the first Exception");
//                throw e;
            }).onClose(()->{
                System.out.println("second close");
//                throw new NullPointerException("the second Exception");
//                throw e;
            }).forEach(System.out::println);
        }
        System.out.println("3.第一个onClose抛出异常,并带着其余的异常作为抑制异常suppressed exceptions一并抛出");
    }
}
