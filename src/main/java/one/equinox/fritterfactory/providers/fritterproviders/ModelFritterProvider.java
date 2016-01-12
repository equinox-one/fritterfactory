package one.equinox.fritterfactory.providers.fritterproviders;


import one.equinox.fritterfactory.FritterFactory;
import one.equinox.fritterfactory.mold.Mold;
import one.equinox.fritterfactory.providers.ModelProvider;

import javax.inject.Provider;

public class ModelFritterProvider<T> implements FritterProvider<T>{
    Class<T> clazz;
    Mold mold;

    public ModelFritterProvider(Class<T> clazz, Mold mold) {
        this.clazz = clazz;
        this.mold = mold;
    }

    public T get(FritterFactory factory){
        return factory.build(clazz, mold);
    }

    public Provider<T> getProvider(FritterFactory factory){
        return new ModelProvider<T>(factory, clazz, mold);
    }
}
