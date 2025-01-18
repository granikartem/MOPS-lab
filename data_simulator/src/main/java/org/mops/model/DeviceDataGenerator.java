package org.mops.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
@AllArgsConstructor
public class DeviceDataGenerator {
    private int generationBound;

    public DeviceMessage generateMessageForDevice(int deviceId) {
        return new DeviceMessage(
                deviceId,
                String.format("validation%d", deviceId),
                ThreadLocalRandom.current().nextInt(generationBound)
        );
    }
}
