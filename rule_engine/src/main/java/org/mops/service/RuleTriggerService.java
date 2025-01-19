package org.mops.service;

import org.mops.model.DeviceMessage;
import org.mops.model.Rule;
import org.mops.model.RuleTrigger;
import org.mops.repository.RuleTriggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RuleTriggerService {
    private final RuleTriggerRepository ruleTriggerRepository;
    private final Set<Rule> rules;

    public RuleTriggerService(RuleTriggerRepository ruleTriggerRepository, Set<Rule> rules) {
        this.ruleTriggerRepository = ruleTriggerRepository;
        this.rules = rules;

    }

    public void processMessage(DeviceMessage message) {
        for (Rule rule : rules) {
            if (rule.check(message)) {
                RuleTrigger ruleTrigger = new RuleTrigger();

                ruleTrigger.setRuleName(rule.getName());
                ruleTrigger.setDeviceId(message.getDeviceId());
                ruleTrigger.setTimestamp(System.currentTimeMillis());

                ruleTriggerRepository.save(ruleTrigger);
            }
        }
    }
}
