package one.equinox.fritterfactory.test;


import one.equinox.fritterfactory.FritterFactory;
import one.equinox.fritterfactory.mold.ClassMold;
import one.equinox.fritterfactory.mold.InstanceMold;
import one.equinox.fritterfactory.providers.ModelProvider;
import one.equinox.fritterfactory.providers.primitives.StringProvider;
import one.equinox.fritterfactory.test.model.MyModel;
import one.equinox.fritterfactory.test.model.MyModelMold;
import one.equinox.fritterfactory.test.model.MyModelMoldWithProviders;

import org.junit.Test;

import static one.equinox.fritterfactory.test.model.SampleValues.SAMPLE_INTEGER;
import static one.equinox.fritterfactory.test.model.SampleValues.SAMPLE_STRING;
import static org.junit.Assert.assertEquals;

public class FritterFactoryTestWithMold {
    FritterFactory fritterFactory = new FritterFactory();

    @Test
    public void buildModelWithMoldClass() throws Exception {
        MyModel model = fritterFactory.build(MyModel.class, new ClassMold(MyModelMold.class));
        assertEquals(model.getStringVal(), SAMPLE_STRING);
        assertEquals(model.getIntVal(), SAMPLE_INTEGER);
        assertEquals(model.getIntegerVal(), (Integer) SAMPLE_INTEGER);
    }

    @Test
    public void buildModelWithMoldInstance() throws Exception {
        MyModelMold mold = new MyModelMold();
        mold.stringVal = new StringProvider().get();

        MyModel model = fritterFactory.build(MyModel.class, new InstanceMold(mold));
        assertEquals(model.getStringVal(), mold.stringVal);
        assertEquals(model.getIntVal(), SAMPLE_INTEGER);
        assertEquals(model.getIntegerVal(), (Integer) SAMPLE_INTEGER);
    }

    @Test
    public void buildModelWithMoldAsProvider() throws Exception {
        FritterFactory fritterFactory = new FritterFactory();
        MyModelMold mold = new MyModelMold();
        InstanceMold intanceMold = new InstanceMold(mold);

        mold.stringVal = new StringProvider().get();
        fritterFactory.addProvider(MyModel.class, new ModelProvider<MyModel>(fritterFactory, MyModel.class, intanceMold));

        MyModel model = fritterFactory.build(MyModel.class, intanceMold);
        assertEquals(model.getStringVal(), mold.stringVal);
        assertEquals(model.getIntVal(), SAMPLE_INTEGER);
        assertEquals(model.getIntegerVal(), (Integer) SAMPLE_INTEGER);
    }

    @Test
    public void buildModelWithMoldClassWithProviders() throws Exception {
        MyModel model = fritterFactory.build(MyModel.class, new ClassMold(MyModelMoldWithProviders.class));
        assertEquals(model.getStringVal(), SAMPLE_STRING);
        assertEquals(model.getIntVal(), SAMPLE_INTEGER);
        assertEquals(model.getIntegerVal(), (Integer) SAMPLE_INTEGER);
    }

}
