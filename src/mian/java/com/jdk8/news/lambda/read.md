## 1.Lambda表达式
    前提条件： 必须是FunctionalInterface，exactly one abstract method

## 2.语法
    1. method无参数    () -> {...}
    2. method有一个参数 item -> {...}
    3. method有一个参数 Type item -> {...}
    4. method有多个参数 (a,b) -> {...}
    5. method有多个参数 (Type1 a,Type2 b) -> {...}
    注：**Type类型可以省略**

## 3.简述
    * Lambda表达式为Java添加了缺失的函数式编程特性，使我们能将函数当作一等公民看待
    * 在将函数作为一等公民的语言中，Lambda表达式的类型是函数。
    * 但在Java中，Lambda表达式是对象，他们必须依附于一类特别的对象类型-----> 函数式接口
    * 在jdk8之前，只能传递值，Lambda传递的是行为