package golets.spring.rest.client.generator.builders;

import golets.spring.rest.client.generator.dto.XTRAData;

import java.util.UUID;

import static golets.spring.rest.client.generator.RandomDataGenerator.*;


public class XTRADataBuilder extends AbstractBuilder implements Builder<XTRAData> {
    @Override
    public XTRAData build() {
        int othersSize = randomPositiveInteger(10);
        return new XTRAData(
                randomPositiveLong(),
                UUID.randomUUID(),
                randomDate(),
                randomHexString(),
                randomHexString(),
                randomHexString(),
                randomHexString(),
                randomHexString(),
                generateStringsMap(othersSize));
    }
}
