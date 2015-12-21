package com.mateuyabar.fritterfactory.providers;

import com.mateuyabar.fritterfactory.FritterFactory;
import com.mateuyabar.fritterfactory.ReflectionUtil;
import com.mateuyabar.fritterfactory.mold.Mold;

import javax.inject.Provider;

public class ModelProvider<T> implements Provider<T>{
    FritterFactory fritterFactory;
    Class<T> clazz;
    Mold mold;

    public ModelProvider(FritterFactory fritterFactory, Class<T> clazz) {
        this(fritterFactory, clazz, null);
    }



    public ModelProvider(FritterFactory fritterFactory, Class<T> clazz, Mold mold) {
        this.clazz = clazz;
        this.fritterFactory = fritterFactory;
        this.mold = mold;
    }

    @Override
    public T get() {
        return fritterFactory.buildModel(clazz, mold);
    }
}
