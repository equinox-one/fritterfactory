package com.mateuyabar.fritterfactory.util;

import java.util.Date;
import java.util.Random;

import javax.inject.Provider;

/**
 * Created by mateuyabar on 15/12/15.
 */
public class RandomFactory implements Provider<Random>{
    private static Random random;

    public RandomFactory(){
        if(random==null) {
            random = new Random();
            random.setSeed(new Date().getTime());
        }
    }

    @Override
    public Random get() {
        return random;
    }
}
