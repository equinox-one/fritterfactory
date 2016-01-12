package one.equinox.fritterfactory.test;


import one.equinox.fritterfactory.FritterFactory;
import one.equinox.fritterfactory.mold.MapMold;
import one.equinox.fritterfactory.mold.Mold;
import one.equinox.fritterfactory.providers.primitives.IntegerProvider;
import one.equinox.fritterfactory.providers.primitives.StringProvider;
import one.equinox.fritterfactory.test.model.MyModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import one.equinox.fritterfactory.test.model.symbols.MyModelSymbols;

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
        Mold mapMold = new MapMold()
                .put(MyModelSymbols.STRING_VAL, STRING_VAL)
                .put(MyModelSymbols.INT_VAL, INTVAL)
                .put(MyModelSymbols.INTEGER_VAL, INTEGER_VAL);
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
