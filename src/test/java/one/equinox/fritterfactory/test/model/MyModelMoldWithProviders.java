package one.equinox.fritterfactory.test.model;

import javax.inject.Provider;

public class MyModelMoldWithProviders {
    public Provider<String> stringVal = new SampleValues.DummyStringProvider();
    public Provider<Integer> intVal = new SampleValues.DummyIntegerProvider();
    public Provider<Integer> integerVal = new SampleValues.DummyIntegerProvider();
}