## Collector (接口)
    mutable reduction operation：可变的 缩小（聚合） 操作
   that accumulates input elements into a mutable result container, optionally transforming
   the accumulated result into a final representation after all input elements
   have been processed.  Reduction operations can be performed either sequentially
   or in parallel
* 积累输入元素到一个可变的容器中，转换积累结果为一个最终的表现形式在所有输入元素被处理完成。这个聚合操作可以串行或者并行。

## Collectors
  * 其中一个CollectorImpl内类实现了Collector接口
  * 提供了大量的聚合操作(Reduction Operations -> Collector)的实现方式。
  * 如：
  1. toCollection(转为一个新的集合)
  2. groupingBy(分组)
  3. partitioningBy(分区--分组的特殊形式：只有true和false两个分组)
  4. joining(对字符的串联)
  5. counting(数量),averaging(平均值),summing(求和)

