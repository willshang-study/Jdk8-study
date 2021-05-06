## Optional(可选择的)

       A container object which may or may not contain a non-null value.
       If a value is present, {@code isPresent()} will return {@code true} and
       {@code get()} will return the value.
       
* 本身是一个容器，装着一个value       
* 为了避免NPE
* 使用函数式编程来用Optional

## value-based （Optional，LocalDateTime）
    没有公有的构造函数
    通过工厂模式创建实例
    它的实例里肯定装着一个value
    当value相等，指的的equals相等，则两个Optional对象可以交换