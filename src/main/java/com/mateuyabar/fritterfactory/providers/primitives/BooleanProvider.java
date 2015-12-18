package com.mateuyabar.fritterfactory.providers.primitives;

import java.util.Random;

import javax.inject.Provider;


public class BooleanProvider implements Provider<Boolean> {
    Random random = new com.mateuyabar.fritterfactory.util.RandomFactory().get();

    @Override
    public Boolean get() {
        return random.nextBoolean();
    }
}
