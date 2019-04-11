package one.equinox.fritterfactory.test;


import one.equinox.fritterfactory.FritterFactory;
import one.equinox.fritterfactory.test.model.MyModel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class FritterFactoryTest {
    FritterFactory fritterFactory = new FritterFactory();

    @Test
    public void buildModelFillsModel() throws Exception {
        MyModel model = fritterFactory.build(MyModel.class);
        assertNotNull(model.getStringVal());
        assertNotNull(model.getIntegerVal());
        assertNotEquals(model.getIntVal(),0);
        assertNotNull(model.getFinalStringVal());

        assertNotNull(MyModel.sNoVal);

        // Make sure that non-null (or final) statics are not overwritten
        assertEquals(MyModel.sVal, "My String");
        assertEquals(MyModel.fVal, "My final String");
        assertNull(MyModel.fNullVal);

        // Assert ignored fields are still null
        assertNull(model.getIgnoredStringVal());
        assertNull(model.getFinalIgnoredString());
        assertNull(MyModel.sIgnoredString);
    }

    @Test
    public void buildListModelFillsModel() throws Exception {
        List<MyModel> models = fritterFactory.buildList(MyModel.class, 10);
        assertEquals(models.size(), 10);
        for(MyModel model : models){
            assertNotNull(model.getStringVal());
            assertNotNull(model.getIntegerVal());
            assertNotEquals(model.getIntVal(), 0);
            assertNotNull(model.getFinalStringVal());
        }
    }

    @Test
    public void buildModelShouldRandomizeValues() throws Exception {
        MyModel model = fritterFactory.build(MyModel.class);
        MyModel model2 = fritterFactory.build(MyModel.class);
        //may fail if unlucky
        assertNotEquals(model.getStringVal(), model2.getStringVal());
        assertNotEquals(model.getIntegerVal(), model2.getIntegerVal());
        assertNotEquals(model.getIntVal(), model2.getIntVal());
        assertNotEquals(model.getFinalStringVal(), model2.getFinalStringVal());
    }
}
