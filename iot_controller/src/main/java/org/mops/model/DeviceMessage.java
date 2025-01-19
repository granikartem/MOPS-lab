package org.mops.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deviceMessages")
@Data
@RequiredArgsConstructor
@JsonDeserialize
@JsonSerialize
public class DeviceMessage {
    @Id
    private String id;
    private int deviceId;
    private String validation;
    private int indication;
}
