package org.mops.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deviceMessages")
@Data
@RequiredArgsConstructor
public class DeviceMessage {
    @Id
    private String id;
    private int deviceId;
    private String validation;
    private int indication;
}
