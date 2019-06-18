package com.tommaso.spike;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoopExampleTest {

    @Test
    public void loopTest() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        Result result = new LoopExample().loopExample(input);
        assertTrue(result.getOkList().contains(2));
        assertEquals(Arrays.asList(1,3), result.getKoList());
    }
}
