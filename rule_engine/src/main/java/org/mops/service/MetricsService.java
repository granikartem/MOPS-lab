package org.mops.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.mops.config.properties.RuleProperties;
import org.mops.model.RuleDescription;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MetricsService {
    private final Map<String, Counter> ruleTriggerCounters;

    public MetricsService(MeterRegistry registry, RuleProperties ruleProperties) {
        this.ruleTriggerCounters = new HashMap<>();

        for (RuleDescription ruleDescription: ruleProperties.getDescriptions()) {
            ruleTriggerCounters.put(
                    ruleDescription.getName(),
                    Counter.builder(ruleDescription.getName() + "_rule_trigger_counter").register(registry)
            );
        }
    }


    public void incrementRuleTrigger(String RuleName) {
        ruleTriggerCounters.get(RuleName).increment();
    }
}
