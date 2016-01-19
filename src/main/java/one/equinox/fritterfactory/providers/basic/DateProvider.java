package one.equinox.fritterfactory.providers.basic;

import one.equinox.fritterfactory.util.RandomFactory;

import javax.inject.Provider;
import java.util.Date;
import java.util.Random;


public class DateProvider implements Provider<Date> {
    Random random = new RandomFactory().get();

    @Override
    public Date get() {
        return new Date(random.nextLong());
    }
}
