package org.mops.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ConnectionSpecs {
    String host;
    int port;
    String path;
}
