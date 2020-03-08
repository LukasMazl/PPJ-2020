package cz.tul.ppj;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {

    public static int sum(Collection<Integer> integerCollection) {
        return integerCollection.stream().mapToInt(i -> i.intValue()).sum();
    }

    public static List<String> filterStartWith(Collection<String> stringCollection, String prefix) {
        return stringCollection.stream().filter((s) ->(s.startsWith(prefix))).collect(Collectors.toList());
    }
}
