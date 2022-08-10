package com.company.domain;

import org.junit.Assert;
import org.junit.Test;


public class AdjustmentOperationTest {

    @Test
    public void shouldReturnAdjustedAddValue() {
        double adjustedValue = AdjustmentOperation.Add.process(.20, .20);
        Assert.assertEquals(.40, adjustedValue, 0);
    }

    @Test
    public void shouldReturnAdjustedSubtractValue() {
        double adjustedValue = AdjustmentOperation.Subtract.process(.20, .10);
        Assert.assertEquals(.10, adjustedValue, 0);
    }

    @Test
    public void shouldReturnAdjustedMultipliedValue() {
        double adjustedValue = AdjustmentOperation.Multiply.process(.10, .10);
        Assert.assertEquals(.01, adjustedValue, 0);
    }
}