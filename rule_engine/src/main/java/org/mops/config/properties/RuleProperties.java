package org.mops.config.properties;

import lombok.Data;
import org.mops.model.RuleDescription;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "rules")
@Data
public class RuleProperties {
    private List<RuleDescription> descriptions;
}
