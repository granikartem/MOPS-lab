package org.mops.model;

import org.mops.service.DeviceMessageService;

import java.util.List;


public class ContinuousRule extends Rule {
    private final DeviceMessageService deviceMessageService;
    private int longevity;


    public ContinuousRule(int longevity, DeviceMessageService deviceMessageService) {
        this.longevity = longevity;
        this.deviceMessageService = deviceMessageService;
    }

    @Override
    public boolean check(DeviceMessage deviceMessage) {
        List<DeviceMessage> lastDeviceMessages = deviceMessageService.getLastNMessagesForDevice(
                deviceMessage.getDeviceId(),
                deviceMessage.getId(),
                longevity
        );

        if (this.getField() == Field.INDICATION) {
            return lastDeviceMessages.stream().allMatch(message -> performComparison(message.getIndication()));
        } else {
            throw new UnsupportedOperationException("Cannot perform comparison on field: " + this.getField());
        }
    }
}
