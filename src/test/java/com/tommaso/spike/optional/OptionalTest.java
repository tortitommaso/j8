package com.tommaso.spike.optional;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

/*
Consistently using Optional values creates a clear distinction
between a missing value that's planned for and a value that's absent only because of a
bug in your algorithm or a problem in your data
 */
class Car {
    private String name ;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
public class OptionalTest {

    @Test(expected = NullPointerException.class) 
    public void test() {
        Integer a = null;
        Optional.of(a);
    }

    @Test(expected = NoSuchElementException.class)
    public void getOnNullableOptionalThrowsException() throws Exception {
        Car car = null;
        Optional<Car> optCar = Optional.ofNullable(car);
        optCar.get().getName();
    }
}
