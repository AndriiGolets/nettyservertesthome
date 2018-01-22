package golets.spring.rest.client.generator.builders;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static golets.spring.rest.client.generator.RandomDataGenerator.randomHexString;


public abstract class AbstractBuilder {
    protected Map<String, String> generateStringsMap(final int size) {
        List<String> keys = IntStream.range(0, size).mapToObj(i -> randomHexString()).collect(Collectors.toList());
        List<String> values = IntStream.range(0, size).mapToObj(i -> randomHexString()).collect(Collectors.toList());
        return IntStream.range(0, keys.size()).boxed().collect(Collectors.toMap(keys::get, values::get));
    }
}
