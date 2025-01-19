package org.mops.service;

import org.mops.dto.DeviceMessageDto;
import org.mops.model.DeviceMessage;
import org.mops.repository.DeviceMessageRepository;
import org.springframework.stereotype.Service;

@Service
public class PersistenceService {
    private final DeviceMessageRepository repository;

    public PersistenceService(DeviceMessageRepository repository) {
        this.repository = repository;
    }

    public DeviceMessage saveMessage(DeviceMessageDto dto) {
        DeviceMessage message = dto.toDeviceMessage();
        return repository.save(message);
    }
}
