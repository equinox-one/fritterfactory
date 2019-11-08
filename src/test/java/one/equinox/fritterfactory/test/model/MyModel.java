package one.equinox.fritterfactory.test.model;

import one.equinox.fritterfactory.annotation.FritterIgnoreField;
import one.equinox.symbols.Symbolize;

@Symbolize
public class MyModel {
    @FritterIgnoreField
    String ignoredStringVal;

    @FritterIgnoreField
    final String finalIgnoredString;

    MyModel() {
        finalStringVal = null;
        finalIgnoredString = null;
    }

    final String finalStringVal;
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
    public String getIgnoredStringVal() { return ignoredStringVal; }
    public String getFinalIgnoredString() { return finalIgnoredString; }
    public String getFinalStringVal() { return finalStringVal; }

    public static String sNoVal;

    @FritterIgnoreField
    public static String sIgnoredString;

    public static String sVal = "My String";
    public static final String fVal = "My final String";

    public static final String fNullVal = null;
}