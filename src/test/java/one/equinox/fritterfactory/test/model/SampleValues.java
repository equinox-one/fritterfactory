package one.equinox.fritterfactory.test.model;


import javax.inject.Provider;

public class SampleValues {
    public static final String SAMPLE_STRING = "qwertyuio";
    public static final int SAMPLE_INTEGER = 200;

    public static class DummyStringProvider implements Provider<String>{
        @Override
        public String get() {
            return SAMPLE_STRING;
        }
    }

    public static class DummyIntegerProvider implements Provider<Integer>{
        @Override
        public Integer get() {
            return SAMPLE_INTEGER;
        }
    }
}
