package com.tommaso.spike;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

enum Color {
    RED, GREEN
}

class Apple {
    private final int _weight;
    private final Color _color;

    public Apple(int weight, Color color) {
        _weight = weight;
        _color = color;
    }

    public Color getColor() {
        return _color;
    }

    public int getWeight() {
        return _weight;
    }
}

public class LambaTest {

    private List<Apple> _inventory;

    @Before
    public void createList() {
        _inventory = Arrays.asList(new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED));
    }

    @Test
    public void beforeJ8() {
        assertEquals(1, filterApplesByColor(_inventory, Color.RED).size());
        assertEquals(3, filterApplesByWeight(_inventory, 10).size());
    }

    @Test
    public void predicate() {
        List<Apple> filtered = filterApples(_inventory, new Predicate<Apple>() {

            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });
        assertEquals(1, filtered.size());
    }
    
    @Test
    public void behaviorParameterization() {
        assertEquals(1, filterApples(_inventory, (Apple apple) -> Color.RED.equals(apple.getColor())).size());
        assertEquals(1, filterApples(_inventory, (a) -> Color.RED.equals(a.getColor())).size());
        assertEquals(3, filterApples(_inventory, (a) -> 10 < a.getWeight()).size());
        assertEquals(3, filterApples(_inventory, (a) -> 10 < a.getWeight()).size());
        Predicate<Apple> greenApples = (a) -> Color.GREEN.equals(a.getColor());
        Predicate<Apple> smallApples = (a) -> a.getWeight() < 100;
        assertEquals(1, filterApples(_inventory, greenApples.and(smallApples)).size());
        assertEquals(2, filterApples(_inventory, greenApples.or(smallApples)).size());
        assertEquals(0, filterApples(_inventory, greenApples.negate().and(smallApples)).size());
    }
    
    
    @Test
    public void groupingBeforeJ8() {
        Map<Color, List<Apple>> map = new HashMap<>();
        for (Apple apple : _inventory) {
            Color color = apple.getColor();
            List<Apple> applesByColor = map.get(color);
            if(applesByColor == null) {
                applesByColor = new ArrayList<>();
                map.put(color, applesByColor);
            }
            applesByColor.add(apple);
        }
        assertEquals(2, map.get(Color.GREEN).size());
        assertEquals(1, map.get(Color.RED).size());
    }
    
    @Test
    public void groupingJ8() {
        Map<Color, List<Apple>> map = _inventory.stream().collect(Collectors.groupingBy(Apple::getColor));
        assertEquals(2, map.get(Color.GREEN).size());
        assertEquals(1, map.get(Color.RED).size());
    }
    
    @Test
    public void collectorsMethodsTest() {
        Long howMany = _inventory.stream().collect(Collectors.counting());
        assertEquals(new Long(3), howMany);
        
        Optional<Apple> max = _inventory.stream().collect(Collectors.maxBy(Comparator.comparingInt(Apple::getWeight)));
        assertEquals(155, max.get().getWeight());

        Integer sum = _inventory.stream().collect(Collectors.summingInt(Apple::getWeight));
        assertEquals(355, sum.intValue());

        double average = _inventory.stream().collect(Collectors.averagingDouble(Apple::getWeight));
        assertEquals(118.33, average, 0.01);

        IntSummaryStatistics intSummaryStatistics = _inventory.stream().collect(Collectors.summarizingInt(Apple::getWeight));
        assertEquals("IntSummaryStatistics{count=3, sum=355, min=80, average=118.333333, max=155}", intSummaryStatistics.toString());
        
        assertEquals("[a-b-c]", Arrays.asList("a", "b", "c").stream().collect(Collectors.joining("-", "[", "]")));
    }

    
    public static List<Apple> filterApplesByColor(List<Apple> inventory,
        Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory,
        int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}
