package com.mateuyabar.fritterfactory.providers.fritterproviders;


import com.mateuyabar.fritterfactory.FritterFactory;

import javax.inject.Provider;

public interface FritterProvider<T> {
    public T get(FritterFactory factory);
    public Provider<T> getProvider(FritterFactory factory);
}
