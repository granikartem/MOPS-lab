package org.mops.repository;

import org.mops.model.DeviceMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DeviceMessageRepository extends MongoRepository<DeviceMessage, String> {
    @Query(value = "{ 'deviceId': ?0, 'id': { '$lte': ?1 } }", sort = "{ '_id': -1 }")
    List<DeviceMessage> findTopNByDeviceIdAndIdLessThanEqualOrderByTimestampDesc(int deviceId, String id, Pageable pageable);
}
