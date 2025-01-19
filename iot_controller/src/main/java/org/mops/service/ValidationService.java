package org.mops.service;

import org.mops.dto.DeviceMessageDto;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    public boolean validate(int deviceId, DeviceMessageDto message) {
        return deviceId == message.getDeviceId() &&
                String.format("validation%d", deviceId).equals(message.getValidation());
    }
}
