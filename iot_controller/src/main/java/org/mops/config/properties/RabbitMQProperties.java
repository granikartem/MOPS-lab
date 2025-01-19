package org.mops.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix ="rabbitmq")
@Data
public class RabbitMQProperties {
    private String queueName;
}
