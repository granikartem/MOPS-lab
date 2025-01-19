package org.mops.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class Rule {
    private String name;
    private Field field;
    private ComparisonType comparisonType;
    private int comparisonValue;

    public abstract boolean check(DeviceMessage deviceMessage);

    protected boolean performComparison(int value) {
        return switch (comparisonType) {
            case EQUALS -> value == comparisonValue;
            case NOT_EQUALS -> value != comparisonValue;
            case GREATER_THAN -> value > comparisonValue;
            case LESS_THAN -> value < comparisonValue;
            case GREATER_THAN_OR_EQUALS -> value >= comparisonValue;
            case LESS_THAN_OR_EQUALS -> value <= comparisonValue;
            default -> false;
        };
    }
}
