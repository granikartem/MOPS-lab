package org.mops.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "connections")
@Data
public class ConnectionProperties {
    private Map<String, ConnectionSpecs> connectionSpecs = new HashMap<>();
}
