package org.mops.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ruleTriggers")
@Data
@RequiredArgsConstructor
public class RuleTrigger  {
    @Id
    private String id;
    private int deviceId;
    private String ruleType;
    private String ruleName;
    private long timestamp;
}
