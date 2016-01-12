package one.equinox.fritterfactory.mold;

import one.equinox.fritterfactory.FritterFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class MapMold implements Mold{
    Map<String, Object> attributes = new HashMap<String, Object>();

    public MapMold(){}

    public MapMold(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public MapMold put(String string, Object value){
        attributes.put(string, value);
        return this;
    }

    @Override
    public Object getMoldValue(FritterFactory factory, Field field) throws Exception {
        Object moldFieldValue = attributes.get(field.getName());
        if(moldFieldValue==null)
            return null;
        return InstanceMold.getValueFromProperty(factory, moldFieldValue);
    }
}
