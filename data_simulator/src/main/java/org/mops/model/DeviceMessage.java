package org.mops.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceMessage {
    private int deviceId;
    private String validation;
    private int indication;
}
