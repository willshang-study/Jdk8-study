package mian.java.com.jdk8.news.function.function;

import java.util.Objects;
@FunctionalInterface
interface MyFunction<T, R> {

    R apply(T t);

    default <V> MyFunction<V, R> compose(MyFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> MyFunction<T, V> andThen(MyFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    static <T> MyFunction<T, T> identity() {
        return t -> t;
    }
}
