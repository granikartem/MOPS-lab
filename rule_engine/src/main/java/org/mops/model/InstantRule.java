package org.mops.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InstantRule extends Rule{
    @Override
    public boolean check(DeviceMessage deviceMessage) {
        if (this.getField() == Field.INDICATION) {
            return super.performComparison(deviceMessage.getIndication());
        } else {
            throw new UnsupportedOperationException("Cannot perform comparison on field: " + this.getField());
        }
    }
}
