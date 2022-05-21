package mian.java.com.jdk8.newfeatures.chapter07;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.stream("Ａ２６4836909".split("")).anyMatch(item->item.matches("[^\\x00-\\xff]")));
    }
}
