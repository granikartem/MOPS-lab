package org.mops.repository;

import org.mops.model.RuleTrigger;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RuleTriggerRepository extends MongoRepository<RuleTrigger, String> {
}
