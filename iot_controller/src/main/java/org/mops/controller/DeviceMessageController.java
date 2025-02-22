package org.mops.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mops.dto.DeviceMessageDto;
import org.mops.model.DeviceMessage;
import org.mops.service.MetricsService;
import org.mops.service.PersistenceService;
import org.mops.service.RuleEngineService;
import org.mops.service.ValidationService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DeviceMessageController {
    private final MetricsService metricsService;
    private final ValidationService validationService;
    private final PersistenceService persistenceService;
    private final RuleEngineService ruleEngineService;

    public DeviceMessageController(MetricsService metricsService, ValidationService validationService, PersistenceService persistenceService, RuleEngineService ruleEngineService) {
        this.metricsService = metricsService;
        this.validationService = validationService;
        this.persistenceService = persistenceService;
        this.ruleEngineService = ruleEngineService;
    }

    @PostMapping("/devices/{deviceId}/messages")
    public ResponseEntity<String> sendMessage(@PathVariable("deviceId") int deviceId, @RequestBody DeviceMessageDto message) throws JsonProcessingException {
        metricsService.incrementDeviceReceived(deviceId);
        boolean isValid = validationService.validate(deviceId, message);
        if (isValid) {
            DeviceMessage deviceMessage = persistenceService.saveMessage(message);
            ruleEngineService.passMessage(deviceMessage);
        } else {
            metricsService.incrementDeviceInvalid(deviceId);
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
