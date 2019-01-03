package com.tommaso.spike.parallelism;

import org.junit.Test;

public class ParallelismTest {

    @Test
    public void howManyThreadAreUsedInParallelStreams() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
