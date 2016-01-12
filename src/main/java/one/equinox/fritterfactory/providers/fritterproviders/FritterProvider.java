package one.equinox.fritterfactory.providers.fritterproviders;


import one.equinox.fritterfactory.FritterFactory;

import javax.inject.Provider;

public interface FritterProvider<T> {
    public T get(FritterFactory factory);
    public Provider<T> getProvider(FritterFactory factory);
}
