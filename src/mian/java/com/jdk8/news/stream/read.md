## stream
    A sequence of elements supporting sequential and parallel aggregate operations. 
    一连串的元素，支持串行和并行的聚合操作
    函数式链式编程风格

## Stream由三部分组成
    * 源
    * 零个或多个中间操作（intermediate operations）    惰性求值
    * 终止操作（termediate operations）   及早求值

* Collection提供了新的Stream()方法
* 流不存储值，通过管道的方式获取值
* 本质是函数式的，对流的操作会生成一个结果，不过并不会修改底层的数据源，集合可以作为流的底层数据源
* 延迟查找，很多流操作（过滤，映射，排序等）都可以延迟实现
* 每次创建的stream只能使用一次