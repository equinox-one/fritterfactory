package com.mateuyabar.fritterfactory.test;


import com.mateuyabar.fritterfactory.FritterFactory;
import com.mateuyabar.fritterfactory.mold.ClassMold;
import com.mateuyabar.fritterfactory.mold.InstanceMold;
import com.mateuyabar.fritterfactory.mold.MapMold;
import com.mateuyabar.fritterfactory.mold.Mold;
import com.mateuyabar.fritterfactory.providers.ModelProvider;
import com.mateuyabar.fritterfactory.providers.primitives.IntegerProvider;
import com.mateuyabar.fritterfactory.providers.primitives.StringProvider;
import com.mateuyabar.fritterfactory.test.model.MyModel;
import com.mateuyabar.fritterfactory.test.model.MyModelMold;
import com.mateuyabar.fritterfactory.test.model.MyModelMoldWithProviders;
import org.junit.Test;

import static com.mateuyabar.fritterfactory.test.model.SampleValues.SAMPLE_INTEGER;
import static com.mateuyabar.fritterfactory.test.model.SampleValues.SAMPLE_STRING;
import static org.junit.Assert.assertEquals;

public class FritterFactoryTestWithMapMold {
    FritterFactory fritterFactory = new FritterFactory();

    public static final String stringVal = "stringVal";
    public static final String intVal = "intVal";
    public static final String integerVal = "integerVal";

    private static final StringProvider stringProvider = new StringProvider();
    public static final String STRING_VAL = stringProvider.get();
    public static final int INTVAL = new IntegerProvider().get();
    public static final Integer INTEGER_VAL = new IntegerProvider().get();




    @Test
    public void buildModelWithMapMold() throws Exception {
        Mold mapMold = new MapMold().put(stringVal, STRING_VAL).put(intVal, INTVAL).put(integerVal, INTEGER_VAL);
        MyModel model = fritterFactory.build(MyModel.class, mapMold);
        assertEquals(model.getStringVal(), STRING_VAL);
        assertEquals(model.getIntVal(), INTVAL);
        assertEquals(model.getIntegerVal(), (Integer) INTEGER_VAL);
    }

    @Test
    public void buildModelWithMapMoldWithProviders() throws Exception {
        int staticInt = 5000;
        IntegerProvider intProvider = new IntegerProvider(staticInt, staticInt+1);
        Mold mapMold = new MapMold().put(intVal, intProvider);

        MyModel model = fritterFactory.build(MyModel.class, mapMold);
        assertEquals(model.getIntVal(), staticInt);
    }
}
