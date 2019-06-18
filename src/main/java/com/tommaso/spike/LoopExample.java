package com.tommaso.spike;


import java.util.ArrayList;
import java.util.List;


public class LoopExample {

    public Result loopExample(List<Integer> input) {
        Result result = new Result();
        for (Integer i: input) {
            if(i % 2 == 0) {
                result.addOk(i);
            } else {
                result.addKo(i);
            }
        }
        return result;
    }
}
