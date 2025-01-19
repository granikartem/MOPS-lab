package org.mops.service;

import org.mops.model.DeviceMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

@Component
public class DeviceMessageListener {
    private final RuleTriggerService ruleTriggerService;

    public DeviceMessageListener(RuleTriggerService ruleTriggerService) {
        this.ruleTriggerService = ruleTriggerService;
    }

    @RabbitListener(queues = "${rabbitmq.queue-name}")
    public void receiveMessage(DeviceMessage deviceMessage) {
        System.out.println(deviceMessage);
        ruleTriggerService.processMessage(deviceMessage);
    }
}
