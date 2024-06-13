package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomNumbersGenerator {
    public static List<Integer> generateUniqueRandomNumbers(int size, int bound) {
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        while (set.size() < size) {
            set.add(random.nextInt(bound));
        }
        return new ArrayList<>(set);
    }

    public static List<Integer> generateRandomNumbers(int size, int bound) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(bound));
        }
        return list;
    }
}
