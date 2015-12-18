package com.mateuyabar.fritterfactory.providers.fritterproviders;


import com.mateuyabar.fritterfactory.FritterFactory;
import com.mateuyabar.fritterfactory.ReflectionUtil;
import com.mateuyabar.fritterfactory.providers.ModelProvider;

import javax.inject.Provider;

public class ModelFritterProvider<T> implements FritterProvider<T>{
    Class<T> clazz;
    Object mold;

    public ModelFritterProvider(Class<T> clazz, Class moldClass){
        this(clazz, ReflectionUtil.newInstance(moldClass));
    }


    public ModelFritterProvider(Class<T> clazz, Object mold) {
        this.clazz = clazz;
        this.mold = mold;
    }

    public T get(FritterFactory factory){
        return factory.build(clazz, mold);
    }

    public Provider<T> getProvider(FritterFactory factory){
        return new ModelProvider<>(factory, clazz, mold);
    }
}
