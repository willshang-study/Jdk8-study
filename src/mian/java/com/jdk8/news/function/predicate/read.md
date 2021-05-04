## predicate (谓语)

## test 
    Represents a predicate (boolean-valued function) of one argument.
    接收一个参数，返回一个boolean   
    
## and
    currentPredicate.test(T) && otherPredicate.test(T)  
    return的是一个Lambda表达式
    返回 一个 Predicate<T>
   
## negate
    !currentPredicate.test(T)
    return的是一个Lambda表达式
    返回 一个 Predicate<T>
    
## or
    同 and

## isEqual
    Returns a predicate that tests if two arguments are equal according
    
    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
            ? Objects::isNull  //方法引用的方式返回 Predicate
            : object -> targetRef.equals(object);
    }

## 为静态方法，接口可直接调用，接收一个参数，返回一个Predicate
    System.out.println(Predicate.isEqual("aaa").test("aaa"));
    