package mian.java.com.jdk8.newfeatures.chapter02;

import java.util.stream.IntStream;

/**
 * 0到100勾股数组合
 * a*a + b*b = c*c
 * listA: [1,100]
 * listB: [1,100]
 * Function1：
 */
public class Pythagorean {

    public static void main(String[] args) {

        // 1. 遍历 a
        IntStream.rangeClosed(1,100).forEach(a ->{
            // 2. a入参，遍历b  (把b的范围改成了a到100。没有必要再从1开始了，否则就会造成重复的三元数)
            IntStream.rangeClosed(a,100).filter(b -> Math.sqrt(a*a+b*b) %1 == 0).mapToObj(b-> new int[]{a,b,(int)Math.sqrt(a*a+b*b)}).forEach(item -> System.out.println("{"+item[0]+","+item[1]+","+item[2]+"}"));
        });
    }
}
