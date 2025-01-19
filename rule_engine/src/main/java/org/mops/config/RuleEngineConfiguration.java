package org.mops.config;

import org.mops.config.properties.RuleProperties;
import org.mops.model.ContinuousRule;
import org.mops.model.InstantRule;
import org.mops.model.Rule;
import org.mops.model.RuleDescription;
import org.mops.service.DeviceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ConfigurationPropertiesScan
public class RuleEngineConfiguration {
    private final DeviceMessageService deviceMessageService;
    private final RuleProperties ruleProperties;

    public RuleEngineConfiguration(DeviceMessageService deviceMessageService, RuleProperties ruleProperties) {
        this.deviceMessageService = deviceMessageService;
        this.ruleProperties = ruleProperties;
    }

    @Bean
    public Set<Rule> ruleSet() {
        Set<Rule> rules = new HashSet<>();

        for (RuleDescription description: ruleProperties.getDescriptions()) {
            Rule rule;

            if (description.getLongevity() == 1) {
                rule = new InstantRule();
            } else if (description.getLongevity() > 1) {
                rule = new ContinuousRule(description.getLongevity(), deviceMessageService);
            } else {
                throw new IllegalArgumentException("Rule longevity cannot be less than 1.");
            }

            rule.setName(description.getName());
            rule.setField(description.getField());
            rule.setComparisonType(description.getComparisonType());
            rule.setComparisonValue(description.getComparisonValue());

            rules.add(rule);
        }

        return rules;
    }
}
