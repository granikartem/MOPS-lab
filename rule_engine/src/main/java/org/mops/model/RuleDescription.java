package org.mops.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RuleDescription {
    String name;
    Field field;
    ComparisonType comparisonType;
    int comparisonValue;
    int longevity;
}
