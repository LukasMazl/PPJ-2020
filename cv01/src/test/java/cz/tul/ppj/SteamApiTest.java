package cz.tul.ppj;


import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SteamApiTest {

    @Test
    public void sumListTest() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        int sum = CollectionUtils.sum(integerList);

        assert sum == 15;
    }

    @Test
    public void sumSetTest() {
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(1);
        integerSet.add(2);
        integerSet.add(3);
        integerSet.add(4);
        integerSet.add(5);
        int sum = CollectionUtils.sum(integerSet);

        assert sum == 15;
    }

    @Test
    public void startWithTest() {
        List<String> strings = Arrays.asList("start", "skip", "test");
        List<String> filtered = CollectionUtils.filterStartWith(strings, "s");

        assert filtered.size() == 2;
    }

    @Test
    public void primeFilterTest() {
        List<Integer> integers = Arrays.asList(5, 6, 7, 8, 9, 10, 11, 12, 13);
        List<Integer> primes = CollectionUtils.filterPrimes(integers);

        assert primes.size() == 4;
    }

    @Test
    public void primeFilterTest2() {
        List<Integer> integers = Arrays.asList(5, 6, 7, 8, 9, 10, 11, 12, 13);
        long primes = CollectionUtils.countOfPrimes(integers);

        assert primes == 4;
    }

    @Test
    public void meanTest() {
        List<Integer> integers = Arrays.asList(5, 6, 7, 8, 9, 10, 11, 12, 13);
        double mean = CollectionUtils.mean(integers);

        assert mean == 9.0;
    }

    @Test
    public void longestSeqTest() {
        List<Integer> integers = Arrays.asList(1, 3, 5, 6, 8, 5, 5, 3, 3, 1, 8);
        int longestSeq = CollectionUtils.longestSequenceOddNumber(integers);
        assert longestSeq == 5;
    }

    @Test
    public void primeFilterTestIntStream() {
        IntStream intStream = IntStream.of(5, 6, 7, 8, 9, 10, 11, 12, 13);
        long primes = SteamUtils.countOfPrimes(intStream);

        assert primes == 4;
    }

    @Test
    public void meanTestIntStream() {
        IntStream intStream = IntStream.of(5, 6, 7, 8, 9, 10, 11, 12, 13);
        double mean = SteamUtils.mean(intStream);

        assert mean == 9.0;
    }

    @Test
    public void longestSeqTestIntStream() {
        IntStream intStream = IntStream.of(1, 3, 5, 6, 8, 5, 5, 3, 3, 1, 8);
        int longestSeq = SteamUtils.longestSequenceOddNumber(intStream);
        assert longestSeq == 5;
    }
}
