package mian.java.com.jdk8.newfeatures.chapter05;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 统计演讲稿中单词的个数
 * Stream<Character> 遍历每一个char的时候，需要两个变量来记录
 *  int counter：目前的字数
 *  boolean lastIsSpace：上一次遍历到的char是否是空格（判断本次遍历字数是否需要加1）
 */
public class WordsCounterTest {
    public static void main(String[] args) {
        String words = "I      have     a dream that one day this nation will rise up and live out the true meaning of its creed: We hold these truths to be self-evident, that all men are created equal." +
                "I have a dream that one day on the red hills of Georgia, the sons of former slaves and the sons of former slave owners will be able to sit down together at the table of brotherhood.";
        // 1. 串行计算words个数（正确）
        WordsCounter countSum1 = words.chars().mapToObj(item->(char)item).
                reduce(new WordsCounter(0, true),
                WordsCounter::accumulator,
                WordsCounter::combiner);
        System.out.println("默认分割器串行计算："+countSum1.counter);

        // 2. 使用默认分割器并行计算words个数（错误：会将words从某个单词中间截取开来，导致一个单词会被数2次）
        WordsCounter countSum2 = words.chars().mapToObj(item->(char)item).parallel().
                reduce(new WordsCounter(0, true),
                        WordsCounter::accumulator,
                        WordsCounter::combiner);
        System.out.println("默认分割器并行计算："+countSum2.counter);

        // 3. 使用自定义WordCounterSpliterator分割器并行计算words个数
        Stream<Character> streamParallel = StreamSupport.stream(new WordCounterSpliterator(words,0,words.length()),true);
        WordsCounter countSum3 = streamParallel.reduce(new WordsCounter(0, true),
                WordsCounter::accumulator,
                WordsCounter::combiner);
        System.out.println("自定义分割器并行计算："+countSum3.counter);
    }

    // 定义计数的 WordsCounter
    static class WordsCounter{
        // 1.int counter：目前已经数过了多少单词；
        final int counter;
        // 2.boolean lastIsSpace：用来记得上一个Character是不是空格
        final boolean lastIsSpace;

        //  U identity
        WordsCounter(int counter, boolean lastIsSpace) {
            this.counter = counter;
            this.lastIsSpace = lastIsSpace;
        }

        // BiFunction<U, ? super T, U> accumulator
        public static WordsCounter accumulator(WordsCounter wordsCounter, Character item){
            // 判断遍历到的元素是不是空格
            if(Character.isWhitespace(item)){
                return new WordsCounter(wordsCounter.counter,true);
            }else{
                // 如果不是空格，判断上一次的元素是不是空格
                // 判断单词数增加1：上一次是空格，当前不是空格，代表遇到一个新的单词
                return new WordsCounter((wordsCounter.lastIsSpace? wordsCounter.counter+1:wordsCounter.counter),false);
            }
        }

        // BinaryOperator<U> combiner
        public static WordsCounter combiner(WordsCounter a, WordsCounter b){
            return new WordsCounter(a.counter + b.counter,true);
        }
    }

    // 当并发流，会将words从某个单词中间截取开来，导致一个单词会被数2次
    // 需要自定义 WordCounterSpliterator，可分 迭代器，
    //      1. T为遍历的元素类型：Character
    //      2. 构造Spliterator的三个变量：words（不可变）,start,end (可通过trySplit和tryAdvance来更改)
    static class WordCounterSpliterator implements Spliterator<Character>{

        final String words;
        int start;
        int end;

        public WordCounterSpliterator(String words, int start, int end) {
            this.words = words;
            this.start = start;
            this.end = end;
        }

        // 对需要遍历的每个元素应用action，当没有元素时，则返回false，还有元素则返回true
        // 此处的action为 TerminalOp的子类ReduceOp，实则是 reduce(三个参数) 的三个参数构造的对象，这三个参数决定遍历要实施的行为
        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            if(start < end){
                action.accept(words.charAt(start++));
                return true;
            }
            return false;
        }

        // 将集合中的一些元素划分出去给到一个新的Spliterator，并返回。
        // 这里通过更改 start和end 分割，多个任务来遍历 words，不同的Spliterator遍历不同的（start~end） 段
        @Override
        public Spliterator<Character> trySplit() {
            int length = end - start;
            if(length < 10){
                return null;
            }
            // start --> start + end/2 --> end
            int middle = start + end/2;
            while(middle < end && Character.isWhitespace(words.charAt(middle))){
                middle ++;
            }
            this.start = middle;
            Spliterator<Character> leftSpliterator = new WordCounterSpliterator(words,start,middle);
            return leftSpliterator;
        }

        @Override
        public long estimateSize() {
            return start - end;
        }

        @Override
        public int characteristics() {
            return SIZED+SUBSIZED;
        }
    }
}
