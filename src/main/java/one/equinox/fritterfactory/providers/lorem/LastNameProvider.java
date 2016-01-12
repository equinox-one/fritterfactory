package one.equinox.fritterfactory.providers.lorem;

import com.thedeanda.lorem.LoremIpsum;

import javax.inject.Provider;


public class LastNameProvider implements Provider<String> {
    @Override
    public String get() {
        return LoremIpsum.getInstance().getLastName();
    }
}
