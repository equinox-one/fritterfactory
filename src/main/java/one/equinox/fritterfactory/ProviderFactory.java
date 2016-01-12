package one.equinox.fritterfactory;

import one.equinox.fritterfactory.providers.primitives.BooleanProvider;
import one.equinox.fritterfactory.providers.primitives.DoubleProvider;
import one.equinox.fritterfactory.providers.primitives.IntegerProvider;
import one.equinox.fritterfactory.providers.lorem.WordProvider;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;


public class ProviderFactory {
    Map<Class<?>, Provider<?>> providers = new HashMap<Class<?>, Provider<?>>();

    public <T> Provider<T> get(Class<T> clazz){
        return (Provider<T>) providers.get(clazz);
    }

    public <T> void addProvider(Class<T> clazz, Provider<T> provider){
        providers.put(clazz, provider);
    }

    public static class DefaultProviderFactory extends ProviderFactory{
        public DefaultProviderFactory (){
            //addProvider(String.class, new StringProvider());
            addProvider(String.class, new WordProvider());
            addProvider(Integer.class, new IntegerProvider());
            addProvider(int.class, new IntegerProvider());
            addProvider(Double.class, new DoubleProvider());
            addProvider(double.class, new DoubleProvider());
            addProvider(Boolean.class, new BooleanProvider());
            addProvider(boolean.class, new BooleanProvider());
        }
    }

}
