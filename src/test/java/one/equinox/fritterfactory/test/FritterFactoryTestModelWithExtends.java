package one.equinox.fritterfactory.test;


import one.equinox.fritterfactory.FritterFactory;
import one.equinox.fritterfactory.mold.MapMold;
import one.equinox.fritterfactory.mold.Mold;
import one.equinox.fritterfactory.test.model.MyModel;
import one.equinox.fritterfactory.test.model.MyModelWithExtends;
import org.junit.Test;
import one.equinox.fritterfactory.test.model.symbols.MyModelWithExtendsSymbols;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class FritterFactoryTestModelWithExtends {
    FritterFactory fritterFactory = new FritterFactory();

    @Test
    public void buildModel() throws Exception {
        MyModelWithExtends model = fritterFactory.build(MyModelWithExtends.class);
        assertNotNull(model.getStringVal());
        assertNotNull(model.getAnotherAttribute());
        assertNotNull(model.getIntegerVal());
        assertNotEquals(model.getIntVal(),0);

    }

    @Test
    public void buildModelWithMapMold() throws Exception {
        Mold mapMold = new MapMold()
                .put(MyModelWithExtendsSymbols.ANOTHER_ATTRIBUTE, FritterFactoryTestWithMapMold.STRING_VAL)
                .put(MyModelWithExtendsSymbols.STRING_VAL, FritterFactoryTestWithMapMold.STRING_VAL)
                .put(MyModelWithExtendsSymbols.INT_VAL, FritterFactoryTestWithMapMold.INTVAL)
                .put(MyModelWithExtendsSymbols.INTEGER_VAL, FritterFactoryTestWithMapMold.INTEGER_VAL);
        MyModelWithExtends model = fritterFactory.build(MyModelWithExtends.class, mapMold);
        assertEquals(model.getStringVal(), FritterFactoryTestWithMapMold.STRING_VAL);
        assertEquals(model.getAnotherAttribute(), FritterFactoryTestWithMapMold.STRING_VAL);
        assertEquals(model.getIntVal(), FritterFactoryTestWithMapMold.INTVAL);
        assertEquals(model.getIntegerVal(), (Integer) FritterFactoryTestWithMapMold.INTEGER_VAL);
    }
}
