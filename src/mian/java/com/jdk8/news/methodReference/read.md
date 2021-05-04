## Method Reference

## 1.方法引用存在的意义？
    函数式接口中 exactly abstract method如果已经有现成的方法可以满足了，那么直接引用这个方法即可，不需要再写一套。

## 2.前提条件
    exactly abstract method的入参，返回参数，必须和 方法引用的方法的入参和返回参数一致
    特例：当 abstract method无返回参数（void）时，则对方法引用的方法的返回参数无要求

## 3.语法
    1. 对象实例::方法名
    2. 静态类::方法名
    3. 类实体::方法名
    4. ClassName::new (构造器引用)