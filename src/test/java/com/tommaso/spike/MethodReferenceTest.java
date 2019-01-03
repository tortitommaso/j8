package com.tommaso.spike;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

class Custom {

    static public void accept(String t) {
        System.out.println(t + " " + t);
    };
}

public class MethodReferenceTest {
    
    @Test
    public void example() {
        List<String> list = Arrays.asList("a","b");
        
        list.stream().forEach(Custom::accept);
        list.stream().map(String::toUpperCase).forEach(System.out::println);;
        list.stream().map(String::length).forEach(System.out::println);;
    }
}
