package com.mateuyabar.fritterfactory.mold;


import com.mateuyabar.fritterfactory.FritterFactory;
import com.mateuyabar.fritterfactory.providers.fritterproviders.FritterProvider;

import java.lang.reflect.Field;

import javax.inject.Provider;

import static com.mateuyabar.fritterfactory.ReflectionUtil.getStoredField;

public class InstanceMold implements Mold {
    Object moldInstance;

    public InstanceMold(Object moldInstance) {
        this.moldInstance = moldInstance;
    }

    /**
     * Given an object, it returns the value its value on a given field.
     * If the value is a provider it gets the value of the provider.
     * If not found, returns null
     *
     * @param factory fritter factory
     * @param field filed to look for
     * @return value of the field
     * @throws Exception
     */
    public Object getMoldValue(FritterFactory factory, Field field) throws Exception{
        if(moldInstance==null)
            return null;
        Class<?> moldClass = moldInstance.getClass();
        Field moldField = getStoredField(moldClass, field.getName());
        if(moldField==null)
            return null;
        moldField.setAccessible(true);
        Object moldFieldValue = moldField.get(moldInstance);

        return getValueFromProperty(factory, moldFieldValue);
    }


    protected static Object getValueFromProperty(FritterFactory factory, Object moldFieldValue){
        if(moldFieldValue instanceof Provider){
            //its a provider, we generate a new value
            moldFieldValue = ((Provider)moldFieldValue).get();
        } else if (moldFieldValue instanceof FritterProvider) {
            //its a fritter provider, we generate a new value
            moldFieldValue = ((FritterProvider) moldFieldValue).get(factory);
        }
        return moldFieldValue;
    }

}
