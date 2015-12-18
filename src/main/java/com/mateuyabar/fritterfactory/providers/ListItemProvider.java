package com.mateuyabar.fritterfactory.providers;

import com.mateuyabar.fritterfactory.util.RandomFactory;

import java.util.List;

import javax.inject.Provider;


public class ListItemProvider<T> implements Provider<T> {
    List<T> items;

    public ListItemProvider(List<T> items) {
        this.items = items;
    }

    @Override
    public T get() {
        int position =  new RandomFactory().get().nextInt(items.size());
        return items.get(position);
    }
}
