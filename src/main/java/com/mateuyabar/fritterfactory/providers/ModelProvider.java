package com.mateuyabar.fritterfactory.providers;

import com.mateuyabar.fritterfactory.FritterFactory;
import com.mateuyabar.fritterfactory.ReflectionUtil;

import javax.inject.Provider;

public class ModelProvider<T> implements Provider<T>{
    FritterFactory fritterFactory;
    Class<T> clazz;
    Object mold;

    public ModelProvider(FritterFactory fritterFactory, Class<T> clazz) {
        this(fritterFactory, clazz, (Object) null);
    }

    public ModelProvider(FritterFactory fritterFactory, Class<T> clazz, Class<?> moldClass) {
        this(fritterFactory, clazz, ReflectionUtil.newInstance(moldClass));
    }

    public ModelProvider(FritterFactory fritterFactory, Class<T> clazz, Object mold) {
        this.clazz = clazz;
        this.fritterFactory = fritterFactory;
        this.mold = mold;
    }

    @Override
    public T get() {
        return fritterFactory.buildModel(clazz, mold);
    }
}
