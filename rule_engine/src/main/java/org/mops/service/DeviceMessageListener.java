package org.mops.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mops.model.DeviceMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

@Component
public class DeviceMessageListener {
    private final RuleTriggerService ruleTriggerService;
    private final ObjectMapper objectMapper;

    public DeviceMessageListener(RuleTriggerService ruleTriggerService) {
        this.ruleTriggerService = ruleTriggerService;
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "${rabbitmq.queue-name}")
    public void receiveMessage(String deviceMessageJson) throws JsonProcessingException {
        System.out.println(deviceMessageJson);
        DeviceMessage deviceMessage = objectMapper.readValue(deviceMessageJson, DeviceMessage.class);

        ruleTriggerService.processMessage(deviceMessage);
    }
}
