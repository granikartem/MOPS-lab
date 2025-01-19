package org.mops.repository;

import org.mops.model.DeviceMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceMessageRepository extends MongoRepository<DeviceMessage, String> {
}
