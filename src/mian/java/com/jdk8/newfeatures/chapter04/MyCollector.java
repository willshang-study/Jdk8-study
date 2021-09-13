package mian.java.com.jdk8.newfeatures.chapter04;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    /**
     * 提供初始化容器 Map<Boolean,Map> 的方法
     * @return
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {

        Supplier<Map<Boolean, List<Integer>>> supplier = ()->{
            Map<Boolean, List<Integer>> result = new HashMap<>();
            result.put(true,new ArrayList<>());
            result.put(false,new ArrayList<>());
            return result;
        };

        return supplier;
    }

    /**
     * 提供将元素Integer添加进容器Map<Boolean,List>内的方法
     * @return
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {

        BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator = (map,item) ->{
            // 取出已经计算出来的质数列表
            List<Integer> primes = map.get(true);
            if(isPrimes(primes,item)){
                map.get(true).add(item);
            }else{
                map.get(false).add(item);
            }
        };

        return accumulator;
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return null;
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> set = new HashSet<>();
        set.add(Characteristics.IDENTITY_FINISH);
        return set;
    }

    /**
     *  判断入参i是否是质数 (从2到i，没有任何一个数可以被i整除)
     *  优化2：只要不被小于本身的已知质数整除即是质数 (用质数做除数)
     * @param primes 小于i的已知质数列表
     * @param i
     * @return
     */
    private boolean isPrimes(List<Integer> primes,int i){
        int a = (int)Math.sqrt(i);
        return getSubList(primes,i).stream().noneMatch(item -> i/item == 0);
//        return IntStream.rangeClosed(2,a).noneMatch(item -> i/item == 0);
//        return primes.stream().noneMatch(item -> i/item == 0);
    }

    List<Integer> getSubList(List<Integer> primes,int i){
        int a = (int)Math.sqrt(i);
        for(int x=0; x<primes.size(); x++){
            if(x > a){
                return primes.subList(0,x);
            }
        }
        return Collections.emptyList();
    }
}
