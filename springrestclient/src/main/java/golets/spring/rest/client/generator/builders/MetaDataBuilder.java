package golets.spring.rest.client.generator.builders;



import golets.spring.rest.client.generator.dto.MetaData;

import java.util.UUID;

import static golets.spring.rest.client.generator.RandomDataGenerator.*;


public class MetaDataBuilder extends AbstractBuilder implements Builder<MetaData> {

    @Override
    public MetaData build() {
        int othersSize = randomPositiveInteger(10);
        return new MetaData(UUID.randomUUID(),
                randomPositiveLong(),
                randomPositiveInteger(),
                randomHexString(),
                randomDate(),
                randomDate(),
                randomHexString(),
                randomHexString(),
                randomHexString(),
                randomPositiveLong(),
                randomPositiveLong(),
                randomHexString(),
                randomHexString(),
                randomHexString(),
                randomHexString(),
                generateStringsMap(othersSize));
    }
}
