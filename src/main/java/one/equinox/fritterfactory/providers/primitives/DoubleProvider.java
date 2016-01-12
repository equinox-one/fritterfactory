package one.equinox.fritterfactory.providers.primitives;

import one.equinox.fritterfactory.util.RandomFactory;

import java.util.Random;

import javax.inject.Provider;


public class DoubleProvider implements Provider<Double> {
    Random random = new RandomFactory().get();

    @Override
    public Double get() {
        return random.nextDouble();
    }
}
