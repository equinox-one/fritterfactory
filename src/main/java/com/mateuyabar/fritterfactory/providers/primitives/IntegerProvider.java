package com.mateuyabar.fritterfactory.providers.primitives;

import java.util.Random;

import javax.inject.Provider;


public class IntegerProvider implements Provider<Integer> {
    Random random = new com.mateuyabar.fritterfactory.util.RandomFactory().get();

    int min, max;

    public IntegerProvider(){
        this(0, 100000);
    }

    public IntegerProvider(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer get() {
        return min + random.nextInt(max-min);
    }
}
