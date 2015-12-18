package com.mateuyabar.fritterfactory.test;


import com.mateuyabar.fritterfactory.FritterFactory;
import com.mateuyabar.fritterfactory.test.model.MyModelMold;
import com.mateuyabar.fritterfactory.test.model.MyModel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class FritterFactoryTest {
    FritterFactory fritterFactory = new FritterFactory();

    @Test
    public void buildModelFillsModel() throws Exception {
        MyModel model = fritterFactory.build(MyModel.class);
        assertNotNull(model.getStringVal());
        assertNotNull(model.getIntegerVal());
        assertNotEquals(model.getIntVal(),0);
    }

    @Test
    public void buildListModelFillsModel() throws Exception {
        List<MyModel> models = fritterFactory.buildList(MyModel.class, 10);
        assertEquals(models.size(), 10);
        for(MyModel model : models){
            assertNotNull(model.getStringVal());
            assertNotNull(model.getIntegerVal());
            assertNotEquals(model.getIntVal(), 0);
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
    }


}
