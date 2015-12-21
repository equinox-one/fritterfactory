package com.mateuyabar.fritterfactory.example;

import com.mateuyabar.fritterfactory.providers.lorem.CityProvider;
import com.mateuyabar.fritterfactory.providers.lorem.CountryProvider;
import com.mateuyabar.fritterfactory.providers.lorem.WordProvider;

import javax.inject.Provider;


public class AdressMold {
    Provider<String> street = new WordProvider(1,3);
    Provider<String> city = new CityProvider();
    Provider<String> country = new CountryProvider();
}
