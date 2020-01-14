package one.equinox.fritterfactory;


import one.equinox.fritterfactory.mold.Mold;
import one.equinox.fritterfactory.providers.ModelProvider;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

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
        return build(modelClass, null);
    }

    public <T> T build(Class<T> modelClass, Mold mold) throws FritterFactoryException  {
        Provider<T> provider = providers.get(modelClass);
        if(mold==null && provider!=null)
            return provider.get();
        else
            return buildModel(modelClass, mold);
    }

    public <T> T buildModel(Class<T> modelClass, Mold mold) throws FritterFactoryException  {
        T result;
        try {
            Constructor<T> constructor = modelClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            result = constructor.newInstance();
        } catch (Exception e){
            throw new FritterFactoryException(modelClass.getName()+" does not define default constructor required by Fritter Factory",e);
        }
        try{
            for (Field field : ReflectionUtil.getStoredFields(modelClass)) {
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
        return buildList(modelClass, size, null);
    }

    public <T> List<T> buildList(Class<T> modelClass, int size, Mold mold) throws FritterFactoryException {
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
    private Object getMoldValue(Mold moldInstance, Field field) throws Exception{
        if(moldInstance!=null)
            return moldInstance.getMoldValue(this, field);
        else return null;
    }

    private Object generateValue(Field field) {
        Class<?> type = field.getType();
        Provider<?> provider = providers.get(type);
        if(provider==null){
            if(type.isEnum()){
                return type.getEnumConstants()[0];
            }
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
