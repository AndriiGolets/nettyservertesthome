package golets.spring.rest.client.generator.builders;



import golets.spring.rest.client.generator.dto.EventLevelData;
import golets.spring.rest.client.generator.dto.MetaData;
import golets.spring.rest.client.generator.dto.RequestObject;
import golets.spring.rest.client.generator.dto.XTRAData;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static golets.spring.rest.client.generator.RandomDataGenerator.randomDate;


public class RequestObjectBuilder extends AbstractBuilder implements Builder<RequestObject> {

    @Override
    public RequestObject build() {
        MetaData metaData = new MetaDataBuilder().build();
        XTRAData xtraData = new XTRADataBuilder().build();
        List<EventLevelData> events = IntStream.range(1, 25)
                .mapToObj(i -> new EventLevelDataBuilder().build())
                .collect(Collectors.toList());
        return new RequestObject(xtraData, metaData, events, randomDate());
    }
}
