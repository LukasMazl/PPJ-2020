package cz.tul.ppj;


import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
