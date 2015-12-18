package com.mateuyabar.fritterfactory.providers.primitives;

import java.util.Random;

import javax.inject.Provider;


public class DoubleProvider implements Provider<Double> {
    Random random = new com.mateuyabar.fritterfactory.util.RandomFactory().get();

    @Override
    public Double get() {
        return random.nextDouble();
    }
}
