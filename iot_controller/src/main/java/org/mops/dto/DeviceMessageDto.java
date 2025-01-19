package org.mops.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.mops.model.DeviceMessage;

@Data
@JsonDeserialize
@AllArgsConstructor
public class DeviceMessageDto {
    private int deviceId;
    private String validation;
    private int indication;

    public DeviceMessage toDeviceMessage() {
        DeviceMessage deviceMessage = new DeviceMessage();

        deviceMessage.setDeviceId(deviceId);
        deviceMessage.setValidation(validation);
        deviceMessage.setIndication(indication);

        return deviceMessage;
    }
}
