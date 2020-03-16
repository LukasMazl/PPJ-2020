package cz.tul.ppj;

import java.util.stream.IntStream;

public class SteamUtils {
    public static long countOfPrimes(IntStream intStream) {
        return intStream.filter(MathUtils::isPrime).count();
    }

    public static double mean(IntStream intStream) {
        return intStream.average().getAsDouble();
    }

    public static int longestSequenceOddNumber(IntStream intStream) {
        final Integer[] lastNumber = new Integer[1];
        lastNumber[0] = 0;
        int longestSequence = intStream.map((value) -> {
            if (value % 2 == 0) {
                lastNumber[0] = 0;
                return 0;
            } else {
                return ++lastNumber[0];
            }
        }).max().getAsInt();
        return longestSequence;
    }
}
