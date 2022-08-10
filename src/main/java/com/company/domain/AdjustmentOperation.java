package com.company.domain;

import static com.company.util.MessageCleanerUtil.format;

public enum AdjustmentOperation {
    Add {
        @Override
        public double process(double initialValue, double adjustedPrice) {
            return format(initialValue + adjustedPrice);
        }
    },
    Subtract {
        @Override
        public double process(double initialValue, double adjustedPrice) {
            return format(initialValue - adjustedPrice);
        }
    },
    Multiply {
        @Override
        public double process(double initialValue, double adjustedPrice) {
            return format(initialValue * adjustedPrice);
        }
    };

    public abstract double process(double initialValue, double adjustedPrice);
}
