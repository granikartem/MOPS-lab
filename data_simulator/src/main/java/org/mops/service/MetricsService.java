package org.mops.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.mops.config.properties.DataSimulationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MetricsService {
    private final Map<Integer, Counter> deviceCounters;

    public MetricsService(MeterRegistry registry, DataSimulationProperties dataSimulationProperties) {
        this.deviceCounters = new HashMap<>();

        for (int i = 0; i < dataSimulationProperties.getDeviceCount(); i++) {
            Counter counter = Counter.builder("device" + i + "_sent_message_counter").register(registry);

            deviceCounters.put(i, counter);
        }
    }

    public void incrementDeviceCounter(int deviceId) {
        deviceCounters.get(deviceId).increment();
    }
}
