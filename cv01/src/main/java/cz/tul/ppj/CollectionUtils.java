package cz.tul.ppj;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionUtils {

    public static int sum(Collection<Integer> integerCollection) {
        return integerCollection.stream().mapToInt(i -> i.intValue()).sum();
    }

    public static List<String> filterStartWith(Collection<String> stringCollection, String prefix) {
        return stringCollection.stream().filter((s) -> (s.startsWith(prefix))).collect(Collectors.toList());
    }

    public static List<Integer> filterPrimes(Collection<Integer> integerCollection) {
        return integerCollection.stream().filter(MathUtils::isPrime).collect(Collectors.toList());
    }

    public static double mean(Collection<Integer> integerCollection) {
        return integerCollection.stream().mapToInt(Integer::intValue).average().getAsDouble();
    }

    public static int longestSequenceOddNumber(List<Integer> integerCollection) {
        final Integer[] lastNumber = new Integer[1];
        lastNumber[0] = 0;
        int longestSequence = integerCollection.stream().mapToInt((value) -> {
            if (value % 2 == 0) {
                lastNumber[0] = 0;
                return 0;
            } else {
                return ++lastNumber[0];
            }
        }).max().getAsInt();
        return longestSequence;
    }

    public static long countOfPrimes(List<Integer> integerList) {
        IntStream intStream = integerList.stream().mapToInt(Integer::intValue);
        return intStream.filter(MathUtils::isPrime).count();
    }

}
