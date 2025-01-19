package org.mops.service;

import jakarta.annotation.PostConstruct;
import org.mops.client.DeviceMessageClient;
import org.mops.config.properties.ConnectionProperties;
import org.mops.config.properties.ConnectionSpecs;
import org.mops.config.properties.DataSimulationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.*;

@Service
public class DeviceMessageService {
    private final DataSimulationProperties dataProperties;

    private final DeviceMessageClient client;
    private final ScheduledExecutorService executor;

    public DeviceMessageService(DataSimulationProperties dataProperties, ConnectionProperties connectionProperties) {
        this.dataProperties = dataProperties;

        ConnectionSpecs iotConnectionSpecs = connectionProperties.getConnectionSpecs().get("iot-controller");
        this.client = new DeviceMessageClient(iotConnectionSpecs, dataProperties.getIndicationBound());
        this.executor = Executors.newScheduledThreadPool(dataProperties.getDeviceCount());
    }

    @PostConstruct
    public void init() {
        startDataSimulation();
    }

    private void startDataSimulation() {
        for (int i = 0; i < dataProperties.getDeviceCount(); i++) {
            int deviceId = i;
            executor.scheduleAtFixedRate(() -> {
                        try {
                            client.generateAndSendDeviceMessage(deviceId);
                        } catch (IOException | InterruptedException e) {
                            System.out.println("request failed");
                            throw new RuntimeException(e);
                        }
                    },
                    0, 5000 / dataProperties.getFrequency(), TimeUnit.MILLISECONDS);
        }
    }
}
