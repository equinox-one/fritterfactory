package one.equinox.fritterfactory.test.model;

import one.equinox.symbols.Symbolize;

@Symbolize
public class MyModel{
    String stringVal;
    int intVal;
    Integer integerVal;
    public String getStringVal() {
        return stringVal;
    }
    public int getIntVal() {
        return intVal;
    }
    public Integer getIntegerVal() {
        return integerVal;
    }
}