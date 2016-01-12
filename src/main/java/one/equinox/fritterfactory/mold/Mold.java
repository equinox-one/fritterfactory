package one.equinox.fritterfactory.mold;


import one.equinox.fritterfactory.FritterFactory;

import java.lang.reflect.Field;

public interface Mold {
    Object getMoldValue(FritterFactory factory, Field filed)  throws Exception;
}
