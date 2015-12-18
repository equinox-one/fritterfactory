package com.mateuyabar.fritterfactory.providers.lorem;

import com.thedeanda.lorem.LoremIpsum;

import javax.inject.Provider;


public class CountryProvider implements Provider<String> {
    @Override
    public String get() {
        return LoremIpsum.getInstance().getCountry();
    }
}
