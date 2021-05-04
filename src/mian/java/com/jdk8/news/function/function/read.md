## Function
## apply
    Represents a function that accepts one argument and produces a result.
    代表接收一个参数，且返回一个结果
## compose
    1  default<V> Function<V,R> compose(Function<? super V,? extends T> before){
    2     return (V v) -> apply(before.apply(v));   //return的是一个Lamda表达式(入参V，返回参数类型是R)
    3  }
* 因V未在泛型接口中定义，则需要在泛型方法中定义
* CurrentFunction  T  -->  R
* beforeFunction V  -->  T  -->  R ：  则 V --> R （Function<V,R>）的一个实现
* 证明compose方法的返回值类型是  Function<V,R>

## andThen
    T --> R --> V  :   T --> V
    
## 如何理解compose中 Function<? super V,? extends T> before
* 因为beforeFunction先执行，他的返回值是需要给currentFunction提供入参的，所以要“严格”，故用“上限通配符”
* compose要返回一个Function<V,R> 入参为V类型，(V v)也限定了入参为V类型，“宽松”即可，因为不可能有不是V类型的其他类型进来
