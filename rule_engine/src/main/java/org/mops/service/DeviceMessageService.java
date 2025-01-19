package org.mops.service;

import org.mops.model.DeviceMessage;
import org.mops.repository.DeviceMessageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceMessageService {
    private final DeviceMessageRepository deviceMessageRepository;

    public DeviceMessageService(DeviceMessageRepository deviceMessageRepository) {
        this.deviceMessageRepository = deviceMessageRepository;
    }

    public List<DeviceMessage> getLastNMessagesForDevice(int deviceId, String id, int n) {
        return deviceMessageRepository.findTopNByDeviceIdAndIdLessThanEqualOrderByTimestampDesc(
                deviceId,
                id,
                PageRequest.of(0, n)
        );
    }
}
