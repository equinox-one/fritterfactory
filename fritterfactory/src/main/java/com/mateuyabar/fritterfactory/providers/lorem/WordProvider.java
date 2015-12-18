package com.mateuyabar.fritterfactory.providers.lorem;

import com.thedeanda.lorem.LoremIpsum;

import javax.inject.Provider;


public class WordProvider implements Provider<String> {
    int max, min;
    public WordProvider() {
        this(1,1);
    }

    public WordProvider(int count) {
        this(count,count);
    }

    public WordProvider(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String get() {
        return LoremIpsum.getInstance().getWords(min, max);
    }
}
