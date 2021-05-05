## BinaryOperator
    Represents an operation upon two operands of the same type, producing a result
    of the same type as the operands. 
    两个入参和返回参数类型都是一样的，它是一种特殊的 BiFunction，继承自BiFunction

## static minBy
    Returns a {@link BinaryOperator} which returns the lesser of two elements
    according to the specified(具体说明)
    接收一个comparator参数

## static maxBy