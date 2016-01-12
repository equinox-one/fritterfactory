package one.equinox.fritterfactory.test.model;

import one.equinox.symbols.Symbolize;

@Symbolize
public class MyModelWithExtends extends MyModel {
    String anotherAttribute;

    public String getAnotherAttribute() {
        return anotherAttribute;
    }
}
