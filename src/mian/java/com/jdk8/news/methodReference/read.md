## Method Reference

## 1.方法引用存在的意义？
    函数式接口中 exactly abstract method如果已经有现成的方法可以满足了，那么直接引用这个方法即可，不需要再写一套。

## 2.前提条件
    exactly abstract method的入参，返回参数，必须和 方法引用的方法的入参和返回参数一致
    特例：当 abstract method无返回参数（void）时，则对方法引用的方法的返回参数无要求

## 3.语法
    1. 对象实例::实例方法名
    2. 类名::静态方法名
    3. 类名::实例方法名
    4. ClassName::new (构造器引用)

## 4. 类名::实例方法名
    实例方法 一定需要 某个实例 才调用，则该实例将作为函数式接口唯一的Abstract Method的第一个参数
    Abstract Method除了第一个参数由“某个实例”提供，其余参数都由“实例方法名”入参提供