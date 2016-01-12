package one.equinox.fritterfactory.providers.primitives;

import one.equinox.fritterfactory.util.RandomFactory;

import java.util.Random;

import javax.inject.Provider;


public class BooleanProvider implements Provider<Boolean> {
    Random random = new RandomFactory().get();

    @Override
    public Boolean get() {
        return random.nextBoolean();
    }
}
