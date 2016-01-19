package one.equinox.fritterfactory.providers.basic;

import one.equinox.fritterfactory.util.RandomFactory;

import javax.inject.Provider;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;


public class CalendarProvider implements Provider<Calendar> {
    Random random = new RandomFactory().get();

    @Override
    public Calendar get() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(random.nextLong()));
        return calendar;
    }
}
