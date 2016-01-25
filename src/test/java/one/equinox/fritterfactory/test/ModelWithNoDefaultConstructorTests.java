package one.equinox.fritterfactory.test;


import one.equinox.fritterfactory.FritterFactory;
import one.equinox.fritterfactory.FritterFactoryException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ModelWithNoDefaultConstructorTests {
    @Rule public final ExpectedException exception = ExpectedException.none();

    FritterFactory factory = new FritterFactory();

    @Test
    public void privateConstructorModel(){
        List<PrivateConstructorModel> list = factory.buildList(PrivateConstructorModel.class);
        assertNotNull(list);
    }

    @Test
    public void noDefaultConstructor(){
        exception.expect(FritterFactoryException.class);
        List<NoDefaultConstructor> list = factory.buildList(NoDefaultConstructor.class);
    }

    public static class PrivateConstructorModel{
        private PrivateConstructorModel(){}
    }

    public static class NoDefaultConstructor{
        private NoDefaultConstructor(int someValue){}
    }
}
