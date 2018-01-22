package golets.spring.rest.client.generator.builders;



import golets.spring.rest.client.generator.dto.EventLevelData;

import java.util.UUID;

import static golets.spring.rest.client.generator.RandomDataGenerator.*;



public class EventLevelDataBuilder extends AbstractBuilder implements Builder<EventLevelData> {
    @Override
    public EventLevelData build() {
        int eventsSize = randomPositiveInteger(100);
        int othersSize = randomPositiveInteger(10);
        return new EventLevelData(UUID.randomUUID(),
                randomDate(),
                randomDate(),
                randomPositiveLong(),
                randomPositiveLong(),
                randomDouble(),
                randomPositiveLong(),
                randomDouble(),
                randomHexString(),
                randomPositiveLong(),
                generateStringsMap(eventsSize),
                generateStringsMap(othersSize));
    }
}
