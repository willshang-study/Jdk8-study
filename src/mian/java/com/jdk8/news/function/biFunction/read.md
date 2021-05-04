## BiFunction

## apply
    Represents a function that accepts two arguments and produces a result.
    接收2个参数，返回一个值
    相当于对Function的一个补充
    
## andThen
    default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after)
先调currentBiFunction apply  入参2个，返回V，该V作为afterFunction的入参
再调afterFunction apply 返回V

组成 入参T,U, 返回参数V  的 BiFunction<T, U, V>