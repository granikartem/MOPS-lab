package org.mops.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mops.model.DeviceMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RuleEngineService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RuleEngineService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void passMessage(DeviceMessage message) throws JsonProcessingException {
        String messageJson = objectMapper.writeValueAsString(message);
        rabbitTemplate.convertAndSend(messageJson);
    }
}
