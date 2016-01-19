package one.equinox.fritterfactory.providers.primitives;

import one.equinox.fritterfactory.util.RandomFactory;

import javax.inject.Provider;
import java.util.Random;


public class LongProvider implements Provider<Long> {
    Random random = new RandomFactory().get();

    @Override
    public Long get() {
        return random.nextLong();
    }
}
