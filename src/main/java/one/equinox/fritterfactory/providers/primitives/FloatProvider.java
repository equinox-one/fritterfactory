package one.equinox.fritterfactory.providers.primitives;

import one.equinox.fritterfactory.util.RandomFactory;

import javax.inject.Provider;
import java.util.Random;


public class FloatProvider implements Provider<Float> {
    Random random = new RandomFactory().get();

    @Override
    public Float get() {
        return random.nextFloat();
    }
}
