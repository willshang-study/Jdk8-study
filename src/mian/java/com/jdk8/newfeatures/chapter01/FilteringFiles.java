package mian.java.com.jdk8.newfeatures.chapter01;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * 你想要筛选一个目录中的所有隐藏
 * 文件。你需要编写一个方法，然后给它一个File，它就会告诉你文件是不是隐藏的。幸好，File
 * 类里面有一个叫作isHidden的方法。我们可以把它看作一个函数，接受一个File，返回一个布
 * 尔值。
 */
public class FilteringFiles {

    public static void main(String[] args) {

        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        /**
         * Java 8的方法引用::语法（即“把这
         * 个方法作为值”）将其传给listFiles方法；请注意，我们也开始用函数代表方法了。
         * 一个好处是，你的代码现在读起来更接近问题的陈述了。方法不
         * 再是二等值了。与用对象引用传递对象类似（对象引用是用new创建的），在Java 8里写下
         * File::isHidden的时候，你就创建了一个方法引用，你同样可以传递它。
         */
        File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);

        Arrays.stream(hiddenFiles).forEach(file -> System.out.println(file.getName()));
        Arrays.stream(hiddenFiles2).forEach(file -> System.out.println(file.getName()));
    }


}
