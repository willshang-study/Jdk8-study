package mian.java.com.jdk8.newfeatures.chapter01;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 1. 对象实例::实例方法名
 * 2. 类名::静态方法名
 * 3. 类名::实例方法名
 * 4. ClassName::new (构造器引用)
 */
public class MethodReference {
    public static void main(String[] args) {

        // 1. 类名::静态方法名
        Function<String, Integer> a = Integer::parseInt;

        // 2. 对象实例::实例方法名
        String s = new String("aaaaa");
        Supplier<Integer> b = s::length;

        // 3. 类名::实例方法名
        Comparator<String> stringComparator = (o1, o2) -> o1.compareToIgnoreCase(o2);
        Comparator<String> stringComparator2 = String::compareToIgnoreCase;

        // 4. ClassName::new (构造器引用)
        Supplier<String> stringSupplier = String::new;
        Function<StringBuilder, String> carFunction = String::new;
    }
}
