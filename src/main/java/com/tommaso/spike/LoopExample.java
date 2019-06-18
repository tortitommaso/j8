package com.tommaso.spike;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


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

    public Result loopExample2(List<Integer> input, Predicate<Integer> p) {
        Result result = new Result();
        for (Integer i: input) {
            if(p.test(i)) {
                result.addOk(i);
            } else {
                result.addKo(i);
            }
        }
        return result;
    }
}
