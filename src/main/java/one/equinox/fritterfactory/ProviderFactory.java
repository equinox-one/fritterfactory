package one.equinox.fritterfactory;

import one.equinox.fritterfactory.providers.basic.CalendarProvider;
import one.equinox.fritterfactory.providers.basic.DateProvider;
import one.equinox.fritterfactory.providers.primitives.*;
import one.equinox.fritterfactory.providers.lorem.WordProvider;

import java.util.Calendar;
import java.util.Date;
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
            addProvider(Long.class, new LongProvider());
            addProvider(long.class, new LongProvider());
            addProvider(Double.class, new DoubleProvider());
            addProvider(double.class, new DoubleProvider());
            addProvider(Float.class, new FloatProvider());
            addProvider(float.class, new FloatProvider());
            addProvider(Boolean.class, new BooleanProvider());
            addProvider(boolean.class, new BooleanProvider());


            addProvider(Date.class, new DateProvider());
            addProvider(Calendar.class, new CalendarProvider());

        }
    }

}
