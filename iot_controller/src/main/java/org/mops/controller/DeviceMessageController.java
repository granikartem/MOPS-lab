package org.mops.controller;

import org.mops.dto.DeviceMessageDto;
import org.mops.service.PersistenceService;
import org.mops.service.RuleEngineService;
import org.mops.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DeviceMessageController {
    private final ValidationService validationService;
    private final PersistenceService persistenceService;
    private final RuleEngineService ruleEngineService;

    public DeviceMessageController(ValidationService validationService, PersistenceService persistenceService, RuleEngineService ruleEngineService) {
        this.validationService = validationService;
        this.persistenceService = persistenceService;
        this.ruleEngineService = ruleEngineService;
    }

    @PostMapping("/devices/{deviceId}/messages")
    public ResponseEntity<String> sendMessage(@PathVariable("deviceId") int deviceId, @RequestBody DeviceMessageDto message) {
        boolean isValid = validationService.validate(deviceId, message);
        if (isValid) {
            persistenceService.saveMessage(message);
            ruleEngineService.passMessage(message);
        }
        System.out.println(deviceId);
        System.out.println(message);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
