package org.mops.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ComparisonType {
    EQUALS("=="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUALS(">="),
    LESS_THAN_OR_EQUALS("<=");

    private final String stringValue;
}
