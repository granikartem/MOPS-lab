package org.mops.service;

import org.mops.dto.DeviceMessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RuleEngineService {
    private final RabbitTemplate rabbitTemplate;

    public RuleEngineService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void passMessage(DeviceMessageDto message) {
        rabbitTemplate.convertAndSend(message);
    }
}
