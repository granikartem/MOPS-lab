package org.mops.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MetricsService {
    private final MeterRegistry registry;
    private final Map<Integer, Counter> deviceReceivedMessageCounters;
    private final Map<Integer, Counter> deviceInvalidMessageCounters;

    public MetricsService(MeterRegistry registry) {
        this.registry = registry;
        this.deviceReceivedMessageCounters = new HashMap<>();
        this.deviceInvalidMessageCounters = new HashMap<>();
    }


    public void incrementDeviceReceived(int deviceId) {
        if (!deviceReceivedMessageCounters.containsKey(deviceId)) {
            Counter counter = Counter.builder("device" + deviceId + "_received_message_counter").register(registry);
            deviceReceivedMessageCounters.put(deviceId, counter);
        }

        deviceReceivedMessageCounters.get(deviceId).increment();


    }

    public void incrementDeviceInvalid(int deviceId) {
        if (!deviceInvalidMessageCounters.containsKey(deviceId)) {
            Counter counter = Counter.builder("device" + deviceId + "_invalid_message_counter").register(registry);
            deviceInvalidMessageCounters.put(deviceId, counter);
        }

        deviceInvalidMessageCounters.get(deviceId).increment();
    }
}
