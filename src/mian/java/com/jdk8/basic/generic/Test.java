package mian.java.com.jdk8.basic.generic;

import lombok.Getter;
import lombok.Setter;

public class Test {

    public static void main(String[] args) {

        // 类型擦除
        // javap -c Test.class 查看JVM字节码，并未有Integer和Number相关信息出现
        Amount<Integer> integerNumber = new Amount<>();
        Amount<Integer> integerNumber2 = new Amount<>();
        Amount<Number> number = new Amount<>();
//        System.out.println(integerNumber == number); // 虽然Number和Integer为父子类关系，但是在泛型中，两者不可进行对比。泛型不会识别父子类
        System.out.println(integerNumber2 == integerNumber); // 类型一致可以比较
        System.out.println("integerNumber和doubleNumber的class类型是否一致："+integerNumber.getClass().equals(number.getClass()));

        // 接口非泛型，方法泛型，Lambda编译报错。编译器无法推断其类型
//        Car car = item -> "";

        Student<Integer,Integer> student = item -> item;
        System.out.println("学生的年龄为：：："+student.getAge(25));
    }

    /**
     * @param <T> 泛型类。用于指定number属性的类型
     */
    @Getter
    @Setter
    static class Amount<T> {
        T amount;
    }

    /**
     * 泛型接口和泛型方法。用于指定getAge方法的入参类型和返回类型
     * <R,V>定义在方法上，该方法无法在Lambda上使用
     */
    interface Car{
        <R, V> R getName(V v);
    }

    /**
     *
     * @param <R>定义在接口层级
     * @param <V>定义在接口层级
     */
    interface Student<R, V> {
        R getAge(V v);
    }
}