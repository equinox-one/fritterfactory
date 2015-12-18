package com.mateuyabar.fritterfactory;


import com.mateuyabar.fritterfactory.providers.ModelProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import static com.mateuyabar.fritterfactory.ReflectionUtil.getStoredField;
import static com.mateuyabar.fritterfactory.ReflectionUtil.getStoredFields;
import static com.mateuyabar.fritterfactory.ReflectionUtil.newInstance;

public class FritterFactory {
    int defaultListSize = 10;
    ProviderFactory providers;

    public FritterFactory(){
        this(new ProviderFactory.DefaultProviderFactory());
    }

    public FritterFactory(ProviderFactory providers){
        this.providers = providers;
    }

    public <T> void addProvider(Class<T> clazz, Provider<T> provider){
        providers.addProvider(clazz, provider);
    }

    public <T> T build(Class<T> modelClass) throws FritterFactoryException {
        return build(modelClass, (Object) null);
    }

    public <T> T build(Class<T> modelClass, Class<?> moldClass) throws FritterFactoryException  {
        return build(modelClass, newInstance(moldClass));
    }

    public <T> T build(Class<T> modelClass, Object mold) throws FritterFactoryException  {
        Provider<T> provider = providers.get(modelClass);
        if(mold==null && provider!=null)
            return provider.get();
        else
            return buildModel(modelClass, mold);
    }

    public <T> T buildModel(Class<T> modelClass, Object mold) throws FritterFactoryException  {
        try {
            T result = modelClass.newInstance();
            for (Field field : getStoredFields(modelClass)) {
                field.setAccessible(true);
                Object value = getMoldValue(mold, field);
                if (value == null)
                    value = generateValue(field);
                field.set(result, value);
            }
            return result;
        } catch (Exception e){
            throw new FritterFactoryException(e);
        }
    }

    public <T> List<T> buildList(Class<T> modelClass) throws FritterFactoryException {
        return buildList(modelClass, defaultListSize);
    }

    public <T> List<T> buildList(Class<T> modelClass, int size) throws FritterFactoryException {
        return buildList(modelClass, size, (Object) null);
    }

    public <T> List<T> buildList(Class<T> modelClass, int size, Class<?> moldClass) throws FritterFactoryException {
        return buildList(modelClass, size, newInstance(moldClass));
    }

    public <T> List<T> buildList(Class<T> modelClass, int size, Object mold) throws FritterFactoryException {
        List<T> result = new ArrayList<T>();
        for(int i=0; i<size; ++i){
            result.add(build(modelClass, mold));
        }
        return result;
    }



    /**
     * Given an object, it returns the value its value on a given field.
     * If the value is a provider it gets the value of the provider.
     * If not found, returns null
     *
     * @param moldInstance object to look for
     * @param field filed to look for
     * @return
     * @throws Exception
     */
    private Object getMoldValue(Object moldInstance, Field field) throws Exception{
        if(moldInstance==null)
            return null;
        Class<?> moldClass = moldInstance.getClass();
        Field moldField = getStoredField(moldClass, field.getName());
        if(moldField==null)
            return null;
        moldField.setAccessible(true);
        Object moldFieldValue = moldField.get(moldInstance);
        if(moldFieldValue instanceof Provider){
            //its a provider, we generate a new value
            moldFieldValue = ((Provider)moldFieldValue).get();
        } else if (moldFieldValue instanceof com.mateuyabar.fritterfactory.providers.fritterproviders.FritterProvider) {
            //its a fritter provider, we generate a new value
            moldFieldValue = ((com.mateuyabar.fritterfactory.providers.fritterproviders.FritterProvider) moldFieldValue).get(this);
        }
        return moldFieldValue;
    }


    private Object generateValue(Field field) {
        Class<?> type = field.getType();
        Provider<?> provider = providers.get(type);
        if(provider==null){
            //We hope it is a submodel
            provider = getProvider(type);
        }
        return provider.get();
    }

    public int getDefaultListSize() {
        return defaultListSize;
    }

    public void setDefaultListSize(int defaultListSize) {
        this.defaultListSize = defaultListSize;
    }

    private <T> Provider<T> getProvider(Class<T> clazz){
        return new ModelProvider<T>(this, clazz);
    }
}
