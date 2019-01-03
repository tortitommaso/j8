package com.tommaso.spike.parallelism;

import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

public class ParallelismTest {

    @Test
    public void howManyThreadAreUsedInParallelStreams() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    public static void main(String[] args) {

        long n = 100000000l;

        long begin = System.currentTimeMillis();
        iterativeSum(n);
        System.out.println("iterativeSum: " + performanceInSeconds(begin));

        begin = System.currentTimeMillis();
        sequentialSum(n);
        System.out.println("sequentialSum: " + performanceInSeconds(begin));

        begin = System.currentTimeMillis();
        parallelSum(n);
        System.out.println("parallelSum: " + performanceInSeconds(begin));

        begin = System.currentTimeMillis();
        parallelRangedSum(n);
        System.out.println("parallelRangedSum: " + performanceInSeconds(begin));

        /*
         * iterate is difficult to divide into independent chunks to execute in parallel.
Specifically, the iterate operation is hard to split into chunks that can be executed independently,
because the input of one function application always depends on the result of the previous application.
By flagging the stream as parallel, you're adding the overhead 
of allocating each sum operation on a different thread to the sequential processing
        */
    }

    private static String performanceInSeconds(long begin) {
        return String.format("%s ms", System.currentTimeMillis() - begin);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .parallel()
            .reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(0L, Long::sum);
    }
}
