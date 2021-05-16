package mian.java.com.jdk8.basic.reflect;

import mian.java.com.jdk8.demo.bean.Car;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        List<String> fieldList = Arrays.asList("brand","name","price");
        Car benz = new Car("Benz","EClass",400000);
        Car unknownCar = new Car();
        System.out.println("复制属性前：unknownCar::::"+unknownCar);
        copyByField(benz,unknownCar,fieldList,Car.class);
//        copyByMethodName(benz,unknownCar,Car.class);
        System.out.println("复制属性后：unknownCar::::"+unknownCar);
    }

    static <T> void copyByField(T source,T target, List<String> fieldList,Class<T> tClass) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(tClass);
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        Arrays.stream(descriptors).filter(descriptor -> fieldList.contains(descriptor.getName())).forEach(descriptor -> {
            Method getMethod = descriptor.getReadMethod();
            Method setMethod = descriptor.getWriteMethod();
            try {
                Object value = getMethod.invoke(source);
                setMethod.invoke(target,value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    static <T> void copyByMethodName(T source,T target,Class<T> tClass) throws Exception {

        Method getMethod = tClass.getDeclaredMethod("getName");
        Method setMethod = tClass.getDeclaredMethod("setName",String.class);
        try {
            Object value = getMethod.invoke(source);
            setMethod.invoke(target,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
