package com.mateuyabar.fritterfactory.test;


import com.mateuyabar.fritterfactory.FritterFactory;
import com.mateuyabar.fritterfactory.providers.ModelProvider;
import com.mateuyabar.fritterfactory.providers.primitives.StringProvider;
import com.mateuyabar.fritterfactory.test.model.MyModel;
import com.mateuyabar.fritterfactory.test.model.MyModelMold;
import com.mateuyabar.fritterfactory.test.model.MyModelMoldWithProviders;

import org.junit.Test;

import static com.mateuyabar.fritterfactory.test.model.SampleValues.SAMPLE_INTEGER;
import static com.mateuyabar.fritterfactory.test.model.SampleValues.SAMPLE_STRING;
import static org.junit.Assert.assertEquals;

public class FritterFactoryTestWithMold {
    FritterFactory fritterFactory = new FritterFactory();

    @Test
    public void buildModelWithMoldClass() throws Exception {
        MyModel model = fritterFactory.build(MyModel.class, MyModelMold.class);
        assertEquals(model.getStringVal(), SAMPLE_STRING);
        assertEquals(model.getIntVal(), SAMPLE_INTEGER);
        assertEquals(model.getIntegerVal(), (Integer) SAMPLE_INTEGER);
    }

    @Test
    public void buildModelWithMoldInstance() throws Exception {
        MyModelMold mold = new MyModelMold();
        mold.stringVal = new StringProvider().get();

        MyModel model = fritterFactory.build(MyModel.class, mold);
        assertEquals(model.getStringVal(), mold.stringVal);
        assertEquals(model.getIntVal(), SAMPLE_INTEGER);
        assertEquals(model.getIntegerVal(), (Integer) SAMPLE_INTEGER);
    }

    @Test
    public void buildModelWithMoldAsProvider() throws Exception {
        FritterFactory fritterFactory = new FritterFactory();
        MyModelMold mold = new MyModelMold();
        mold.stringVal = new StringProvider().get();
        fritterFactory.addProvider(MyModel.class, new ModelProvider<MyModel>(fritterFactory, MyModel.class, mold));

        MyModel model = fritterFactory.build(MyModel.class, mold);
        assertEquals(model.getStringVal(), mold.stringVal);
        assertEquals(model.getIntVal(), SAMPLE_INTEGER);
        assertEquals(model.getIntegerVal(), (Integer) SAMPLE_INTEGER);
    }

    @Test
    public void buildModelWithMoldClassWithProviders() throws Exception {
        MyModel model = fritterFactory.build(MyModel.class, MyModelMoldWithProviders.class);
        assertEquals(model.getStringVal(), SAMPLE_STRING);
        assertEquals(model.getIntVal(), SAMPLE_INTEGER);
        assertEquals(model.getIntegerVal(), (Integer) SAMPLE_INTEGER);
    }

}
