package com.mateuyabar.fritterfactory.mold;


import com.mateuyabar.fritterfactory.FritterFactory;

import java.lang.reflect.Field;

public interface Mold {
    Object getMoldValue(FritterFactory factory, Field filed)  throws Exception;
}
