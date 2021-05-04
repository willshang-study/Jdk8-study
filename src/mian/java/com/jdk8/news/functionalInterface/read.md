## FunctionalInterface(函数式接口)

    1. 只有一个抽象方法（重写Object类方法的抽象方法不算，如toString）
    2. 使用FunctionalInterface注解标识（也可不用，编译器也认为是函数式接口）
    3. 非函数式接口若使用 Lambda expressions，method reference(方法引用) 来实现会报编译错误