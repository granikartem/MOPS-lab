package org.mops.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
@AllArgsConstructor
public class DeviceDataGenerator {
    private int generationBound;

    public DeviceMessage generateMessageForDevice(int deviceId) {
        int chance = ThreadLocalRandom.current().nextInt(0, 100);
        String validation;

        if (chance == 99) {
            validation = "invalid";
        } else {
            validation = String.format("validation%d", deviceId);
        }
        return new DeviceMessage(
                deviceId,
                validation,
                ThreadLocalRandom.current().nextInt(generationBound)
        );
    }
}
