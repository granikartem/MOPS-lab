package org.mops.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "data-simulation")
@Data
public class DataSimulationProperties {
    private long initialDelayMillis;
    private int deviceCount;
    private int frequency;
    private int indicationBound;
}
