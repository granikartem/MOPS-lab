package org.mops.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final Counter customCounter;

    public MetricsService(MeterRegistry registry) {
        this.customCounter = Counter.builder("custom_metric_counter")
                .description("Описание счетчика")
                .register(registry);
    }

    public void process() {
        // Логика вашего сервиса
        customCounter.increment();
    }
}
